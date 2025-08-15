import util.FileFilterService;
import util.StatisticService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationStarter {
    public static void main(String[] args) {

        boolean appendMode = false;
        String prefix = "";
        String customPath = "";
        String statsMode = "none";
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

                case "-s":
                    statsMode = "short";
                    break;

                case "-f":
                    statsMode = "full";
                    break;

                default:
                    fileNames.add(args[i]);

            }

        }

        try {

            FileFilterService service = new FileFilterService(prefix, customPath);
            StatisticService stats = service.processFiles(fileNames, appendMode, statsMode);

            if (!"none".equals(statsMode)) {

                stats.printStats("full".equals(statsMode));

            }

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

}
