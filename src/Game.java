import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * <p>
 * This file acts as the class that initializes and creates the different levels, menu
 * splash screen and bosses in the game. It essentially ties every other class together
 * so that the driver can work more cleanly.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 18th, 2022
 * <p>
 * Ten minutes were spent by Ryan Atlas on this file on May 18th, 2022.
 * 2 hours were spent by Daniel Morgan on this file over May 27th and 28th, 2022.
 * 2 hours were spent by Daniel Morgan May 30th-June 3rd fixing collision and physics
 * 2 hours were by Ryan Atlas June 1st-3rd working on moving between screens and creating levels
 * 30 minutes were spent by Samuel Huang on June 3rd working on the animation timeline and splash screen/menu methods
 * 2 hours were spent by Ryan Atlas June 6-10th adjusted how levels are drawn, Level1 and Level2 methods which have were adjusted many times
 * 2 hours were spent by Samuel Huang on June 6-10th modifying code to make interaction work correctly
 * 3 hours were spent by Daniel Morgan June 6-10th making the camera, toScreen, toWorld and navigateLevelScreen methods
 * </p>
 */
public class Game {
    /** Camera zoom factor */
    public static final float ZOOM = 1.5f;
    /** If we are currently playing in debug mode */
    public static final boolean IS_DEBUG_MODE = true;
    /** Gravity applied to all moveable objects */
    public static final float GRAVITY = 0.055f;
    /** ArrayList of gameobjects in the current scene */
    public static ArrayList <GameObject> gameObjects = new ArrayList <> ();
    /** Current scene*/
    private static Scene scene;
    /** The instance of the player */
    public static GameObject player;
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
    /** Whether the object in the current level is picked up by the user*/
    public static boolean objectFound;
    /** Whether the all questions are answered correctly*/
    public static int questionsCorrect;
    /** Array of signs and whether each is read */
    public static boolean[] signsRead;
    /** Array of boolean check if the object task is complete for that stage */
    public static boolean[] stageObjectTask;
    /** Array of boolean check if the riddle task is complete for that stage */
    public static boolean[] stageRiddleTask;
    /** Array of boolean check if the main  task is complete for that stage */
    public static boolean[] stageMainTask;
    /** Level number */
    public static int levelNum;

