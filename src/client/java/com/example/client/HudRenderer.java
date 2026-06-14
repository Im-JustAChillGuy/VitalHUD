package com.example.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class HudRenderer {

    public static void init() {}

    public static void render(GuiGraphics context) {

        Minecraft client = Minecraft.getInstance();

        if (client.player == null) return;

        String fps    = "FPS: " + client.getFps();
        String cps    = "CPS: " + ClickTracker.getCPS();
        String speed  = "Speed: " + String.format("%.2f", SpeedTracker.getSpeed());
        String coords = CoordinatesTracker.getCoordinates();
        String light  = "Light: " + LightLevelTracker.getLightLevel();

        draw(context, "fps",    fps);
        draw(context, "cps",    cps);
        draw(context, "speed",  speed);
        draw(context, "coords", coords);
        draw(context, "light",  light);
    }

    private static void draw(GuiGraphics context, String elementName, String text) {
        HudElement el = HudManager.get(elementName);
        if (el == null) return;
        context.drawString(Minecraft.getInstance().font, text, el.getX(), el.getY(), 0xFFFFFF);
    }
}
