package net.lux.mixin;

import net.lux.core.ModManager;
import net.lux.core.ConfigManager;
import net.lux.gui.ModListScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Component title) { super(title); }

    @Inject(at = @At("TAIL"), method = "init")
    private void onInit(CallbackInfo ci) {
        this.addRenderableWidget(Button.builder(Component.literal("Lux Mods"), button -> {
            this.minecraft.setScreen(new ModListScreen(this));
        }).bounds(this.width / 2 - 100, this.height / 4 + 48 + 24 * 3, 200, 20).build());
    }
    
    @Inject(at = @At("TAIL"), method = "render")
    private void renderLuxInfo(GuiGraphics context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (ConfigManager.config != null && ConfigManager.config.showModCount) {
            String label = ConfigManager.config.loaderName + ": " + ModManager.getModCount() + " Mods Loaded";
            var font = Minecraft.getInstance().font;
            int y = this.height - 10;
            context.drawString(font, label, 2, y, 0xFFFFFF, true);
        }
    }
}
