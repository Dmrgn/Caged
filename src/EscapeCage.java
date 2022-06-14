import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 * <p>
 * This class contains data about the interactable signs in the game that help
 * teach and quiz the player
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since June 13th, 2022
 * <p>
 * File was created by Samuel Huang June 13th, 2022. 1 hour spent. Comments were added, variables created,
 * the constructor was made and the getNode(), draw(), inRange(), display() and update() methods were added
 * </p>
 */
public class EscapeCage extends CollidableObject implements Interactable{

    /** JavaFX node for the platform*/
    private Node node;
    /** Image for the door's locked texture */
    private Image imageLocked;
    /** Image for the door's locked texture */
    private Image imageOpen;
    /** Image for the scenario after opening the cage texture */
    private Image scenario;
    /** Whether the door is locked */
    private boolean isLocked;
    /** Vector for the normal position of the door */
    private Vector normalPos;
    private int level;
    private boolean answered;

    private int userAnswer;
    private int answer;

    private boolean accessing;

    private boolean answeredCorrectly;

    public EscapeCage(Image scenario, int x, int y, int level, int answer)
    {
        isLocked = true;
        imageLocked = new Image("assets/doors/cageClosed.png");
        imageOpen = new Image("assets/doors/cageOpen.png");;
        this.scenario = scenario;
        node = new ImageView(imageLocked);
        pos = new Vector(x, y);
        normalPos = new Vector(x, y);
        createHitBox(pos.add(new Vector(0, 0)), pos.add(new Vector((float)imageLocked.getWidth(), (float)imageOpen.getHeight())));
        this.level = level;
        answered = false;
        userAnswer = 0;
        answeredCorrectly = false;
        accessing = false;
        this.answer = answer;
    }
    public void update() {
        if (inRange((Player)Game.player) && Keyboard.isKeyDown(KeyCode.E) && Game.stageObjectTask[level] && Game.stageRiddleTask[level] && Game.stageMainTask[level] && isLocked) {
            isLocked = false;
            ((ImageView)node).setImage(imageOpen);
        }
        if(isLocked) {
            ((ImageView)node).setImage(imageLocked);
            pos = normalPos;
        }
        else if(!answeredCorrectly){
            display();
            if (accessing && !answeredCorrectly) {
                ((ImageView) node).setImage(scenario);
                displayScenario();
                pos = Game.toWorld(new Vector(20, 20));
            } else {
                ((ImageView) node).setImage(imageOpen);
                pos = normalPos;
            }
        }

    }

    /**
     * Getter method for the Node
     * @return The Node
     */
    public Node getNode() {
        return node;
    }
    @Override
    public void draw() {
        clearTransformations();
        if (accessing && !answeredCorrectly) {
            setTranslate(Game.toWorld(new Vector(0,0)).mul(-1));
            getNode().relocate((pos.x), (pos.y));
        } else {
            setScale(Game.ZOOM, Main.getDims().div(2));
            setTranslate(Game.cameraPos);
            getNode().relocate((pos.x)*Game.ZOOM, (pos.y)*Game.ZOOM);
        }
    }

    /**
     * Method to check whether the player is in range of the object
     * @param p The player
     * @return Whether or not the player is in range and therefore can interact with the door
     */
    public boolean inRange(Player p){
        return (Math.abs(p.getNode().getLayoutX() - node.getLayoutX()) < 200 && Math.abs(p.getNode().getLayoutY() - node.getLayoutY()) < 200);
    }

    public void display() {
        if ((Vector.dist(Game.player.pos, this.pos) < 100) && Keyboard.isKeyDown(KeyCode.E)) {
            accessing = true;
            Player.playerMoving = false;
            Game.player.getNode().setVisible(false);

        }
    }
    /**
     * Method to display the scenario when going through doors
     */
    public void displayScenario() {
        Player.playerMoving = false;
        Game.player.getNode().setVisible(false);
        ((ImageView) node).setImage(scenario);
        if (!answered) {
            if (Keyboard.isKeyDown(KeyCode.DIGIT1)) {
                userAnswer = 1;
                answered = true;
            } else if (Keyboard.isKeyDown(KeyCode.DIGIT2)) {
                userAnswer = 2;
                answered = true;
            } else if (Keyboard.isKeyDown(KeyCode.DIGIT3)) {
                userAnswer = 3;
                answered = true;
            } else if (Keyboard.isKeyDown(KeyCode.DIGIT4)) {
                userAnswer = 4;
                answered = true;
            }
        }
        if (answered) {
            if (userAnswer == answer && userAnswer != 0) {
                messageBox(true);
            } else {
                messageBox(false);
            }
        }
    }
    /**
     * Displays whether or not the user got the answer correct
     */
    public void messageBox(boolean correct) {
        Image correctBox = new Image("assets/signs/Correct.png");
        Image incorrectBox = new Image("assets/signs/Incorrect.png");
        if(correct)
        {
            ((ImageView)node).setImage(correctBox);
        }
        else
        {
            ((ImageView)node).setImage(incorrectBox);
        }
        if(Keyboard.isKeyDown(KeyCode.H) && correct)
        {
            answeredCorrectly = true;
            answered = false;
            accessing = false;
            userAnswer = 0;
            Game.player.getNode().setVisible(true);
            Player.playerMoving = true;
            pos = normalPos;
            ((ImageView) node).setImage(imageOpen);
            if(level == 0)
            {
                //teleport to lvl 1
                Game.player.pos = new Vector(750, 1725);
            }
            else if(level == 1)
            {
                //teleport to lvl2
                Game.player.pos = new Vector(750, 825);
            }
            else if(level == 2)
            {
                //teleport to final boss fight
                Game.navigateLevel(Game.getLevel(2), 1, 0);
            }
            //teleport player somewhere

        }
        if(Keyboard.isKeyDown(KeyCode.H) && !correct)
        {
            answeredCorrectly = false;
            answered = false;
            accessing = false;
            userAnswer = 0;
            Game.player.getNode().setVisible(true);
            Player.playerMoving = true;
        }

    }
}
