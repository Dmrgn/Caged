import java.io.IOException;
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
 * 2 hours were spent by Daniel Morgan on this file over May 27th and 28th, 2022.
 * </p>
 */
public class Game {
    /** Gravity applied to all moveable objects */
    public static final float GRAVITY = 0.01f;
    /** ArrayList of gameobjects in the current scene */
    private static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    /** Current scene*/
    private Scene scene;
    /** Current group of scene layers: foreground/background/midground */
    private Group sceneGroup = new Group();
    /** Individual scene layers to contain rendered objects */
    private Group foreground = new Group();
    private Group midground = new Group();
    private Group background = new Group();
    /** Game's current level*/
    private int level;
    /** Programatic representation of scene layers */
    public static enum SceneLayer {
        FOREGROUND,
        MIDGROUND,
        BACKGROUND
    }
    /** Reference to the current window object */
    Stage window;
    /**
     * Inits a game scene
     * @param w The window to use for the game
     */
    public Game(Stage w) {
        window = w;
        level = 1;
        window.setTitle("Caged Inside the Mind");
        window.setMinWidth(Main.getWidth());
        window.setMinHeight(Main.getHeight());
        window.setResizable(false);
        // render the background, then midground, then foreground first
        background.setViewOrder(0);
        midground.setViewOrder(1);
        foreground.setViewOrder(2);
        // add layers to sceneGroup
        sceneGroup.getChildren().addAll(foreground, midground, background);
        // add sceneGroup to the window and create the scene
        buildScene(sceneGroup);
        // add a player and platform to the scene
        GameObject player = attachObject(new Player(50,Main.getHeight()-200), SceneLayer.FOREGROUND);
        Level level1 = new Level1();
        createLevel(level1);
        //GameObject platform = attachObject(new Platform("assets/platform.png",50,Main.getHeight()-100), SceneLayer.FOREGROUND);
        // set the current scene
        window.setScene(scene);
    }
    /**
     * Tests if the passed object is touching any collidable objects in the scene
     * @param object1
     * @return
     */
    public static boolean touchingCollidable(CollidableObject object1) {
        for (GameObject object2 : gameObjects) {
            if (object2 instanceof CollidableObject && object1 != object2) {
                if (CollidableObject.touching((CollidableObject)object2, object1)) {
                    return true;
                }
            }
        };
        return false;
    }
    /**
     * Tests if the passed hitbox is touching any collidable objects in the scene
     * @param object1
     * @return
     */
    public static boolean touchingCollidable(HitBox hitbox) {
        for (GameObject obj : gameObjects) {
            if (obj instanceof CollidableObject) {
                if (HitBox.areBoxesColliding(((CollidableObject)obj).getHitBox(), hitbox)) {
                    return true;
                }
            }
        };
        return false;
    }
    /**
     * Creates and initializes the different levels of the game
     * as they are needed.
     * @param level The Level to be created
     */
    public void createLevel(Level level) {
        try {
            ArrayList<GameObject> objects = level.getObjects();
            for (GameObject obj : objects) {
                attachObject(obj, SceneLayer.FOREGROUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateLevelScreen(Level level){
        level.levelScreen++;
        foreground.getChildren().clear();        
        createLevel(level);
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
     * and attaches it to the specified {@link SceneLayer}
     * @param gameObject The gameobject to add to the scene
     * @param layer The layer to add the {@link GameObject} gameObject to
     * @return The added {@link GameObject}
     */
    public GameObject attachObject(GameObject gameObject, SceneLayer layer) {
        gameObjects.add(gameObject);
        switch (layer) {
            case FOREGROUND:
                foreground.getChildren().add(gameObject.getNode());
                break;
            case MIDGROUND:
                midground.getChildren().add(gameObject.getNode());
                break;
            case BACKGROUND:
                background.getChildren().add(gameObject.getNode());
                break;
        }
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
                for (GameObject obj: gameObjects){
                    obj.update();
                    obj.draw();
                }
                if (player.pos.x >= 1280) {
                    updateLevelScreen(level);
                    player = attachObject(new Player(250,Main.getHeight()-200), SceneLayer.FOREGROUND);
                    window.setScene(scene);
                } else if (player.pos.y >= 720) {
                    createLevel(level);
                    player = attachObject(new Player(250,Main.getHeight()-200), SceneLayer.FOREGROUND);
                    window.setScene(scene);
                }
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
        scene = new Scene(g, 1280, 720, Color.BLACK);
    }
}