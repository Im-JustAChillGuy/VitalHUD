package com.example.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Iterator;

public class ClickTracker {

    private static final ArrayList<Long> clicks = new ArrayList<>();
    private static boolean wasMouseDown = false;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            long windowHandle = Minecraft.getInstance().getWindow().getWindow();

            boolean isMouseDown = InputConstants.isKeyDown(windowHandle, GLFW.GLFW_MOUSE_BUTTON_1);

            // Register click only on the transition from up to down
            if (isMouseDown && !wasMouseDown) {
                clicks.add(System.currentTimeMillis());
            }
            wasMouseDown = isMouseDown;

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
