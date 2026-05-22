package com.example.client;

import java.util.HashMap;
import java.util.Map;

public class HudManager {

    private static final Map<String, HudElement> elements = new HashMap<>();

    public static void init() {

        register(new HudElement("fps", 10, 10));
        register(new HudElement("cps", 10, 25));
        register(new HudElement("speed", 10, 40));
        register(new HudElement("coords", 10, 55));
        register(new HudElement("light", 10, 70));
    }

    public static void register(HudElement element) {
        elements.put(element.getName(), element);
    }

    public static HudElement get(String name) {
        return elements.get(name);
    }
}
