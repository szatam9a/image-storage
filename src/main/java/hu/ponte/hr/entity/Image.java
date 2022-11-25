package hu.ponte.hr.entity;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "images")
public class Image {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "filename")
    private String fileName;
    @Column(name = "filetype")
    private String fileType;
    @Column(name = "size")
    private Long size;
    @Lob
    private byte[] data;
}
