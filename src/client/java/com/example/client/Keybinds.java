package com.example.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

import net.minecraft.client.KeyMapping.Category;

import org.lwjgl.glfw.GLFW;

public class Keybinds {

    private static final KeyMapping OPEN_EDITOR =
        KeyBindingHelper.registerKeyBinding(
            new KeyMapping(
                "key.vitalhud.editor",
                GLFW.GLFW_KEY_K,
                Category.MISC
            )
        );

    public static void init() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (OPEN_EDITOR.consumeClick()) {

                Minecraft.getInstance().setScreen(
                    new HudEditorScreen()
                );
            }
        });
    }
}
