package Tanks;

public class TankTiger extends AbstractTank {
    private int maxSpeed = 36;

    public TankTiger(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        this.setArmor(1);
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void printCharacteristics() {
        System.out.println("Tank Tiger\nmaxSpeed: " + this.getMaxSpeed() + "\narmor: " + armor);
    }
}
