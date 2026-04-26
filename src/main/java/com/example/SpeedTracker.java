package com.imjustachillguy.vitalhud.stats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.util.math.Vec3d;

public class SpeedTracker {

    private static Vec3d lastPos = null;
    private static double speed = 0;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            Vec3d pos = client.player.getPos();

            if (lastPos != null) {
                double dx = pos.x - lastPos.x;
                double dz = pos.z - lastPos.z;
                double horizontal = Math.sqrt(dx * dx + dz * dz);
                speed = horizontal * 20;
            }

            lastPos = pos;
        });
    }

    public static double getSpeed() {
        return speed;
    }
}
