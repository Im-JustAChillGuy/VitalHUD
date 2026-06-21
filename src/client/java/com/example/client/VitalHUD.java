package com.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudElementRegistry;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class VitalHUD implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        HudManager.init();
        ClickTracker.init();
        SpeedTracker.init();
        Keybinds.init();

        HudElementRegistry.attachElementAfterAll(
            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("vitalhud", "hud"),
            (GuiGraphicsExtractor graphics, net.minecraft.client.DeltaTracker tickCounter) -> {
                HudRenderer.render(graphics);
            }
        );
    }
}
