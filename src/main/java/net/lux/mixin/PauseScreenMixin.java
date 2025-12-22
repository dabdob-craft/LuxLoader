package net.lux.mixin;

import net.lux.gui.ModListScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PauseScreen.class)
public abstract class PauseScreenMixin extends Screen {
    protected PauseScreenMixin(Component title) { super(title); }

    @Inject(at = @At("TAIL"), method = "init")
    private void onInit(CallbackInfo ci) {
        this.addRenderableWidget(Button.builder(Component.literal("Lux Mods"), button -> {
            this.minecraft.setScreen(new ModListScreen(this));
        }).bounds(this.width / 2 - 100, this.height / 4 + 120, 200, 20).build());
    }
}
