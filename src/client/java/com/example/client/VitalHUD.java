package com.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class VitalHUD implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        HudRenderer.init();
        ClickTracker.init();
        SpeedTracker.init();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            HudRenderer.render();
            HudManager.init();
        });
    }
}
