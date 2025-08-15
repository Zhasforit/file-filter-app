package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleFileReader implements FileReaderService{
    public List<String> read(String filePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            return reader.lines()
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.toList());
        }
    }
}
