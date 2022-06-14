import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class EscapeRoomSign extends GameObject implements Interactable{

    /** JavaFX node for the platform*/
    private Node node;
    /** The text on the sign */
    private Image message;
    /** The blank image for the location of the sign */
    private Image blankImage;
    /** Normal position of the sign*/
    private Vector normal;
    /** Correct answer*/
    private int answer = 0;
    /** User's answer*/
    private int userAnswer;
    /**Whether the player is accessing the sign*/
    private boolean accessing;
    /** Whether the sign has been answered correctly*/
    private boolean answeredCorrectly;
    /** Whether the sign has been answered*/
    private boolean answered;
    /** Whether the sign is a riddle sign*/
    private boolean riddle;
    /** What stage the signs are in*/
    private int level;

    public EscapeRoomSign(Image message, int x, int y, int answer, int level, boolean riddle)
    {
        this.message = message;
        blankImage = new Image("assets/teleporter.png");
        node = new ImageView(blankImage);
        pos = new Vector(x, y);
        normal = new Vector(x, y);
        this.answer = answer;
        accessing = false;
        answeredCorrectly = false;
        userAnswer = 0;
        answered = false;
        this.riddle = riddle;
        this.level = level;
    }
    /**
     * Overridden update method from GameObject
     */
    public void update() {
        if(riddle && Game.stageObjectTask[level]) {
            display();
            if (accessing && !answeredCorrectly) {
                ((ImageView) node).setImage(message);
                checkQuestions();
                pos = Game.toWorld(new Vector(20, 20));
            } else {
                ((ImageView) node).setImage(blankImage);
                pos = normal;
            }
        }
        else if(!riddle && Game.stageObjectTask[level] && Game.stageRiddleTask[level])
        {
            display();
            if (accessing && !answeredCorrectly) {
                ((ImageView) node).setImage(message);
                checkQuestions();
                pos = Game.toWorld(new Vector(20, 20));
            } else {
                ((ImageView) node).setImage(blankImage);
                pos = normal;
            }
        }
        else
        {
            ((ImageView) node).setImage(blankImage);
            pos = normal;
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
        if(riddle && Game.stageObjectTask[level]) {
            if (accessing && !answeredCorrectly) {
                setTranslate(Game.toWorld(new Vector(0, 0)).mul(-1));
                getNode().relocate((pos.x), (pos.y));
            } else {
                setScale(Game.ZOOM, Main.getDims().div(2));
                setTranslate(Game.cameraPos);
                getNode().relocate((pos.x) * Game.ZOOM, (pos.y) * Game.ZOOM);
            }
        }
        else if(!riddle && Game.stageObjectTask[level] && Game.stageRiddleTask[level]) {
            if (accessing && !answeredCorrectly) {
                setTranslate(Game.toWorld(new Vector(0, 0)).mul(-1));
                getNode().relocate((pos.x), (pos.y));
            } else {
                setScale(Game.ZOOM, Main.getDims().div(2));
                setTranslate(Game.cameraPos);
                getNode().relocate((pos.x) * Game.ZOOM, (pos.y) * Game.ZOOM);
            }
        }
    }
    /**
     * Whether the player is in range
     * @param player Current player
     * @return True if in range
     */
    public boolean inRange(Player player) {
        return (Vector.dist(Game.player.pos, this.pos) < 100);
    }
    /**
     * Displays the message on the sign for riddle/scenario
     */
    public void display() {
        if ((Vector.dist(Game.player.pos, this.pos) < 100) && Keyboard.isKeyDown(KeyCode.E) && !answeredCorrectly) {
            accessing = true;
            Player.playerMoving = false;
            Game.player.getNode().setVisible(false);

        }
    }
    /**
     * Displays the message on the sign for question signs
     */
    public void checkQuestions() {
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
            } else if (Keyboard.isKeyDown(KeyCode.DIGIT5)) {
                userAnswer = 5;
                answered = true;
            } else if (Keyboard.isKeyDown(KeyCode.DIGIT6)) {
                userAnswer = 6;
                answered = true;
            }
            else if (Keyboard.isKeyDown(KeyCode.DIGIT7)) {
                userAnswer = 7;
                answered = true;
            } else if (Keyboard.isKeyDown(KeyCode.DIGIT8)) {
                userAnswer = 8;
                answered = true;
            }
        }
        if (answered) {
            if (userAnswer == answer && userAnswer != 0) {
                //tell upward it works
                messageBox(true);
            } else {
                //tell upward it is false;
                //answeredCorrectly = false;
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
            if(riddle)
            {
                Game.stageRiddleTask[level] = true;
            }
            else
            {
                Game.stageMainTask[level] = true;
            }
            accessing = false;
            answered = false;
            userAnswer = 0;
            Game.player.getNode().setVisible(true);
            Player.playerMoving = true;
        }
        else if(Keyboard.isKeyDown(KeyCode.H) && !correct)
        {
            answeredCorrectly = false;
            accessing = false;
            answered = false;
            userAnswer = 0;
            Game.player.getNode().setVisible(true);
            Player.playerMoving = true;
        }

    }

}
