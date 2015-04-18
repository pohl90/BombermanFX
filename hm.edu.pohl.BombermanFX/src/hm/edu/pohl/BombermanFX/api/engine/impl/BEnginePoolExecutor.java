package hm.edu.pohl.BombermanFX.api.engine.impl;

import hm.edu.pohl.BombermanFX.api.engine.BEngine;
import hm.edu.pohl.BombermanFX.api.engine.BEnginePoolExecutable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

public abstract class BEnginePoolExecutor extends BEngine implements BEnginePoolExecutable {

    private static final int SECOND_TO_MILLIS = 1000;
    private static final float NANOS_TO_MILLIS = 1_000_000F;
    private static final int RENDERING_PASSES = 20;
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    protected final List<BEngine> engines = new ArrayList<>();

    private final LongProperty fpsProperty = new SimpleLongProperty();

    private long measureTime;
    private long fpsTime = 0;
    private int fpsAverageCounter = 0;

    @Override
    public void startEngines() {
        executor.scheduleAtFixedRate(() -> perform(), 0, getPeriod(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void stopEngines() {
        executor.shutdown();
    }

    @Override
    public void addEngine(final BEngine engine) {
        engines.add(engine);
    }

    @Override
    public LongProperty fpsProperty() {
        return fpsProperty;
    }

    public abstract long getPeriod();

    protected void startFPSMeasuring() {
        measureTime = System.nanoTime();
    }

    protected void stopFPSMeasuring() {
        final long delta = System.nanoTime() - measureTime + getPeriod() * 1_000_000;
        fpsTime += delta;

        fpsAverageCounter++;
        if (fpsAverageCounter == RENDERING_PASSES) {
            fpsProperty.set((long) (SECOND_TO_MILLIS / (fpsTime / (NANOS_TO_MILLIS * RENDERING_PASSES))));
            fpsTime = 0;
            fpsAverageCounter = 0;
        }
    }
}
