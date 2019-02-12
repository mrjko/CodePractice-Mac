package com.company.Practice;


import java.util.Hashtable;
import java.util.Set;

public class HashTableResult {

    private Hashtable<String, Integer> ht;

    public HashTableResult(Hashtable ht) {
        this.ht = ht;
    }

    public void printResult() {
        Set<String> keys = ht.keySet();
        for(String key : keys) {
            System.out.println(key + ": " + ht.get(key));
        }
    }


}
