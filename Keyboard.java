import java.util.ArrayList;

import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard {

    private static ArrayList<KeyCode> keysDown = new ArrayList<KeyCode>();

    static boolean isKeyDown(KeyCode code) {
        if (keysDown.contains(code))
            return true;
        return false;
    }

    static void init(Scene scene) {
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
