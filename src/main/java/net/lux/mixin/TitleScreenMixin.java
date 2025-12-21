package net.lux.mixin;

import net.lux.core.ModManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    
    @Inject(at = @At("TAIL"), method = "render")
    private void renderLuxInfo(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        String label = "LuxLoader: " + ModManager.getModCount() + " Mods Loaded";
        
        var font = MinecraftClient.getInstance().textRenderer;
        
        int screenHeight = ((TitleScreen)(Object)this).height;
        
        context.drawTextWithShadow(font, label, 2, screenHeight - 10, 0xFFFFFF);
    }
}
