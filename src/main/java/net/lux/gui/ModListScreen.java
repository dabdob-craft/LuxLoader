package net.lux.gui;

import net.lux.core.ModMetadata;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import java.io.File;

public class ModListScreen extends Screen {
    private final Screen parent;
    private ModListWidget modList;
    private ModMetadata selectedMod;
    private EditBox searchBox;

    public ModListScreen(Screen parent) {
        super(Component.literal("LuxLoader - Mods"));
        this.parent = parent;
    }

    public void setSelectedMod(ModMetadata mod) {
        this.selectedMod = mod;
    }

    @Override
    protected void init() {
        this.searchBox = new EditBox(this.font, 12, 35, this.width / 2 - 24, 20, Component.literal("Search"));
        this.searchBox.setResponder(text -> this.modList.filter(text));
        this.addRenderableWidget(this.searchBox);

        this.modList = new ModListWidget(this, this.minecraft, this.width / 2 - 20, this.height, 60, this.height - 40);
        this.modList.setLeftPos(10);
        this.addRenderableWidget(this.modList);

        this.addRenderableWidget(Button.builder(Component.literal("Open Folder"), button -> {
            File modsFolder = new File(this.minecraft.gameDirectory, "mods");
            if (!modsFolder.exists()) modsFolder.mkdirs();
            Util.getPlatform().openFile(modsFolder);
        }).bounds(10, this.height - 30, 95, 20).build());


        this.addRenderableWidget(Button.builder(Component.literal("Done"), button -> {
            this.minecraft.setScreen(this.parent);
        }).bounds(this.width / 2 - 100, this.height - 30, 200, 20).build());
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        this.modList.render(context, mouseX, mouseY, delta);
        this.searchBox.render(context, mouseX, mouseY, delta);
        
        context.drawCenteredString(this.font, this.title, this.width / 2, 10, 0xFFFFFF);

        if (selectedMod != null) {
            int x = this.width / 2 + 10;
            context.blit(selectedMod.geticon(), x, 45, 0, 0, 48, 48, 48, 48);
            context.drawCenteredString(this.font, "--- Details ---", x + (this.width / 4), 45, 0xFFAA00);
            context.drawString(this.font, selectedMod.getName(), x, 65, 0xFFFFFF, true);
            context.drawString(this.font, "Version: " + selectedMod.getVersion(), x, 80, 0xAAAAAA, false);
            context.drawString(this.font, "Author: " + selectedMod.getAuthor(), x, 95, 0xAAAAAA, false);
            context.drawWordWrap(this.font, Component.literal(selectedMod.getDescription()), x, 115, this.width / 2 - 20, 0xDDDDDD);
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void tick() {
        this.searchBox.tick();
    }
}
