package com.company;
import java.util.ArrayList;

public class MyBinarySearchTree<K extends Comparable<K>, V> {
    public Node root;

    public class Node {
        private K key;
        private V val;
        Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public void insert(K key, V val) {
        root = insert(root, key, val);
    }

    private Node insert(Node current, K key, V val) {
        if (current == null)
            return new Node(key, val);
        int comp = key.compareTo(current.key);

        if (comp < 0)
            current.left = insert(current.left, key, val);
        else if (comp > 0)
            current.right = insert(current.right, key, val);
        else
            current.val = val;
        return current;
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    public V get(K key) {
        return get(root, key);
    }

    public V get(Node current, K key) {
        while (current != null) {
            int comp = key.compareTo(current.key);

            if (comp < 0)
                current = current.left;
            else if (comp > 0)
                current = current.right;
            else
                return current.val;
        }

        return null;
    }

    public void remove(K key) {
        root = remove(root, key);
    }

    private Node remove(Node current, K key) {
        if (current == null)
            return null;
        int comp = key.compareTo(current.key);

        if (comp < 0)
            current.left = remove(current.left, key);
        else if (comp > 0)
            current.right = remove(current.right, key);
        else {
            if (current.left == null && current.right == null)
                return null;

            if (current.left == null)
                return current.right;

            if (current.right == null)
                return current.left;

            Node node = current;
            current = findSmallest(node.right);
            current.right = deleteSmallest(node.right);
            current.left = node.left;
        }
        return current;
    }

    private Node findSmallest(Node node) {
        return node.left == null ? node : findSmallest(node.right);
    }

    private Node deleteSmallest(Node node) {
        if (node.left == null)
            return  node.right;

        node.left = deleteSmallest(node.left);
        return node;
    }

    /*I dont know how to make Iterable<K> and accessible K and V at the same time*/
    public Iterable<Node> iter() {
        ArrayList<Node> nodes = new ArrayList<>();
        inOrderTraversal(root, nodes);
        return nodes;
    }

    private void inOrderTraversal(Node node, ArrayList<Node> nodes) {
        if (node == null)
            return; //stop

        inOrderTraversal(node.left, nodes);
        nodes.add(node);
        inOrderTraversal(node.right, nodes);
    }
}