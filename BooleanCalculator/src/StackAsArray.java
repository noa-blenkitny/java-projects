
public class StackAsArray implements Stack {
    private Object[] arr;
    private static final int increment = 5;
    private int size;
    private int counter;

    public StackAsArray() {
        arr = new Object[5];
        this.size = 5;
        this.counter = 0;
    }

    /**
     * Add o to the top of this Stack.
     * If the stack is full enlarge the stack.
     *
     * @param o The object to be pushed.
     */
    public void push(Object o) {
        Object[] tmpArr;

        if (counter == size) {
            tmpArr = new Object[size + increment];
            for (int j = 0; j < arr.length; j = j + 1) {
                tmpArr[j] = arr[j];
            }
            arr = tmpArr;
            size = size + increment;
        }

        arr[counter] = o;
        counter++;
    }

    /**
     * Remove and return the top element of this Stack.
     *
     * @return the formerly top element of the stack.
     * @throws RuntimeException if trying to pop from an empty stack.
     */
    public Object pop() {
        Object res;

        if (isEmpty()) {
            throw new RuntimeException("The Stack is empty!");
        } else {
            res = arr[counter - 1];
            arr[counter - 1] = null;
            this.counter--;
            return res;
        }
    }

    /**
     * Returns whether the stack is empty.
     *
     * @return true if and only if the stack is empty.
     */
    public boolean isEmpty() {
        return this.counter == 0;
    }
}


