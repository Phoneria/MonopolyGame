public class Game {
    private ListJsonReader listJsonReader=new ListJsonReader();// It holds all cards in the arraylist
    private PropertyJsonReader propertyJsonReader =new PropertyJsonReader();// It holds all property's in arraylist

    public ListJsonReader getListJsonReader() {
        return listJsonReader;
    }

    public PropertyJsonReader getPropertyJsonReader() {
        return propertyJsonReader;
    }
}
