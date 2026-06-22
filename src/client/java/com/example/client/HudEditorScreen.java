package com.example.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import org.lwjgl.glfw.GLFW;

public class HudEditorScreen extends Screen {

    private HudElement dragging = null;
    private int dragOffsetX = 0;
    private int dragOffsetY = 0;
    private boolean wasMouseDown = false;

    public HudEditorScreen() {
        super(Component.literal("VitalHUD Editor"));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        // Dark semi-transparent background
        graphics.fill(0, 0, this.width, this.height, 0x88000000);

        // Draw title
        graphics.text(this.font, "VitalHUD Editor - Drag elements to move",
                      10, 10, 0xFFFFFFFF, true);

        // Draw each draggable HUD element
        for (String name : new String[]{"fps", "cps", "speed", "coords", "light"}) {
            HudElement el = HudManager.get(name);
            if (el == null) continue;

            String label = "[" + el.getName().toUpperCase() + "]";
            int w = this.font.width(label);
            int h = this.font.lineHeight;

            // Highlight box around element
            graphics.fill(el.getX() - 2, el.getY() - 2,
                          el.getX() + w + 2, el.getY() + h + 2,
                          0xAA005599);
            // Draw label
            graphics.text(this.font, label, el.getX(), el.getY(), 0xFFFFFFFF, true);
        }

        // Instructions at bottom
        graphics.text(this.font, "Press [ESC] to close and save",
                      5, this.height - 12, 0xFFAAAAAA, false);

        // Poll mouse state to handle dragging
        Window window = Minecraft.getInstance().getWindow();
        boolean isMouseDown = InputConstants.isKeyDown(window, GLFW.GLFW_MOUSE_BUTTON_1);

        if (isMouseDown && !wasMouseDown) {
            // Mouse button just pressed - start drag if over an element
            for (String name : new String[]{"fps", "cps", "speed", "coords", "light"}) {
                HudElement el = HudManager.get(name);
                if (el == null) continue;

                String label = "[" + el.getName().toUpperCase() + "]";
                int w = this.font.width(label);
                int h = this.font.lineHeight;

                if (mouseX >= el.getX() - 2 && mouseX <= el.getX() + w + 2
                 && mouseY >= el.getY() - 2 && mouseY <= el.getY() + h + 2) {
                    dragging = el;
                    dragOffsetX = mouseX - el.getX();
                    dragOffsetY = mouseY - el.getY();
                    break;
                }
            }
        } else if (isMouseDown && dragging != null) {
            // Continue dragging
            dragging.setPosition(mouseX - dragOffsetX, mouseY - dragOffsetY);
        } else if (!isMouseDown) {
            // Mouse released
            dragging = null;
        }

        wasMouseDown = isMouseDown;
    }

    @Override
    public void onClose() {
        // Save config when screen closes
        HudManager.saveConfig();
        super.onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
