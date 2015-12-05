package Tanks;

public class TankT34 extends AbstractTank{
    private int maxSpeed = 50;

    public TankT34(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void printCharacteristics(){
        System.out.println("Tank T34\nmaxSpeed: " + this.getMaxSpeed() + "\narmor: -");
    }

    @Override
    public String toString() {
        return "I am class TankT34!";
    }

    @Override
    public void move() throws Exception {
        System.out.println("Start move tank TankT34!");
        super.move();
    }

}
