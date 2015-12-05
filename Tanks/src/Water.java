package Tanks;

import java.awt.*;

public class Water implements Drawable {
    private int x;
    private int y;

    Water(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 255, 215));
        g.fillRect(x, y, 64, 64);
    }

}
