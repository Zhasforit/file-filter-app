package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileFilterService {

    Path integers = Path.of("integers.txt");
    Path floats = Path.of("floats.txt");
    Path strings = Path.of("strings.txt");

    public void filterFile(Path input) throws IOException {
        try(
                BufferedReader reader = Files.newBufferedReader(input);
                BufferedWriter intWriter = Files.newBufferedWriter(integers);
                BufferedWriter floatWriter = Files.newBufferedWriter(floats);
                BufferedWriter stringWriter = Files.newBufferedWriter(strings)
        ) {
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