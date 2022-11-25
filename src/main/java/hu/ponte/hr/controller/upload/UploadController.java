package hu.ponte.hr.controller.upload;

import hu.ponte.hr.expcetion.FileSizeTooBigException;
import hu.ponte.hr.services.ImageStore;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequestMapping("api/file")
@AllArgsConstructor
public class UploadController
{
    private ImageStore imageStore;

    @RequestMapping(value = "post", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String handleFormUpload(@RequestParam("file") MultipartFile file) throws IOException, FileSizeTooBigException {
        return imageStore.saveImageToDB(file).getId().toString();
    }
}
