package hm.edu.pohl.BombermanFX.api.engine;

import hm.edu.pohl.BombermanFX.api.audio.BSoundFactory;
import hm.edu.pohl.BombermanFX.api.engine.impl.DrawEngine;
import hm.edu.pohl.BombermanFX.api.engine.impl.PhysicsEngine;
import hm.edu.pohl.BombermanFX.api.model.impl.Monster;
import hm.edu.pohl.BombermanFX.api.model.impl.Player;
import hm.edu.pohl.BombermanFX.api.model.input.InputListener;
import hm.edu.pohl.BombermanFX.utils.BConstants;
import javafx.beans.property.LongProperty;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;

public class BEngineHandler {

    private final BEnginePoolExecutable physicsEngine;
    private final BEnginePoolExecutable drawEngine;

    private final AudioClip gameStartClip = BSoundFactory.createGameStartClip();

    public BEngineHandler() {

        physicsEngine = new PhysicsEngine();
        physicsEngine.addEngine(BEngine.powerupEngine);
        physicsEngine.addEngine(BEngine.bombEngine);
        physicsEngine.addEngine(BEngine.flameEngine);
        physicsEngine.addEngine(BEngine.entityEngine);

        drawEngine = new DrawEngine(physicsEngine.fpsProperty());

        addPlayer("Gunnar", BConstants.BLOCK, BConstants.BLOCK, true);
        addPlayer("DJ", BConstants.BLOCK, BConstants.BLOCK, false);
        addMonster();
        startEngines();
    }

    public LongProperty fpsProperty() {
        return physicsEngine.fpsProperty();
    }

    public void startEngines() {
        physicsEngine.startEngines();
        drawEngine.startEngines();
        gameStartClip.play(BConstants.volumeProperty.get());
    }

    public void stopEngines() {
        drawEngine.stopEngines();
        physicsEngine.stopEngines();
    }

    public void addPlayer(final String name, final double x, final double y, final boolean isPlayerOne) {
        final InputListener inputListener = new InputListener(isPlayerOne);
        final Player player = new Player(name, x, y, inputListener);
        BEngine.players.add(player);
        BEngine.canvas.addEventHandler(KeyEvent.ANY, inputListener);
    }

    public void addMonster() {
        final Monster monster = new Monster(BConstants.BLOCK, BConstants.BLOCK);
        monster.setName("Monster");
        BEngine.monsters.add(monster);
    }
}
