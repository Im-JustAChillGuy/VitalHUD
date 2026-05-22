package com.imjustachillguy.vitalhud;

import com.imjustachillguy.vitalhud.HudRenderer;
import com.imjustachillguy.vitalhud.ClickTracker;
import com.imjustachillguy.vitalhud.SpeedTracker;
public class VitalHUD implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderer.init();
        ClickTracker.init();
        SpeedTracker.init();
    }
}
