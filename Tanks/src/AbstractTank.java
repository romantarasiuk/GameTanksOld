package Tanks;

import java.awt.*;

public abstract class AbstractTank implements Drawable,Destoyable{
    protected int speed = 10;
    protected int armor = 0;
    protected int x;
    protected int y;

    protected Colors color;
    protected int crew;
    protected int maxSpeed;

    protected Direction direction;

    protected ActionField af;
    protected BattleField bf;

    public AbstractTank(ActionField af, BattleField bf){
        this(af,bf,128,512,Direction.UP);
    }

    public AbstractTank(ActionField af, BattleField bf, int x, int y, Direction direction){
        this.af = af;
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void updateX(int x) {
        this.x += x;
    }

    public void updateY(int y) {
        this.y += y;
    }

    public void move() throws Exception{
        af.processMove(this);
    }
    public void turn(Direction direction) throws Exception{
        this.direction = direction;
        af.processTurn(this);
    }
    public void fire() throws Exception{
        Bullet bullet = new Bullet((x + 25), (y + 25), direction);
        af.processFire(bullet);
    }
    public void destroy() throws Exception{
        this.setX(-100);
        this.setY(-100);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public void draw(Graphics g) {
        if (this.armor > 0) {
            g.setColor(new Color(0, 0, 0));
        } else {
            g.setColor(new Color(255, 0, 0));
        }
        g.fillRect(this.getX(), this.getY(), 64, 64);

        g.setColor(new Color(0,255,0));
        if (this.getDirection() == Direction.UP) {
            g.fillRect(this.getX() + 20, this.getY(), 24, 34);
        } else if (this.getDirection() == Direction.DOWN) {
            g.fillRect(this.getX() + 20, this.getY() + 30, 24, 34);
        } else if (this.getDirection() == Direction.LEFT) {
            g.fillRect(this.getX(), this.getY() + 20, 34, 24);
        } else {
            g.fillRect(this.getX() + 30, this.getY() + 20, 34, 24);
        }
    }
}
