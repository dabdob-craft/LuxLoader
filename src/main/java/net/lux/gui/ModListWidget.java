package net.lux.gui;

import net.lux.core.ModManager;
import net.lux.core.ModMetadata;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.network.chat.Component;

public class ModListWidget extends ObjectSelectionList<ModListWidget.ModEntry> {

    public ModListWidget(Minecraft client, int width, int height, int top, int bottom) {
        super(client, width, height, top, bottom, 36);
        
        for (ModMetadata mod : ModManager.getLoadedMods()) {
            this.addEntry(new ModEntry(mod));
        }
    }

    public class ModEntry extends ObjectSelectionList.Entry<ModEntry> {
        private final ModMetadata metadata;

        public ModEntry(ModMetadata metadata) {
            this.metadata = metadata;
        }

        @Override
        public void render(GuiGraphics context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            context.drawString(Minecraft.getInstance().font, metadata.getName(), x + 5, y + 5, 0xFFFFFF, true);
            context.drawString(Minecraft.getInstance().font, "v" + metadata.getVersion(), x + 5, y + 17, 0xAAAAAA, false);
        }

        @Override
        public Component getNarration() {
            return Component.literal(metadata.getName());
        }
    }
}
