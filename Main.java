// import static org.lwjgl.glfw.GLFW.*;
// import static org.lwjgl.opengl.GL40.*;
// import static org.lwjgl.system.MemoryUtil.*;
// 
// import org.lwjgl.glfw.GLFWVidMode;
// import org.lwjgl.opengl.GL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
/**
 * <p>
 * This file acts as the main driver class that runs the game. This is the only class to have a main method
 * and controls the main game loop, calling methods from the game class and keeping the game running as long as
 * the player chooses not to exit. The player class contains state and behaviours for a player object.
 * The player object can be controled by the keyboard to perform actions such as 
 * movement and slashing. Contains functionality for drawing the player.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.1
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * 1 hour was spent by Ryan Atlas on this file on May 17th, 18th and 20th, 2022 making the game loop and adding comments.
 * Daniel Morgan spent 3 hours over may 17th and 18th 2022 adding LWJGL graphical functionality
 * </p>
 */
public class Main extends Application {
   /** Whether or not the player has exited the main game loop*/
   public static boolean exited = false;
    /** The width of the program window */
    private static final int width = 1280;
    /** The height of the program window */
    private static final int height = 720;
    /** The dimensions of the program window stored as a vector */
    private static final Vector dims = new Vector(width, height);

    /** Instance of the main thread this program is running on */
    // private Thread thread;
//     /** Boolean set to false when program should exit */
     private boolean running = false;
// 
//     /** Pointer to the memory address storing the current window */
//     private long window;

    /** Instance of the player */
     public Player player;

    /** Method run when the program should start on a new thread */
  //   public void start() {
//         running = true;
//         thread = new Thread(this, "Game");
//         thread.start();
//     }
// 
//     /** Method run when the program should initialize GLFW and OpenGL */
//     private void init() {
//         if (!glfwInit()) {
//             throw new RuntimeException("Could not init GlFW.");
//         }
// 
//         window = glfwCreateWindow(width, height, "Game", NULL, NULL);
//         if (window == NULL) {
//             throw new RuntimeException("Failed to create the GLFW window.");
//         }
// 
//         glfwSetKeyCallback(window, new Keyboard());
// 
//         GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
//         glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
// 
//         glfwMakeContextCurrent(window);
//         glfwSwapInterval(1);
//         glfwShowWindow(window);
// 
//         GL.createCapabilities();
//         glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
// 
//         glMatrixMode(GL_PROJECTION);
//         glLoadIdentity();
//         glOrtho(0.0, width, height, 0.0, -1.0, 1.0);
//         glMatrixMode(GL_MODELVIEW);
// 
//         glEnable(GL_TEXTURE_2D);
// 
//         try{
//             player = new Player(20f, 20f);
//         } catch (Exception e) {
//             System.out.println(e.toString());
//         }
//     }
// 
//     /** Main game loop run on the main thread */
//     public void run() {
//         init();
//         while (running) {
//             update();
//             draw();
// 
//             if (glfwWindowShouldClose(window))
//                 running = false;
//         }
//     }
// 
//     /** Handles updating the game logic for the current frame */
//     private void update() {
//         glfwPollEvents();
//         player.update();
//     }
// 
//     /** Handles rendering the current frame */
//     private void draw() {
//         glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//         player.draw();
//         glfwSwapBuffers(window);
//     }

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
    @Override
    public void start(Stage window){
      window.setTitle("Caged Inside the Mind");
      window.setMinWidth(width);
      window.setMinHeight(height);
      window.setResizable(false);
      player = new Player(0, 0);
      Scene scene = player.getScene();
      window.setScene(scene);
      AnimationTimer at = new AnimationTimer() {
         @Override
         public void handle (long l) {
            player.update();
            player.draw();
         }
      };
      at.start();
      window.show();
    }
   /**
   * Main method that runs at the start of the program
   * and creates a Game object and calls all of its methods
   * @param args Used to call launch
   */
   public static void main(String[] args) {
      launch(args);
      // Game isp = new Game();
//       isp.splashScreen();
//       while(!exited) {
        //  int choice = isp.mainMenu();
//          switch(choice) {
//             case 1:
//                break;
//             case 2:
//                break;
//             case 3:
//                break;
//             case 4:
//                exited = true;
//                break;
//          }
//       }
   }
}