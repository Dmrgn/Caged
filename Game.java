import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.*;
import java.io.File;
/**
 * <p>
 * This file acts as the class that initializes and creates the different levels, menu
 * splash screen and bosses in the game. It essentially ties every other class together
 * so that the driver can work more cleanly.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 3.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 18th, 2022
 * <p>
 * Ten minutes were spent by Ryan Atlas on this file on May 18th, 2022.
 * 2 hours were spent by Daniel Morgan on this file over May 27th and 28th, 2022.
 * 2 hours were spent by Daniel Morgan May 30th-June 3rd fixing collision and physics
 * 2 hours were by Ryan Atlas June 1st-3rd working on moving between screens and creating levels
 * 30 minutes were spent by Samuel Huang on June 3rd working on the animation timeline and splash screen/menu methods
 * </p>
 */
public class Game {
    /** Gravity applied to all moveable objects */
    public static final float GRAVITY = 0.08f;
    /** ArrayList of gameobjects in the current scene */
    private static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    /** Current scene*/
    private Scene scene;
    /** The instance of the player */
    private GameObject player;
    /** Current group of scene layers: foreground/background/midground */
    private Group sceneGroup = new Group();
    /** Individual scene layers to contain rendered objects: foreground */
    private Group foreground = new Group();
    /** Individual scene layers to contain rendered objects: midground*/
    private Group midground = new Group();
    /** Individual scene layers to contain rendered objects: background */
    private Group background = new Group();
    /** Game's current level*/
    private Level level;
    /** Media player for music*/
    private MediaPlayer mediaPlayer;
    /** Programatic representation of scene layers */
    public static enum SceneLayer {
        FOREGROUND,
        MIDGROUND,
        BACKGROUND
    }
    /** Reference to the current window object */
    public Stage window;
    /**
     * Inits a game scene
     * @param w The window to use for the game
     */
    public Game(Stage w) {
        window = w;
        window.setTitle("Caged Inside the Mind");
        window.setMinWidth(Main.getWidth());
        window.setMinHeight(Main.getHeight());
        window.setResizable(false);
        // render the background, then midground, then foreground first
        //background.setViewOrder(0);
        //midground.setViewOrder(1);
        //foreground.setViewOrder(2);
        // add layers to sceneGroup
        sceneGroup.getChildren().addAll(foreground, midground, background);
        // add sceneGroup to the window and create the scene
        buildScene(sceneGroup);
        // add a player and platform to the scene
        player = attachObject(new Player(50,Main.getHeight()-200), SceneLayer.FOREGROUND);
        Level level1 = new Level1();
        createLevel(level1);
        //GameObject platform = attachObject(new Platform("assets/platform.png",50,Main.getHeight()-100), SceneLayer.FOREGROUND);
        // set the current scene
        window.setScene(scene);
    }
    /**
     * Tests if the passed object is touching any collidable objects in the scene
     * @param object1 The object being passed in to see if it is colliding
     * @return Whether an object is touching a collidable object
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
     * @param parent Original object being checked
     * @param hitbox Hitbox being checked
     * @return Whether the hitbox is touching a collidable
     */
    public static boolean touchingCollidable(GameObject parent, HitBox hitbox) {
        for (GameObject obj : gameObjects) {
            if (obj instanceof CollidableObject && obj != parent) {
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
            this.level = level;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the file at the index of the files array in the Level class and loads all the new
     * GameObjects while clearing old ones
     * @param level Current level
     * @param screen Screen number to read the right file
     */
    public void updateLevelScreen(Level level, int screen){
        level.levelScreen = screen;
        foreground.getChildren().clear();
        gameObjects.clear();

    }
    /**
     * Creates the two boss fights in the game which are designed to teach the
     * player to never give up, even when all hope seems lost
     * @param boss The boss whose fight is being created
     */
    public void createBossfight(Boss boss) {}
    /**
     * Creates and displays the splash screen to the user
     * @throws FileNotFoundException In case the files cannot be found
     */


    public void splashScreen() throws FileNotFoundException {
        SplashScreen splash = new SplashScreen(window);
        Media menuTheme = new Media(new File("Caged Main Theme.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(menuTheme);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        splash.runSplashScreen();
    }
    /**
     * Adds the specified object to the game's list of objects
     * and attaches it to the specified {@link SceneLayer}
     * @param gameObject The GameObject to add to the scene
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
     * @throws FileNotFoundException For splashScreen
     */
    public void playGame() throws FileNotFoundException{
        splashScreen();
        MainMenu menu = new MainMenu(window);
        //Instructions instructions = new Instructions(window);
        //Credits credits = new Credits(window);
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (GameObject obj: gameObjects){
                    obj.update();
                    obj.draw();
                }
                if(menu.getSelection() == 1) {
                    if (level instanceof Level1) {
                        if (level.levelScreen == 0 && player.pos.x >= 1280) {
                            updateLevelScreen(level, 1);
                            createLevel(level);
                            player = attachObject(new Player(250, Main.getHeight() - 200), SceneLayer.FOREGROUND);
                            window.setScene(scene);
                        } else if (level.levelScreen == 0 && player.pos.y >= 720) {
                            createLevel(level);
                            player = attachObject(new Player(250, Main.getHeight() - 200), SceneLayer.FOREGROUND);
                            window.setScene(scene);
                        }
                        if (level.levelScreen == 1 && player.pos.x <= 0) {
                            updateLevelScreen(level, 0);
                            createLevel(level);
                            player = attachObject(new Player(1180, 250), SceneLayer.FOREGROUND);
                            window.setScene(scene);
                        } else if (level.levelScreen == 1 && player.pos.y >= 720) {
                            createLevel(level);
                            player = attachObject(new Player(250, Main.getHeight() - 200), SceneLayer.FOREGROUND);
                            window.setScene(scene);
                        }
                    }
                } else if (menu.getSelection() == 2) {
                    System.out.println("Display Instructions");
                }
                else if(menu.getSelection() == 3) {
                    System.out.println("Display Credits");
                }
            }
        };
        AnimationTimer menuTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                menu.checkSelections(this, at);
            }
        };

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(18000), ev -> {
            menu.display();
            menuTimer.start();
        }));
        timeline.play();
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