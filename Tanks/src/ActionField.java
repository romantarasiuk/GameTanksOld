package Tanks;

import javax.swing.*;
import java.awt.*;

public class ActionField  extends JPanel {

    private BattleField battleField = new BattleField();
    private TankT34 defender = new TankT34(this,battleField,512,0,Direction.LEFT);

    private Bullet bullet = new Bullet(-100, -100, null);

    private long agressorDestroyTime = Long.MAX_VALUE;

    private TankTiger agressor = new TankTiger(this, battleField, battleField.getPresetAgressorCoordinates("x"),
            battleField.getPresetAgressorCoordinates("y"), Direction.LEFT);

    public void processMove(AbstractTank tank) throws Exception {
        this.defender = (TankT34) tank;
        Direction direction = tank.getDirection();
        int step = 1;
        int covered = 0;

        // check limits x: 0, 513; y: 0, 513
        if ((direction == Direction.UP && tank.getY() == 0)
                || (direction == Direction.DOWN && tank.getY() >= 512)
                || (direction == Direction.LEFT && tank.getX() == 0)
                || (direction == Direction.RIGHT && tank.getX() >= 512)) {
            return;
        }

        tank.turn(direction);

        while (covered < 64) {
            if (direction == Direction.UP) {
                tank.updateY(-step);
            } else if (direction == Direction.DOWN) {
                tank.updateY(step);
            } else if (direction == Direction.LEFT) {
                tank.updateX( - step);
            } else {
                tank.updateX(step);
            }
            covered += step;

            repaint();
            Thread.sleep(tank.getSpeed());
        }

    }
    public void processTurn(AbstractTank tank) throws Exception {repaint();}
    public void processFire(Bullet bullet) throws Exception {
        this.bullet = bullet;
        int step = 1;
        while ((bullet.getX() > -14 && bullet.getX() < 590) && (bullet.getY() > -14 && bullet.getY() < 590)) {
            if (defender.getDirection() == Direction.UP) {
                bullet.updateY(-step);
            } else if (defender.getDirection() == Direction.DOWN) {
                bullet.updateY(step);
            } else if (defender.getDirection() == Direction.LEFT) {
                bullet.updateX( - step);
            } else {
                bullet.updateX(step);
            }
            if (processInterception()){
                bullet.destroy();
            }
            repaint();
            Thread.sleep(bullet.getSpeed());
        }
    }


    public void runTheGame() throws Exception {
        defender.fire();
        defender.fire();
        defender.fire();
        defender.fire();
        defender.fire();
        defender.fire();
        defender.fire();
        defender.fire();
        defender.fire();
        defender.fire();


    }
    private boolean processInterception() throws Exception {
        String coordinates = getQuadrant(bullet.getX(),bullet.getY());
        int y = Integer.parseInt(coordinates.split("_")[0]);
        int x = Integer.parseInt(coordinates.split("_")[1]);

        if (y >= 0 && y < 9 && x >= 0 & x < 9){
            if (!battleField.scanQuadrant(y,x).trim().isEmpty() & !battleField.scanQuadrant(y,x).trim().equals("W")) {
                battleField.updateQuadrant(y, x, " ");
                return true;
            }
            // destroy agressor
            if (getQuadrant(bullet.getX(),bullet.getY()).equals(getQuadrant(agressor.getX(), agressor.getY()))){
                if (agressor.armor == 1) {
                    agressor.setArmor(0);
                    bullet.destroy();
                }
                else {
                    agressor.destroy();
                    bullet.destroy();
                    agressorDestroyTime = System.currentTimeMillis();
                }
            }
            //restore agressor
            if ((System.currentTimeMillis() - agressorDestroyTime) > 3000){
                agressorDestroyTime = Long.MAX_VALUE;
                agressor.setX(battleField.getPresetAgressorCoordinates("x"));
                agressor.setY(battleField.getPresetAgressorCoordinates("y"));
            }

        }
        return false;
    }

    private String getQuadrant(int x, int y) {
        return y / 64 + "_" + x / 64;
    }

    public ActionField() throws Exception {
        JFrame frame = new JFrame("TANKS BATTLE");
        frame.setLocation(0, 0);
        frame.setMinimumSize(new Dimension(64*battleField.getDimentionX()+16, 64*battleField.getDimentionY()+40));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        battleField.draw(g);
        bullet.draw(g);
        defender.draw(g);
        agressor.draw(g);
    }

}
