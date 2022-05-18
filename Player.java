public class Player {
    public Vector pos;
    public Player(float x, float y) {
        this.pos = new Vector(x, y);
    }
    public void draw() {
        Graphics.drawRect(pos.x, pos.y, 100f, 100f);
    }
}