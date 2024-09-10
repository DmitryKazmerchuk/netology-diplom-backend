package ru.netology.netologydiplombackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.netologydiplombackend.dto.FileDto;
import ru.netology.netologydiplombackend.entity.FileEntity;
import ru.netology.netologydiplombackend.repository.FileRepository;
import ru.netology.netologydiplombackend.security.JwtToken;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FileService {
    private final FileRepository fileRepository;
    private final JwtToken jwtToken;

    public FileService(FileRepository fileRepository, JwtToken jwtToken) {
        this.fileRepository = fileRepository;
        this.jwtToken = jwtToken;
    }

    public List<FileDto> getFiles(String authToken, int limit) {
        String owner = jwtToken.getUsernameFromToken(authToken.substring(7));
        Optional<List<FileEntity>> fileList = fileRepository.findAllByOwner(owner);
        return fileList.get().stream().map(fr -> new FileDto(fr.getFilename(), fr.getSize()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public void uploadFile(String authToken, String filename, MultipartFile file) throws IOException {
        String owner = jwtToken.getUsernameFromToken(authToken.substring(7));
        fileRepository.save(new FileEntity(filename, file.getContentType(), file.getSize(), file.getBytes(), owner));
    }

    public void deleteFile(String authToken, String filename) {
        String owner = jwtToken.getUsernameFromToken(authToken.substring(7));
        fileRepository.removeByFilenameAndOwner(filename, owner);
    }

    public FileEntity downloadFile(String authToken, String filename) {
        String owner = jwtToken.getUsernameFromToken(authToken.substring(7));
        return fileRepository.findByFilenameAndOwner(filename, owner);
    }

    public void renameFile(String authToken, String filename, String newFilename) {
        String owner = jwtToken.getUsernameFromToken(authToken.substring(7));
        fileRepository.renameFile(filename, newFilename, owner);
    }
}