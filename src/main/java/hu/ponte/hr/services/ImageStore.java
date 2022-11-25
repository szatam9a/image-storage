package hu.ponte.hr.services;

import hu.ponte.hr.controller.ImageMeta;
import hu.ponte.hr.entity.Image;
import hu.ponte.hr.expcetion.FileSizeTooBigException;
import hu.ponte.hr.repository.ImageRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class ImageStore {
    @Autowired
    private ImageRepository imageRepository;

    @Value("${image.max-storage}")
    private int MAX_STORAGE_PER_IMAGE_IN_MEGABYTE; // in megabyte

    public Image saveImageToDB(MultipartFile file) throws IOException, FileSizeTooBigException {

        if (file.getSize() > 1024 * 1024 * MAX_STORAGE_PER_IMAGE_IN_MEGABYTE) {
            throw new FileSizeTooBigException(file.getSize(), MAX_STORAGE_PER_IMAGE_IN_MEGABYTE);
        }
        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setFileType(file.getContentType());
        image.setData(file.getBytes());
        image.setSize(file.getSize());
        return imageRepository.save(image);
    }

    public List<ImageMeta> getAllImagesMetaData() {
        return imageRepository.findAll().stream().map(image -> ImageMeta.builder()
                .id(image.getId().toString())
                .name(image.getFileName())
                .size(image.getSize())
                .mimeType(image.getFileType())
                .digitalSign("").build()).collect(Collectors.toList());
    }

    public Image findById(String id) {
        return imageRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NoSuchElementException());
    }
}
