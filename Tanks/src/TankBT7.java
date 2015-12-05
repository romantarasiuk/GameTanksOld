package Tanks;

public class TankBT7 extends AbstractTank{
    private int maxSpeed = 72;

    public TankBT7(ActionField af, BattleField bf, int i, int i1, Direction down){
        super(af,bf);
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void printCharacteristics(){
        System.out.println("Tank BT7\nmaxSpeed: " + this.getMaxSpeed() + "\narmor: -");
    }
}
