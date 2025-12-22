package net.lux.core;

public class ModMetadata {
    public String id;
    public String name;
    public String version;
    public String description;
    public String author;

    public ModMetadata(String id, String name, String version, String description, String author) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.description = description;
        this.author = author;
    }

    public String getName() { return name; }
    public String getVersion() { return version; }
    public String getDescription() { return description; }
    public String getAuthor() { return author; }
}
