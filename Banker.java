public class Banker extends Gamers{
    private int money = 100000;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money += money;
    }
}
