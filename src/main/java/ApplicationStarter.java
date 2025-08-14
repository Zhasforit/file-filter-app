import util.FileFilterService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationStarter {
    public static void main(String[] args) {

        boolean appendMode = false;
        String prefix = "";
        String customPath = "";
        List<String> fileNames = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {

            switch (args[i]) {

                case "-a":
                    appendMode = true;
                    break;

                case "-p":
                    prefix = args[++i];
                    break;

                case "-o":
                    customPath = args[++i];
                    break;

                default:
                    fileNames.add(args[i]);

            }

        }

        try {

            FileFilterService service = new FileFilterService(prefix, customPath);
            service.processFiles(fileNames, appendMode);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

}
