package com.imjustachillguy.vitalhud.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import com.imjustachillguy.vitalhud.stats.ClickTracker;
import com.imjustachillguy.vitalhud.stats.SpeedTracker;

public class HudRenderer {

    public static void init() {
        HudRenderCallback.EVENT.register(HudRenderer::render);
    }

    private static void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        int x = 10;
        int y = 10;

        context.drawText(client.textRenderer, "FPS: " + client.getCurrentFps(), x, y, 0xFFFFFF, true);
        context.drawText(client.textRenderer, "CPS: " + ClickTracker.getCPS(), x, y + 12, 0xFFFFFF, true);
        context.drawText(client.textRenderer, "Speed: " + String.format("%.2f", SpeedTracker.getSpeed()), x, y + 24, 0xFFFFFF, true);
    }
}
