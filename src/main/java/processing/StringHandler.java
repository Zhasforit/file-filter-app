package processing;

import io.FileWriterService;
import stats.StatisticsCollector;

import java.io.IOException;

public class StringHandler implements DataTypeHandler {
    public boolean matches(String value) {
        return true;
    }

    public void process(String value, StatisticsCollector stats, FileWriterService writer, boolean append) throws IOException {
        stats.addString(value);
        writer.write("strings.txt", value + "\n", append);
    }
}
