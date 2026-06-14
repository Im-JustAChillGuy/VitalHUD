package com.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class VitalHUD implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        HudManager.init();
        HudRenderer.init();
        ClickTracker.init();
        SpeedTracker.init();
        Keybinds.init();

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            HudRenderer.render(drawContext);
        });
    }
}
