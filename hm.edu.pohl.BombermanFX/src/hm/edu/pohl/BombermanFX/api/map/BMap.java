package hm.edu.pohl.BombermanFX.api.map;

import hm.edu.pohl.BombermanFX.utils.BConstants;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

/**
 * This class represents the levels and various sprites for the map
 *
 * @author Marc
 *
 */
public class BMap {
    
    private static final String DIR = "hm/edu/pohl/BombermanFX/api/map/sprites/map/";
    
    // Variables for the level and its rgb colors
    private Image chosenLevel;
    private int[][] chosenLevelRGB;
    
    // The levels for the game. The sizes are:
    // Width = 23 Height = 13
    private Image myLevel01;
    
    // Variables for the images of the blocks
    private Image imageDestroyableBlock;
    private Image imageBlock;
    private Image imageGround;
    
    // Destroyable Block
    public final static int HEX_WHITE = 0xFFFFFFFF;
    // Solid Block
    public final static int HEX_BLACK = 0xFF000000;
    // Ground
    public final static int HEX_GREEN = 0xFF00FF00;
    public final static int HEX_BLUE = 0xFF0000FF;
    public final static int HEX_RED = 0xFFFF0000;
    
    public BMap() {
        initImages();
        loadLevel();
    }
    
    public void setLevel(final int level) {
        if (level == 1) {
            chosenLevel = myLevel01;
        }
        chosenLevelRGB = new int[BConstants.MAP_HEIGHT][BConstants.MAP_WIDTH];
        final PixelReader pixelReader = chosenLevel.getPixelReader();
        for (int y = 0; y < chosenLevelRGB.length; y++) {
            for (int x = 0; x < chosenLevelRGB[0].length; x++) {
                chosenLevelRGB[y][x] = pixelReader.getArgb(x, y);
            }
        }
    }
    
    public void destroyAt(final int x, final int y) {
        chosenLevelRGB[y][x] = HEX_GREEN;
    }

    public boolean validate(final int x, final int y) {
        if (x < 0 || x > chosenLevelRGB[0].length) {
            return false;
        }
        if (y < 0 || y > chosenLevelRGB.length) {
            return false;
        }
        return true;
    }

    public int getRGBAt(final int x, final int y) {
        return chosenLevelRGB[y][x];
    }
    
    public int[][] getChosenLevelRGB() {
        return chosenLevelRGB;
    }
    
    public Image getDestroyableBlockImage() {
        return imageDestroyableBlock;
    }
    
    public Image getBlock() {
        return imageBlock;
    }
    
    public Image getGround() {
        return imageGround;
    }
    
    public Image getChosenLevel() {
        return chosenLevel;
    }
    
    private void initImages() {
        imageDestroyableBlock = BImageProvider.getDestroyableBlockImage();
        imageBlock = BImageProvider.getBlock();
        imageGround = BImageProvider.getGround();
    }

    private void loadLevel() {
        final Image level01 = new Image(ClassLoader.getSystemResourceAsStream(DIR + "level01.png"));
        myLevel01 = level01;
        setLevel(1);
    }
}
