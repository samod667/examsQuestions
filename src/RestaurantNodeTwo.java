public class RestaurantNodeTwo {
    private String _food;

    private RestaurantNodeTwo _next;
    private RestaurantNodeTwo _prev;

    public RestaurantNodeTwo(String s){
        _food = s;
        _next = null;
        _prev = null;
    }

    public String getFood(){
        return _food;
    }

    public void setNext(RestaurantNodeTwo node){
        _next = node;
    }

    public RestaurantNodeTwo getNext(){
        return _next;
    }
}
