package com.company;

public class Main {

    public static void main(String[] args) {
        MyBinarySearchTree binaryTree = new MyBinarySearchTree();

        binaryTree.insert(5, 2);
        binaryTree.insert(3, 7);
        binaryTree.insert(4, 11);
        binaryTree.insert(2, 80);
        binaryTree.insert(6, 23);
        binaryTree.insert(7, 12);

        System.out.println(binaryTree.iterator());
        /* The output:
        Key is 2 and value is 80
        Key is 3 and value is 7
        Key is 4 and value is 11
        Key is 5 and value is 2
        Key is 6 and value is 23
        Key is 7 and value is 12
        [2, 3, 4, 5, 6, 7]
         */
        System.out.println();

        System.out.println(binaryTree.get(4)); //11
        System.out.println(binaryTree.get(8)); //null
        System.out.println(binaryTree.get(3)); //7
        System.out.println();

        binaryTree.remove(4);
        binaryTree.remove(6);
        System.out.println(binaryTree.get(6)); //null
        System.out.println();

        System.out.println(binaryTree.iterator());
        /*
        Key is 2 and value is 80
        Key is 3 and value is 7
        Key is 5 and value is 2
        Key is 7 and value is 12
        [2, 3, 5, 7]
         */
        System.out.println();
    }
}