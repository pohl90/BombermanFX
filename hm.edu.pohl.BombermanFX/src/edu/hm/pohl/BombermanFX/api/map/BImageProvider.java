package edu.hm.pohl.BombermanFX.api.map;

import javafx.scene.image.Image;

public class BImageProvider {

    private final static String DIR = "edu/hm/pohl/BombermanFX/api/map/sprites";

    public static Image[] getFrontImages() {
        final Image[] frontImages = new Image[8];
        for (int i = 0; i < frontImages.length; i++) {
            final Image image = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/player/front/Bman_F_f0" + i
                    + ".png"));
            frontImages[i] = image;
        }
        return frontImages;
    }

    public static Image[] getBackImages() {
        final Image[] backImages = new Image[8];
        for (int i = 0; i < backImages.length; i++) {
            final Image image = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/player/back/Bman_B_f0" + i
                    + ".png"));
            backImages[i] = image;
        }
        return backImages;
    }

    public static Image[] getRightImages() {
        final Image[] sideImages = new Image[8];
        for (int i = 0; i < sideImages.length; i++) {
            final Image image = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/player/right/Bman_F_f0" + i
                    + ".png"));
            sideImages[i] = image;
        }
        return sideImages;
    }

    public static Image[] getLeftImages() {
        final Image[] leftImages = new Image[8];
        for (int i = 0; i < leftImages.length; i++) {
            final Image image = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/player/left/Bman_F_f0" + i
                    + ".png"));
            leftImages[i] = image;
        }
        return leftImages;
    }

    public static Image getRestInPeace() {
        Image rest = null;
        final Image image = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/rip.png"));
        rest = image;
        return rest;
    }

    public static Image[] getBombImages() {
        final Image[] bombs = new Image[3];
        bombs[0] = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/bomb/Bomb_f01.png"));
        bombs[1] = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/bomb/Bomb_f02.png"));
        bombs[2] = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/bomb/Bomb_f03.png"));
        return bombs;
    }

    public static Image[] getFlameImages() {
        final Image[] images = new Image[5];
        for (int i = 0; i < images.length; i++) {
            final Image image = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/flame/Flame_f0" + i + ".png"));
            images[i] = image;
        }
        return images;
    }

    public static Image[] getMobFrontImages() {
        final Image[] images = new Image[6];
        for (int i = 0; i < images.length; i++) {
            final Image image = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/mob/front/Creep_F_f0" + i
                    + ".png"));
            images[i] = image;
        }
        return images;
    }

    public static Image[] getMobBackImages() {
        final Image[] images = new Image[6];
        for (int i = 0; i < images.length; i++) {
            final Image image = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/mob/back/Creep_B_f0" + i
                    + ".png"));
            images[i] = image;
        }
        return images;
    }

    public static Image[] getMobRightImages() {
        final Image[] images = new Image[7];
        for (int i = 0; i < images.length; i++) {
            final Image image = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/mob/right/Creep_S_f0" + i
                    + ".png"));
            images[i] = image;
        }
        return images;
    }

    public static Image[] getMobLeftImages() {
        final Image[] images = new Image[7];
        for (int i = 0; i < images.length; i++) {
            final Image image = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/mob/left/Creep_S_f0" + i
                    + ".png"));
            images[i] = image;
        }
        return images;
    }

    public static Image[] getPowerupImages() {
        final Image[] images = new Image[5];
        final Image image1 = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/powerups/BombPowerup.png"));
        final Image image2 = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/powerups/FlamePowerup.png"));
        final Image image3 = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/powerups/SpeedPowerup.png"));
        final Image image4 = new Image(
                ClassLoader.getSystemResourceAsStream(DIR + "/powerups/SwitchControlPowerup.png"));
        final Image image5 = new Image(ClassLoader.getSystemResourceAsStream(DIR + "/powerups/HeartPowerup.png"));
        images[0] = image1;
        images[1] = image2;
        images[2] = image3;
        images[3] = image4;
        images[4] = image5;
        return images;
    }

    public static Image getDestroyableBlockImage() {
        return new Image(ClassLoader.getSystemResourceAsStream(DIR + "/map/DestroyableBlock.png"));
    }

    public static Image getBlock() {
        return new Image(ClassLoader.getSystemResourceAsStream(DIR + "/map/SolidBlock.png"));
    }

    public static Image getGround() {
        return new Image(ClassLoader.getSystemResourceAsStream(DIR + "/map/BackgroundTile.png"));
    }

    public static Image getGameIcon() {
        return new Image(ClassLoader.getSystemResourceAsStream(DIR + "/icon.png"));
    }

}
