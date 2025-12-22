package net.lux.gui;

import net.lux.core.ModManager;
import net.lux.core.ModMetadata;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.network.chat.Component;
import java.util.Locale;

public class ModListWidget extends ObjectSelectionList<ModListWidget.ModEntry> {
    private final ModListScreen parent;

    public ModListWidget(ModListScreen parent, Minecraft client, int width, int height, int top, int bottom) {
        super(client, width, height, top, bottom, 36);
        this.parent = parent;
        filter("");
    }

    public void filter(String searchTerm) {
        this.clearEntries();
        String search = searchTerm.toLowerCase(Locale.ROOT);
        for (ModMetadata mod : ModManager.getLoadedMods()) {
            if (mod.getName().toLowerCase(Locale.ROOT).contains(search) || mod.getId().toLowerCase(Locale.ROOT).contains(search)) {
                this.addEntry(new ModEntry(mod));
            }
        }
    }

    @Override
    public void setSelected(ModEntry entry) {
        super.setSelected(entry);
        if (entry != null) {
            parent.setSelectedMod(entry.metadata);
        }
    }

    public class ModEntry extends ObjectSelectionList.Entry<ModEntry> {
        protected final ModMetadata metadata;

        public ModEntry(ModMetadata metadata) {
            this.metadata = metadata;
        }

        @Override
        public void render(GuiGraphics context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            context.drawString(Minecraft.getInstance().font, metadata.getName(), x + 5, y + 5, 0xFFFFFF, true);
            context.drawString(Minecraft.getInstance().font, "v" + metadata.getVersion(), x + 5, y + 17, 0xAAAAAA, false);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            ModListWidget.this.setSelected(this);
            return true;
        }

        @Override
        public Component getNarration() {
            return Component.literal(metadata.getName());
        }
    }
}
