package net.lux.gui;

import net.lux.core.ModMetadata;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ModListScreen extends Screen {
    private final Screen parent;
    private ModListWidget modList;
    private ModMetadata selectedMod;

    public ModListScreen(Screen parent) {
        super(Component.literal("LuxLoader - Mods"));
        this.parent = parent;
    }

    public void setSelectedMod(ModMetadata mod) {
        this.selectedMod = mod;
    }

    @Override
    protected void init() {
        this.modList = new ModListWidget(this, this.minecraft, this.width / 2 - 20, this.height, 40, this.height - 40);
        this.modList.setLeftPos(10);
        this.addRenderableWidget(this.modList);

        this.addRenderableWidget(Button.builder(Component.literal("Done"), button -> {
            this.minecraft.setScreen(this.parent);
        }).bounds(this.width / 2 - 100, this.height - 30, 200, 20).build());
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        this.modList.render(context, mouseX, mouseY, delta);
        context.drawCenteredString(this.font, this.title, this.width / 2, 15, 0xFFFFFF);

        if (selectedMod != null) {
            int x = this.width / 2 + 10;
            context.drawString(this.font, selectedMod.getName(), x, 50, 0xFFFFFF, true);
            context.drawString(this.font, "Version: " + selectedMod.getVersion(), x, 65, 0xAAAAAA, false);
            context.drawString(this.font, "Author: " + selectedMod.getAuthor(), x, 80, 0xAAAAAA, false);
            context.drawWordWrap(this.font, Component.literal(selectedMod.getDescription()), x, 100, this.width / 2 - 20, 0xDDDDDD);
        }

        super.render(context, mouseX, mouseY, delta);
    }
}
