package com.example.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class LightLevelTracker {

    public static int getLightLevel() {
        Minecraft client = Minecraft.getInstance();

        if (client.player == null || client.level == null) return 0;

        Level level = client.level;
        int x = (int) client.player.getX();
        int y = (int) client.player.getY();
        int z = (int) client.player.getZ();

        int lightLevel = level.getLightEmission(Blocks.LIGHT.defaultBlockState());
        return lightLevel;
    }
}
