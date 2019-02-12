package com.company.Practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeNode implements Comparable<TreeNode>{
    public Integer val;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(Integer i) {
        this.val = i;
    }

    public TreeNode(Integer[] arr) {

        if (arr.length < 1) {
            System.out.println("empty tree made");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        int arrCounter = 1;
        int childCounter = 2;

        this.val = arr[0];

        queue.add(this);

        while (arrCounter < arr.length) {
            if (childCounter == 0) {
                queue.poll();
                childCounter = 2;
                continue;
            } else {
                if (childCounter % 2 == 0) { // add to left
                    TreeNode left = new TreeNode(arr[arrCounter]);
                    queue.peek().left = left;
                    queue.add(left);
                } else {
                    TreeNode right = new TreeNode(arr[arrCounter]);
                    queue.peek().right = right;
                    queue.add(right);
                }
                childCounter--;
            }
            arrCounter++;
        }

    }

    @Override
    public int compareTo(TreeNode o) {
        return o.val - this.val;
    }


}
