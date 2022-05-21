/**
 * <p>
 * The player class contains state and behaviours for a player object.
 * The player object can be controled by the keyboard to perform actions such as 
 * movement and slashing. Contains functionality for drawing the player.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.1
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * File was created by Daniel Morgan on May 17th, 2022.
 * Daniel Morgan spent 30 minutes on this file on May 17th
 * Ryan Atlas spent 20 minutes on this file on May 20th, adding new comments and attributes
 * </p>
 */

public class Player implements GameObject{
   /** The player's position stored as a Vector*/
    public Vector pos;
    /** The player's current hp*/
    private int hp;
    /* The player's movement speed which is a constant*/
    private static final int SPEED = 10;
    /**
    * The Player class constructor which takes in two floats 
    * in order to initialize the position vector and sets all other
    * variables to their default.
    * @param x The player's x coordinate
    * @param y The player's y coordinate
    */
    public Player(float x, float y) {
        this.pos = new Vector(x, y);
        hp = 100;
    }
    /** 
    * Updates the player's position Vector, implementation of the method
    * from the GameObject interface
    */
    public void update() {
        Vector diff = Vector.sub(Main.getDims(), pos);
        float percent = (diff.x + diff.y) / (Main.getWidth() + Main.getHeight());
        pos = pos.lerp(Main.getDims(), (1 - percent) * 0.01f);
    }
    /**
    * D\aws the player on the screen, implementation of the method
    * from the GameObject interface
    */
    public void draw() {
        Graphics.drawRect(pos.x, pos.y, 100f, 100f);
    }
}