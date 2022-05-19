import java.util.ArrayList;

import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*;

/**
 * <p>
 * This class creates an abstraction for dealing with the user's keyboard input.
 * It keeps track of the keys the user currently has pressed.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * File was created by Daniel Morgan on may 17th, 2022.
 * Daniel Morgan spent 1 hours on may 17th.
 * </p>
 */

public class Keyboard extends GLFWKeyCallback {

    public static ArrayList<Integer> keys = new ArrayList<Integer>();

    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            keys.add(Integer.valueOf(key));
            System.out.println("Here");
        }
        if (action == GLFW_RELEASE) {
            keys.remove(Integer.valueOf(key));
        }
    }
}
