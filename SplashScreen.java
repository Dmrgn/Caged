import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.*;
import javafx.scene.paint.*;

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
    /** This Image variable stores the cage image. */
    private Image cage;
    private ImageView imageViewCage; 
    /** This Image array variable stores all the text images for the Logo Page Animation. */
    private Image[] screen1Text;
    private ImageView[] imageViewScreen1Text;
    /** This Image array variable stores all the text images for the Game Name Page Animation. */
    private Image[] screen2Text;
    private ImageView[] imageViewScreen2Text;

    /**
     * The constructor of the Splash Screen Class.
     * Images are initialized to then be used in the splash screen.
     */
    public SplashScreen(Stage stage)  {
       this.stage = stage; 
       screen1Text = new Image[2];
       screen2Text = new Image[5];
       imageViewScreen1Text = new ImageView[2];
       imageViewScreen2Text = new ImageView[5];
       
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
    private void importImagesS2() throws FileNotFoundException
    {
       cage = new Image(new FileInputStream("ISP Splash Screen Images\\Cage.png")); 
       imageViewCage = new ImageView(cage);
       screen2Text[0] = new Image(new FileInputStream("ISP Splash Screen Images\\Check\\Caged.png"));
       screen2Text[1] = new Image(new FileInputStream("ISP Splash Screen Images\\Check\\Inside.png"));
       screen2Text[2] = new Image(new FileInputStream("ISP Splash Screen Images\\Check\\The.png")); 
       screen2Text[3] = new Image(new FileInputStream("ISP Splash Screen Images\\Check\\Mind.png"));
       screen2Text[4] = new Image(new FileInputStream("ISP Splash Screen Images\\Continue.png"));
       imageViewScreen2Text[0] = new ImageView(screen2Text[0]);
       imageViewScreen2Text[1] = new ImageView(screen2Text[1]);
       imageViewScreen2Text[2] = new ImageView(screen2Text[2]);
       imageViewScreen2Text[3] = new ImageView(screen2Text[3]);
       imageViewScreen2Text[4] = new ImageView(screen2Text[4]);
    }
    

    /**
     * This method gets called to display and animate the company name and logo portion of the splash screen
     *
     */
    public void animateCompName() throws FileNotFoundException {
      importImagesS1();
      //Setting the position of the image LOGO:
      imageViewLogo.setX(450); 
      imageViewLogo.setY(150);
      imageViewLogo.setFitHeight(350); 
      imageViewLogo.setFitWidth(350); 
      imageViewLogo.setPreserveRatio(true);
      //Setting the position of textImage1
      imageViewScreen1Text[0].setX(460);
      imageViewScreen1Text[0].setY(100);
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
      importImagesS2();
      //Setting the position of the image LOGO:
      imageViewCage.setX(450); 
      imageViewCage.setY(150);
      imageViewCage.setFitHeight(350); 
      imageViewCage.setFitWidth(350); 
      imageViewCage.setPreserveRatio(true);
      //Setting the position of textImage
      imageViewScreen2Text[0].setX(250);
      imageViewScreen2Text[0].setY(100);
      imageViewScreen2Text[0].setFitHeight(175); 
      imageViewScreen2Text[0].setFitWidth(150); 
      imageViewScreen2Text[0].setPreserveRatio(true);      
      //Setting the position of textImage2
      imageViewScreen2Text[1].setX(425);
      imageViewScreen2Text[1].setY(100);
      imageViewScreen2Text[1].setFitHeight(175); 
      imageViewScreen2Text[1].setFitWidth(150); 
      imageViewScreen2Text[1].setPreserveRatio(true);
      //Setting the position of textImage3
      imageViewScreen2Text[2].setX(600);
      imageViewScreen2Text[2].setY(100);
      imageViewScreen2Text[2].setFitHeight(175); 
      imageViewScreen2Text[2].setFitWidth(150); 
      imageViewScreen2Text[2].setPreserveRatio(true);
      //Setting the position of textImage3
      imageViewScreen2Text[3].setX(775);
      imageViewScreen2Text[3].setY(100);
      imageViewScreen2Text[3].setFitHeight(175); 
      imageViewScreen2Text[3].setFitWidth(150); 
      imageViewScreen2Text[3].setPreserveRatio(true);
      //Creating a Group object  
      Group root = new Group(imageViewCage, imageViewScreen2Text[0], imageViewScreen2Text[1], imageViewScreen2Text[2], imageViewScreen2Text[3]); 
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 500); 
      stage.setScene(scene);
    }

}