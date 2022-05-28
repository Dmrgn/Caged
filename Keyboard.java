import java.util.ArrayList;

import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
/**
 * <p>
 * This class creates an abstraction for dealing with the user's keyboard input.
 * It keeps track of the keys the user currently has pressed.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 2.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * File was created by Daniel Morgan on may 17th, 2022.
 * Daniel Morgan spent 1 hours on May 17th.
 * Daniel Morgan spent 30 minutes on May 27th updating for JavaFX.
 * </p>
 */
public class Keyboard {
    /** All keys currently being pressed down*/
    private static ArrayList<KeyCode> keysDown = new ArrayList<KeyCode>();

    /**
     * Checks whether a key is currently down
     * @param code KeyCode for key
     * @return Whether it is down
     */
    public static boolean isKeyDown(KeyCode code) {
        if (keysDown.contains(code))
            return true;
        return false;
    }
    /**
     * Initialize a keyboard listener to detect keys being pressed
     * @param scene Current scene
     */
    public static void init(Scene scene) {
        scene.setOnKeyPressed(new EventHandler < KeyEvent > () {
            @Override
            public void handle(KeyEvent e) {
                if (!keysDown.contains(e.getCode())) {
                    keysDown.add(e.getCode());
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler < KeyEvent > () {
            @Override
            public void handle(KeyEvent e) {
                keysDown.remove(e.getCode());
            }
        });
    }
}