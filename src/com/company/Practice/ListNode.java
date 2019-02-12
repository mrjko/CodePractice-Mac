package com.company.Practice;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) {val = x;}

    public void print() {
        ListNode curr = this;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
    }
}