package hm.edu.pohl.BombermanFX.ui.widgets;

import hm.edu.pohl.BombermanFX.api.engine.BEngine;
import hm.edu.pohl.BombermanFX.api.engine.impl.PhysicsEngine;
import hm.edu.pohl.BombermanFX.api.map.BMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BCanvas extends Canvas {

    private final BMap map = BEngine.map;
    private boolean egdeDetectionEnabled = false;

    public BCanvas() {
        addEventFilter(MouseEvent.ANY, event -> requestFocus());
        addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.F1)) {
                egdeDetectionEnabled = !egdeDetectionEnabled;
            }
            if (event.getCode().equals(KeyCode.F2)) {
                PhysicsEngine.fpsMeasureEnabled = !PhysicsEngine.fpsMeasureEnabled;
            }
        });
    }

    public void draw(final Image image, final double x, final double y, final double width, final double height) {
        final GraphicsContext gc = getGraphicsContext2D();
        gc.drawImage(image, x, y, width, height);
        if (egdeDetectionEnabled) {
            gc.strokeRect(x, y, width, height);
        }
    }

    public Image getImageAt(final int x, final int y) {
        final Image image;
        final int color = map.getRGBAt(x, y);

        if (color == BMap.HEX_BLACK) {
            image = map.getBlock();
        } else if (color == BMap.HEX_GREEN || color == BMap.HEX_BLUE || color == BMap.HEX_RED) {
            image = map.getGround();
        } else {
            image = map.getDestroyableBlockImage();
        }
        return image;
    }

    public void drawString(final String name, final double x, final double y, final Color color) {
        final GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(color);
        gc.fillText(name, x, y);
    }

    public void clear() {
        final GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
    }
}
