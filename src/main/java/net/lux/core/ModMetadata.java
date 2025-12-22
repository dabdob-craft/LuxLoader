package net.lux.core;

import net.minecraft.resources.ResourceLocation;

public class ModMetadata {
    public String id;
    public String name;
    public String version;
    public String description;
    public String author;
    public ResourceLocation icon;

    public ModMetadata(String id, String name, String version, String description, String author) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.description = description;
        this.author = author;
        this.icon = new ResourceLocation("lux", "textures/gui/icon.png");
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getVersion() { return version; }
    public String getDescription() { return description; }
    public String getAuthor() { return author; }
    public ResourceLocation getIcon() { return icon; }
}
