package ru.itis.services;

import java.util.List;

public interface FileEncodeService<T> {
    List<String> encodeFiles(List<T> files);
}
