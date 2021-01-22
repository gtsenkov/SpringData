package com.example.jsonexdemo.models.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class FileIOUtilImpl implements FileIOUtil {

    @Override
    public String readFileContent(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath))
                .stream()
                .filter(x-> !x.isEmpty())
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public void write(String content, String filePath) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content), StandardCharsets.UTF_8);
    }
}
