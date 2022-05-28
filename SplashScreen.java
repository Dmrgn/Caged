import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.animation.*;
import javafx.util.*;
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
    private ImageView imageViewLogo; 
    /** This Image array variable stores all the text images for the Logo Page Animation. */
    private Image[] screen1Text;
    private ImageView[] imageViewScreen1Text;
    /**
     * The constructor of the Splash Screen Class.
     * Images are initialized to then be used in the splash screen.
     */
    public SplashScreen(Stage stage)  {
       this.stage = stage; 
       screen1Text = new Image[2];
       imageViewScreen1Text = new ImageView[2];
    }
    private void importImagesS1() throws FileNotFoundException
    {
       logo = new Image(new FileInputStream("ISP Splash Screen Images\\Logo.png")); 
       imageViewLogo = new ImageView(logo);
       screen1Text[0] = new Image(new FileInputStream("ISP Splash Screen Images\\CompanyName.png"));
       screen1Text[1] = new Image(new FileInputStream("ISP Splash Screen Images\\Presents.png"));
       imageViewScreen1Text[0] = new ImageView(screen1Text[0]);
       imageViewScreen1Text[1] = new ImageView(screen1Text[1]);
    }
    /**
     * This method gets called to display and animate the company name and logo portion of the splash screen
     *
     */
    public void animateCompName() throws FileNotFoundException {
      importImagesS1();
      //Setting the position of the image LOGO:
      imageViewLogo.setX(450); 
      imageViewLogo.setY(-250);
      imageViewLogo.setFitHeight(350); 
      imageViewLogo.setFitWidth(350); 
      imageViewLogo.setPreserveRatio(true);
      //Setting the position of textImage1
      imageViewScreen1Text[0].setX(460);
      imageViewScreen1Text[0].setY(-150);
      imageViewScreen1Text[0].setFitHeight(125); 
      imageViewScreen1Text[0].setFitWidth(350); 
      imageViewScreen1Text[0].setPreserveRatio(true);      
      //Setting the position of textImage1
      imageViewScreen1Text[1].setX(545);
      imageViewScreen1Text[1].setY(525);
      imageViewScreen1Text[1].setFitHeight(40); 
      imageViewScreen1Text[1].setFitWidth(180); 
      imageViewScreen1Text[1].setPreserveRatio(true);
      //Creating a Group object  
      Group root = new Group(imageViewLogo, imageViewScreen1Text[0], imageViewScreen1Text[1]);  
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 500); 
      RadialGradient background = new RadialGradient(0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
            new Stop(0, Color.WHITE),
            new Stop(1, Color.BLACK)
      }); 
      TranslateTransition translateCompName = new TranslateTransition();
      translateCompName.setNode(imageViewScreen1Text[0]);
      translateCompName.setDuration(Duration.millis(1000));
      translateCompName.setByY(250);
      translateCompName.play();
      //Translates the logo
      TranslateTransition translateLogo = new TranslateTransition();
      translateLogo.setNode(imageViewLogo);
      translateLogo.setDuration(Duration.millis(2000));
      translateLogo.setByY(400);
      translateLogo.play();
      scene.setFill(background);
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
    public void animateGameName() throws FileNotFoundException {
      Text title = new Text("Caged Inside the Mind");
      title.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 62));
      title.setFill(Color.WHITE);
      title.setX(350);
      title.setY(100);
      Text continueText = new Text("Press any key to continue...");
      continueText.setFont(Font.font ("Times New Roman", 36));
      continueText.setFill(Color.WHITE);
      continueText.setX(450);
      continueText.setY(600);
      //Creating a Group object  
      Group root = new Group(title, continueText); 
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 500); 
      scene.setFill(Color.BLACK);
      stage.setScene(scene);
    }

}