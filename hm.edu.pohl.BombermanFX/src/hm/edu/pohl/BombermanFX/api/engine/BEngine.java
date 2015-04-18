package hm.edu.pohl.BombermanFX.api.engine;

import hm.edu.pohl.BombermanFX.api.engine.impl.BombEngine;
import hm.edu.pohl.BombermanFX.api.engine.impl.EntityEngine;
import hm.edu.pohl.BombermanFX.api.engine.impl.FlameEngine;
import hm.edu.pohl.BombermanFX.api.engine.impl.PowerupEngine;
import hm.edu.pohl.BombermanFX.api.map.BMap;
import hm.edu.pohl.BombermanFX.api.model.impl.Bomb;
import hm.edu.pohl.BombermanFX.api.model.impl.Flame;
import hm.edu.pohl.BombermanFX.api.model.impl.Monster;
import hm.edu.pohl.BombermanFX.api.model.impl.Player;
import hm.edu.pohl.BombermanFX.api.model.impl.Powerup;
import hm.edu.pohl.BombermanFX.ui.widgets.BCanvas;

import java.util.ArrayList;
import java.util.List;

public abstract class BEngine {

    public static final List<Player> players = new ArrayList<>();
    public static final List<Monster> monsters = new ArrayList<>();
    public static final List<Bomb> bombs = new ArrayList<>();
    public static final List<Flame> flames = new ArrayList<>();
    public static final List<Powerup> powerups = new ArrayList<>();

    public static final BEngine entityEngine = new EntityEngine();
    public static final BEngine bombEngine = new BombEngine();
    public static final BEngine flameEngine = new FlameEngine();
    public static final BEngine powerupEngine = new PowerupEngine();

    public static final BMap map = new BMap();
    public static final BCanvas canvas = new BCanvas();

    public abstract void perform();
}
