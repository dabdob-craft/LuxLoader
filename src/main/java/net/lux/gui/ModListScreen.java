package net.lux.gui;

import net.lux.core.ModManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ModListScreen extends Screen {
    private final Screen parent;

    public ModListScreen(Screen parent) {
        super(Component.literal("LuxLoader - Mods"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.addRenderableWidget(Button.builder(Component.literal("Done"), button -> {
            this.minecraft.setScreen(this.parent);
        }).bounds(this.width / 2 - 100, this.height - 30, 200, 20).build());
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        
        context.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        
        int yOffset = 50;
        context.drawCenteredString(this.font, "Total Mods: " + ModManager.getModCount(), this.width / 2, yOffset, 0xAAAAAA);
        
        super.render(context, mouseX, mouseY, delta);
    }
}
