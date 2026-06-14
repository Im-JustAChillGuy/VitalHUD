package com.example.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class HudRenderer {

    public static void render(GuiGraphicsExtractor graphics) {

        Minecraft client = Minecraft.getInstance();

        if (client.player == null) return;

        String fps    = "FPS: " + client.getFps();
        String cps    = "CPS: " + ClickTracker.getCPS();
        String speed  = "Speed: " + String.format("%.2f", SpeedTracker.getSpeed());
        String coords = CoordinatesTracker.getCoordinates();
        String light  = "Light: " + LightLevelTracker.getLightLevel();

        draw(graphics, "fps",    fps);
        draw(graphics, "cps",    cps);
        draw(graphics, "speed",  speed);
        draw(graphics, "coords", coords);
        draw(graphics, "light",  light);
    }

    private static void draw(GuiGraphicsExtractor graphics, String elementName, String text) {
        HudElement el = HudManager.get(elementName);
        if (el == null) return;
        graphics.text(Minecraft.getInstance().font, text, el.getX(), el.getY(), 0xFFFFFFFF, true);
    }
}
