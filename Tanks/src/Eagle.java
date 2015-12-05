package Tanks;

import java.awt.*;

public class Eagle implements Drawable, Destoyable {
    private int x;
    private int y;
    private BattleField bf;

    Eagle(BattleField bf, int x, int y){
        this.x = x;
        this.y = y;
        this.bf = bf;
    }
    @Override
    public void destroy() throws Exception {
        bf.updateQuadrant(y, x, " ");
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(255, 0, 232));
        g.fillRect(x, y, 64, 64);
    }

}
