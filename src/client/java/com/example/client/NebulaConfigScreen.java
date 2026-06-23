package com.example.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.network.chat.Component;

public class NebulaConfigScreen extends Screen {

    private int selectedElement = 0;
    private int selectedAxis = 0;
    private boolean editing = false;
    private String inputValue = "";

    private static final String[] ELEMENT_NAMES = {"fps", "cps", "speed", "coords", "light"};
    private static final String[] DISPLAY_NAMES = {"FPS", "CPS", "Speed", "Coordinates", "Light Level"};

    public NebulaConfigScreen() {
        super(Component.literal("Nebula Client Config"));
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        graphics.fill(0, 0, this.width, this.height, 0x88000000);

        graphics.text(this.font, "Nebula Client - HUD Position Config",
                      10, 10, 0xFFFFFFFF, true);

        graphics.text(this.font, "UP/DOWN: Select | LEFT/RIGHT: Axis | ENTER: Edit | ESC: Save & Exit",
                      10, 30, 0xFFAAAAAA, false);

        int y = 60;
        HudElement currentElement = HudManager.get(ELEMENT_NAMES[selectedElement]);

        for (int i = 0; i < ELEMENT_NAMES.length; i++) {
            HudElement el = HudManager.get(ELEMENT_NAMES[i]);
            if (el == null) continue;

            boolean isSelected = (i == selectedElement);
            int color = isSelected ? 0xFFFFFF00 : 0xFFAAAAAA;

            graphics.text(this.font, DISPLAY_NAMES[i] + ": X=" + el.getX() + " Y=" + el.getY(),
                          20, y, color, false);
            y += 20;
        }

        y += 20;
        if (editing && currentElement != null) {
            graphics.text(this.font, "Editing: " + DISPLAY_NAMES[selectedElement] + " " + (selectedAxis == 0 ? "X" : "Y"),
                          20, y, 0xFF00FF00, true);
            y += 20;
            graphics.text(this.font, "Type number and press ENTER:",
                          20, y, 0xFFFFFFFF, false);
            y += 20;
            graphics.text(this.font, "Current: " + (selectedAxis == 0 ? currentElement.getX() : currentElement.getY()),
                          20, y, 0xFFFFFFFF, false);
            y += 20;
            graphics.text(this.font, "Input: " + inputValue + "_",
                          20, y, 0xFF00FF00, true);
        } else {
            graphics.text(this.font, "Selected: " + DISPLAY_NAMES[selectedElement],
                          20, y, 0xFF00FFFF, true);
            y += 20;
            graphics.text(this.font, "Axis: " + (selectedAxis == 0 ? "X" : "Y"),
                          20, y, 0xFF00FFFF, true);
            y += 20;
            graphics.text(this.font, "Press ENTER to edit",
                          20, y, 0xFFFFFFFF, false);
        }
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        int keyCode = event.getKey();
        
        if (editing) {
            if (keyCode == 257 || keyCode == 335) {
                if (!inputValue.isEmpty()) {
                    try {
                        int value = Integer.parseInt(inputValue);
                        HudElement el = HudManager.get(ELEMENT_NAMES[selectedElement]);
                        if (el != null) {
                            if (selectedAxis == 0) {
                                el.setPosition(value, el.getY());
                            } else {
                                el.setPosition(el.getX(), value);
                            }
                        }
                    } catch (NumberFormatException ignored) {
                    }
                }
                editing = false;
                inputValue = "";
                selectedAxis = (selectedAxis + 1) % 2;
                return true;
            } else if (keyCode == 259) {
                if (!inputValue.isEmpty()) {
                    inputValue = inputValue.substring(0, inputValue.length() - 1);
                }
                return true;
            }
        } else {
            if (keyCode == 265) {
                selectedElement = (selectedElement - 1 + ELEMENT_NAMES.length) % ELEMENT_NAMES.length;
                selectedAxis = 0;
                return true;
            } else if (keyCode == 264) {
                selectedElement = (selectedElement + 1) % ELEMENT_NAMES.length;
                selectedAxis = 0;
                return true;
            } else if (keyCode == 263) {
                selectedAxis = 0;
                return true;
            } else if (keyCode == 262) {
                selectedAxis = 1;
                return true;
            } else if (keyCode == 257 || keyCode == 335) {
                editing = true;
                inputValue = "";
                return true;
            }
        }

        if (keyCode == 256) {
            HudManager.saveConfig();
            this.onClose();
            return true;
        }

        return super.keyPressed(event);
    }

    @Override
    public boolean charTyped(CharacterEvent event) {
        char codePoint = event.getCodePoint();
        
        if (editing && Character.isDigit(codePoint)) {
            inputValue += codePoint;
            return true;
        }
        if (editing && codePoint == '-' && inputValue.isEmpty()) {
            inputValue = "-";
            return true;
        }
        return super.charTyped(event);
    }

    @Override
    public void onClose() {
        HudManager.saveConfig();
        super.onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
