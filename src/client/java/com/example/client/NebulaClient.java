package com.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.resources.Identifier;

public class VitalHUD implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        HudManager.init();
        ClickTracker.init();
        SpeedTracker.init();
        Keybinds.init();

        HudElementRegistry.addLast(
            Identifier.fromNamespaceAndPath("vitalhud", "hud"),
            (graphics, tickCounter) -> HudRenderer.render(graphics)
        );
    }
}
