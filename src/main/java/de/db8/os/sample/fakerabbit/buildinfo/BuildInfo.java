package de.db8.os.sample.fakerabbit.buildinfo;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

public final class BuildInfo {

    private final Map<String, String> info;

    public static final String UNDEFINED = "undefined";

    public BuildInfo() {
        this.info = new HashMap<>();
    }

    public BuildInfo(Properties info) {
        this.info = new Hashtable<>();
        info.forEach((key, value) -> this.info.put(key.toString(), value.toString()));
    }

    public String getBranch() {
        return info.getOrDefault("branch", UNDEFINED);
    }

    public String getCommitId() {
        return info.getOrDefault("commit.id", UNDEFINED);
    }

    public String getVersion() {
        return info.getOrDefault("version", UNDEFINED);
    }

    public String getBuildUser() {
        return info.getOrDefault("build.user", UNDEFINED);
    }

    public String getBuildHostname() {
        return info.getOrDefault("build.hostname", UNDEFINED);
    }

    public String getBuildDate() {
        return info.getOrDefault("build.date", UNDEFINED);
    }

    public String getBuildOsName() {
        return info.getOrDefault("build.os.name", UNDEFINED);
    }

    public String getBuildOsVersion() {
        return info.getOrDefault("build.os.version", UNDEFINED);
    }

    public String getBuildJavaVendor() {
        return info.getOrDefault("build.java.vendor", UNDEFINED);
    }

    public String getBuildJavaVersion() {
        return info.getOrDefault("build.java.version", UNDEFINED);
    }
}
