package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileFilterService {

    public void processFiles(List<String> fileNames, boolean appendMode) throws IOException {

        StringBuilder intData = new StringBuilder();
        StringBuilder floatData = new StringBuilder();
        StringBuilder stringData = new StringBuilder();

        for (String fileName : fileNames) {
            processSingleFile(fileName, intData, floatData, stringData);
        }

        writeResults(intData, floatData, stringData, appendMode);

    }

    public void processSingleFile(String filename,
                                  StringBuilder intData,
                                  StringBuilder floatData,
                                  StringBuilder stringData) throws IOException {

       try (BufferedReader reader = Files.newBufferedReader(Path.of(filename))) {

           String line;
           while ((line = reader.readLine()) != null) {

               if (line.isEmpty()) {
                   continue;
               }

               if (TypeCheckerService.isInteger(line)) {
                   intData.append(line).append("\n");
               }

               else if (TypeCheckerService.isDouble(line)) {
                   floatData.append(line).append("\n");
               }

               else {
                   stringData.append(line).append("\n");
               }

           }

       }

    }

    public void writeResults(
            StringBuilder intData,
            StringBuilder floatData,
            StringBuilder stringData,
            boolean appendMode
    ) throws IOException {

        StandardOpenOption[] options = appendMode ?
                new StandardOpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.APPEND} :
                new StandardOpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};

        if (!intData.isEmpty()) {
            Files.writeString(Path.of("integers.txt"), intData.toString(), options);
        }

        if (!floatData.isEmpty()) {
            Files.writeString(Path.of("floats.txt"), floatData.toString(), options);
        }

        if (!stringData.isEmpty()) {
            Files.writeString(Path.of("strings.txt"), stringData.toString(), options);
        }

    }

}