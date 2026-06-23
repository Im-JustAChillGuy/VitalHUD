package com.example.client;

import net.minecraft.client.Minecraft;

public class CoordinatesTracker {

    public static String getCoordinates() {
        Minecraft client = Minecraft.getInstance();

        if (client.player == null) return "Coords: N/A";

        double x = client.player.getX();
        double y = client.player.getY();
        double z = client.player.getZ();

        return String.format("Coords: %.1f, %.1f, %.1f", x, y, z);
    }
}
