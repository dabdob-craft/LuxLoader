package net.lux.core;

public class ModMetadata {
    public String id;
    public String name;
    public String version;

    public ModMetadata(String id, String name, String version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
