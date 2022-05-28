import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.animation.*;
import javafx.util.*;
import javafx.scene.shape.*;
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
 * Samuel Huang spent 3 hours on this file.
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
    /** This Image variable stores the logo image. */
    private Image cage;
    private ImageView imageViewCage;
    
    private boolean done = false;
   /**
     * The constructor of the Splash Screen Class.
     * Images are initialized to then be used in the splash screen.
     */
    public SplashScreen(Stage stage)  {
       this.stage = stage; 
       screen1Text = new Image[2];
       imageViewScreen1Text = new ImageView[2];
    }
    private void importImages() throws FileNotFoundException
    {
       logo = new Image(new FileInputStream("ISP Splash Screen Images\\Logo.png")); 
       imageViewLogo = new ImageView(logo);
       cage = new Image(new FileInputStream("ISP Splash Screen Images\\Cage.png")); 
       imageViewCage = new ImageView(cage);
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
      //Setting the position of the image LOGO:
      imageViewLogo.setX(450); 
      imageViewLogo.setY(-350);
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
      translateCompName.setDuration(Duration.millis(2000));
      translateCompName.setByY(250);
      //Translates the logo
      TranslateTransition translateLogo = new TranslateTransition();
      translateLogo.setNode(imageViewLogo);
      translateLogo.setDuration(Duration.millis(2000));
      translateLogo.setByY(500);
      scene.setFill(background);
      stage.setScene(scene);
      //Fades in Presents
      FadeTransition presents = new FadeTransition(Duration.millis(2000), imageViewScreen1Text[1]);
      presents.setFromValue(0);
      presents.setToValue(1);
      //Plays all animations in order with a time in between
      SequentialTransition transitionPart = new SequentialTransition (translateCompName,new PauseTransition(Duration.millis(1000)),translateLogo,  
         new PauseTransition(Duration.millis(1000)),presents);  //8 seconds
      transitionPart.play();  
      
    }
    
    /**
     * This method gets called to display and animate the transition animation from the company name to the logo.
     *
     */
    public void transition() throws FileNotFoundException{
      //Fade out everything
      FadeTransition logoFade = new FadeTransition(Duration.millis(1000), imageViewLogo);
      FadeTransition word1Fade = new FadeTransition(Duration.millis(1000), imageViewScreen1Text[0]);
      FadeTransition word2Fade = new FadeTransition(Duration.millis(1000), imageViewScreen1Text[1]);
      logoFade.setToValue(0);
      word1Fade.setToValue(0);
      word2Fade.setToValue(0);
      SequentialTransition transitionPart = new SequentialTransition (new PauseTransition(Duration.millis(9000)), word1Fade, logoFade, word2Fade);  //9+3 = 12 seconds
      transitionPart.play();
      
    }
    /**
     * This method gets called to display and animate the game name of the splash screen
     *
     */
    public void animateGameName() throws FileNotFoundException {
      //Text 1
      Text title = new Text("Caged Inside the Mind");
      title.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 62));
      title.setFill(Color.WHITE);
      title.setX(350);
      title.setY(100);
      //Add Cage
      imageViewCage.setX(500); 
      imageViewCage.setY(150);
      imageViewCage.setFitHeight(350); 
      imageViewCage.setFitWidth(350); 
      imageViewCage.setPreserveRatio(true);
      //Text bottom
      Text continueText = new Text("Please Hold Tight While the Game Loads...");
      continueText.setFont(Font.font ("Times New Roman", 36));
      continueText.setFill(Color.WHITE);
      continueText.setX(360);
      continueText.setY(600);
      //Rectangle Background
      Rectangle background = new Rectangle(0, 0, 1280, 720);
      background.setFill(Color.BLACK);
      //Creating a Group object  
      Group root = new Group(background, title, continueText, imageViewCage); 
      //Creating a scene object 
      Scene scene = new Scene(root, 1260, 680); 
      /*
      Timer timer = new Timer();
            TimerTask screen2 = new TimerTask() {
                @Override
                public void run() {
                     stage.setScene(scene);
                }
            };
            timer.schedule(screen2, 13000L);  
            */
      RadialGradient backgroundGradient = new RadialGradient(0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
            new Stop(0, Color.WHITE),
            new Stop(1, Color.BLACK)
      }); 
      scene.setFill(backgroundGradient);
      Timeline timeline = new Timeline(new KeyFrame(Duration.millis(12000), ev -> {
        stage.setScene(scene);
        System.out.println("hi");
       }));
       timeline.play();
      //animations
      FadeTransition titleFade = new FadeTransition(Duration.millis(1000), title);
      FadeTransition blackScreen = new FadeTransition(Duration.millis(2000), background);
      FadeTransition cageFade = new FadeTransition(Duration.millis(1000), imageViewCage);
      FadeTransition continueFade = new FadeTransition(Duration.millis(1000), continueText);
      blackScreen.setFromValue(0);
      blackScreen.setToValue(1);
      titleFade.setFromValue(0);
      titleFade.setToValue(1);
      cageFade.setFromValue(0);
      cageFade.setToValue(1);
      continueFade.setFromValue(0);
      continueFade.setToValue(1);
      SequentialTransition transitionParts = new SequentialTransition (new PauseTransition(Duration.millis(13000)), blackScreen, titleFade, cageFade, continueFade);  
      transitionParts.play();
    }
    
    public void runSplashScreen() throws FileNotFoundException
    {
       importImages();
       animateCompName();
       transition();
       animateGameName();
     }
}