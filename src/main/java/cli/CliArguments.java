package cli;

import java.util.ArrayList;
import java.util.List;

public class CliArguments {
    private boolean appendMode;
    private String prefix = "";
    private String customPath = "";
    private String statsMode = "none";
    private final List<String> fileNames = new ArrayList<>();

    public boolean isAppendMode() { return appendMode; }
    public void setAppendMode(boolean appendMode) { this.appendMode = appendMode; }

    public String getPrefix() { return prefix; }
    public void setPrefix(String prefix) { this.prefix = prefix; }

    public String getCustomPath() { return customPath; }
    public void setCustomPath(String customPath) { this.customPath = customPath; }

    public String getStatsMode() { return statsMode; }
    public void setStatsMode(String statsMode) { this.statsMode = statsMode; }

    public List<String> getFileNames() { return fileNames; }
}
