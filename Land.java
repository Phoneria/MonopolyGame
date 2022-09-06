import java.util.ArrayList;

public class Land extends Properties{
    private ArrayList<ArrayList<String>> list = new ArrayList<>();

    public ArrayList<ArrayList<String>> getList() {
        return list;
    }

    public Land() {
        for(ArrayList<String> s: getPropertyJsonReader().getElements().get(0)){
            list.add(s);
        }
    }
}
