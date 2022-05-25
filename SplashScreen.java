import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.*;
/**
 * <p>
 * This class contains code for the SplashScreen
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 19th, 2022
 * <p>
 * File was created by Samuel Huang on May 19th, 2022.
 * Samuel Huang spent 10 minutes on this file.
 * </p>
 */
public class SplashScreen {

    private Stage stage;
    /** This Image variable stores the logo image. */
    private Image logo;
    /** This Image variable stores the cage image. */
    private Image cage;
    /** This Image array variable stores all the text images for the Logo Page Animation. */
    private Image[] text1;
    /** This Image array variable stores all the text images for the Game Name Page Animation. */
    private Image[] text2;
        
    private ImageView imageViewLogo; 

    /**
     * The constructor of the Splash Screen Class.
     * Images are initialized to then be used in the splash screen.
     */
    public SplashScreen(Stage stage)  {
       this.stage = stage; 
       
    }
    public void importImages() throws FileNotFoundException
    {
       logo = new Image(new FileInputStream("C:\\Users\\SamuelH\\Documents\\ICS 12\\ISP\\Caged-master\\ISP Splash Screen Images\\Logo.png")); 
       imageViewLogo = new ImageView(logo); 
    }

    /**
     * This method gets called to display and animate the company name and logo portion of the splash screen
     *
     */
    public void animateCompName() throws FileNotFoundException {
      importImages();
      //Setting the position of the image logo 
      imageViewLogo.setX(200); 
      imageViewLogo.setY(250);
      //setting the fit height and width of the image view 
      imageViewLogo.setFitHeight(250); 
      imageViewLogo.setFitWidth(250); 
      //Setting the preserve ratio of the image view 
      imageViewLogo.setPreserveRatio(true);      
      //Creating a Group object  
      Group root = new Group(imageViewLogo);  
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 500);  
      stage.setScene(scene);
      //stage.show(); 
      
    }
    
    /**
     * This method gets called to display and animate the transition animation from the company name to the logo.
     *
     */
    public void transition() {

    }
    /**
     * This method gets called to display and animate the game name of the splash screen
     *
     */
    public void animateGameName() {

    }

}