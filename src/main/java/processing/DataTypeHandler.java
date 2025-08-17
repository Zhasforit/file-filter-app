package processing;

import io.FileWriterService;
import stats.StatisticsCollector;

import java.io.IOException;

public interface DataTypeHandler {
    boolean matches(String value);
    void process(String value, StatisticsCollector stats, FileWriterService writer, boolean append) throws IOException;
}
