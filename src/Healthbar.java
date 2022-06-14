import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * <p>
 * This file is used to draw the player's health bar so that they can keep track of their health. Health is
 * lost by getting hit by enemies and regained by dealing damage to enemies by dashing through them.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since June 11th, 2022
 * <p>
 * 30 minutes were spent by Ryan Atlas making this file on June 11th.
 * </p>
 */
public class Healthbar extends GameObject{
    /** Position vector */
    public Vector pos;
    /** Current hp value to display (out of 100)*/
    private int hp;
    /** Node to store rectangles*/
    private Group node;
    /** Rectangles that comprise the healthbar*/
    private Rectangle backing, health, missing;
    /**
     * Constructor that initializes variables
     * @param hp Current hp value of player
     */
    public Healthbar(int hp){
        this.hp = hp;
        pos = Game.toWorld(new Vector(0, 40));
        backing = new Rectangle(pos.x, pos.y, 220, 40);
        backing.setFill(Color.BLACK);
        health = new Rectangle(pos.x+10, pos.y+10, 2*hp, 20);
        health.setFill(Color.RED);
        missing = new Rectangle(pos.x+10+2*hp, pos.y+10, 200-2*hp, 20);
        missing.setFill(Color.WHITE);
        node = new Group(backing, health, missing);
    }
    /**
     * Update method to update the healthbar each frame so it
     * moves with the player and decreases/increases as necessary
     */
    public void update(){
        this.hp = ((Player)Game.player).hp;
        pos = Game.toWorld(new Vector(130, 0));
        backing.setX(pos.x);
        backing.setY(pos.y);
        health.setX(pos.x+10);
        health.setY(pos.y+10);
        health.setWidth(2*hp);
        missing.setX(pos.x+10+2*hp);
        missing.setY(pos.y+10);
        missing.setWidth(200-2*hp);
    }
    /**
     * Draw method from GameObject
     */
    public void draw(){

    }
    /**
     * Gets the node
     * @return Current node
     */
    public Node getNode(){
        return node;
    }
}
