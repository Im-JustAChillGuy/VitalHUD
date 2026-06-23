package com.example.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;

public class LightLevelTracker {

    public static int getLightLevel() {
        Minecraft client = Minecraft.getInstance();

        if (client.player == null || client.level == null) return 0;

        Level level = client.level;

        return level.getMaxLightLevel();
    }
}
