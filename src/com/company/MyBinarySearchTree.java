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
                Node temp = current;
                current = findSmallestNode(temp.right);
                current.right = deleteSmallestNode(temp.right);
                current.left = temp.left;
            }
        }
        return current;
    }

    //get the smallest node
    private Node findSmallestNode(Node node) {
        return node.left == null ? node : findSmallest(node.right);
    }

    //delete smallest node
    private Node deleteSmallestNode(Node node) {
        if (node.left == null)
            return  node.right;

        node.left = deleteSmallestNode(node.left);
        return node;
    }

    //Updated iterator
    public Iterable<K> iterator() {
        ArrayList<K> keys = new ArrayList<>();
        inOrderTraversal(root, keys);
        return keys;
    }

    //check all left nodes, then root, then finally right nodes
    private void inOrderTraversal(Node node, ArrayList<K> keys) {
        if (node == null)
            return;

        inOrderTraversal(node.left, keys);

        /* Here, we firstly go to the left to reach the smallest key,
        and then we start to output the keys and values of the nodes.
         */
        System.out.println("Key is " + node.key + " and value is " + node.val);

        keys.add(node.key);
        inOrderTraversal(node.right, keys);
    }

}