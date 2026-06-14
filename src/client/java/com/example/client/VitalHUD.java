package com.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudElementRegistry;
import net.minecraft.client.DeltaTracker;
import net.minecraft.resources.ResourceLocation;

public class VitalHUD implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        HudManager.init();
        ClickTracker.init();
        SpeedTracker.init();
        Keybinds.init();

        HudElementRegistry.attachElementAfterAll(
            ResourceLocation.fromNamespaceAndPath("vitalhud", "hud"),
            (graphics, tickCounter) -> HudRenderer.render(graphics)
        );
    }
}
