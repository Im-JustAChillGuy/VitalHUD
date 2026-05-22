package com.example.client;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import net.minecraft.client.Minecraft;

public class VitalHUD implements ClientModInitializer {

    private boolean openedEditor = false;

    @Override
    public void onInitializeClient() {

        HudManager.init();

        HudRenderer.init();
        ClickTracker.init();
        SpeedTracker.init();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            HudRenderer.render();

            if (!openedEditor) {

                Minecraft.getInstance().setScreen(new HudEditorScreen());

                openedEditor = true;
            }
        });
    }
}
