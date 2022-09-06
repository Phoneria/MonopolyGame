import java.util.ArrayList;

public class ChanceList extends ActionSquares{


    private ArrayList<String> list = new ArrayList<>();

    public ArrayList<String> getActionList() {
        return list;
    }

    public ChanceList() {
        for(String s: getListJsonReader().getElements().get(0)){
            list.add(s);
    }

    }
}
