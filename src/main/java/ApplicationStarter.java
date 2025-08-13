import util.FileFilterService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ApplicationStarter {
    public static void main(String[] args) throws IOException {

//        Scanner sc = new Scanner(System.in);
//        String inputFileName = sc.nextLine();

        String inputFileName = args[0];
        Path filePath = Path.of(inputFileName);

        FileFilterService fileFilter = new FileFilterService();
        fileFilter.filterFile(filePath);
    }
}
