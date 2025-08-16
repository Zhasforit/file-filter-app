import cli.CliArguments;
import cli.CliArgumentsParser;
import io.FileReaderService;
import io.SimpleFileReader;
import io.SimpleFileWriter;
import processing.DataProcessor;
import processing.DataTypeHandler;
import processing.FloatHandler;
import processing.IntegerHandler;
import processing.StringHandler;
import stats.StatisticsCollector;

import java.io.IOException;
import java.util.List;

public class ApplicationStarter {
    public static void main(String[] args) {
        CliArgumentsParser parser = new CliArgumentsParser();
        CliArguments config = parser.parse(args);

        try {
            SimpleFileWriter writer = new SimpleFileWriter(config.getPrefix(), config.getCustomPath());
            FileReaderService reader = new SimpleFileReader();
            StatisticsCollector stats = new StatisticsCollector();

            List<DataTypeHandler> handlers = List.of(
                    new IntegerHandler(),
                    new FloatHandler(),
                    new StringHandler()
            );

            DataProcessor processor = new DataProcessor(handlers, stats, writer, config.isAppendMode());

            for (String file : config.getFileNames()) {
                try {
                    List<String> lines = reader.read(file);
                    for (String line : lines) {
                        try {
                            processor.processLine(line);
                        } catch (IOException e) {
                            System.err.println("Ошибка обработки строки \"" + line + "\": " + e.getMessage());
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при чтении файла \"" + file + "\": " + e.getMessage());
                }
            }

            try {
                writer.flushAll(config.isAppendMode());
            } catch (IOException e) {
                System.err.println("Ошибка при сохранении файлов: " + e.getMessage());
            }

            if (!"none".equals(config.getStatsMode())) {
                stats.printStats("full".equals(config.getStatsMode()));
            }

        } catch (IOException e) {
            System.err.println("Ошибка инициализации: " + e.getMessage());
        }
    }
}
