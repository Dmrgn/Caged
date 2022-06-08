import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
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
    /** Camera zoom factor */
    private static final float ZOOM = 1.4f;
    /** If we are currently playing in debug mode */
    public static final boolean IS_DEBUG_MODE = true;
    /** Gravity applied to all moveable objects */
    public static final float GRAVITY = 0.055f;
    /** ArrayList of gameobjects in the current scene */
    private static ArrayList < GameObject > gameObjects = new ArrayList < GameObject > ();
    /** Current scene*/
    private static Scene scene;
    /** The instance of the player */
    private static GameObject player;
    /** Current group of scene layers: foreground/background/midground */
    private Group sceneGroup = new Group();
    /** Individual scene layers to contain rendered objects: foreground */
    private static Group foreground = new Group();
    /** Individual scene layers to contain rendered objects: midground*/
    private static Group midground = new Group();
    /** Individual scene layers to contain rendered objects: background */
    private static Group background = new Group();
    /** Array of levels in the game */
    private static Level[] levels;
    /** Game's current level*/
    private static Level level;
    /** The position of the camera */
    public static Vector cameraPos = new Vector(0, 0);
    /** Media player for music*/
    private MediaPlayer mediaPlayer;
    /** Whether the current door can be opened*/
    public static boolean canOpenDoor;
    /** Programatic representation of scene layers */
    public static enum SceneLayer {
        FOREGROUND,
        MIDGROUND,
        BACKGROUND
    }
    /** Reference to the current window object */
    public static Stage window;
    /**
     * Inits a game scene
     * @param w The window to use for the game
     */
    public Game(Stage w) {
        window = w;
        window.setTitle("Caged Inside the Mind");
        window.setMinWidth(Main.getWidth());
        window.setMinHeight(Main.getHeight());
        window.setResizable(true);
        canOpenDoor = false;
        // render the background, then midground, then foreground first
        //background.setViewOrder(1);
        //midground.setViewOrder(2);
        //foreground.setViewOrder(3);
        w.widthProperty().addListener((obs, oldVal, newVal) -> {
            Main.setWidth(newVal.intValue());
        });
        w.heightProperty().addListener((obs, oldVal, newVal) -> {
            Main.setWidth(newVal.intValue());
        });
        // add layers to sceneGroup
        sceneGroup.getChildren().addAll(foreground, midground, background);
        // add sceneGroup to the window and create the scene
        buildScene(sceneGroup);
        // add a player and platform to the scene
        player = attachObject(new Player(-1000, 0), SceneLayer.FOREGROUND);
        levels = new Level[1];
        levels[0] = new Level1();
        createLevel(levels[0]);
        // set the current scene
        window.setScene(scene);
    }
    /**
     * Tests if the passed object is touching any collidable objects in the scene
     * @param object1 The object being passed in to see if it is colliding
     * @return Whether an object is touching a collidable object
     */
    public static boolean touchingCollidable(CollidableObject object1) {
        for (GameObject object2: gameObjects) {
            if (object2 instanceof CollidableObject && object1 != object2) {
                if (CollidableObject.touching((CollidableObject) object2, object1)) {
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
        for (GameObject obj: gameObjects) {
            if (obj instanceof CollidableObject && obj != parent) {
                if (HitBox.areBoxesColliding(((CollidableObject) obj).getHitBox(), hitbox)) {
                    return true;
                }
            }
        };
        return false;
    }
    /**
     * Navigates the player to the next level or screen
     * @param level The level to navigate to
     * @param screen The screen within that level to navigate to
     */
    public static void navigateLevel(Level level, int screen) {
        updateLevelScreen(level, 1);
        createLevel(level);
        player = attachObject(new Player(250, Main.getHeight() - 200), SceneLayer.FOREGROUND);
        window.setScene(scene);
    }
    /**
     * Returns the first instance of an object with the same class as the passed object
     * @param obj The object with a class to specify
     * @return The first instance of an object with the same class
     */
    public static GameObject firstInstanceOfClass(GameObject obj) {
        for (GameObject child : gameObjects) {
            if (child.getClass().equals(obj.getClass())) {
                return child;
            }
        }
        return null;
    }
    /**
     * Returns the ith level from the Game class
     * @return The ith level from the Game class
     */
    public static Level getLevel(int index) {
        return levels[index];
    }
    /**
     * Creates and initializes the different levels of the game
     * as they are needed.
     * @param level The Level to be created
     */
    public static void createLevel(Level l) {
        try {
            ArrayList < GameObject > objects = l.getObjects();
            for (GameObject obj: objects) {
                attachObject(obj, SceneLayer.FOREGROUND);
            }
            level = l;
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
    public static void updateLevelScreen(Level level, int screen) {
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

    public void level1() {
        if (level instanceof Level1) {
            if (level.levelScreen == 0 && player.pos.x >= 4000) {
                updateLevelScreen(level, 1);
                createLevel(level);
                player = attachObject(new Player(250, Main.getHeight() - 200), SceneLayer.FOREGROUND);
                window.setScene(scene);
            } else if (level.levelScreen == 0 && player.pos.y >= 1500) {
                updateLevelScreen(level, 0);
                createLevel(level);
                player = attachObject(new Player(250, Main.getHeight() - 200), SceneLayer.FOREGROUND);
                window.setScene(scene);
            }
            if (level.levelScreen == 1 && player.pos.x <= 0) {
                updateLevelScreen(level, 0);
                createLevel(level);
                player = attachObject(new Player(1180, 250), SceneLayer.FOREGROUND);
                window.setScene(scene);
            } else if (level.levelScreen == 1 && player.pos.y >= 1500) {
                updateLevelScreen(level, 1);
                createLevel(level);
                player = attachObject(new Player(250, Main.getHeight() - 200), SceneLayer.FOREGROUND);
                window.setScene(scene);
            }
        }
    }
    /**
     * Adds the specified object to the game's list of objects
     * and attaches it to the specified {@link SceneLayer}
     * @param gameObject The GameObject to add to the scene
     * @param layer The layer to add the {@link GameObject} gameObject to
     * @return The added {@link GameObject}
     */
    public static GameObject attachObject(GameObject gameObject, SceneLayer layer) {
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
    public void mainMenu() throws FileNotFoundException {
        Rectangle left = new Rectangle();
        left.setFill(Color.BLACK);
        //left.setViewOrder(0);
        Rectangle top = new Rectangle();
        top.setFill(Color.BLACK);
        //top.setViewOrder(0);
        Rectangle right = new Rectangle();
        right.setFill(Color.BLACK);
        //right.setViewOrder(0);
        Rectangle bottom = new Rectangle();
        bottom.setFill(Color.BLACK);
        //bottom.setViewOrder(0);
        sceneGroup.getChildren().addAll(left, top, right, bottom);

        if (!IS_DEBUG_MODE) {
            splashScreen();
        }

        MainMenu menu = new MainMenu(window);
        Instructions instructions = new Instructions(window);
        Credits credits = new Credits(window);
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Vector diff = cameraPos.mul(1);
                cameraPos = player.pos.mul(-1f).add(Main.getDims().div(2)).add(new Vector(0, 50));
                cameraPos = Vector.lerp(diff, cameraPos, 0.1f);
                int padding = 400;
                for (GameObject obj: gameObjects) {
                    if (obj instanceof Mold) {
                        ImageView imageView = (ImageView) obj.getNode();
                        double w = imageView.getImage().getWidth(), h = imageView.getImage().getHeight();

                        // left curtain
                        left.setX(obj.pos.x - padding);
                        left.setY(obj.pos.y - padding);
                        left.setWidth(padding);
                        left.setHeight(h + padding * 2);
                        // top curtain
                        top.setX(obj.pos.x - padding);
                        top.setY(obj.pos.y - padding);
                        top.setWidth(w + padding * 2);
                        top.setHeight(padding);
                        // right curtain
                        right.setX(obj.pos.x + w);
                        right.setY(obj.pos.y - padding);
                        right.setWidth(padding);
                        right.setHeight(h + padding * 2);
                        // bottom curtain
                        bottom.setX(obj.pos.x - padding);
                        bottom.setY(obj.pos.y + h);
                        bottom.setWidth(w + padding * 2);
                        bottom.setHeight(padding);

                        left.getTransforms().clear();
                        top.getTransforms().clear();
                        right.getTransforms().clear();
                        bottom.getTransforms().clear();

                        Scale sca = new Scale();
                        sca.setX(ZOOM);
                        sca.setY(ZOOM);
                        sca.setPivotX(Main.getDims().div(2).x);
                        sca.setPivotY(Main.getDims().div(2).y);
                        Translate trans = new Translate();
                        trans.setX(cameraPos.x);
                        trans.setY(cameraPos.y);
                        left.getTransforms().addAll(sca, trans);
                        top.getTransforms().addAll(sca, trans);
                        right.getTransforms().addAll(sca, trans);
                        bottom.getTransforms().addAll(sca, trans);
                    }
                    obj.clearTransformations();
                    obj.setScale(ZOOM, Main.getDims().div(2));
                    obj.setTranslate(cameraPos);
                    if (obj instanceof Background) {
                        obj.clearTransformations();
                        obj.setTranslate(cameraPos);
                    }
                    obj.update();
                    obj.draw();
                }
                if (menu.getSelection() == -1) {
                    window.setScene(menu.getScene());
                    menu.setSelection(0);
                } else if (menu.getSelection() == 0) {} else if (menu.getSelection() == 1) {
                    level1();
                } else if (menu.getSelection() == 2) {
                    instructions.controlScreens();
                    window.setScene(instructions.getScene());
                    if (Keyboard.isKeyDown(KeyCode.H)) {
                        System.out.println("Works");
                        menu.setSelection(-1);
                    }
                } else if (menu.getSelection() == 3) {
                    credits.display();
                    window.setScene(credits.getScene());
                    if (Keyboard.isKeyDown(KeyCode.H)) {
                        System.out.println("Works");
                        menu.setSelection(-1);
                    }
                }
            }
        };
        AnimationTimer menuTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                menu.checkMenu(this, at);
            }
        };
        menu.display();
        window.setScene(menu.getScene());
        menuTimer.start();
        System.out.println("hi");
    }
    /**
     * Method that is active as long as the player is currently playing
     * the actual gameplay section of the game
     * @throws FileNotFoundException For splashScreen
     */
    public void playGame() throws FileNotFoundException {
        if (!IS_DEBUG_MODE) {
            splashScreen();
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(IS_DEBUG_MODE ? 10 : 18000), ev -> {
            try {
                mainMenu();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }));
        timeline.play();
        window.show();
    }
    /** Converts the Vector in screen coordinates to world coordinates */
    public static Vector toWorld(Vector v) {
        return v.sub(cameraPos);
    }
    /** Converts the Vector in world coordinates to screen coordinates */
    public static Vector toScreen(Vector v) {
        return v.add(cameraPos);
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