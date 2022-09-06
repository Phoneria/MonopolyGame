import java.util.ArrayList;

public class CommunityChestList extends ActionSquares{

    private ArrayList<String> list = new ArrayList<>();

    public ArrayList<String> getActionList() {
        return list;
    }

    public CommunityChestList() {
        for(String s: getListJsonReader().getElements().get(1)){
            list.add(s);
        }
    }
}
