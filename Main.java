import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
/*
There is a lot of function to make my jon easier. I have write all them to don't write same thing again and again

Firstly ı have crated a map with arraylists under the main section.And then , I have used them to check areas places

Then ı have created our players and banker and started to read commands


*/
public class Main {
    //   printWriter.println
    public static String[] readFile (String path) {
        try {
            int i = 0;
            int length = Files.readAllLines(Paths.get(path)).size();

            String[] results = new String[length];
            for (String line : Files.readAllLines(Paths.get(path))){
                results[i++] = line;
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }// Read files
    public static int chanceNum = 0;//It holds chanceNum. If it will be more than 5, then it will be assigned as 0
    public static int  communityChestNum= 0;//It holds chanceListNumber. If it will be more than 5, then it will be assigned as 0
    public static String otherGamePlay(Player player1 ,Player player2, Gamers banker,int move,Player first,Player second){
        if(player1.getPosition() == 1) {
            return (player1.getName() + "\t" + move + "\t" + player1.getPosition() + "\t" +first.getMoney()+"\t"+second.getMoney()+ "\t" + player1.getName() + " is in the GO square");
        }
        else if (player1.getPosition()==5|| player1.getPosition() == 39){
            player1.setMoney(-100);
            banker.setMoney(100);
            return (player1.getName() + "\t" + move + "\t" + player1.getPosition() + "\t" +first.getMoney()+"\t"+second.getMoney()+ "\t" + player1.getName() + " paid Tax ");
        }
        else if (player1.getPosition()==11||player1.getPosition()==31){
            player1.setJailCounter(3);
            player1.assignPosition(11);
            return (player1.getName() + "\t" + move + "\t" + player1.getPosition() + "\t" +first.getMoney()+"\t"+second.getMoney()+ "\t" + player1.getName() + " went to jail ");
        }
        else if (player1.getPosition()==21){
            player1.setJailCounter(1);
            return (player1.getName() + "\t" + move + "\t" + player1.getPosition() + "\t" +first.getMoney()+"\t"+second.getMoney()+ "\t" + player1.getName() + " in Free Parking ");
        }// I have assigned jailCounter to see how many tour remain
        return " ";
    }//It makes other jobes in the game
    public static String gameplay(Player player1,Player player2,Gamers banker,ArrayList<Integer> landMap,ArrayList<Integer> railroadMap,ArrayList<Integer> companyMap,Land land2,Railroad railroad,Company company,int move,Player first,Player second){
        {
            String land =" ";//Firstly ı have assigned land as empty. And then fill it according to position
            if(landMap.contains(player1.getPosition())){
                for(ArrayList<String> s : land2.getList()){
                    if(Integer.parseInt(s.get(0)) == player1.getPosition()){
                        land = s.get(1);
                        if(player2.getProperties().contains(land)){
                            int price =Integer.parseInt(s.get(2));
                            int rent = 0;
                            if(price <2000){
                                rent = (price*4)/10;
                            }
                            else if(price<3000){
                                rent=(price*3)/10;
                            }
                            else if(price<4000){
                                rent=(price*35)/100;
                            }
                            player1.setMoney(-rent);
                            player2.setMoney(rent);
                            return (player1.getName()+"\t"+move+"\t"+player1.getPosition()+"\t"+first.getMoney()+"\t"+second.getMoney()+"\t"+player1.getName() +" paid rent for "+land);
                        }// If player2 has this land. Then it take rent and player1 pay rent
                        else if(player1.getProperties().contains(land)){
                            return (player1.getName()+"\t"+move+"\t"+player1.getPosition()+"\t"+first.getMoney()+"\t"+second.getMoney()+"\t"+player1.getName()+" has "+land);

                        }// If player1 has this land. Then nothing will happened
                        else{
                            player1.setProperties(s.get(1));
                            player1.setMoney(-Integer.parseInt(s.get(2)));
                            banker.setMoney(Integer.parseInt(s.get(2)));
                            return (player1.getName()+"\t"+move+"\t"+player1.getPosition()+"\t"+first.getMoney()+"\t"+second.getMoney()+"\t"+player1.getName()+" bought "+land);

                        }// If player2 doesn't has this land. Then player1 will take it
                    }
                }
            }// It check if position is in the landMap
            else if(railroadMap.contains(player1.getPosition())){
                for(ArrayList<String> s : railroad.getList()){
                    if(Integer.parseInt(s.get(0)) == player1.getPosition()){
                        land = s.get(1);

                        if(player2.getProperties().contains(land)){
                            int rent = 25*player2.getRailroadCounter();

                            player1.setMoney(-rent);
                            player2.setMoney(rent);
                            return (player1.getName()+"\t"+move+"\t"+player1.getPosition()+"\t"+first.getMoney()+"\t"+second.getMoney()+"\t"+player1.getName()+" paid rent for "+land);
                        }// If player2 has this land. Then it take rent and player1 pay rent
                        else{
                            player1.setRailroadCounter(1);
                            player1.setProperties(s.get(1));
                            player1.setMoney(-Integer.parseInt(s.get(2)));
                            banker.setMoney(Integer.parseInt(s.get(2)));
                            return (player1.getName()+"\t"+move+"\t"+player1.getPosition()+"\t"+first.getMoney()+"\t"+second.getMoney()+"\t"+player1.getName()+" bought "+land);

                        }// If player2 doesn't has this land. Then player1 will take it
                    }
                }
            }
            else if(companyMap.contains(player1.getPosition())){
                for(ArrayList<String> s : company.getList()){
                    if(Integer.parseInt(s.get(0)) == player1.getPosition()){
                        land = s.get(1);

                        if(player2.getProperties().contains(land)){
                            int rent = move*4;

                            player1.setMoney(-rent);
                            player2.setMoney(rent);
                            return (player1.getName()+"\t"+move+"\t"+player1.getPosition()+"\t"+first.getMoney()+"\t"+second.getMoney()+"\t"+player1.getName()+ " paid rent for "+land);
                        }// If player2 has this land. Then it take rent and player1 pay rent
                        else{
                            player1.setRailroadCounter(1);
                            player1.setProperties(s.get(1));
                            player1.setMoney(-Integer.parseInt(s.get(2)));
                            banker.setMoney(Integer.parseInt(s.get(2)));
                            return (player1.getName()+"\t"+move+"\t"+player1.getPosition()+"\t"+first.getMoney()+"\t"+second.getMoney()+"\t"+player1.getName()+" bought "+land);

                        }// If player2 doesn't has this land. Then player1 will take it
                    }
                }
            }

        }
        return " " ;
    }
    // The game has beed playing in this function
    public static ArrayList<String> show(Player player1,Player player2,Gamers banker){
        ArrayList<String> output = new ArrayList<>();
        output.add("-------------------------------------------------------------------------------------------------------------------------"+"\n");
        output.add("Player 1"+"\t"+player1.getMoney()+"\t"+ "have:"+"\t");
        if(player1.getProperties().size() == 0){
            output.add("\n");
        }
        for(String s : player1.getProperties()){
            if(player1.getProperties().size() == 1){
                output.add(s+"\n");
            }
            else {
                if(s.equals(player1.getProperties().get(player1.getProperties().size()-1))){
                    output.add(s+"\n");
                }
                else{output.add(s+", ");}

            }
        }

        output.add("Player 2"+"\t"+player2.getMoney()+"\t"+ "have:"+"\t");
        if(player2.getProperties().size() == 0){
            output.add("\n");
        }
        for(String s : player2.getProperties()){
            if(player2.getProperties().size() == 1){
                output.add(s+"\n");
            }
            else {
                if(s.equals(player2.getProperties().get(player2.getProperties().size()-1))){
                    output.add(s+"\n");
                }
                else{output.add(s+", ");}

            }
        }
        output.add("Banker\t"+banker.getMoney()+"\n");
        String winner ;
        if(player1.getMoney()> player2.getMoney()){
            winner = "Player1";
        }
        else {
            winner = "Player2";
        }
        output.add("Winner"+"\t"+winner+"\n");
        output.add("-------------------------------------------------------------------------------------------------------------------------"+"\n");
        return output;
    }//It shows our players situation
    public static String chanceListPlay(ArrayList<String> communityList,ArrayList<String> chanceList,Player first,Player second,Gamers banker,int move,Player player1,Player player2) {
        String order = communityList.get(chanceNum%6);
        chanceNum++;
        if (order.equals("Advance to Go (Collect $200)")){
            first.assignPosition(1);
            first.setMoney(200);
            banker.setMoney(-200);
            return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Advance to Go (Collect $200) ");


        }
        else if (order.equals("Advance to Leicester Square")){
            first.assignPosition(27);
            if(first.getProperties().contains("Leicester Square")){
                return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+second.getName()+" draw Advance to Leicester Square "+first.getName()+" has Leicester Square ");
            }// If first player has this area this output will be written
            else if(second.getProperties().contains("Leicester Square")){
                second.setMoney(780);
                first.setMoney(-780);
                return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Advance to Leicester Square "+first.getName()+" paid rent for Leicester Square ");
            }// If second player own this place , then first player will pay rent
            else {
                first.setMoney(-2600);
                banker.setMoney(2600);
                first.setProperties("Leicester Square");
                return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+second.getName()+" draw Advance to Leicester Square "+first.getName()+" bought Leicester Square ");

            }// In other case , first player will buy this land

        }
        else if (order.equals("Go back 3 spaces")){
            first.setPosition(-3);
            if(first.getPosition()==5){
                first.setMoney(-100);
                banker.setMoney(100);
                return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+second.getName()+" draw Go back 3 spacesPlayer "+first.getName()+" paid Tax ");

            }
            else if(first.getPosition()==20){
                if(first.getProperties().contains("Vine Street")){
                    return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+second.getName()+" draw Go back 3 spacesPlayer "+first.getName()+" has Vine Street");
                }
                else if(second.getProperties().contains("Vine Street")){
                    first.setMoney(-800);
                    second.setMoney(800);
                    return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+second.getName()+" draw Go back 3 spacesPlayer "+first.getName()+" paid rent for Vine Street");
                }
                else{
                    first.setMoney(-2000);
                    banker.setMoney(2000);
                    return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+second.getName()+" draw Go back 3 spacesPlayer "+first.getName()+" bought Vine Street");

                }
            }
            else if(first.getPosition()==37){
                String order2 = chanceList.get(communityChestNum%11);
                communityChestNum++;
                if(order2.equals("Advance to Go (Collect $200)")){
                    first.assignPosition(1);
                    first.setMoney(200);
                    banker.setMoney(-200);
                    return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Go back 3 spacesPlayer"+first.getName()+" draw Advance to Go (Collect $200)");

                }
                else if(order2.equals("Bank error in your favor - collect $75")){
                    first.setMoney(75);
                    banker.setMoney(-75);
                    return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Go back 3 spacesPlayer"+first.getName()+" draw Bank error in your favor - collect $75");

                }
                else if(order2.equals("Doctor's fees - Pay $50")){
                    first.setMoney(50);
                    banker.setMoney(-50);
                    return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Go back 3 spacesPlayer"+first.getName()+" draw Doctor's fees - Pay $50");

                }
                else if(order2.equals("It is your birthday Collect $10 from each player")){
                    first.setMoney(20);
                    second.setMoney(-10);
                    banker.setMoney(-10);
                    return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Go back 3 spacesPlayer"+first.getName()+" draw It is your birthday Collect $10 from each player");

                }
                else if(order2.equals("Grand Opera Night - collect $50 from every player for opening night seats")){
                    first.setMoney(100);
                    second.setMoney(-50);
                    banker.setMoney(-50);
                    return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Go back 3 spacesPlayer"+first.getName()+" draw Grand Opera Night - collect $50 from every player for opening night seats");

                }
                else if(order2.equals("Income Tax refund - collect $20")){
                    first.setMoney(20);
                    banker.setMoney(-20);
                    return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Go back 3 spacesPlayer"+first.getName()+" draw Income Tax refund - collect $20");

                }
                else if(order2.equals("Life Insurance Matures - collect $100")){
                    first.setMoney(100);
                    banker.setMoney(-100);
                    return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Go back 3 spacesPlayer"+first.getName()+" draw Life Insurance Matures - collect $100");

                }
                else if(order2.equals("Pay School Fees of $50")){
                    first.setMoney(-50);
                    banker.setMoney(50);
                    return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Go back 3 spacesPlayer"+first.getName()+" draw Pay School Fees of $50");

                }
                else if(order2.equals("You inherit $100")){
                    first.setMoney(100);
                    banker.setMoney(-100);
                    return(first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Go back 3 spacesPlayer"+first.getName()+" draw You inherit $100");

                }
                else if(order2.equals("From sale of stock you get $50")){
                    first.setMoney(50);
                    banker.setMoney(-50);
                    return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Go back 3 spacesPlayer"+first.getName()+" draw From sale of stock you get $50");

                }



            }

        }
        else if (order.equals("Pay poor tax of $15")) {
            first.setMoney(-15);
            banker.setMoney(15);
            return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Pay poor tax of $15");

        }
        else if (order.equals("Your building loan matures - collect $150")){
            first.setMoney(150);
            banker.setMoney(-150);
            return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Your building loan matures - collect $150");

        }
        else if (order.equals("You have won a crossword competition - collect $100")){
            first.setMoney(100);
            banker.setMoney(-100);
            return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw You have won a crossword competition - collect $100");
        }

        // If players draw more than 6 card, then list will be return to start
        return " ";
    }
    //I dont want to write this twice. So ı have assigned our players according to elements of functions
    public static String communityChestListPlay(ArrayList<String> chanceList, Player first,Player second, Gamers banker,int move,Player player1,Player player2){
        String order = chanceList.get(communityChestNum%11);
        communityChestNum++;


        if(order.equals("Advance to Go (Collect $200)")){
            first.assignPosition(1);
            first.setMoney(200);
            banker.setMoney(-200);
            return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Community Chest -advance to go ");

        }
        else if(order.equals("Bank error in your favor - collect $75")){
            first.setMoney(75);
            banker.setMoney(-75);
            return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Bank error in your favor - collect $75");

        }
        else if(order.equals("Doctor's fees - Pay $50")){
            first.setMoney(50);
            banker.setMoney(-50);
            return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Doctor's fees - Pay $50");

        }
        else if(order.equals("It is your birthday Collect $10 from each player")){
            first.setMoney(20);
            second.setMoney(-10);
            banker.setMoney(-10);
            return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw It is your birthday Collect $10 from each player");

        }
        else if(order.equals("Grand Opera Night - collect $50 from every player for opening night seats")){
            first.setMoney(100);
            second.setMoney(-50);
            banker.setMoney(-50);
            return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Grand Opera Night - collect $50 from every player for opening night seats");

        }
        else if(order.equals("Income Tax refund - collect $20")){
            first.setMoney(20);
            banker.setMoney(-20);
            return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Income Tax refund - collect $20");

        }
        else if(order.equals("Life Insurance Matures - collect $100")){
            first.setMoney(100);
            banker.setMoney(-100);
            return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Life Insurance Matures - collect $100");

        }
        else if(order.equals("Pay School Fees of $50")){
            first.setMoney(-50);
            banker.setMoney(50);
            return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw Pay School Fees of $50");

        }
        else if(order.equals("You inherit $100")){
            first.setMoney(100);
            banker.setMoney(-100);
            return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw You inherit $100");

        }
        else if(order.equals("From sale of stock you get $50")){
            first.setMoney(50);
            banker.setMoney(-50);
            return (first.getName()+"\t"+move+"\t"+first.getPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+first.getName()+" draw From sale of stock you get $50");

        }

        // If players draw more than 6 card, then list will be return to start
        return " " ;
    }
    //I dont want to write this twice. So ı have assigned our players according to elements of functions
    public static void main(String[] args) {


        ArrayList<String> commands = new ArrayList<>();//It holds commands in arraylist
        for( String s : readFile(args[0])){
            commands.add(s);
        }// It assign commands to commands arraylist

        Land land2 = new Land();
        Railroad railroad =new Railroad();
        Company company = new Company();
        CommunityChestList communityChestList = new CommunityChestList();
        ChanceList chanceList = new ChanceList();



        ArrayList<Integer> landMap = new ArrayList<>();
        ArrayList<Integer> railroadMap = new ArrayList<>();
        ArrayList<Integer> companyMap = new ArrayList<>();

        ArrayList<Integer> communityChestMap = new ArrayList<>(Arrays.asList(3,18,34));
        ArrayList<Integer> chanceMap = new ArrayList<>(Arrays.asList(8,23,37));
        ArrayList<Integer> otherMap  = new ArrayList<>(Arrays.asList(1,5,11,21,31,39));

        ArrayList<Integer> actions = new ArrayList<>();// It holds communityChestMap,chanceMap and otherMap  numbers to make my job easier

        for (int i = 0; i<land2.getList().size(); i++){
            landMap.add(Integer.valueOf(land2.getList().get(i).get(0)));
        }// It adds lands ID's to landMap
        for (int i = 0; i<railroad.getList().size(); i++){
            railroadMap.add(Integer.valueOf(railroad.getList().get(i).get(0)));
        }
        for (int i = 0; i<company.getList().size(); i++){
            companyMap.add(Integer.valueOf(company.getList().get(i).get(0)));
        }
        for(Integer i :chanceMap){
            actions.add(i);
        }// It adds chanceMap numbers to actions arraylist
        for(Integer i :communityChestMap){
            actions.add(i);
        }
        for(Integer i :otherMap){
            actions.add(i);
        }


        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Banker banker = new Banker();

        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter("monitoring.txt");

        }catch (IOException e){
            e.printStackTrace();
        }// Writing outputs to the monitoring.txt
        PrintWriter printWriter= new PrintWriter(fileWriter);


        for(int i  = 0 ; i<commands.size(); i++){
            int pass1 = player1.getPassingFromStart();//It holds how many time player1 pass through start and compare pass1 with player1.getPassingFromStart in the commands.
            //If they are different , it means player 1 passed through the start and take 200tl
            int pass2 = player2.getPassingFromStart();

            if(commands.get(i).startsWith("Player 1")){
                String[] dice = commands.get(i).split(";");
                int move = Integer.parseInt(dice[1]);// It holds dice number(how many square player1 needs to move)

                if(player1.getMoney()==0){
                    printWriter.println(player1.getName() + "\t" + move + "\t" + player1.getPosition() + "\t" + player1.getMoney() + "\t" + player2.getMoney() + "\t" + player1.getName() + " goes bankrupt ");
                    for(String s:show(player1,player2,banker)){
                        printWriter.println(s);
                    }
                    break;
                }// If player1 has zero money so game will be finished


                if(player1.getJailCounter()==0) {
                    player1.setPosition(move); // Player1 is moved according to dice
                    if(!actions.contains(player1.getPosition())){
                        printWriter.println(gameplay(player1, player2, banker, landMap, railroadMap, companyMap,land2,railroad,company, move,player1,player2));
                    }// If position of player1 isn't in the other list(if position is property) then this command is going to work
                    else {
                        if (chanceMap.contains(player1.getPosition())) {
                            printWriter.println(chanceListPlay(chanceList.getActionList(),communityChestList.getActionList(), player1, player2, banker, move,player1,player1));
                        }
                        else if (communityChestMap.contains(player1.getPosition())) {
                            printWriter.println(communityChestListPlay(communityChestList.getActionList(), player1, player2, banker, move,player1,player2));

                        }
                        else if(otherMap.contains(player1.getPosition())) {
                            printWriter.println(otherGamePlay(player1, player2, banker, move,player1,player2));
                        }
                    }//If it is in other list it is going to work
                }// It checks player1 is in the jail or not
                else{
                    printWriter.println(player1.getName() + "\t" + move + "\t" + player1.getPosition() + "\t" + player1.getMoney() + "\t" + player2.getMoney() + "\t" + player1.getName() + " in jail (count="+(4-player1.getJailCounter())+")");
                    player1.setJailCounter(-1);
                }// If player1 in the jail , for three rounds this command will be work

            }
            else if(commands.get(i).startsWith("Player 2")){
                String[] dice = commands.get(i).split(";");
                int move = Integer.parseInt(dice[1]);
                if(player2.getMoney()==0){
                    printWriter.println(player2.getName() + "\t" + move + "\t" + player2.getPosition() + "\t" + player1.getMoney() + "\t" + player2.getMoney() + "\t" + player2.getName() + " goes bankrupt ");
                    for(String s:show(player1,player2,banker)){
                        printWriter.println(s);
                    }
                    break;
                }
                if(player2.getJailCounter()==0) {
                    player2.setPosition(move);
                    if(!actions.contains(player2.getPosition())) {
                        printWriter.println(gameplay(player2, player1, banker, landMap, railroadMap, companyMap,land2,railroad,company, move,player1,player2));
                    }
                    else {
                        if (chanceMap.contains(player2.getPosition())) {

                            printWriter.println(chanceListPlay(chanceList.getActionList(),communityChestList.getActionList(), player2, player1, banker, move,player1,player2));
                        }
                        else if (communityChestMap.contains(player2.getPosition())) {
                            printWriter.println(communityChestListPlay(communityChestList.getActionList(), player2, player1, banker, move,player1,player2));

                        }
                        else if (otherMap.contains(player2.getPosition())) {
                            printWriter.println(otherGamePlay(player2, player1, banker, move,player1,player2));
                        }
                    }
                }
                else {
                    printWriter.println(player2.getName() + "\t" + move + "\t" + player2.getPosition() + "\t" + player1.getMoney() + "\t" + player2.getMoney() + "\t" + player2.getName() + " in jail (count="+(4-player2.getJailCounter())+")");
                    player2.setJailCounter(-1);
                }
            }// I do the same things to the player2

            if(pass1 != player1.getPassingFromStart()){
                banker.setMoney(-200);
            }// If they are not equal, it means player1 has passed through start. I give money to player1 in the setPosition function
            // And take money from bank in this function
            if(pass2 !=player2.getPassingFromStart()){
                banker.setMoney(-200);
            }
            else if(commands.get(i).startsWith("show")){
                for(String s:show(player1,player2,banker)){
                    printWriter.print(s);
                }
            }


            if(player1.getMoney() ==0 || player2.getMoney() == 0){
                for(String s:show(player1,player2,banker)){
                    printWriter.print(s);
                }
                break;
            }// If one of them has lost all money, then game is going to over
            else if(i==(commands.size()-1)){
                for(String s:show(player1,player2,banker)){
                    printWriter.print(s);
                }
            }// if the commands run out , than our game is going to over




        }



        printWriter.flush();
        printWriter.close();
    }

}
