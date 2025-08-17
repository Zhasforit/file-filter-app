package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleFileReader implements FileReaderService {
    public List<String> read(String filePath) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            return reader.lines()
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.toList());
        } catch (NoSuchFileException e) {
            System.err.println("Файл не найден: " + filePath);
            return List.of();
        } catch (AccessDeniedException e) {
            System.err.println("Нет прав для чтения файла: " + filePath);
            return List.of();
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла " + filePath + ": " + e.getMessage());
            return List.of();
        }
    }
}
