package net.lux.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ModListScreen extends Screen {
    private final Screen parent;
    private ModListWidget modList;

    public ModListScreen(Screen parent) {
        super(Component.literal("LuxLoader - Mods"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.modList = new ModListWidget(this.minecraft, this.width, this.height, 40, this.height - 40);
        this.addSelectableChild(this.modList);

        this.addRenderableWidget(Button.builder(Component.literal("Done"), button -> {
            this.minecraft.setScreen(this.parent);
        }).bounds(this.width / 2 - 100, this.height - 30, 200, 20).build());
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        this.modList.render(context, mouseX, mouseY, delta);
        context.drawCenteredString(this.font, this.title, this.width / 2, 15, 0xFFFFFF);
        
        super.render(context, mouseX, mouseY, delta);
    }
}
