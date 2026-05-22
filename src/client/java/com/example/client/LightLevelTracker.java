package com.example.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public class LightLevelTracker {

    public static int getLightLevel() {

        Minecraft client = Minecraft.getInstance();

        if (client.player == null || client.level == null) {
            return 0;
        }

        BlockPos pos = client.player.blockPosition();

        return client.level.getMaxLocalRawBrightness(pos);
    }
}
