import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;


public class ListJsonReader{
    private  ArrayList<String> elements1 = new ArrayList<>();
    private  ArrayList<String> elements2 = new ArrayList<>();
    private  ArrayList<ArrayList<String>> elements = new ArrayList<ArrayList<String>>();

    public  ArrayList<ArrayList<String>> getElements() {
        return elements;
    }

    public ListJsonReader(){
        JSONParser processor = new JSONParser();
        try (Reader file = new FileReader("list.json")){
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray chanceList = (JSONArray) jsonfile.get("chanceList");
            for(Object i:chanceList){
                elements1.add((String)((JSONObject)i).get("item"));
            }
            JSONArray communityChestList = (JSONArray) jsonfile.get("communityChestList");
            for(Object i:communityChestList){
                elements2.add((String)((JSONObject)i).get("item"));
            }
            elements.add(elements1);
            elements.add(elements2);
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

}

