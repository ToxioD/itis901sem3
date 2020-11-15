package ru.itis.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileService<T> {
    void upload(InputStream stream, String filename) throws IOException;
    List<T> getAllFiles();
}
