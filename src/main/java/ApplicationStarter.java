import util.FileFilterService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationStarter {
    public static void main(String[] args) {

        boolean appendMode = false;
        List<String> fileNames = new ArrayList<>();

        for (String arg : args) {

            if (arg.equals("-a")) {
                appendMode = true;
            }

            else {
                fileNames.add(arg);
            }

        }

        try {

            FileFilterService service = new FileFilterService();
            service.processFiles(fileNames, appendMode);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

}
