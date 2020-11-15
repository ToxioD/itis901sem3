package ru.itis.services;

import org.apache.commons.io.FilenameUtils;
import ru.itis.models.Photo;
import ru.itis.repositories.PhotoCrudRepositoryImpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PhotoService implements FileService<Photo> {

    private PhotoCrudRepositoryImpl photoRepository;

    public PhotoService(PhotoCrudRepositoryImpl photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public void upload(InputStream stream, String filename) throws IOException {
        String extension = FilenameUtils.getExtension(filename);
        String path = System.getenv("PHOTO_UPLOAD_PATH") + System.currentTimeMillis() + "." + extension;
        Files.copy(stream, Paths.get(path));
        photoRepository.save(Photo.builder().path(path).build());
    }

    @Override
    public List<Photo> getAllFiles() {
        return photoRepository.findAll();
    }
}
