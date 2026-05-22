package com.example.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class HudRenderer {

    public static void init() {

        HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> {
            layeredDrawer.attachLayerAfter(
                net.minecraft.client.gui.LayeredDrawLayers.CROSSHAIR,
                "vitalhud",
                HudRenderer::render
            );
        });
    }

    private static void render(GuiGraphics context, float tickDelta) {

        Minecraft client = Minecraft.getInstance();

        if (client.player == null) return;

        int x = 10;
        int y = 10;

        context.drawString(
            client.font,
            "FPS: " + client.getFps(),
            x,
            y,
            0xFFFFFF
        );

        context.drawString(
            client.font,
            "CPS: " + ClickTracker.getCPS(),
            x,
            y + 12,
            0xFFFFFF
        );

        context.drawString(
            client.font,
            "Speed: " + String.format("%.2f", SpeedTracker.getSpeed()),
            x,
            y + 24,
            0xFFFFFF
        );
    }
}
