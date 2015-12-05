package Tanks;

import java.awt.*;

public class Rock implements Drawable, Destoyable {
    private int x;
    private int y;
    private BattleField bf;

    Rock(BattleField bf, int x, int y){
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
        g.setColor(new Color(100, 100, 100));
        g.fillRect(x, y, 64, 64);
    }

}
