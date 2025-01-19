import java.util.Comparator;

public class BinarySearchTree<T> {

    private int size;
    private Node<T> root;
    private Comparator<T> comparator;

    public BinarySearchTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        this(null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T element) {
        elementNotNullCheck(element);

        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        Node<T> parentNode = null;
        Node<T> currentNode = root;
        int cmpRes = 0;
        while (currentNode != null) {
            cmpRes = compare(element, currentNode.element);
            parentNode = currentNode;
            if (cmpRes == 0) {
                currentNode.element = element;
                return;
            } else if (cmpRes < 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        Node<T> newNode = new Node<>(element, parentNode);
        if (cmpRes > 0) {
            parentNode.right = newNode;
        } else {
            parentNode.left = newNode;
        }
        size++;
    }

    private int compare(T e1, T e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<T>) e1).compareTo(e2);
    }

    private void elementNotNullCheck(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
    }

    private static class Node<T> {

        T element;
        Node<T> left;
        Node<T> right;
        Node<T> parent;

        public Node(T element, Node<T> parent) {
            this.element = element;
            this.parent = parent;
        }

    }

}