    /** Programmatic representation of scene layers */
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
        window.setResizable(false);
        canOpenDoor = false;
        objectFound = false;
        questionsCorrect = 0;
        signsRead = new boolean[4];
        stageObjectTask = new boolean[3];
        stageRiddleTask = new boolean[3];
        stageMainTask = new boolean[3];
        // render the background, then midground, then foreground first
        w.widthProperty().addListener((obs, oldVal, newVal) -> {
            Main.setWidth(newVal.intValue());
        });
        w.heightProperty().addListener((obs, oldVal, newVal) -> {
            Main.setWidth(newVal.intValue());
        });
        // add layers to sceneGroup
        sceneGroup.getChildren().clear();
        gameObjects.clear();
        sceneGroup.getChildren().addAll(foreground, midground, background);
        // add sceneGroup to the window and create the scene
        buildScene(sceneGroup);
        // add a player and platform to the scene
        player = attachObject(new Player(-1000, 0), SceneLayer.FOREGROUND);
        levels = new Level[3];
        levels[0] = new Level1();
        levels[1] = new Level2();
        levels[2] = new Level3();
        levelNum = 1;
        createLevel(levels[levelNum-1]);
        Player.playerMoving = true;
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
    public static void navigateLevel(Level level, int screen, int teleporterLocationIndex) {
        int temp = levelNum;
        updateLevelScreen(level, screen);
        createLevel(level);
        if (temp != levelNum){
            canOpenDoor = false;
        }
        ArrayList<TeleportLocation> locations = new ArrayList<TeleportLocation>();
        for (GameObject obj : gameObjects) {
            if (obj instanceof TeleportLocation)
                locations.add((TeleportLocation)obj);
        }
        TeleportLocation loc = locations.get(teleporterLocationIndex);
        player = attachObject(new Player(loc.pos.x, loc.pos.y), SceneLayer.FOREGROUND);
        attachObject(((Player)player).hpBar, SceneLayer.FOREGROUND);
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
     * @param l The Level to be created
     */
    public static void createLevel(Level l) {
        try {
            ArrayList < GameObject > objects = l.getObjects();
            for (GameObject obj: objects) {
                attachObject(obj, SceneLayer.FOREGROUND);
            }
            level = l;
            if (l instanceof Level1){
                levelNum = 1;
                Game.questionsCorrect = 5;
            } else if (l instanceof Level2){
                levelNum = 2;
                //reset some game object variables
                Game.questionsCorrect = 0;
                canOpenDoor = false;
                objectFound = false;
            } else {
                levelNum = 3;
            }
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
//        Media menuTheme = new Media(new File("Caged Main Theme.mp3").toURI().toString());
//        mediaPlayer = new MediaPlayer(menuTheme);
//        mediaPlayer.setVolume(0.3);
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//        mediaPlayer.play();
        splash.runSplashScreen();
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
    public static boolean breakGame = false;
    public static void mainMenu() throws FileNotFoundException {
        MainMenu menu = new MainMenu(window);
        Instructions instructions = new Instructions(window);
        Credits credits = new Credits(window);
        BrotherTips tips = new BrotherTips();
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (breakGame) {
                    breakGame = false;
                    try {

                        Main.recreate(window);
                    } catch (FileNotFoundException e) {
                    }
                    this.stop();
                }
                if (menu.getSelection() == -1) {
                    window.setScene(menu.getScene());
                    menu.setSelection(0);
                } else if (menu.getSelection() == 1) {
                    if(tips.isStart()) {
                        Vector diff = cameraPos.mul(1);
                        cameraPos = player.pos.mul(-1f).add(Main.getDims().div(2)).add(new Vector(0, 50));
                        cameraPos = Vector.lerp(diff, cameraPos, 0.1f);
                        for (int i = 0; i < gameObjects.size(); i++) {
                            GameObject obj = gameObjects.get(i);
                            obj.clearTransformations();
                            obj.setScale(ZOOM, Main.getDims().div(2));
                            obj.setTranslate(cameraPos);
                            if (obj instanceof Background) {
                                obj.clearTransformations();
                                obj.setTranslate(cameraPos);
                            }
                            obj.update();
                            if (gameObjects.size() == 0) break;
                            obj.draw();
                        }
                        if (levelNum == 3) {
                            if (player.pos.y >= 3000) {
                                navigateLevel(level, level.levelScreen, 0);
                            }
                        } else {
                            if (player.pos.y >= 1500) {
                                navigateLevel(level, level.levelScreen, 0);
                            }
                        }
                    }
                    else {
                        tips.display();
                        window.setScene(tips.getScene());
                    }
                } else if (menu.getSelection() == 2) {
                    instructions.controlScreens();
                    window.setScene(instructions.getScene());
                    if (Keyboard.isKeyDown(KeyCode.H)) {
                        menu.setSelection(-1);
                    }
                } else if (menu.getSelection() == 3) {
                    credits.display();
                    window.setScene(credits.getScene());
                    if (Keyboard.isKeyDown(KeyCode.H)) {
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
    /**
     * Converts the Vector in screen coordinates to world coordinates
     * @param v The vector to transform
     * @return Transformed vector
     */
    public static Vector toWorld(Vector v) {
        Point2D p = new Point2D(0, 0);
        try {
            Scale scale = new Scale();
            scale.setPivotX(Main.getWidth()/2);
            scale.setPivotY(Main.getHeight()/2);
            scale.setX(ZOOM);
            scale.setY(ZOOM);
            p = scale.inverseTransform(new Point2D(v.x, v.y));
            Translate trans = new Translate();
            trans.setX(cameraPos.x);
            trans.setY(cameraPos.y);
            p = trans.inverseTransform(p);
        } catch (Exception e) {
        }
        return new Vector((float)p.getX(), (float)p.getY());
    }
    /**
     * Converts the Vector in world coordinates to screen coordinates
     * @param v The vector to transform
     * @return Transformed vector
     */
    public static Vector toScreen(Vector v) {
        Point2D p = new Point2D(0, 0);
        try {
            Scale scale = new Scale();
            scale.setPivotX(Main.getWidth()/2);
            scale.setPivotY(Main.getHeight()/2);
            scale.setX(ZOOM);
            scale.setY(ZOOM);
            p = scale.transform(new Point2D(v.x, v.y));
            Translate trans = new Translate();
            trans.setX(cameraPos.x);
            trans.setY(cameraPos.y);
            p = trans.transform(p);

        } catch (Exception e) {
        }
        return new Vector((float)p.getX(), (float)p.getY());
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
     * @param g The group to be added to the scene
     */
    public void buildScene(Group g) {
        scene = new Scene(g, Main.getWidth(), Main.getHeight(), Color.BLACK);
    }
}