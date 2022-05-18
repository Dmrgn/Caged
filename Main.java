import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

class Main implements Runnable {
    private static int width = 1280;
    private static int height = 720;

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

        // glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
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
        // if (Keyboard.keys.contains(GLFW_KEY_A)) {

        // }
        // if (Keyboard.keys.contains(GLFW_KEY_W)) {
            
        // }
        // if (Keyboard.keys.contains(GLFW_KEY_D)) {
            
        // }
        // if (Keyboard.keys.contains(GLFW_KEY_S)) {
            
        // }
        Vector diff = Vector.sub(new Vector(width,height), player.pos);
        float percent = diff.x+diff.y;
        player.pos = player.pos.lerp(new Vector(width,height), 0.001f);
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        player.draw();
        glfwSwapBuffers(window);
    }

    static public int getWidth() {
        return width;
    }

    static public int getHeight() {
        return height;
    }

    public static void main(String[] args) {
        new Main().start();
    }
}