package com.example.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import org.lwjgl.glfw.GLFW;

public class Keybinds {

    private static boolean wasPressed = false;

    public static void init() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            Window window = Minecraft.getInstance().getWindow();

            boolean pressed = InputConstants.isKeyDown(window, GLFW.GLFW_KEY_K);

            if (pressed && !wasPressed) {
                Minecraft.getInstance().setScreen(new HudEditorScreen());
            }

            wasPressed = pressed;
        });
    }
}
