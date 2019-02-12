package com.company.Practice;

import java.util.Arrays;

public class HashSet {

    private int[] hashset;
    private static int SIZE = 10000;

    /** Initialize your data structure here. */
    public HashSet() {
        hashset = new int[SIZE];
        Arrays.fill(hashset, -1);
    }

    public void add(int key) {
        int hash = key % SIZE;
        while (hashset[hash] != -1) {
            hash = (hash >= 10000) ? 0 : hash+1;
        }
        hashset[hash] = key;
    }

    public void remove(int key) {
        int hash = key % SIZE;
        while (hashset[hash] != -1) {
            if (hashset[hash] == key) {
                hashset[hash] = -1;
                return;
            }
            hash++;
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        for (int i : hashset) {
            if (i == key)
                return true;
        }
        return false;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */