
public class RecPoint implements ObjectWithCoordinates {
    private int x;
    private int y;
    private Object Data;

    //Constructor for a point in Rectangle
    public RecPoint(Object data, int X, int Y){
        this.x = X;
        this.y = Y;
        this.Data = data;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;}

    public Object getData() {
        return this.Data;
    }

    // to string method
    public String toString() {
    String res = (Data + " X=" + x + " Y=" + y);
    return res;
    }
}
