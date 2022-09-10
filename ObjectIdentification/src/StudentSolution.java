
import java.util.LinkedList;

public class StudentSolution implements MyInterface {

    //creating two avl trees, one where the x coordinate of the point is the key and the other y.
    private AVL<ObjectWithCoordinates> x_tree = new AVL<ObjectWithCoordinates>();
    private AVL<ObjectWithCoordinates> y_tree = new AVL<ObjectWithCoordinates>();

    /**
     * reads a line from the DBFile and creates a point with objectName as the data,
     * objectX as the x coordinate and objectY as the y coordinate.
     * then insert the point to x_tree and y_tree
     *
     * @param objectName- the data of the point.
     * @param objectX-    the x coordinate of the point.
     * @param objectY-    the y coordinate of the point.
     */
    @Override
    public void insertDataFromDBFile(String objectName, int objectX, int objectY) {
        Point point = new Point(objectName, objectX, objectY);
        x_tree.insert(objectX, point);
        y_tree.insert(objectY, point);
    }

    /**
     * return an array with string representation of the points that their coordinates are between
     * ( leftTopX,leftTopY)-(rightBottomX,rightBottomY)
     *
     * @param leftTopX-     the x coordinate of the left point of the range
     * @param leftTopY-     the y coordinate of the left point of the range
     * @param rightBottomX- the x coordinate of the right point of the range
     * @param rightBottomY- the y coordinate of the right point of the range
     * @return an array with string representation of the points that their coordinates are between
     * ( leftTopX,leftTopY)-(rightBottomX,rightBottomY),
     * an empty array if there is none.
     */
    @Override
    public String[] firstSolution(int leftTopX, int leftTopY, int rightBottomX,
                                  int rightBottomY) {
        ObjectWithCoordinates[] x_range = x_tree.range(leftTopX, rightBottomX);
        ObjectWithCoordinates[] y_range = y_tree.range(leftTopY, rightBottomY);
        if (x_range.length == 0 || y_range.length == 0) {
            return new String[0];
        } else {
            HashTable x_table = new HashTable(859); // prime number, not close to a power of 2.
            LinkedList<String> res = new LinkedList<String>();

            for (int x = 0; x < x_range.length; x++) {
                x_table.insert(x_range[x]);
            }

            for (int y = 0; y < y_range.length; y++) {
                ObjectWithCoordinates intersection = x_table.search(y_range[y].getX(), y_range[y].getY());
                if (intersection != null) {
                    res.addFirst(intersection.toString());
                }
            }

            return res.toArray(new String[res.size()]);
        }
    }

    /**
     * return an array with string representation of the points that their coordinates are between
     * ( leftTopX,leftTopY)-(rightBottomX,rightBottomY)
     *
     * @param leftTopX-     the x coordinate of the left point of the range
     * @param leftTopY-     the y coordinate of the left point of the range
     * @param rightBottomX- the x coordinate of the right point of the range
     * @param rightBottomY- the y coordinate of the right point of the range
     * @return an array with string representation of the points that their coordinates are between
     * ( leftTopX,leftTopY)-(rightBottomX,rightBottomY),
     * an empty array if there is none.
     */
    @Override
    public String[] secondSolution(int leftTopX, int leftTopY,
                                   int rightBottomX, int rightBottomY) {
        ObjectWithCoordinates[] x_range = x_tree.range(leftTopX, rightBottomX);
        ObjectWithCoordinates[] y_range = y_tree.range(leftTopY, rightBottomY);
        ObjectWithCoordinates[] min;

        LinkedList<String> res = new LinkedList<String>();

        if (x_range.length >= y_range.length) {
            min = y_range;


            for (int i = 0; i < min.length; i++) {
                if (((ObjectWithCoordinates) min[i]).getX() >= leftTopX && ((ObjectWithCoordinates) min[i]).getX() <= rightBottomX) {
                    res.addFirst(min[i].toString());
                }
            }
        } else {
            min = x_range;
            for (int i = 0; i < min.length; i++) {
                if (((ObjectWithCoordinates) min[i]).getY() >= leftTopY && ((ObjectWithCoordinates) min[i]).getY() <= rightBottomY) {
                    res.addFirst(min[i].toString());
                }
            }
        }

        return res.toArray(new String[res.size()]);
    }
}

