package com.example.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;

public class HudManager {

    private static final Map<String, HudElement> elements = new HashMap<>();
    private static final String CONFIG_FILENAME = "vitalhud.json";

    public static void init() {
        // Create default elements
        register(new HudElement("fps", 10, 10));
        register(new HudElement("cps", 10, 25));
        register(new HudElement("speed", 10, 40));
        register(new HudElement("coords", 10, 55));
        register(new HudElement("light", 10, 70));

        // Load saved positions from config file
        loadConfig();
    }

    public static void register(HudElement element) {
        elements.put(element.getName(), element);
    }

    public static HudElement get(String name) {
        return elements.get(name);
    }

    // Load positions from config file in .minecraft/config/vitalhud.json
    private static void loadConfig() {
        try {
            Path configDir = Minecraft.getInstance().gameDirectory.toPath().resolve("config");
            Path configFile = configDir.resolve(CONFIG_FILENAME);

            if (Files.exists(configFile)) {
                String content = Files.readString(configFile);
                parseConfig(content);
            }
        } catch (Exception e) {
            System.err.println("Failed to load VitalHUD config: " + e.getMessage());
        }
    }

    // Parse simple JSON: {"fps":{"x":10,"y":10},"cps":{"x":10,"y":25},...}
    private static void parseConfig(String json) {
        // Simple regex-based parsing (good enough for this use case)
        Pattern pattern = Pattern.compile("\"(\\w+)\":\\{\"x\":(\\d+),\"y\":(\\d+)\\}");
        Matcher matcher = pattern.matcher(json);

        while (matcher.find()) {
            String name = matcher.group(1);
            int x = Integer.parseInt(matcher.group(2));
            int y = Integer.parseInt(matcher.group(3));

            HudElement el = elements.get(name);
            if (el != null) {
                el.setPosition(x, y);
            }
        }
    }

    // Save current positions to config file
    public static void saveConfig() {
        try {
            Path configDir = Minecraft.getInstance().gameDirectory.toPath().resolve("config");
            Files.createDirectories(configDir);

            Path configFile = configDir.resolve(CONFIG_FILENAME);

            // Build simple JSON
            StringBuilder json = new StringBuilder("{");
            boolean first = true;
            for (Map.Entry<String, HudElement> entry : elements.entrySet()) {
                if (!first) json.append(",");
                first = false;
                HudElement el = entry.getValue();
                json.append(String.format("\"%s\":{\"x\":%d,\"y\":%d}",
                    el.getName(), el.getX(), el.getY()));
            }
            json.append("}");

            Files.writeString(configFile, json.toString());
            System.out.println("VitalHUD config saved to " + configFile);
        } catch (IOException e) {
            System.err.println("Failed to save VitalHUD config: " + e.getMessage());
        }
    }
}
