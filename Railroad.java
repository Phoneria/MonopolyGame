import java.util.ArrayList;

public class Railroad extends Properties{
    private ArrayList<ArrayList<String>> list = new ArrayList<>();

    public ArrayList<ArrayList<String>> getList() {
        return list;
    }

    public Railroad() {
        for(ArrayList<String> s: getPropertyJsonReader().getElements().get(1)){
            list.add(s);
        }
    }
}
