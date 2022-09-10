
public class Point implements ObjectWithCoordinates {
    private String data;
    private int x;
    private int y;

    /**
     * Creates a new point with data name and x,y coordinates
     *
     * @param name- the name of the point.
     * @param x-    the x coordinate of the Point.
     * @param y-    the x coordinate of the Point.
     */
    public Point(String name, int x, int y) {
        this.data = name;
        this.x = x;
        this.y = y;
    }

    /**
     * return the x coordinate of the Point.
     *
     * @return the x coordinate of the Point
     */
    public int getX() {
        return this.x;
    }

    /**
     * return the y coordinate of the Point.
     *
     * @return the y coordinate of the Point
     */
    public int getY() {
        return this.y;
    }

    /**
     * return data (name) the Point.
     *
     * @return the data
     */
    public Object getData() {
        return this.data;
    }

    /**
     * Returns a string representation of Point
     */
    @Override
    public String toString() {
        String s = "";
        s += this.data;
        s += " X=";
        s += (this.x);
        s += " Y=";
        s += this.y;
        return s;
    }
}
