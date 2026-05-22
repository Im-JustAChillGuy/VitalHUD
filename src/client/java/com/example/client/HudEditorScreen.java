package com.example.client;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HudEditorScreen extends Screen {

    public HudEditorScreen() {
        super(Component.literal("VitalHUD Editor"));
    }

    @Override
    public void render(net.minecraft.client.gui.GuiDrawContext graphics, int mouseX, int mouseY, float delta) {

        super.render(graphics, mouseX, mouseY, delta);

        int x = 100;
        int y = 100;

        graphics.fill(x, y, x + 60, y + 12, 0x80000000);

        graphics.drawString(
            minecraft.font,
            "FPS",
            x + 2,
            y + 2,
            0xFFFFFF,
            true
        );
    }
}
