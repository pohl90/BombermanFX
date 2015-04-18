package hm.edu.pohl.BombermanFX.utils;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class BConstants {

    public static final DoubleProperty volumeProperty = new SimpleDoubleProperty(0.15);

    public static final long DRAW_ENGINE_MODIFIER = 10;
    public static final long PHYSICS_ENGINE_MODIFIER = 10;

    public static final double BLOCK = 40;
    public static final int HEIGHT = 700;
    public static final int WIDTH = 920;
    public static final int MAP_HEIGHT = 13;
    public static final int MAP_WIDTH = 23;

    public static final double BOMB_WIDTH = BLOCK * 0.7;
    public static final double BOMB_HEIGHT = BLOCK * 0.7;
    public static final double BOMB_COUNT_DOWN = PHYSICS_ENGINE_MODIFIER * 6.5;
    public static final double BOMB_COUNT_DOWN_MODIFIER = 0.1;
    public static final double BOMB_KICK_SPEED = BLOCK / (PHYSICS_ENGINE_MODIFIER * 5);

    public static final double FLAME_COUNT_DOWN = BOMB_COUNT_DOWN / 3.5;
    public static final double FLAME_COUNT_DOWN_MODIFIER = 0.05;
    public static final double FLAME_WIDTH = BLOCK * 0.9;
    public static final double FLAME_HEIGHT = BLOCK * 0.9;

    public static final double POWERUP_WIDTH = BLOCK * 0.75;
    public static final double POWERUP_HEIGHT = BLOCK * 0.75;

    public static final int PLAYER_IMAGE_DELAY = 40;
    public static final double PLAYER_IMMUNE_COUNT_DOWN = BOMB_COUNT_DOWN / 2;
    public static final double PLAYER_WIDTH = BLOCK * 0.7;
    public static final double PLAYER_HEIGHT = BLOCK * 0.7;
    public static final int PLAYER_FIREPOWER = 1;
    public static final int PLAYER_BOMBLIMIT = 10;
    public static final int PLAYER_LIFES = 3;
    public static final double PLAYER_SPEED = BLOCK / PHYSICS_ENGINE_MODIFIER * 0.25;
    public static final double PLAYER_SPEED_MODIFIER = 0.2;
    public static final int PLAYER_RUN_DELAY = 15;

    public static final int MONSTER_IMAGE_DELAY = 30;
    public static final double MONSTER_SPEED = PLAYER_SPEED / 2;
    public static final double MONSTER_WIDTH = BLOCK * 0.9;
    public static final double MONSTER_HEIGHT = BLOCK * 0.9;
    public static final int MONSTER_LIFE = 1;

    public static final double ENTITY_DEAD_WIDTH = BLOCK * 0.75;
    public static final double ENTITY_DEAD_HEIGHT = BLOCK * 0.75;
    public static final double ENTITY_REVERSE_CONTROL_COUNT_DOWN = PHYSICS_ENGINE_MODIFIER * 7;
    public static final double REVERSE_CONTROL_MODIFIER = 0.1;

}
