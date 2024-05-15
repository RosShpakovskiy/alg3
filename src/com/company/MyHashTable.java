package com.company;

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V val;
        private HashNode<K, V> next;

        public HashNode(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString(){
            return "{ " + key + " " + val + "}";
        }
    }

    private HashNode<K, V>[] chainArray;

    private int M = 11; //default num of chains

    private int size; //size of a table

    //empty table
    public MyHashTable() {
        chainArray = new HashNode[M];
        size = 0;
    }

    //empty table with some M (num of chains)
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    //hashing
    private int hash(K key) {
        return key.hashCode() % M; //Hashing formula
    }

    public void insert(K key, V val) {
        int index = hash(key); //hashing
        HashNode<K, V> head = chainArray[index];

        while (head != null) {
            if (head.key.equals(key)) {
                head.val = val;
                return;
            }
            head = head.next;
        }

        head = chainArray[index];
        HashNode<K, V> newNode = new HashNode<K, V>(key, val);
        newNode.next = head;
        chainArray[index] = newNode;
        size++;
    }

    public V get(K key) {
        int index = hash(key); //hashing
        HashNode<K, V> head = chainArray[index];

        while (head != null) {
            if (head.val.equals(key)) { //is equal?
                return head.val; //found
            }

            head = head.next;  //check next
        }
        return null; //not found
    }

    public V remove(K key) {
        int index = hash(key); //hashing
        HashNode<K, V> head = chainArray[index];
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) { //is equal?
                break; //stop
            }

            prev = head;
            head = head.next; //check next
        }

        size--;

        if (prev != null)
            prev.next = head.next;

        else
            chainArray[index] = head.next;
        return head.val;
    }

    public boolean contains(V val) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];

            while (head != null) {
                if (head.val.equals(val)) //is equal?
                    return true; //found

                head = head.next; //check next
            }
        }
        return false; //not found
    }

    public K getKey(V val) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];

            while (head != null) {
                if (head.val.equals(val)) //is equal?
                    return head.key; //found

                head = head.next; //check next
            }

        }
        return null; // not found
    }
}