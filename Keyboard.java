import java.util.ArrayList;

import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*;

public class Keyboard extends GLFWKeyCallback {

    public static ArrayList<Integer> keys = new ArrayList<Integer>();

    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            keys.add(Integer.valueOf(key));
        }
        if (action == GLFW_RELEASE) {
            keys.remove(Integer.valueOf(key));
        }
    }
}
