import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Healthbar extends GameObject{
    public Vector pos;
    public int hp;
    private Group node;
    private Rectangle backing, health, missing;
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
    public void update(){
        this.hp = ((Player)Game.player).hp;
        pos = Game.toWorld(new Vector(0, 0));
        backing.setX(pos.x);
        backing.setY(pos.y);
        health.setX(pos.x+10);
        health.setY(pos.y+10);
        health.setWidth(2*hp);
        missing.setX(pos.x+10+2*hp);
        missing.setY(pos.y+10);
        missing.setWidth(200-2*hp);
    }
    public void draw(){

    }
    public Node getNode(){
        return node;
    }
}
