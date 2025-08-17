package processing;

import io.FileWriterService;
import stats.StatisticsCollector;

import java.io.IOException;
import java.util.List;

public class DataProcessor {
    private final List<DataTypeHandler> handlers;
    private final StatisticsCollector stats;
    private final FileWriterService writer;
    private final boolean append;

    public DataProcessor(List<DataTypeHandler> handlers, StatisticsCollector stats, FileWriterService writer, boolean append) {
        this.handlers = handlers;
        this.stats = stats;
        this.writer = writer;
        this.append = append;
    }

    public void processLine(String line) throws IOException {
        for (DataTypeHandler h : handlers) {
            if (h.matches(line)) {
                h.process(line, stats, writer, append);
                break;
            }
        }
    }
}
