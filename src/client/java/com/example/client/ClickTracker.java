package com.example.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.Iterator;

public class ClickTracker {

    private static final ArrayList<Long> clicks = new ArrayList<>();

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.mouse.wasLeftButtonClicked()) {
                clicks.add(System.currentTimeMillis());
            }

            long now = System.currentTimeMillis();
            Iterator<Long> it = clicks.iterator();

            while (it.hasNext()) {
                if (now - it.next() > 1000) {
                    it.remove();
                }
            }
        });
    }

    public static int getCPS() {
        return clicks.size();
    }
}
