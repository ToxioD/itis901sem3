package ru.itis.services;

import org.apache.commons.io.FileUtils;
import ru.itis.models.Photo;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FileBase64EncodeServiceImpl implements FileEncodeService<Photo> {

    private BASE64Encoder base64Encoder;

    public FileBase64EncodeServiceImpl(BASE64Encoder base64Encoder) {
        this.base64Encoder = base64Encoder;
    }

    @Override
    public List<String> encodeFiles(List<Photo> files) {
        List<String> encodedFiles = files.stream()
                .map(x -> new File(x.getPath()))
                .map(x -> {
                    try {
                        return FileUtils.readFileToByteArray(x);
                    } catch (IOException e) {
                        return new byte[0];
                    }
                })
                .map(x -> "data:image/png;base64," + base64Encoder.encode(x))
                .collect(Collectors.toList());
        return encodedFiles;
    }
}
