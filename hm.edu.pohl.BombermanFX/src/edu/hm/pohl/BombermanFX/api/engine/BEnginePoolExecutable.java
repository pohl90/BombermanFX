package edu.hm.pohl.BombermanFX.api.engine;

import javafx.beans.property.LongProperty;

public interface BEnginePoolExecutable {
    
    public void startEngines();
    
    public void stopEngines();
    
    public void addEngine(final BEngine engine);

    public LongProperty fpsProperty();
    
}
