public class RestaurantListTwo {
    private RestaurantNodeTwo _head;
    private RestaurantNodeTwo _tail;

    public RestaurantListTwo(){
        _head = null;
        _tail = null;
    }

    public void addFood(String s){
        RestaurantNodeTwo node = new RestaurantNodeTwo(s);

        if(_head == null){
            _head = node;
        } else{
            RestaurantNodeTwo ptr = _head;
            while(ptr.getNext() != null){
                ptr = ptr.getNext();
            }
            ptr.setNext(node);
        }
    }

    public String toString(){
        return toString(this._head, "");
    }

    private String toString(RestaurantNodeTwo node, String str){
        if(node == null){
            return str;
        }

        str += node.getFood() + " ";

        return toString(node.getNext(), str);
    }

    public int findMinDiff(String x, String y){
        RestaurantNodeTwo ptr = this._head;

        int xIndex = -1, yIndex = -1;
        int minDiff = Integer.MAX_VALUE;
        int index = 0;

        while(ptr != null){
            if(ptr.getFood().equals(x)){
                xIndex = index;
            }
            if(ptr.getFood().equals(y)){
                yIndex = index;
            }

            if(xIndex != -1 && yIndex != -1){
                if(Math.abs(xIndex - yIndex) < minDiff){
                    minDiff = Math.abs(xIndex - yIndex);
                }
            }

            index++;
            ptr = ptr.getNext();
        }

        return minDiff;
    }

}
