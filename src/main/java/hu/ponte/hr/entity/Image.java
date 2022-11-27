package hu.ponte.hr.entity;

import lombok.Data;
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
    @Column(name = "digital_sign", length = 400 )
    private String digitalSign;
    @Lob
    private byte[] data;
}
