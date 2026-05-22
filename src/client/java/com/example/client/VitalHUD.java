package com.example.client;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import net.minecraft.client.Minecraft;

public class VitalHUD implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        HudManager.init();

        HudRenderer.init();
        ClickTracker.init();
        SpeedTracker.init();

        Minecraft.getInstance().setScreen(new HudEditorScreen());

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            HudRenderer.render();
        });
    }
}
