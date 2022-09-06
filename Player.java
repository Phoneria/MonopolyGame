import java.util.ArrayList;


public class Player extends Gamers{
    private int money  = 15000;
    private int position = 1;
    private ArrayList<String> properties = new ArrayList<>();
    private int jailCounter ;
    private int railroadCounter;
    private String name ;
    private int passingFromStart ;

    public Player(String name) {
        this.name=name;
    }

    public int getPassingFromStart() {
        return passingFromStart;
    }
    public String getName() {
        return name;
    }
    public int getRailroadCounter() {
        return railroadCounter;
    }
    public int getJailCounter() {
        return jailCounter;
    }
    public int getPosition() {
        return position;
    }
    public int getMoney() {
        return money;
    }
    public ArrayList<String> getProperties() {
        return properties;
    }

    public void setMoney(int money) {
        if((this.money + money) > 0 ) {
            this.money += money;
        }
        else {
            this.money= 0 ;
        }
    }// It can't be assigned less than zero
    public void setPosition(int position) {
        if ((this.position + position) <=40){
            this.position += position;
        }
        else {
            this.position = ((this.position + position) % 40);
            passingFromStart++;
            money+=200;

        }


    } // It can't be assigned more than 40. If it is, than position will be remainder from division ny 40
    public void setProperties(String property) {
        properties.add(property);
    }
    public void setRailroadCounter(int railroadCounter) {
        this.railroadCounter += railroadCounter;
    }
    public void setJailCounter(int jailCounter) {
        if(this.jailCounter + jailCounter <= 3){
            this.jailCounter += jailCounter;
        }
        else{
            this.jailCounter = 0 ;
        }

    }// It can be assigned maximum 3 . If it jailCounter is greater than 3, than jailCounter will be assigned as 0
    public void assignPosition(int position){
        this.position = position;
    }// It assigns player to first square

}
