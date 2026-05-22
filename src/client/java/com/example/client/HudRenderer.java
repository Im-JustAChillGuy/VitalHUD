package com.example.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;

public class HudRenderer {

    public static void init() {

    }

    public static void render() {

        Minecraft client = Minecraft.getInstance();

        if (client.player == null) return;

        Font font = client.font;

        int x = 10;
        int y = 10;

        String fps = "FPS: " + client.getFps();
        String cps = "CPS: " + ClickTracker.getCPS();
        String speed = "Speed: " + String.format("%.2f", SpeedTracker.getSpeed());

        // Temporary debug output
        System.out.println(fps);
        System.out.println(cps);
        System.out.println(speed);
    }
}
