package processing;

import io.FileWriterService;
import stats.StatisticsCollector;

import java.io.IOException;

public class IntegerHandler implements DataTypeHandler {
    public boolean matches(String value) {
        try {
            Long.parseLong(value);
            return !value.contains(".");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void process(String value, StatisticsCollector stats, FileWriterService writer, boolean append) throws IOException {
        stats.addInteger(Long.parseLong(value));
        writer.write("integers.txt", value + "\n", append);
    }
}
