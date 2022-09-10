
public class AVLNode<T> {
    private AVLNode<T> leftchild;
    private AVLNode<T> rightchild;
    private AVLNode<T> father;
    private int key;
    private T data;
    private int balance;

    /**
     * Creates a new AVLNode with the key,data and balance 0
     *
     * @param key-  the key of the node
     * @param data- the data of the node
     */
    public AVLNode(int key, T data) {
        this.leftchild = null;
        this.rightchild = null;
        this.father = null;
        this.key = key;
        this.data = data;
        this.balance = 0;
    }


    /**
     * return the left child of the node
     *
     * @return the left child
     */
    public AVLNode<T> getLeftChild() {
        return this.leftchild;
    }

    /**
     * sets leftchild as the left child of this node.
     *
     * @param leftchild- the avl node that you want to set as the left child
     */
    public void setLeftChild(AVLNode<T> leftchild) {
        this.leftchild = leftchild;
    }

    /**
     * return the right child of the node
     *
     * @return the right child
     */
    public AVLNode<T> getRightChild() {
        return this.rightchild;
    }

    /**
     * sets rightchild as the right child of this node.
     *
     * @param rightchild- the avl node that you want to set as the right child
     */
    public void setRightChild(AVLNode<T> rightchild) {
        this.rightchild = rightchild;
    }

    /**
     * return the father of the node
     *
     * @return the father
     */
    public AVLNode<T> getFather() {
        return this.father;
    }

    /**
     * sets father as the father of this node.
     *
     * @param father- the avl node that you want to set as the father
     */
    public void setFather(AVLNode<T> father) {
        this.father = father;
    }

    /**
     * return the key of the node
     *
     * @return the key
     */
    public int getKey() {
        return this.key;
    }

    /**
     * return the data of the node
     *
     * @return the data
     */
    public T getData() {
        return this.data;
    }

    /**
     * return the balance of the node
     *
     * @return the balance
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * sets balance as the balance of this node.
     *
     * @param balance- the balance(int) that you want to set as the balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Returns a string representation of AVLNode
     */
    @Override
    public String toString() {

        String s = "";

        if (getLeftChild() != null) {
            s += "(";
            s += getLeftChild().toString();
            s += ")";
        }

        s += getKey();

        if (getRightChild() != null) {
            s += "(";
            s += getRightChild().toString();
            s += ")";
        }

        return s;
    }
}

