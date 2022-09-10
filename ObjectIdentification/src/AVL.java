
import java.util.LinkedList;

public class AVL<T> {
    private AVLNode<T> root;

    /**
     * Creates a new empty AVL
     */
    public AVL() {
        this.root = null;
    }

    private void left_rotate(AVLNode<T> x) {
        AVLNode<T> y = x.getRightChild();
        x.setRightChild(y.getLeftChild());
        if (y.getLeftChild() != null) {
            y.getLeftChild().setFather(x);
        }

        y.setFather(x.getFather());

        if (x.getFather() == null) {
            this.root = y;
        } else if (x == x.getFather().getLeftChild()) {
            (x.getFather()).setLeftChild(y);
        } else {
            (x.getFather()).setRightChild(y);
        }

        y.setLeftChild(x);
        x.setFather(y);

        // updating the balance.
        x.setBalance(x.getBalance() - 1 - Math.max(0, y.getBalance()));
        y.setBalance(y.getBalance() - 1 + Math.min(0, x.getBalance()));
    }

    private void right_rotate(AVLNode<T> x) {
        AVLNode<T> y = x.getLeftChild();
        x.setLeftChild(y.getRightChild());
        if (y.getRightChild() != null) {
            y.getRightChild().setFather(x);
        }

        y.setFather(x.getFather());
        if (x.getFather() == null) {
            this.root = y;
        } else if (x == x.getFather().getRightChild()) {
            x.getFather().setRightChild(y);
        } else {
            x.getFather().setLeftChild(y);
        }

        y.setRightChild(x);
        x.setFather(y);

        // updating the balance
        x.setBalance(x.getBalance() + 1 - Math.min(0, y.getBalance()));
        y.setBalance(y.getBalance() + 1 + Math.max(0, x.getBalance()));
    }

    private void update_balance(AVLNode<T> node) {
        if (node.getBalance() < -1 || node.getBalance() > 1) {
            rebalance(node);
            return;
        }

        if (node.getFather() != null) {
            if (node == node.getFather().getLeftChild()) {
                node.getFather().setBalance(node.getFather().getBalance() - 1);
            }

            if (node == node.getFather().getRightChild()) {
                node.getFather().setBalance(node.getFather().getBalance() + 1);
            }

            if (node.getFather().getBalance() != 0) {
                update_balance(node.getFather());
            }
        }
    }

    private void rebalance(AVLNode<T> node) {
        if (node.getBalance() > 0) {
            if (node.getRightChild().getBalance() < 0) {
                right_rotate(node.getRightChild());
                left_rotate(node);
            } else {
                left_rotate(node);
            }
        } else if (node.getBalance() < 0) {
            if (node.getLeftChild().getBalance() > 0) {
                left_rotate(node.getLeftChild());
                right_rotate(node);
            } else {
                right_rotate(node);
            }
        }
    }

    /**
     * creates a new AVLNode with the key and data and insert it into the tree.
     *
     * @param key-  the key of the node
     * @param data- the data of the node
     */
    public void insert(int key, T data) {
        AVLNode<T> node = new AVLNode<T>(key, data);
        AVLNode<T> y = null;
        AVLNode<T> x = this.root;

        while (x != null) {
            y = x;
            if (node.getKey() < x.getKey()) {
                x = x.getLeftChild();
            } else {
                x = x.getRightChild();
            }
        }

        node.setFather(y);
        if (y == null) {
            root = node;
        } else if (node.getKey() < y.getKey()) {
            y.setLeftChild(node);
        } else {
            y.setRightChild(node);
        }

        update_balance(node);
    }

    private AVLNode<T> search_helper(AVLNode<T> node, int key) {
        if (node == null || key == node.getKey()) {
            return node;
        }

        if (key < node.getKey()) {
            return search_helper(node.getLeftChild(), key);
        }
        return search_helper(node.getRightChild(), key);
    }


    /**
     * search the node with the key- key and returns its data.
     *
     * @param key- the key of the node that you want to search
     * @return the data of the node with the key, null if the node is not in the tree
     */
    public T search(int key) {
        return search_helper(this.root, key).getData();
    }

    /**
     * return the root of the tree
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        return this.root;
    }

    /**
     * returns a linked list of ObjectWithCoordinates that their key's in the tree
     * are in range [a,b]
     *
     * @param node- the node in the tree you want to start the search
     * @param a-    the bottom of the range
     * @param b-    the top of the range
     * @param res-  the linked list that you want to insert the objects to
     * @returna linked list of ObjectWithCoordinates that their key's in the tree
     * are in range [a,b]
     */
    private LinkedList<ObjectWithCoordinates> get_values(AVLNode<T> node, int a, int b, LinkedList<ObjectWithCoordinates> res) {
        if (node == null)
            return res;
        if (node.getKey() >= a && node.getKey() <= b) {
            res.addLast((ObjectWithCoordinates) node.getData());
            if (node.getLeftChild() != null) {
                this.get_values(node.getLeftChild(), a, b, res);
            }

            if (node.getRightChild() != null) {
                this.get_values(node.getRightChild(), a, b, res);
            }
        } else if (node.getKey() < a) {
            this.get_values(node.getRightChild(), a, b, res);
        } else {
            this.get_values(node.getLeftChild(), a, b, res);
        }
        return res;
    }

    /**
     * returns an array of ObjectWithCoordinates that their key's in the tree are in range [a,b]
     *
     * @param a- the bottom of the range
     * @param b- the top of the range
     * @return- an array of ObjectWithCoordinates that their key's in the tree are in range [a,b]
     */
    public ObjectWithCoordinates[] range(int a, int b) {
        LinkedList<ObjectWithCoordinates> res = new LinkedList<ObjectWithCoordinates>();
        this.get_values(root, a, b, res);
        return res.toArray(new ObjectWithCoordinates[res.size()]);
    }
}
	

				
	


