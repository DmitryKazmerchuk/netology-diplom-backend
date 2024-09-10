package ru.netology.netologydiplombackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String type;
    private Long size;
    @Lob
    private byte[] content;
    private String owner;

    public FileEntity(String filename, String type, Long size, byte[] content, String owner) {
        this.filename = filename;
        this.type = type;
        this.size = size;
        this.content = content;
        this.owner = owner;
    }
}