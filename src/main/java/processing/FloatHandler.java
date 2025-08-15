package processing;

import io.FileWriterService;
import stats.StatisticsCollector;

import java.io.IOException;

public class FloatHandler implements DataTypeHandler {
    public boolean matches(String value) {
        try {
            Double.parseDouble(value);
            return value.contains(".");
        }
        catch (NumberFormatException e) { return false; }
    }
    public void process(String value, StatisticsCollector stats, FileWriterService writer, boolean append) throws IOException {
        stats.addFloat(Double.parseDouble(value));
        writer.write("floats.txt", value + "\n", append);
    }
}
