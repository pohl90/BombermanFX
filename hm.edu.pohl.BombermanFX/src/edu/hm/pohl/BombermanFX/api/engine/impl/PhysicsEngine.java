package edu.hm.pohl.BombermanFX.api.engine.impl;

import edu.hm.pohl.BombermanFX.api.engine.BEngine;
import edu.hm.pohl.BombermanFX.utils.BConstants;
import javafx.application.Platform;

public class PhysicsEngine extends BEnginePoolExecutor {

    public static boolean fpsMeasureEnabled = true;

    @Override
    public void perform() {
        if (Platform.isFxApplicationThread()) {
            doPerform();
        } else {
            Platform.runLater(() -> doPerform());
        }
    }

    @Override
    public long getPeriod() {
        return BConstants.PHYSICS_ENGINE_MODIFIER;
    }

    private void doPerform() {
        final boolean measureFPS = fpsMeasureEnabled;
        if (measureFPS) {
            startFPSMeasuring();
        }
        engines.forEach(BEngine::perform);
        if (measureFPS) {
            stopFPSMeasuring();
        }
    }

}
