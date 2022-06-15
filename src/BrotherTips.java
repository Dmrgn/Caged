import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 * <p>
 * This class contains code for the BrotherTips Transition Screen
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 19th, 2022
 * <p>
 * File was created by Samuel Huang on May 19th, 2022.
 * Samuel Huang spent 10 minutes on this file.
 * Samuel Huang spent 30 minutes on this file June 10th.
 * </p>
 */
public class BrotherTips {
    /** This Image variable stores the current Transition page. */
    private ImageView screen;
    /** Current scene*/
    private Scene scene;
    /** Whether stared*/
    private boolean start;
    /**
     * The constructor of the BrotherTips Class. Inits stuff
     */
    public BrotherTips() {
        screen = new ImageView(new Image("assets/doors/BrotherTips1.png"));
        start = false;
    }
    /**
     * This method gets called to display the tips screen
     *
     */
    boolean hasShown = false;
    /**
     * Display method to display the screen
     */
    public void display() {
        //Setting the position of the  image:
        //Creating a scene object
        screen.setX(0);
        screen.setY(0);
        screen.setFitWidth(1266);
        screen.setFitHeight(720);
        screen.setPreserveRatio(true);
        hasShown = true;
        Group root = new Group(screen);
        scene = new Scene(root);
        Keyboard.init(scene);
        if(Keyboard.isKeyDown(KeyCode.H))
        {
            start = true;
        }
    }
    public Scene getScene()
    {
        return scene;
    }

    public boolean isStart()
    {
        return start;
    }
}