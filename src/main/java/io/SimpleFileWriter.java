package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class SimpleFileWriter implements FileWriterService {
    private final Path baseDir;
    private final String prefix;

    private final Map<String, BufferedWriter> writers = new HashMap<>();
    private final Map<String, Integer> lineCounters = new HashMap<>();

    private static final int FLUSH_THRESHOLD = 10_000; // flush каждые 10k строк

    public SimpleFileWriter(String prefix, String customPath) throws IOException {
        this.prefix = prefix == null ? "" : prefix;
        this.baseDir = customPath.isEmpty() ? Paths.get("") : Paths.get("." + customPath);

        if (!this.baseDir.toString().isEmpty()) {
            Files.createDirectories(this.baseDir);
        }
    }

    @Override
    public void write(String fileName, String content, boolean append) {
        BufferedWriter bw = writers.computeIfAbsent(fileName, fn -> {
            try {
                Path file = baseDir.resolve(prefix + fn);
                return Files.newBufferedWriter(file,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE,
                        append ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING);
            } catch (AccessDeniedException e) {
                System.err.println("Нет прав для записи файла: " + fn);
                return null;
            } catch (IOException e) {
                System.err.println("Ошибка при открытии файла " + fn + ": " + e.getMessage());
                return null;
            }
        });

        if (bw == null) return;

        try {
            bw.write(content);

            int count = lineCounters.getOrDefault(fileName, 0) + 1;
            lineCounters.put(fileName, count);

            if (count >= FLUSH_THRESHOLD) {
                bw.flush();
                lineCounters.put(fileName, 0);
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл " + fileName + ": " + e.getMessage());
        }
    }

    public void flushAll(boolean append) throws IOException {
        for (BufferedWriter bw : writers.values()) {
            if (bw != null) {
                try {
                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии файла: " + e.getMessage());
                }
            }
        }
        writers.clear();
        lineCounters.clear();
    }
}