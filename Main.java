import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

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
 * @since May 17th, 2022
 * <p>
 * File was created by Daniel Morgan on may 17th, 2022.
 * Daniel Morgan spent 3 hours over may 17th and 18th 2022
 * </p>
 */

class Main implements Runnable {
    /** The width of the program window */
    private static final int width = 1280;
    /** The height of the program window */
    private static final int height = 720;
    /** The dimensions of the program window stored as a vector */
    private static final Vector dims = new Vector(width,height);

    /** Instance of the main thread this program is running on */
    private Thread thread;
    /** Boolean set to false when program should exit */
    private boolean running = false;

    /** Pointer to the memory address storing the current window */
    private long window;

    /** Instance of the player */
    public Player player = new Player(20f,20f);

    /** Method run when the program should start on a new thread */
    public void start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    /** Method run when the program should initialize GLFW and OpenGL */
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
    }

    /** Main game loop run on the main thread */
    public void run() {
        init();
        while (running) {
            update();
            draw();

            if (glfwWindowShouldClose(window))
                running = false;
        }
    }

    /** Handles updating the game logic for the current frame */
    private void update() {
        glfwPollEvents();
        player.update();
    }

    /** Handles rendering the current frame */
    private void draw() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        player.draw();
        glfwSwapBuffers(window);
    }

    /** 
     * returns the width of the current window 
     * @return Width of the current window in pixels
     */
    public static int getWidth() {
        return width;
    }
    /** 
     * returns the height of the current window 
     * @return Height of the current window in pixels
     */
    public static int getHeight() {
        return height;
    }
    /** 
     * returns the dimensions of the current window as a Vector 
     * @return Dimensions of the current window as a {@link Vector}
     */
    public static Vector getDims() {
        return dims;
    }

    /**
     * Main method run at the start of the program
     */
    public static void main(String[] args) {
        new Main().start();
    }
}