package edu.hm.pohl.BombermanFX.api.engine.impl;

import static edu.hm.pohl.BombermanFX.utils.BConstants.BLOCK;
import static edu.hm.pohl.BombermanFX.utils.BConstants.MAP_HEIGHT;
import static edu.hm.pohl.BombermanFX.utils.BConstants.MAP_WIDTH;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import edu.hm.pohl.BombermanFX.api.model.VulnerableEntity;
import edu.hm.pohl.BombermanFX.api.model.impl.Player;
import edu.hm.pohl.BombermanFX.utils.BConstants;
import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class DrawEngine extends BEnginePoolExecutor {

    private static final String SPACING = " | ";
    private final LongProperty fpsPropertyToDraw;

    public DrawEngine(final LongProperty fpsPropertyToDraw) {
        this.fpsPropertyToDraw = fpsPropertyToDraw;
    }

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
        return BConstants.DRAW_ENGINE_MODIFIER;
    }

    private void doPerform() {
        canvas.clear();
        drawMap();
        drawBombs();
        drawFlames();
        drawPowerups();
        drawEntities();
        drawPlayerStats();
        drawFps();
    }

    private void drawMap() {
        final double blockSize = BConstants.BLOCK;
        for (int y = 0; y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                canvas.draw(canvas.getImageAt(x, y), x * blockSize, y * blockSize, blockSize, blockSize);
            }
        }
    }

    private void drawBombs() {
        bombs.forEach(bomb -> {
            final Image image = bomb.getCurrentImage();
            final double x = bomb.getX();
            final double y = bomb.getY();
            canvas.draw(image, x, y, bomb.getWidth(), bomb.getHeight());
        });
    }

    private void drawFlames() {
        flames.forEach(flame -> {
            final Image image = flame.getCurrentImage();
            final double width = flame.getWidth();
            final double height = flame.getHeight();
            final double deltaX = (BLOCK - width) / 2;
            final double deltaY = (BLOCK - height) / 2;
            flame.getRelativeExplosionPoints().forEach(point -> {
                final double x = point.getX() * BLOCK + deltaX;
                final double y = point.getY() * BLOCK + deltaY;
                canvas.draw(image, x, y, width, height);
            });

        });
    }

    private void drawPowerups() {
        powerups.forEach(powerup -> {
            final Image image = powerup.getCurrentImage();

            final double x = powerup.getX();
            final double y = powerup.getY();
            canvas.draw(image, x, y, powerup.getWidth(), powerup.getHeight());
        });
    }

    private void drawEntities() {
        final List<VulnerableEntity> entities = new ArrayList<>();
        entities.addAll(players);
        entities.addAll(monsters);
        final Stream<VulnerableEntity> sortedEntities = entities.stream().sorted(
                (e1, e2) -> (int) (e1.getY() - e2.getY()));
        sortedEntities.forEach(entity -> {
            final Image image = entity.getCurrentImage();
            final String name = entity.getName();

            final double x = entity.getX();
            final double y = entity.getY();
            canvas.draw(image, x, y, entity.getWidth(), entity.getHeight());

            if (!entity.isDead() && entity.isImmuneToDamage()) {
                canvas.draw(entity.getImmuneImage(), x, y, entity.getWidth(), entity.getHeight());
            }
            canvas.drawString(name, x, y - 5, Color.WHITE);
        });
    }

    private void drawFps() {
        if (PhysicsEngine.fpsMeasureEnabled) {
            final long fps = fpsPropertyToDraw.get();
            canvas.drawString("FPS [" + Long.toString(fps) + "]", 10, 15, Color.WHITE);
        }
    }

    private void drawPlayerStats() {
        double y = BConstants.MAP_HEIGHT * BLOCK + 20;

        for (final Player player : players) {
            final String name = player.getName();
            final String life = "Life [" + player.getLife() + "]";
            final String firePower = "Power [" + player.getFirePower() + "]";
            final String bombs = "Bombs [" + player.getDroppedBombs().size() + "/" + player.getMaxBombs() + "]";
            final String speed = "Speed [" + (int) (player.getSpeed() * 5) + "]";
            final String stats = name + SPACING + life + SPACING + firePower + SPACING + bombs + SPACING + speed;
            canvas.drawString(stats, 10, y, Color.WHITE);
            y += 20;
        }
    }
}
