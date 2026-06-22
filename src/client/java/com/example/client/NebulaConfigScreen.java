package com.example.nebula;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

public class NebulaConfigScreen {

    public static Screen new(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(Component.literal("Nebula Client"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory category = builder.getOrCreateCategory(Component.literal("HUD Positions"));

        // FPS Position
        category.addEntry(entryBuilder
            .startIntField(Component.literal("FPS X"), HudManager.get("fps").getX())
            .setDefaultValue(10)
            .setSaveConsumer(x -> HudManager.get("fps").setPosition(x, HudManager.get("fps").getY()))
            .build());

        category.addEntry(entryBuilder
            .startIntField(Component.literal("FPS Y"), HudManager.get("fps").getY())
            .setDefaultValue(10)
            .setSaveConsumer(y -> HudManager.get("fps").setPosition(HudManager.get("fps").getX(), y))
            .build());

        // CPS Position
        category.addEntry(entryBuilder
            .startIntField(Component.literal("CPS X"), HudManager.get("cps").getX())
            .setDefaultValue(10)
            .setSaveConsumer(x -> HudManager.get("cps").setPosition(x, HudManager.get("cps").getY()))
            .build());

        category.addEntry(entryBuilder
            .startIntField(Component.literal("CPS Y"), HudManager.get("cps").getY())
            .setDefaultValue(25)
            .setSaveConsumer(y -> HudManager.get("cps").setPosition(HudManager.get("cps").getX(), y))
            .build());

        // Speed Position
        category.addEntry(entryBuilder
            .startIntField(Component.literal("Speed X"), HudManager.get("speed").getX())
            .setDefaultValue(10)
            .setSaveConsumer(x -> HudManager.get("speed").setPosition(x, HudManager.get("speed").getY()))
            .build());

        category.addEntry(entryBuilder
            .startIntField(Component.literal("Speed Y"), HudManager.get("speed").getY())
            .setDefaultValue(40)
            .setSaveConsumer(y -> HudManager.get("speed").setPosition(HudManager.get("speed").getX(), y))
            .build());

        // Coordinates Position
        category.addEntry(entryBuilder
            .startIntField(Component.literal("Coordinates X"), HudManager.get("coords").getX())
            .setDefaultValue(10)
            .setSaveConsumer(x -> HudManager.get("coords").setPosition(x, HudManager.get("coords").getY()))
            .build());

        category.addEntry(entryBuilder
            .startIntField(Component.literal("Coordinates Y"), HudManager.get("coords").getY())
            .setDefaultValue(55)
            .setSaveConsumer(y -> HudManager.get("coords").setPosition(HudManager.get("coords").getX(), y))
            .build());

        // Light Level Position
        category.addEntry(entryBuilder
            .startIntField(Component.literal("Light Level X"), HudManager.get("light").getX())
            .setDefaultValue(10)
            .setSaveConsumer(x -> HudManager.get("light").setPosition(x, HudManager.get("light").getY()))
            .build());

        category.addEntry(entryBuilder
            .startIntField(Component.literal("Light Level Y"), HudManager.get("light").getY())
            .setDefaultValue(70)
            .setSaveConsumer(y -> HudManager.get("light").setPosition(HudManager.get("light").getX(), y))
            .build());

        builder.setFinishButtonKeybind(256); // ESC key
        return builder.build();
    }
}
