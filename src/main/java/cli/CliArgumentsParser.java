package cli;

import java.util.ArrayList;
import java.util.List;

public class CliArgumentsParser {

    public CliArguments parse(String[] args) {
        CliArguments config = new CliArguments();
        List<String> files = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "-o":
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        config.setCustomPath(args[++i]);
                    } else {
                        throw new IllegalArgumentException("Флаг -o требует указания пути");
                    }
                    break;
                case "-p":
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        config.setPrefix(args[++i]);
                    } else {
                        throw new IllegalArgumentException("Флаг -p требует указания префикса");
                    }
                    break;
                case "-a":
                    config.setAppendMode(true);
                    break;
                case "-s":
                    if (config.getStatsMode().equals("full")) {
                        throw new IllegalArgumentException("Флаги -s и -f нельзя использовать одновременно");
                    }
                    config.setStatsMode("short");
                    break;
                case "-f":
                    if (config.getStatsMode().equals("short")) {
                        throw new IllegalArgumentException("Флаги -s и -f нельзя использовать одновременно");
                    }
                    config.setStatsMode("full");
                    break;
                case "--help":
                case "-h":
                    printHelp();
                    System.exit(0);
                    break;
                default:
                    if (arg.startsWith("-")) {
                        throw new IllegalArgumentException("Неизвестный параметр: " + arg);
                    }
                    files.add(arg);
            }
        }

        if (files.isEmpty()) {
            throw new IllegalArgumentException("Не указаны входные файлы");
        }

        config.setFileNames(files);
        return config;
    }

    private void printHelp() {
        System.out.println("Использование: java -jar target/file-filtering-utility-1.0.0.jar [OPTIONS] file1 file2 ...");
        System.out.println();
        System.out.println("Опции:");
        System.out.println("  -o <path>       Указать директорию для сохранения выходных файлов");
        System.out.println("  -p <prefix>     Указать префикс для выходных файлов");
        System.out.println("  -a              Режим дозаписи (append), сохраняет промежуточные результаты");
        System.out.println("  -s              Вывести короткую статистику");
        System.out.println("  -f              Вывести полную статистику");
        System.out.println("  -h, --help      Показать эту справку и выйти");
        System.out.println();
    }
}
