import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;


public class PropertyJsonReader {
    private ArrayList<ArrayList<String>> elements1 = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> elements2 = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> elements3 = new ArrayList<ArrayList<String>>();

    private ArrayList<ArrayList<ArrayList<String>>> elements = new ArrayList<ArrayList<ArrayList<String>>>();

    public ArrayList<ArrayList<ArrayList<String>>> getElements() {
        return elements;
    }


    public PropertyJsonReader(){

        JSONParser processor = new JSONParser();
        try (Reader file = new FileReader("property.json")){
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray Land = (JSONArray) jsonfile.get("1");
            for(Object i:Land){
                ArrayList<String> elements0 = new ArrayList<>();
                //You can reach items by using statements below:
                elements0.add(String.valueOf(Integer.parseInt((String)((JSONObject)i).get("id"))));
                elements0.add((String)((JSONObject)i).get("name"));
                elements0.add(String.valueOf(Integer.parseInt((String)((JSONObject)i).get("cost"))));
                //And you can add these items to any data structure (e.g. array, linkedlist etc.
                elements1.add(elements0);

            }


            JSONArray RailRoad = (JSONArray) jsonfile.get("2");
            for(Object i:RailRoad){
                ArrayList<String> elements0 = new ArrayList<>();
                //You can reach items by using statements below:
                elements0.add(String.valueOf(Integer.parseInt((String)((JSONObject)i).get("id"))));
                elements0.add((String)((JSONObject)i).get("name"));
                elements0.add(String.valueOf(Integer.parseInt((String)((JSONObject)i).get("cost"))));
                //And you can add these items to any data structure (e.g. array, linkedlist etc.
                elements2.add(elements0);
            }

            JSONArray Company = (JSONArray) jsonfile.get("3");
            for(Object i:Company){
                ArrayList<String> elements0 = new ArrayList<>();
                //You can reach items by using statements below:
                elements0.add(String.valueOf(Integer.parseInt((String)((JSONObject)i).get("id"))));
                elements0.add((String)((JSONObject)i).get("name"));
                elements0.add(String.valueOf(Integer.parseInt((String)((JSONObject)i).get("cost"))));
                elements3.add(elements0);

            }

            elements.add(elements1);
            elements.add(elements2);
            elements.add(elements3);
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
    }
    //You can add function(s) if you want
}