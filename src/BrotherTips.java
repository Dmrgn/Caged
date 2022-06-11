import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * <p>
 * This class contains code for the BrotherTips Transition Screen
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 3.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 19th, 2022
 * <p>
 * File was created by Samuel Huang on May 19th, 2022.
 * Samuel Huang spent 10 minutes on this file.
 * </p>
 */
public class BrotherTips {
    /** This Image variable stores the current Transition page. */
    private ImageView screen;

    private Scene scene;
    /**
     * The constructor of the BrotherTips Class.
     * @param page is used to take in the current Image used for the certain transition
     */
    public BrotherTips(Image page) {
        screen = new ImageView(page);
    }
    /**
     * This method gets called to display the tips screen
     *
     */
    public void display() {
        //Setting the position of the  image:
        screen.setX(0);
        screen.setY(0);
        screen.setFitWidth(1266);
        screen.setFitHeight(720);
        screen.setPreserveRatio(true);
        Group root = new Group(screen);
        //Creating a scene object
        scene = new Scene(root, Main.getWidth(), Main.getHeight());
        Keyboard.init(scene);
    }
    public Scene getScene()
    {
        return scene;
    }



}