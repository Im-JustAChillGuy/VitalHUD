package com.example.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HudEditorScreen extends Screen {

    private HudElement dragging = null;
    private int dragOffsetX = 0;
    private int dragOffsetY = 0;
    private double lastMouseX = 0;
    private double lastMouseY = 0;

    public HudEditorScreen() {
        super(Component.literal("VitalHUD Editor"));
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        lastMouseX = mouseX;
        lastMouseY = mouseY;

        // Dark transparent background
        graphics.fill(0, 0, this.width, this.height, 0x88000000);

        // Draw each element as a draggable label
        for (String name : new String[]{"fps", "cps", "speed", "coords", "light"}) {
            HudElement el = HudManager.get(name);
            if (el == null) continue;

            String label = "[" + el.getName().toUpperCase() + "]";
            int w = this.font.width(label);
            int h = this.font.lineHeight;

            graphics.fill(el.getX() - 2, el.getY() - 2,
                          el.getX() + w + 2, el.getY() + h + 2,
                          0xAA005599);
            graphics.text(this.font, label, el.getX(), el.getY(), 0xFFFFFFFF, true);
        }

        graphics.text(this.font, "Drag elements to reposition. Press Escape to close.",
                      5, this.height - 12, 0xFFAAAAAA, false);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (String name : new String[]{"fps", "cps", "speed", "coords", "light"}) {
            HudElement el = HudManager.get(name);
            if (el == null) continue;

            String label = "[" + el.getName().toUpperCase() + "]";
            int w = this.font.width(label);
            int h = this.font.lineHeight;

            if (mouseX >= el.getX() - 2 && mouseX <= el.getX() + w + 2
             && mouseY >= el.getY() - 2 && mouseY <= el.getY() + h + 2) {
                dragging = el;
                dragOffsetX = (int) mouseX - el.getX();
                dragOffsetY = (int) mouseY - el.getY();
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (dragging != null) {
            dragging.setPosition((int) mouseX - dragOffsetX, (int) mouseY - dragOffsetY);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        dragging = null;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
