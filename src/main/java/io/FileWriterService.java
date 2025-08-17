package io;

import java.io.IOException;

public interface FileWriterService {
    void write(String fileName, String content, boolean append) throws IOException;
}
