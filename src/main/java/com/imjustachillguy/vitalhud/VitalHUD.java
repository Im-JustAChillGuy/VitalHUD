package com.imjustachillguy.vitalhud;

import net.fabricmc.api.ClientModInitializer;

public class VitalHUD implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderer.init();
        ClickTracker.init();
        SpeedTracker.init();
    }
}
