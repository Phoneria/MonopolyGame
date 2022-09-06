import java.util.ArrayList;

public class Company extends Properties{
    private ArrayList<ArrayList<String>> list = new ArrayList<>();

    public ArrayList<ArrayList<String>> getList() {
        return list;
    }

    public Company() {
        for(ArrayList<String> s: getPropertyJsonReader().getElements().get(2)){
            list.add(s);
        }
    }
}
