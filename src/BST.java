import java.util.Iterator;
import java.util.Stack;
public class BST<T extends Comparable<T>> implements Iterable<T>{
    private Node<T> root;
    private int size;

    // Implement the constructor
    public BST(){
        this.root = null;
        this.size = 0;
    }

    public Node<T> getRoot(){
        return this.root;
    }

    // Implement the clear method
    public void clear(){
        root = null;
    }
    // Implement the size method
    public int size(){ return this.size; }

    public boolean isEmpty(){ return this.root == null;}
    // Implement the insert method
    public void insert(T e){
        root = Insert(root, e);
    }

    public Node<T> Insert(Node<T> node, T data){
        if(node == null)
            return new Node<T>(data, null);

        int cval = data.compareTo(node.getData());
        if(cval < 0){
            node.setLeft(Insert(node.getLeft(), data));
        } else if (cval >0) {
            node.setRight(Insert(node.getRight(), data));
        }

        return node;

    }

    // Implement the search method
    public Node<T> searchRecursive(Node<T> r, T e){
        if(r == null)
            return r;

        if(e.compareTo(r.getData()) == 0)
            return r;

        if(e.compareTo(r.getData()) < 0)
            return searchRecursive(r.getLeft(), e);

        if(e.compareTo(r.getData()) > 0)
            return searchRecursive(r.getRight(), e);

        System.out.println("We should never arrive here");
        return r; // Should never arrive here.
    }


    // Implement the remove method

    public void remove(T e){

        Node<T> node = searchRecursive(this.root, e);
        if (node == null) {
            return;
        }
        size--;


        if(node.getLeft() != null && node.getRight() != null){
            Node<T> successor = Successor(node);

            node.setData(successor.getData());
            node = successor;
        }

        Node<T> child  = (node.getLeft() != null) ? node.getLeft() : node.getRight();

        if(child != null) {
            child.setParent(node.getParent());

            if (node.getParent() == null) {
                root = child;
            } else if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(child);
            } else {
                node.getParent().setRight(child);
            }
        } else if (node.getParent() == null){
            root = null;
        } else {
            if(node == node.getParent().getLeft()){
                node.getParent().setLeft(null);
            } else {
                node.getParent().setRight(null);
            }
        }
    }



    public Node<T> Successor(Node<T> node) {
        Node<T> leftMostRightSubtree = node.getRight();
        while (leftMostRightSubtree.getLeft() != null) {
            leftMostRightSubtree = leftMostRightSubtree.getLeft();
        }
        return leftMostRightSubtree;


    }

    // Implement the iterator method
    public Iterator<T> iterator(){
        return new BSTIterator();
    }

    // Implement the BSTIterator class
    private class BSTIterator implements Iterator<T> {
        private Stack<Node<T>> stack;

        public BSTIterator(){
            stack = new Stack<>();
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public T next() {
            Node<T> node = stack.pop();
            if(node.getRight() != null)
                stack.push(node.getRight());
            if(node.getLeft() != null)
                stack.push(node.getLeft());

            return node.getData();
        }




    }

}



