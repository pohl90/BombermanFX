package hm.edu.pohl.BombermanFX.api.model.input;

import hm.edu.pohl.BombermanFX.api.engine.util.EntityDirection;
import hm.edu.pohl.BombermanFX.api.model.impl.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputListener implements EventHandler<KeyEvent> {

    protected boolean up;
    protected boolean down;
    protected boolean left;
    protected boolean right;

    private KeyCode KEYSTROKE_UP;
    private KeyCode KEYSTROKE_RIGHT;
    private KeyCode KEYSTROKE_DOWN;
    private KeyCode KEYSTROKE_LEFT;
    private KeyCode KEYSTROKE_BOMB;
    private Player player;

    public InputListener(final boolean isPlayerOne) {
        if (isPlayerOne) {
            KEYSTROKE_UP = KeyCode.UP;
            KEYSTROKE_RIGHT = KeyCode.RIGHT;
            KEYSTROKE_DOWN = KeyCode.DOWN;
            KEYSTROKE_LEFT = KeyCode.LEFT;
            KEYSTROKE_BOMB = KeyCode.ENTER;
        } else {
            KEYSTROKE_UP = KeyCode.W;
            KEYSTROKE_RIGHT = KeyCode.D;
            KEYSTROKE_DOWN = KeyCode.S;
            KEYSTROKE_LEFT = KeyCode.A;
            KEYSTROKE_BOMB = KeyCode.SPACE;
        }
    }

    @Override
    public void handle(final KeyEvent event) {
        if (player == null) {
            return;
        }
        final KeyCode keyCode = event.getCode();
        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            left = keyCode.equals(KEYSTROKE_LEFT) ? true : left;
            right = keyCode.equals(KEYSTROKE_RIGHT) ? true : right;
            up = keyCode.equals(KEYSTROKE_UP) ? true : up;
            down = keyCode.equals(KEYSTROKE_DOWN) ? true : down;
            if (keyCode.equals(KEYSTROKE_BOMB)) {
                player.setDropBomb(true);
            }
        } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            left = keyCode.equals(KEYSTROKE_LEFT) ? false : left;
            right = keyCode.equals(KEYSTROKE_RIGHT) ? false : right;
            up = keyCode.equals(KEYSTROKE_UP) ? false : up;
            down = keyCode.equals(KEYSTROKE_DOWN) ? false : down;
        }
        if (up && down || !up && !down) {
            player.setVerticalDirection(null);
        } else if (up) {
            player.setVerticalDirection(EntityDirection.UP);
        } else if (down) {
            player.setVerticalDirection(EntityDirection.DOWN);
        }
        if (left && right || !left && !right) {
            player.setHorizontalDirection(null);
        } else if (left) {
            player.setHorizontalDirection(EntityDirection.LEFT);
        } else if (right) {
            player.setHorizontalDirection(EntityDirection.RIGHT);
        }

    }

    public void setPlayer(final Player player) {
        this.player = player;
    }
    
    /**
     * @return True if left is pressed
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * @return True if right is pressed
     */
    public boolean isRight() {
        return right;
    }

    /**
     * @return True if up is pressed
     */
    public boolean isUp() {
        return up;
    }

    /**
     * @return True if down is pressed
     */
    public boolean isDown() {
        return down;
    }

    /**
     * Clears the input of the key directions
     */
    public void clear() {
        up = false;
        down = false;
        left = false;
        right = false;
    }
}
