import java.util.ArrayList;

import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.*;
/**
 * <p>
 * This class creates an abstraction for dealing with the user's keyboard input.
 * It keeps track of the keys the user currently has pressed.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 3.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * File was created by Daniel Morgan on may 17th, 2022.
 * Daniel Morgan spent 1 hours on May 17th.
 * Daniel Morgan spent 30 minutes on May 27th updating for JavaFX.
 * Ryan Atlas spent 20 minutes on May 30th adding mouse input
 * </p>
 */
public class Keyboard {
    /** All keys currently being pressed down*/
    private static ArrayList<KeyCode> keysDown = new ArrayList<KeyCode>();
    /** Is the mouse being clicked currently*/
    private static boolean mouseClicked = false;
    /** Mouse x coord*/
    private static double mouseX;
    /** Mouse y coord*/
    private static double mouseY;
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
     * Gets whether the mouse is being clicked
     * @return Is the mouse being clicked?
     */
    public static boolean isMouseClicked(){
        return mouseClicked;
    }
    /**
     * Gets the coordinates of the mouse
     * @return The x and y coordinates as indices 0 and 1 in an array respectively
     */
    public static Vector mouseCoords(){
        return new Vector((float)mouseX, (float)mouseY);
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
        scene.setOnMousePressed(new EventHandler <MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("mouse: " + Game.toWorld(Keyboard.mouseCoords()));
                mouseClicked = true;
                mouseX = e.getSceneX();
                mouseY = e.getSceneY();
            }
        });
        scene.setOnMouseReleased(new EventHandler <MouseEvent>() {
            @Override
            public void handle(MouseEvent e){
                mouseClicked = false;
            }
        });
    }
}