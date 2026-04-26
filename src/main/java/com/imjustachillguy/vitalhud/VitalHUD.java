package com.imjustachillguy.vitalhud;

import net.fabricmc.api.ClientModInitializer;
import com.imjustachillguy.vitalhud.hud.HudRenderer;
import com.imjustachillguy.vitalhud.stats.ClickTracker;
import com.imjustachillguy.vitalhud.stats.SpeedTracker;

public class VitalHUD implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderer.init();
        ClickTracker.init();
        SpeedTracker.init();
    }
}
