import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * <p>
 * This class contains code for the Credits Screen
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 19th, 2022
 * <p>
 * File was created by Samuel Huang on May 19th, 2022.
 * Samuel Huang spent 30 minutes on this file.
 * </p>
 */
public class Credits {
    /** This ImageView variable stores the credits page. */
    private ImageView creditScreen;
    /** Current window */
    private Stage window;
    /** Current scene */
    private Scene scene;
    /**
     * The constructor of the Credits Class.
     * @param window Window to display screen to
     * @throws FileNotFoundException In case file cannot be found it must be thrown (should never occur)
     */
    public Credits(Stage window) throws FileNotFoundException
    {
        creditScreen = new ImageView( new Image(new FileInputStream("Page Screens/CreditsScreen.png")));//add file first
        this.window = window;
    }
    /**
     * This method gets called to display the credits screen
     *
     */
    public void display()  {
        //Setting the position of the  image:
        creditScreen.setX(0);
        creditScreen.setY(0);
        creditScreen.setFitWidth(1266);
        creditScreen.setFitHeight(720);
        creditScreen.setPreserveRatio(true);
        Group root = new Group(creditScreen);
        //Creating a scene object
        scene = new Scene(root, Main.getWidth(), Main.getHeight());
        //window.setScene(scene);
        Keyboard.init(scene);
    }
    /**
     * Getter method for the scene instance variable
     * @return The game scene
     */
    public Scene getScene()
    {
        return scene;
    }
}