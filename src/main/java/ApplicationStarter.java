import util.FileFilterService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationStarter {
    public static void main(String[] args) {

        boolean appendMode = false;
        String prefix = "";
        List<String> fileNames = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {

            switch (args[i]) {

                case "-a":
                    appendMode = true;
                    break;

                case "-p":
                    prefix = args[++i];
                    break;

                default:
                    fileNames.add(args[i]);

            }

        }

        try {

            FileFilterService service = new FileFilterService(prefix);
            service.processFiles(fileNames, appendMode);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

}
