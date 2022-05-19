/**
 * <p>
 * The player class contains state and behaviours for a player object.
 * The player object can be controled by the keyboard to perform actions such as 
 * movement and slashing. Contains functionality for drawing the player.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 19th, 2022
 * <p>
 * File was created by Daniel Morgan on may 19th, 2022.
 * </p>
 */

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

class Main implements Runnable {
    private static int width = 1280;
    private static int height = 720;
    private static Vector dims = new Vector(width,height);

    private Thread thread;
    private boolean running = false;

    private long window;

    public Player player = new Player(20f,20f);

    public void start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    private void init() {
        if (!glfwInit()) {
            throw new RuntimeException("Could not init GlFW.");
        }

        window = glfwCreateWindow(width, height, "Game", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window.");
        }

        glfwSetKeyCallback(window, new Keyboard());

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        System.out.println((new Vector(0,0)).lerp(new Vector(10,10), 0.1f));
    }

    public void run() {
        init();
        while (running) {
            update();
            render();

            if (glfwWindowShouldClose(window))
                running = false;
        }
    }

    private void update() {
        glfwPollEvents();
        player.update();
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        player.draw();
        glfwSwapBuffers(window);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static Vector getDims() {
        return dims;
    }

    public static void main(String[] args) {
        new Main().start();
    }
}