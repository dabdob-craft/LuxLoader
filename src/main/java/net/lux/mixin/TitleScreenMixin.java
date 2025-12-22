package net.lux.mixin;

import net.lux.core.ModManager;
import net.lux.core.ConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    
    @Inject(at = @At("TAIL"), method = "render")
    private void renderLuxInfo(GuiGraphics context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (ConfigManager.config != null && ConfigManager.config.showModCount) {
            String label = ConfigManager.config.loaderName + ": " + ModManager.getModCount() + " Mods Loaded";
            var font = Minecraft.getInstance().font;
            int y = ((TitleScreen)(Object)this).height - 10;
            context.drawString(font, label, 2, y, 0xFFFFFF, true);
        }
    }
}
