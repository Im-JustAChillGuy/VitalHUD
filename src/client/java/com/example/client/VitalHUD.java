package com.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.GuiGraphics;

public class VitalHUD implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        HudManager.init();
        ClickTracker.init();
        SpeedTracker.init();
        Keybinds.init();

        HudRenderCallback.EVENT.register((graphics, tickCounter) -> {
            HudRenderer.render(graphics);
        });
    }
}
