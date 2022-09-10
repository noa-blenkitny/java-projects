

import java.util.LinkedList;

public class HashTable {
    private int size;
    private LinkedList<ObjectWithCoordinates>[] hash;

    /**
     * Creates a new HashTable of size -size
     *
     * @param size- the size that you want for the hash table
     */
    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.size = size;
        hash = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            hash[i] = null;
        }
    }

    /**
     * Performs the hash function and returns the result.
     *
     * @param x- the x coordinate of the ObjectWithCoordinates
     * @param y- the y coordinate of the ObjectWithCoordinates
     * @return the result of the hash function
     */
    public int hash_func(int x, int y) {
        return (x + y) % this.size;
    }

    /**
     * Inserts the object to the hash table
     *
     * @param object- the ObjectWithCoordinates that you want to insert.
     */
    public void insert(ObjectWithCoordinates object) {
        int index = hash_func(object.getX(), object.getY());

        LinkedList<ObjectWithCoordinates> points = hash[index];

        if (points == null) {
            points = new LinkedList<ObjectWithCoordinates>();
            points.addFirst(object);

            hash[index] = points;
        } else {
            points.addFirst(object);
        }
    }

    /**
     * Searches the ObjectWithCoordinates with coordinates (x,y) and returns it.
     *
     * @param x- the x coordinate of the ObjectWithCoordinates
     * @param y- the y coordinate of the ObjectWithCoordinates
     * @return the ObjectWithCoordinates with coordinates (x,y),
     * null if it is not in the hash table.
     */
    public ObjectWithCoordinates search(int x, int y) {

        int index = this.hash_func(x, y);
        LinkedList<ObjectWithCoordinates> points = hash[index];

        if (points == null) {
            return null;
        } else {
            Object[] points_array = points.toArray();
            for (int i = 0; i < points_array.length; i++) {
                if (((ObjectWithCoordinates) (points_array[i])).getX() == x && ((ObjectWithCoordinates) (points_array[i])).getY() == y) {
                    return (ObjectWithCoordinates) (points_array[i]);
                }
            }
        }


        return null;
    }
}
	

