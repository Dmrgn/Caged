import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * <p>
 * This file acts as the class that initializes and creates the different levels, menu
 * splash screen and bosses in the game. It essentially ties every other class together
 * so that the driver can work more cleanly.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 18th, 2022
 * <p>
 * Ten minutes were spent by Ryan Atlas on this file on May 18th, 2022.
 * </p>
 */
public class Game {
    /** ArrayList of gameobjects in the current scene */
    private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    /** Current scene*/
    private Scene scene;
    /** Reference to the current window object */
    Stage window;
    /**
     * 
     */
    public Game(Stage w) {
        window = w;
        window.setTitle("Caged Inside the Mind");
        window.setMinWidth(Main.getWidth());
        window.setMinHeight(Main.getHeight());
        window.setResizable(false);
        GameObject player = attachObject(new Player(50,100));
        buildScene(new Group(player.getNode()));
        window.setScene(scene);
    }
    /**
     * Creates and initializes the different levels of the game
     * as they are needed.
     * @param level The Level to be created
     */
    public void createLevel(Level level) {
        
    }
    /**
     * Creates the two boss fights in the game which are designed to teach the
     * player to never give up, even when all hope seems lost
     * @param boss The boss whose fight is being created
     */
    public void createBossfight(Boss boss) {}
    /** 
     * Creates and displays the splash screen to the user
     */
    public void splashScreen() {}
    /**
     * Creates and displays the main menu to the user
     * @return The user's selection of options from the menu
     */
    public int mainMenu() {
        return 0;
    }
    /**
     * Adds the specified object to the game's list of objects
     */
    public GameObject attachObject(GameObject gameObject) {
        gameObjects.add(gameObject);
        return gameObject;
    }
    /**
     * Method that is active as long as the player is currently playing
     * the actual gameplay section of the game
     */
    public void playGame() {
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gameObjects.forEach((GameObject obj) -> {
                    obj.update();
                    obj.draw();
                });
            }
        };
        at.start();
        window.show();
    }
    /**
     * Returns the scene
     * @return The current scene
     */
    public Scene getScene() {
        return scene;
    }
    /**
     * Builds a scene with the specified group
     */
    public void buildScene(Group g) {
        scene = new Scene(g, 1280, 720, Color.WHITE);
    }
}