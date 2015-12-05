package Tanks;

import java.awt.*;
import java.util.Random;

public class BattleField implements Drawable{
    private boolean COLORDED_MODE = true;
    int BF_WIDTH = 576;
    int BF_HEIGHT = 576;
    String[][] battleField = {
            {" ", "B", " ", " ", "W", "B", " ", " ", " "},
            {"B", "B", " ", " ", " ", " ", " ", "B", "B"},
            {"B", "B", " ", "B", " ", "B", " ", "B", "B"},
            {"B", "B", "B", "B", " ", "B", "B", "B", "B"},
            {"W", "W", "W", "W", " ", "W", "W", "W", "W"},
            {"B", "B", "B", "B", " ", "B", "B", "B", "B"},
            {"R", "B", "B", "B", " ", "B", "B", "B", "R"},
            {"B", "R", "B", " ", " ", " ", "B", "R", "B"},
            {"B", "B", "R", "B", "E", "B", "R", "B", "B"}};

    public BattleField() {
    }

    public BattleField(String[][] battleField) {
        this.battleField = battleField;
    }

    public String scanQuadrant(int v, int h) {
        return battleField[v][h];
    }

    public void updateQuadrant(int v, int h, String str) {
        battleField[v][h] = str;
    }

    public int getDimentionX() {
        return battleField[0].length;
    }

    public int getDimentionY() {
        return battleField.length;
    }

    public int getBF_WIDTH() {
        return BF_WIDTH;
    }

    public int getBF_HEIGHT() {
        return BF_HEIGHT;
    }

    public int getPresetAgressorCoordinates(String xORy) {
        int result;
        int[][] presetQuadrants = {{0, 0}};
        Random random = new Random();
        int randomQuadrant = random.nextInt(presetQuadrants.length);
        if (xORy.equals("x")) result = presetQuadrants[randomQuadrant][0];
        else if (xORy.equals("y")) result = presetQuadrants[randomQuadrant][1];
        else result = -1;
        return result;
    }

    private String getQuadrantXY(int v, int h) {
        return (v - 1) * 64 + "_" + (h - 1) * 64;
    }

    @Override
    public void draw(Graphics g) {
        int i = 0;
        Color cc;
        for (int v = 0; v < 9; v++) {
            for (int h = 0; h < 9; h++) {
                if (COLORDED_MODE) {
                    if (i % 2 == 0) {
                        cc = new Color(252, 241, 177);
                    } else {
                        cc = new Color(233, 243, 255);
                    }
                } else {
                    cc = new Color(180, 180, 180);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * 64, v * 64, 64, 64);
            }
        }

        for (int j = 0; j < this.getDimentionY(); j++) {
            for (int k = 0; k < this.getDimentionX(); k++) {
                String coordinates = getQuadrantXY(j + 1, k + 1);
                int separator = coordinates.indexOf("_");
                int y = Integer.parseInt(coordinates.substring(0, separator));
                int x = Integer.parseInt(coordinates.substring(separator + 1));
                if (this.scanQuadrant(j, k).equals("B")) {
                    Brick brick = new Brick(this,x,y);
                    brick.draw(g);
                } else if (this.scanQuadrant(j, k).equals("R")) {
                    Rock rock = new Rock(this,x,y);
                    rock.draw(g);
                }else if (this.scanQuadrant(j, k).equals("W")) {
                    Water water = new Water(x,y);
                    water.draw(g);
                } else if (this.scanQuadrant(j, k).equals("E")) {
                    Eagle eagle = new Eagle(this,x,y);
                    eagle.draw(g);
                }
            }
        }
    }
}
