package processing;

import io.FileWriterService;
import stats.StatisticsCollector;

import java.io.IOException;

public class IntegerHandler implements DataTypeHandler {
    public boolean matches(String value) {
        try { Integer.parseInt(value); return true; }
        catch (NumberFormatException e) { return false; }
    }
    public void process(String value, StatisticsCollector stats, FileWriterService writer, boolean append) throws IOException {
        stats.addInteger(Integer.parseInt(value));
        writer.write("integers.txt", value + "\n", append);
    }
}
