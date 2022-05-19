/**
 * <p>
 * The player class contains state and behaviours for a player object.
 * The player object can be controled by the keyboard to perform actions such as 
 * movement and slashing. Contains functionality for drawing the player.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * File was created by Daniel Morgan on may 17th, 2022.
 * </p>
 */

public class Player {
    public Vector pos;
    public Player(float x, float y) {
        this.pos = new Vector(x, y);
    }
    public void update() {
        Vector diff = Vector.sub(Main.getDims(), pos);
        float percent = (diff.x + diff.y) / (Main.getWidth() + Main.getHeight());
        pos = pos.lerp(Main.getDims(), (1 - percent) * 0.01f);
    }
    public void draw() {
        Graphics.drawRect(pos.x, pos.y, 100f, 100f);
    }
}