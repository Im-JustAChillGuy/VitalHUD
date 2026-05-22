package com.example.client;

import net.minecraft.client.Minecraft;

public class CoordinatesTracker {

    public static String getCoordinates() {

        Minecraft client = Minecraft.getInstance();

        if (client.player == null) {
            return "XYZ: ?";
        }

        int x = (int) client.player.getX();
        int y = (int) client.player.getY();
        int z = (int) client.player.getZ();

        return "XYZ: " + x + " " + y + " " + z;
    }
}
