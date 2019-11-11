package de.db8.os.sample.fakerabbit.log;

public class LogEntry {
    private final String level;
    private final String message;

    public LogEntry(String level, String message) {
        this.level = level;
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }
}
