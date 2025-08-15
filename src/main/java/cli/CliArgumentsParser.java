package cli;

public class CliArgumentsParser {
    public CliArguments parse(String[] args) {
        CliArguments config = new CliArguments();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-a" -> config.setAppendMode(true);
                case "-p" -> config.setPrefix(args[++i]);
                case "-o" -> config.setCustomPath(args[++i]);
                case "-s" -> config.setStatsMode("short");
                case "-f" -> config.setStatsMode("full");
                default -> config.getFileNames().add(args[i]);
            }
        }
        return config;
    }
}
