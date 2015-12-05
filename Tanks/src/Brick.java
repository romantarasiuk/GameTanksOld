package Tanks;

import java.awt.*;

public class Brick implements Drawable, Destoyable {
    private int x;
    private int y;
    private BattleField bf;

    Brick (BattleField bf, int x, int y){
        this.bf = bf;
        this.x = x;
        this.y = y;
    }
    @Override
    public void destroy() throws Exception {
        bf.updateQuadrant(y, x, " ");
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 255));
        g.fillRect(x, y, 64, 64);
    }

}
