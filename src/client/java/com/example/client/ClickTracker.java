package com.example.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Iterator;

public class ClickTracker {

    private static final ArrayList<Long> clicks = new ArrayList<>();

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            long window = Minecraft.getInstance().getWindow().getWindow();

            if (InputConstants.isKeyDown(window, GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
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
