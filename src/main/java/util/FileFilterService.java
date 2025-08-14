package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileFilterService {

    public void processFiles(List<String> fileNames, boolean appendMode) throws IOException {

        StandardOpenOption[] options = appendMode ?
                new StandardOpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.APPEND} :
                new StandardOpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};

        try(
                BufferedWriter intWriter = Files.newBufferedWriter(Path.of("integers.txt"), options);
                BufferedWriter floatWriter = Files.newBufferedWriter(Path.of("floats.txt"), options);
                BufferedWriter stringWriter = Files.newBufferedWriter(Path.of("strings.txt"), options)
        ) {

            for (String fileName : fileNames) {
                processSingleFile(fileName, intWriter, floatWriter, stringWriter);
            }

        }

    }

    public void processSingleFile(String filename,
                                  BufferedWriter intWriter,
                                  BufferedWriter floatWriter,
                                  BufferedWriter stringWriter) throws IOException {

       try (BufferedReader reader = Files.newBufferedReader(Path.of(filename))) {

           String line;
           while ((line = reader.readLine()) != null) {

               if (line.isEmpty()) {
                   continue;
               }

               if (TypeCheckerService.isInteger(line)) {
                   intWriter.write(line);
                   intWriter.newLine();
               }

               else if (TypeCheckerService.isDouble(line)) {
                   floatWriter.write(line);
                   floatWriter.newLine();
               }

               else {
                   stringWriter.write(line);
                   stringWriter.newLine();
               }

           }

       }

    }

}