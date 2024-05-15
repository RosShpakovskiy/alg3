package com.company;
import java.util.ArrayList;

public class MyBinarySearchTree<K extends Comparable<K>, V> {
    public Node root; //Main element (root) of a tree

    public class Node {
        private K key;
        private V val;
        Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private int size; //size of a tree

    //empty tree
    public void BinarySearchTree() {
        root = null;
        size = 0;
    }

    //return size of a tree
    public int size() {
        return size;
    }

    //insert a value with some key
    public void insert(K key, V val) {
        root = insert(root, key, val);
    }

    //overloaded
    private Node insert(Node current, K key, V val) {
        if (current == null) {
            size++;
            return new Node(key, val);
        }
        int comp = key.compareTo(current.key); //compare two keys (left or right movement)

        if (comp < 0)
            current.left = insert(current.left, key, val);
        else if (comp > 0)
            current.right = insert(current.right, key, val);
        else
            current.val = val;
        return current;
    }

    //get the value from a key
    public V get(K key) {
        return get(root, key);
    }

    //overloaded
    public V get(Node current, K key) {
        while (current != null) {
            int comp = key.compareTo(current.key); //compare two keys (left or right movement)

            if (comp < 0)
                current = current.left;
            else if (comp > 0)
                current = current.right;
            else
                return current.val;
        }

        return null;
    }

    //remove the node with some key
    public void remove(K key) {
        root = remove(root, key);
    }

    //overloaded
    private Node remove(Node current, K key) {
        if (current == null)
            return null;
        int comp = key.compareTo(current.key); //compare two keys (left or right movement)

        if (comp < 0)
            current.left = remove(current.left, key);
        else if (comp > 0)
            current.right = remove(current.right, key);
        else {
            //no children
            if (current.left == null && current.right == null)
                return null;

            //one child (right)
            if (current.left == null)
                return current.right;

            //one child (left)
            if (current.right == null)
                return current.left;

            //two children
            else {
                Node node = current;
                current = findSmallest(node.right);
                current.right = deleteSmallest(node.right);
                current.left = node.left;
            }
        }
        return current;
    }

    //get the smallest node
    private Node findSmallest(Node node) {
        return node.left == null ? node : findSmallest(node.right);
    }

    //delete smallest node
    private Node deleteSmallest(Node node) {
        if (node.left == null)
            return  node.right;

        node.left = deleteSmallest(node.left);
        return node;
    }

    //I don't know how to make Iterable<K> and accessible K and V at the same time
    public Iterable<Node> iter() {
        ArrayList<Node> nodes = new ArrayList<>();
        inOrderTraversal(root, nodes);
        return nodes;
    }

    //check all left nodes, then root, then finally right nodes
    private void inOrderTraversal(Node node, ArrayList<Node> nodes) {
        if (node == null)
            return; //stop

        inOrderTraversal(node.left, nodes);
        nodes.add(node);
        inOrderTraversal(node.right, nodes);
    }
}