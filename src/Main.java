import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileNotFoundException;

/**
 * <p>
 * This file acts as the main driver class that runs the game. This is the only class to have a main method
 * and controls the main game loop, calling methods from the game class and keeping the game running as long as
 * the player chooses not to exit. The player class contains state and behaviours for a player object.
 * The player object can be controlled by the keyboard to perform actions such as
 * movement and slashing. Contains functionality for drawing the player.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * 1 hour was spent by Ryan Atlas on this file on May 17th, 18th and 20th, 2022 making the game loop and adding comments.
 * Daniel Morgan spent 3 hours over may 17th and 18th 2022 adding LWJGL graphical functionality
 * Ryan Atlas spent 1 hour adding the window functionality for JavaFX and adding the main animation loop
 * for the Player on May 25th and 26th
 * </p>
 */
public class Main extends Application {
    /** Whether or not the player has exited the main game loop*/
    public static boolean exited = false;
    /** The width of the program window */
    private static int width = 1280;
    /** The height of the program window */
    private static int height = 720;
    /** The dimensions of the program window stored as a vector */
    private static final Vector dims = new Vector(width, height);
    /** Boolean set to false when program should exit */
    private boolean running = false;
    /** Instance of the player */
    public Player player;
    /** Instance of the current game */
    public static Game game;
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
     * returns the width of the current window
     * @return Width of the current window in pixels
     */
    public static void setWidth(int newWidth) {
        width = newWidth;
    }
    /**
     * returns the height of the current window
     * @return Height of the current window in pixels
     */
    public static void setHeight(int newHeight) {
        height = newHeight;
    }
    /**
     * returns the dimensions of the current window as a Vector
     * @return Dimensions of the current window as a {@link Vector}
     */
    public static Vector getDims() {
        return dims;
    }
    /**
     * Overriden start method inherited from JavaFX's Application class.
     * This method is used to display the window of the game as well as to
     * control the size of the window and display scenes on it
     * @param window The window that the game is displayed on
     */
    @Override
    public void start(Stage window) throws FileNotFoundException {
        recreate(window);
    }
    /** Recreats the game instance */
    public static void recreate(Stage window) throws FileNotFoundException {
        game = new Game(window);
        Keyboard.init(game.getScene());
        game.playGame();
    }
    /**
     * Main method that runs at the start of the program
     * and creates a Game object and calls all of its methods
     * @param args Used to call launch
     */
    public static void main(String[] args) {
        launch(args);
    }
}