package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class SimpleFileWriter implements FileWriterService {
    private final Path baseDir;
    private final String prefix;
    private final Map<String, StringBuilder> buffers = new HashMap<>();

    public SimpleFileWriter(String prefix, String customPath) throws IOException {
        this.prefix = prefix == null ? "" : prefix;
        this.baseDir = customPath.isEmpty() ? Paths.get("") : Paths.get("." + customPath);
        if (!this.baseDir.toString().isEmpty()) {
            Files.createDirectories(this.baseDir);
        }
    }

    public void write(String fileName, String content, boolean append) throws IOException {
        if (append) {
            Path file = baseDir.resolve(prefix + fileName);
            Files.writeString(file, content,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } else {
            buffers.computeIfAbsent(fileName, k -> new StringBuilder())
                    .append(content);
        }
    }

    public void flushAll(boolean append) throws IOException {
        if (!append) {
            for (Map.Entry<String, StringBuilder> entry : buffers.entrySet()) {
                Path file = baseDir.resolve(prefix + entry.getKey());
                Files.writeString(file, entry.getValue().toString(),
                        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        }
    }
}
