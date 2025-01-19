import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

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

    public void preOrderTraversal(Node<T> currNode) {
        if (currNode == null) {
            return;
        }
        System.out.println(currNode.element);
        preOrderTraversal(currNode.left);
        preOrderTraversal(currNode.right);
    }

    public void inOrderTraversal(Node<T> currNode) {
        if (currNode == null) {
            return;
        }
        inOrderTraversal(currNode.left);
        System.out.println(currNode.element);
        inOrderTraversal(currNode.right);
    }

    public void postOrderTraversal(Node<T> currNode) {
        if (currNode == null) {
            return;
        }
        postOrderTraversal(currNode.left);
        postOrderTraversal(currNode.right);
        System.out.println(currNode.element);
    }

    public void levelOrderTraversal() {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 0; i < size; i++) {
            Node<T> currNode = queue.poll();
            System.out.println(currNode.element);
            if (currNode.left != null) {
                queue.offer(currNode.left);
            }
            if (currNode.right != null) {
                queue.offer(currNode.right);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {10, 20, 30, 40, 50, 60, 70};
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer i : arr) {
            bst.add(i);
        }
        // test code here...

    }

}