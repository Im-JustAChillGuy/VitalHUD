package com.example.client;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HudEditorScreen extends Screen {

    public HudEditorScreen() {
        super(Component.literal("VitalHUD Editor"));
    }

    @Override
    public void render() {

        super.render();

        fill(100, 100, 160, 112, 0x80000000);

        drawString(
            minecraft.font,
            "FPS",
            102,
            102,
            0xFFFFFF
        );
    }
}
