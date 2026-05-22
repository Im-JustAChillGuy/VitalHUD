package com.example.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public class SpeedTracker {

    private static Vec3 lastPos = null;
    private static double speed = 0.0;

    public static void init() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (client.player == null) return;

            Vec3 pos = client.player.position();

            if (lastPos != null) {

                double dx = pos.x - lastPos.x;
                double dz = pos.z - lastPos.z;

                speed = Math.sqrt(dx * dx + dz * dz) * 20.0;
            }

            lastPos = pos;
        });
    }

    public static double getSpeed() {
        return speed;
    }
}
