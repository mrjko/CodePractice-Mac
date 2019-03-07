package com.company;


import com.company.Practice.*;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.*;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Main {

    private static int COUNTER = 1;

    static class StringLengthComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length();
        }
    }

    public static int searchInsert(int[] nums, int target) {
        int j = 0;
        for (int i : nums) {
            if ((i == target) || (i > target))
                return j;

            j++;
        }

        return j;
    }

    public static int[] plusOne(int[] digits) {
        ArrayList<Integer> res = new ArrayList<>();
        boolean carry = false;
        for (int i = digits.length-1; i > 0; i--) {
            int curr = digits[i];
            if (carry)
                curr += 1;

            if (curr == 10) {
                res.add(i, 0);
                carry = true;
            } else {

            }

        }
        return new int[]{};
    }

    public static int[] cellCompete(int[] cells, int days) // if neighbours are equal then itself will be 0 next day otherwise 1
    {
        // edge cases, will just return original set if the day is invalid or 0
        if (days <= 0) {
            return cells;
        }

        int[] ans = cells;

        while (days > 0) {
            ans = cellCompeteHelper(ans);
            days--;
        }

        return ans;

    }

    public static int[] cellCompeteHelper(int[] cells) {

        if (cells.length == 0) {
            return cells;
        }

        if (cells.length == 1) {
            return new int[]{0};
        }

        int[] ans = new int[cells.length];

        int prev, next;

        for (int curr = 0; curr < cells.length; curr++) {
            prev = curr - 1;
            next = curr + 1;
            if (prev < 0) { // first case
                if (cells[next] == 0) {
                    ans[curr] = 0;
                } else {
                    ans[curr] = 1;
                }
            } else if (next > cells.length - 1) { // last case
                if (cells[prev] == 0) {
                    ans[curr] = 0;
                } else {
                    ans[curr] = 1;
                }
            } else { // middle cases
                if (cells[prev] != cells[next]) {
                    ans[curr] = 1;
                } else {
                    ans[curr] = 0;
                }
            }
        }

        return ans;
    }

    public static boolean findGCD(int[] arr, int denom) {
        for (int i : arr) {
            if (i % denom != 0) {
                return false;
            }
        }
        return true;
    }


    public static int generalizedGCD(int arr[]) // if doesnt work 1 always work
    {
        // INSERT YOUR CODE HEREe
        if (arr.length == 1) {
            return arr[0];
        }

        if (arr.length == 0) {
            return 0;
        }


        Arrays.sort(arr); // sort it first so start with smallest

        int curr = 1;
        int ans = -1;

        while (curr <= arr[0]) {
            if (findGCD(arr, curr)) {
                ans = curr;
            }
            curr++;
        }

        return ans;
    }

    public static List<List<Integer>> amazonTest() {


        Comparator<List<Integer>> distComparator = new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                Double x1Square = Math.pow(o1.get(0), 2);
                Double x2Square = Math.pow(o1.get(1), 2);
                Double xDist = Math.sqrt(x1Square + x2Square);

                Double y1Square = Math.pow(o2.get(0), 2);
                Double y2Square = Math.pow(o2.get(1), 2);
                Double yDist = Math.sqrt(y1Square + y2Square);

                return xDist.intValue() - yDist.intValue();
            }
        };

        PriorityQueue<List<Integer>> test = new PriorityQueue<List<Integer>>(10, distComparator);

        ArrayList<List<Integer>> allLocations = new ArrayList<List<Integer>>();


        ArrayList<Integer> innerList = new ArrayList<>();
        innerList.add(1);
        innerList.add(7);

        ArrayList<Integer> innerList2 = new ArrayList<>();
        innerList2.add(4);
        innerList2.add(4);

        ArrayList<Integer> innerList3 = new ArrayList<>();
        innerList3.add(-2);
        innerList3.add(-2);

        allLocations.add(innerList);
        allLocations.add(innerList2);
        allLocations.add(innerList3);

        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();

        for (List<Integer> list : allLocations) {
            test.add(list);
        }

        Integer counter = 2;

        while (!test.isEmpty() && counter > 0) {
            res.add(test.poll());
            counter--;
        }

        return res;
    }

    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public static boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        return (left.val == right.val)
                && isMirror(left.right, right.left)
                && isMirror(left.left, right.right);
    }

    public static int[] twoSums(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
    }

    public static void addNext(ListNode target, int x) {
        ListNode curr = target;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new ListNode(x);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (isOpenBraces(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char curr = stack.pop();
                if (!isMatchingBraces(curr, c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }


    public static boolean isMatchingBraces(char open, char close) {
        if (open == '(')
            return close == ')';
        else if (open == '{')
            return close == '}';
        else if (open == '[')
            return close == ']';

        return false;
    }

    public static boolean isOpenBraces(char c) {
        return (c == '(') || (c == '{') || (c == '[');
    }

    public static void main(String[] args) {

//        PriorityQueue<TreeNode> q = new PriorityQueue<>((a,b) -> {
//            String test = "test";
//            for (int i = 0; i < test.length(); i++) {
//
//            }
//            return a.val - b.val;
//        });
//
//        q.add(new TreeNode(3));
//        q.add(new TreeNode(2));
//        q.add(new TreeNode(1));
//
//        while (!q.isEmpty()) {
//            echo(q.poll().val);
//        }
//
//        String[] testArr = new String[]{"apple", "orange", "pepperoni"};

//        Arrays.sort(testArr, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o2.length() - o1.length();
//            }
//        });

//        Arrays.sort(testArr, new StringLengthComparator());

//        Arrays.sort(testArr, (a, b) -> b.length() - a.length());

//        TreeNode[] testTreeNodeArr = new TreeNode[]{new TreeNode(1), new TreeNode(2)};
//
//        Arrays.sort(testTreeNodeArr, (a, b) -> b.val - a.val);
//
//        for (TreeNode n : testTreeNodeArr) {
//            echo(n.val);
//        }
//

        echo(sortColors(new int[]{2,0,1}));
    }

    // sort into 0,1,2
    // Could you come up with a one-pass algorithm using only constant space?
    // if 2 just bring itt o the back and if 0 just bring it to the front
    public static int[] sortColors(int[] nums) {
        int temp;
        int start = 0;
        int end = nums.length - 1;
        int i = 0;

        while (start < end && i <= end) {
            if (nums[i] == 0) { // 0
                temp = nums[start];
                nums[start] = nums[i];
                nums[i] = temp;
                start++;
                i++;
            } else if (nums[i] == 2) { // 2
                temp = nums[end];
                nums[end] = nums[i];
                nums[i] = temp;
                end--;
            } else { // 1
                i++;
            }
        }

        return nums;
    }

    public static int[] removeMoreThan3Duplicates(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i : nums) {
            if (!count.containsKey(i)) {
                count.put(i, 1);
                res.add(i);
            } else if (count.containsKey(i) && count.get(i) < 3) {
                count.put(i, count.get(i) + 1);
                res.add(i);
            }
        }

        int[] resArr = new int[res.size()];
        int i = 0;
        for (Integer num : res) {
            resArr[i] = num;
            i++;
        }

        return resArr;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        int i;
        for (i = 0; i<nums.length-2; i++){
            if(i == 0 || (i > 0 && nums[i]!=nums[i-1])){
                int sum = 0-nums[i];
                int lo = i+1, hi = nums.length-1;
                while(lo<hi){
                    if (nums[lo] + nums[hi] == sum){
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while(lo<hi && nums[lo] == nums[lo+1]) lo++;
                        while(lo<hi && nums[hi] == nums[hi-1]) hi--;
                        lo++;
                        hi--;
                    }else if (nums[lo] + nums[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        echo(i);
        return res;
    }

    public static void printMaze(int[][] maze) {
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                System.out.print(maze[row][col]);
            }
            System.out.println(" ");
        }
    }

    public static class Coord {
        private int x;
        private int y;

        public Coord(int r, int c) {
            this.y = r;
            this.x = c;
        }

        public void printCoord() {
            System.out.println("(" + this.x + ", " + this.y + ")");
        }

        public int getRow() { return this.y;  };
        public int getCol() { return this.x; };
    }

    public static boolean isValidCoord(int row, int col) {
        if (row < 0 || row > 9 || col < 0 || col > 9) {
            return false;
        }

        return true;
    }

    public static boolean isDeadEnd(int[][] maze, int row, int col) {
        if (isValidCoord(row - 1, col) && maze[row - 1][col] == 0) {
            return false;
        }
        if (isValidCoord(row + 1, col) && maze[row + 1][col] == 0) {
            return false;
        }
        if (isValidCoord(row, col + 1) && maze[row][col + 1] == 0) {
            return false;
        }
        if (isValidCoord(row, col - 1) && maze[row][col - 1] == 0) {
            return false;
        }
        return true;
    }

    // we know dis a dead end (visited nodes are numbered as 2)
    public static Coord backTrack(int[][] maze, int row, int col, List<Coord> path) {
        maze[row][col] = 1;
        path.remove(path.size() - 1);

        if (isValidCoord(row-1, col) && maze[row-1][col] == 2) {
            return new Coord(row-1, col);
        }
        if (isValidCoord(row+1, col) && maze[row+1][col] == 2) {
            return new Coord(row+1, col);
        }
        if (isValidCoord(row, col+1) && maze[row][col+1] == 2) {
            return new Coord(row, col+1);
        }
        if (isValidCoord(row, col-1) && maze[row][col-1] == 2) {
            return new Coord(row, col-1);
        }

        return null;
    }

    public static void mazeSolverHelper(int[][] maze, int row, int col, List<Coord> path) {
        System.out.println("mazeSolverHelper" + row + col);
        if (!isValidCoord(row, col)) return;

        maze[row][col] = 2;
        path.add(new Coord(row, col));

        if (row == 9 && col == 9) return;

        while (isDeadEnd(maze, row, col)) {
            Coord c = backTrack(maze, row, col, path);
            row = c.getRow();
            col = c.getCol();
        }

        if (isValidCoord(row+1, col) && maze[row+1][col] == 0) {
            mazeSolverHelper(maze, row+1, col, path);
        }

        if (isValidCoord(row-1, col) && maze[row-1][col] == 0) {
            mazeSolverHelper(maze, row-1, col, path);
        }

        if (isValidCoord(row, col+1) && maze[row][col+1] == 0) {
            mazeSolverHelper(maze, row, col+1, path);
        }

        if (isValidCoord(row, col-1) && maze[row][col-1] == 0) {
            mazeSolverHelper(maze, row, col-1, path);
        }

    }

//    public static List<Coord> populateResult(int[][] maze) {
//
//    }

    public static int[][] mazeSolver(int[][] maze, List<Coord> path) {
        mazeSolverHelper(maze, 0, 0, path);
        // maze should be marked 2 from end to start
//        List<Coord> path = populateResult(maze);
        return maze;
    }

    public static int repeatedStringMatch(String a, String b) {
        int counter = 0;
        String c = a;
        int repeat = b.length() / a.length() + 1;
        while (counter <= repeat){
            counter++;
            if (a.contains(b)) {
                return counter;
            } else {
                a += c;
            }
        }

        return -1;
    }

    public static boolean isArraySorted(int[] arr, boolean asc) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (asc) {
                if ((arr[i+1] - arr[i]) < 0) {
                    return false;
                }
            } else {
                if ((arr[i+1] - arr[i]) > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int getSwapCounter(int[] arr, boolean asc) {
        int temp;
        int tracker = (asc) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        int swapIndex = 0;
        int counter = 0;

        for (int i = 0; i < arr.length; i++) {
            temp = arr[i];
            for (int j = i; j < arr.length; j++) {
                if (asc) {
                    if (tracker > arr[j]) {
                        tracker = arr[j];
                        swapIndex = j;
                    }
                } else {
                    if (tracker < arr[j]) {
                        tracker = arr[j];
                        swapIndex = j;
                    }
                }
            }

            // swap smallest with i
            if (i != swapIndex) {
                arr[i] = tracker;
                arr[swapIndex] = temp;
                counter++;
            }

            tracker = (asc) ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            if (isArraySorted(arr, asc)) {
                return counter;
            }
        }

        return (arr.length == 0) ? 0 : counter;
    }

    // returns the min amount of swaps for sorting it to min or max
    public static int lilysHomeworkHelper(int[] minArr, int[] maxArr) {
        int temp;
        int smallest = Integer.MAX_VALUE;
        int largest = Integer.MIN_VALUE;
        int minSwapIndex = 0;
        int maxSwapIndex = 0;
        int minCounter = 0;
        int maxCounter = 0;

        boolean minSaved = false;
        boolean maxSaved = false;

        int minRes = 0;
        int maxRes = 0;

        for (int i = 0; i < minArr.length; i++) {
            temp = minArr[i];
            for (int j = i; j < minArr.length; j++) {
                if (smallest > minArr[j]) {
                    smallest = minArr[j];
                    minSwapIndex = j;
                }
                if (largest < maxArr[j]) {
                    largest = maxArr[j];
                    maxSwapIndex = j;
                }
            }

            // swap smallest with i
            if (i != minSwapIndex) {
                minArr[i] = smallest;
                minArr[minSwapIndex] = temp;
                minCounter++;
            }

            if (i != maxSwapIndex) {
                maxArr[i] = largest;
                maxArr[maxSwapIndex] = temp;
                maxCounter++;
            }

            smallest = Integer.MAX_VALUE;
            largest = Integer.MIN_VALUE;

            if (isArraySorted(minArr, true) && !minSaved) {
                minSaved = true;
                minRes = minCounter;
            }

            if (isArraySorted(maxArr, false) && !maxSaved) {
                maxSaved = true;
                maxRes = maxCounter;
            }
        }

        return (minArr.length == 0) ? 0 : Math.min(minRes, maxRes);
    }

    public static int lilysHomework(int[] arr) {
        // bbsort O(n2)

        return lilysHomeworkHelper(arr, arr.clone());


//        int counter1 = getSwapCounter(arr.clone(), true);
//        int counter2 = getSwapCounter(arr, false);
//
//        return Math.min(counter1, counter2);
    }

    public static String isValid(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        // fill the map wit data
        for (Character c : s.toCharArray()) {
            map.computeIfPresent(c, (k, v) -> v + 1);
            map.putIfAbsent(c, 1);
        }

        // count and check if is valid
        Integer count1 = null;
        Integer count2 = null;
        int oneCounter = 0;

        // if we find a third unique count then it is false
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(1)) oneCounter++;
            if (!entry.getValue().equals(count1) && !entry.getValue().equals(count2)) {
                if (count1 == null) {
                    count1 = entry.getValue();
                } else if (count2 == null) {
                    if (!count1.equals(entry.getValue())) {
                        count2 = entry.getValue();
                    }
                } else {
                    return "NO";
                }
            }
        }

        if (count1 == null || count2 == null) {
            return "YES";
        }

        if (count1.equals(1) || count2.equals(1)) {
            if (oneCounter == 1) {
                return "YES";
            } else {
                return "NO";
            }
        }

        return (Math.abs(count1 - count2) == 1) ? "YES" : "NO";
    }

    public static String getTheOddNumber(HashMap<Character, Integer> map) {
        Integer count1 = null;
        Integer count2 = null;

        // if we find a third unique count then it is false
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != count1 || entry.getValue() != count2) {
                if (count1 == null) {
                    count1 = entry.getValue();
                } else if (count2 == null) {
                    count2 = entry.getValue();
                } else {
                    return "NO";
                }
            }
        }

//        if (count1.equals(1) || count2.equals(1)) {
//            return "YES";
//        }
//
//        if (Math.abs(count1 - count2) == 1) {
//            return "YES";
//        }
//
        return ((count1.equals(1) || count2.equals(1)) || (Math.abs(count1 - count2) == 1)) ?
                "YES" : "NO";

    }


    public static List<Integer> largestValues(TreeNode root) {
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();

        List<Integer> res = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        int rowAmountCurr = 1;
        int childrenAmt = 0;

        nodeQueue.add(root);

        while(!nodeQueue.isEmpty()) {

            TreeNode curr;
            if (rowAmountCurr == 0) {
                curr = nodeQueue.peek();
            } else {
                curr = nodeQueue.pop();
            }

//            TreeNode curr = nodeQueue.pop();

            if (rowAmountCurr != 0) {
                if (max < curr.val)
                    max = curr.val;
            }

            if (rowAmountCurr != 0) {
                if (curr.left != null) {
                    nodeQueue.add(curr.left);
                    childrenAmt++;
                }

                if (curr.right != null) {
                    nodeQueue.add(curr.right);
                    childrenAmt++;
                }
                rowAmountCurr--;

            } else {
                res.add(max);
                rowAmountCurr = childrenAmt;
                max = Integer.MIN_VALUE;
                childrenAmt = 0;
            }

        }

        res.add(max);

        return res;
    }


    static int countOccurrenceOfA(String s) {
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a')
                res++;
        }
        return res;
    }
    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        // find ratio
        int aOccurrence = countOccurrenceOfA(s);
        double ratio =  aOccurrence / (double) s.length();

        // find modulos
        int mod = (int) n % s.length();

        // substring original string by 0 -> modulos inclusive
        String substring = s.substring(0, mod);

        // count the 'a' for substring
        int subAOccurrence = countOccurrenceOfA(substring);

        // return sum == (n * ratio) + (countOccurrenceOfA(substring))
        double divide =  (n - mod) * ratio;

        return (long) divide + countOccurrenceOfA(substring);
    }

    // convert bst such that each value is now the sum of its value and everything larger than itself
    private static int sum = 0;
    /*
        - given the nature of BST, an inverse in-order traversal that keeps track of the sum is able to do the trick
        - the first iteration, because it is reverse inorder, will be the largest number so the sum is at 0
        - then each step the sum is getting larger and the next traversed element will always add the correct value
     */
    public static TreeNode convertBST(TreeNode root) {

        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    public static boolean isPalindrome(String s) {
        int j = s.length() - 1;
        for (int i = 0; i < j; i++) {
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                if (!(Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i)))) {
                    continue;
                } else if (!(Character.isLetter(s.charAt(j)) || Character.isDigit(s.charAt(j)))) {
                    i--;
                } else {
                    return false;
                }
            }
            j--;
        }

        return true;
    }

    // returns -1 if not found
    public static int binarySearch(int[] arr, int val) {
        Arrays.sort(arr);
        int lo = 0;
        int hi = arr.length - 1;
        int mid = (int) Math.floor((hi + lo) / 2);

        while (lo <= hi) {
            if (arr[mid] == val) return mid;
            mid = (int) Math.floor((hi + lo) / 2);
            if (arr[mid] > val) { // saerch left
                hi = mid - 1;
            } else if (arr[mid] < val) {
                lo = mid + 1;
            }
        }

        return -1;
    }

    public static List<List<Integer>> generate(int numRows) {
        int i = 0;
        int j = 0;
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        while (i < numRows) {
            if (i == 0) {                          // beginning of the list
                temp.add(1);
                res.add((ArrayList) temp.clone());
                temp.clear();
                i++;
            } else if (j == res.get(i-1).size()) { // end of the list
                temp.add(1);
                res.add((ArrayList) temp.clone());
                temp.clear();
                j = 0;
                i++;
            } else {
                if (j == 0) {
                    temp.add(1);
                } else {
                    temp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
                j++;
            }
        }

        return res;
    }

    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        return coinChangeHelper(coins, amount);
    }

    public static int coinChangeHelper(int[] coins, int amount) {

        if (amount == 0) return 0;

        if (coins.length == 0) {
            return -1;
        }

        if ((coins.length == 1 && coins[0] > amount)){
            return -1;
        }

        int diff = amount - coins[coins.length - 1];
        int[] newCoins = Arrays.copyOfRange(coins, 0, coins.length - 1);

        if (diff > 0) {
            return 1 + coinChangeHelper(coins, diff);
        } else if (diff < 0) {
            return coinChangeHelper(newCoins, amount);
        } else {
            return 1;
        }

    }

    public static class DecodeString {
        int times;
        String str;

        public DecodeString(int t, String s) {
            this.times = t;
            this.str = s;
        }
    }

    public static String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }

    public static void populateQueue(LinkedList queue, String s) {

        StringBuilder sb = new StringBuilder();
        int num = 0;
        String str;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                num = getTimes(sb.toString());
                sb.delete(0, sb.length());
            } else if (s.charAt(i) == ']') {
                str = sb.toString();
                queue.add(new DecodeString(num, str));
                sb.delete(0, sb.length());
            } else {
                sb.append(s.charAt(i));
            }
        }

        if (sb.length() > 0)
            queue.add(new DecodeString(1, sb.toString()));

    }

    public static int getTimes(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c) || c == '-') {
                sb.append(c);
            }
        }
        return sb.length() == 0 ? 1 : Integer.parseInt(sb.toString());
    }

    public static List<Integer> mergeKSortedArrays(int[][] sortedArrays) {
        List<List<Integer>> arrays = new ArrayList<>(sortedArrays.length);
        for (int i = 0; i < sortedArrays.length; ++i) { // O(m*n) total element
            int jLength = sortedArrays[0].length;
            arrays.add(new ArrayList(jLength));
            for (int j = 0; j < jLength; ++j) {
                arrays.get(i).add(sortedArrays[i][j]);
            }
        }

        List<Integer> res = new ArrayList<Integer>();
        int min = Integer.MAX_VALUE;
        int index;

        while (arrays.size() > 1) {
            index = 0;
            min = Integer.MAX_VALUE;

            // m = number of rows in the sortedArray
            // O(m*n)

            for (int i = 0; i < arrays.size(); i++) { //
                if (arrays.get(i).get(0) < min) {
                    min = arrays.get(i).get(0);
                    index = i;
                }
            }

            arrays.get(index).remove(0);
            if (arrays.get(index).size() == 0) {
                arrays.remove(index);
            }

            res.add(min);
        }

        if (arrays.size() == 1)
            for (int elem : arrays.get(0)) // append everything from the last list into the result
                res.add(elem);

        return res;
    }

    public static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public static String getRandomMarble(String[] marbles) {
        int randomIndex = randomWithRange(0, marbles.length - 1);
        String res = marbles[randomIndex];
        if (randomIndex == 0) {
            marbles = Arrays.copyOfRange(marbles, 1, marbles.length);
            echo(marbles);
        } else if (randomIndex == marbles.length - 1) {
            marbles = Arrays.copyOfRange(marbles, 0, marbles.length - 1);
            echo(marbles);
        } else {
//            String[] temp = marbles.clone();
//            System.arraycopy(temp, 0, marbles, 0, randomIndex - 1);
//            System.arraycopy(temp, randomIndex+1, marbles, randomIndex, temp.length - 1);
            echo(marbles);
        }

        return res;
    }

    public static boolean isSubTree(TreeNode s, TreeNode t) {
        Stack<TreeNode> stack = new Stack<>();

        stack.add(s);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (isIdenticalTree(curr, t)) {
                    return true;
            } else {
                if (curr.left != null)
                    stack.push(curr.left);
                if (curr.right != null)
                    stack.push(curr.right);
            }
        }

        return false;
    }

    public static boolean isIdenticalTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null && t != null) return false;
        if (s != null && t == null) return false;

        if (s.val == t.val) {
            if (isIdenticalTree(s.left, t.left)) {
                if (isIdenticalTree(s.right, t.right)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static int rob(int[] nums) {
        int [] res = new int [nums.length+2];
        for (int i=0;i<nums.length;i++)
        {
            res[i+2]=Math.max(nums[i]+res[i],res[i+1]);

        }
        return res[nums.length+1];

    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (sr < 0 || sc < 0 || sr > image.length - 1 || sc > image[sr].length - 1)
            return image;

        if (image[sr][sc] != newColor) {
            int oldColor = image[sr][sc];
            floodFillHelper(image, sr, sc, newColor, oldColor);
        }

        return image;
    }

    public static void floodFillHelper(int[][] image, int sr, int sc, int newColor, int oldColor) {
        if (sr < 0 || sc < 0 || sr > image.length - 1 || sc > image[sr].length - 1)
            return;

        if (image[sr][sc] != newColor && image[sr][sc] == oldColor) {
            image[sr][sc] = newColor;
            floodFillHelper(image, sr - 1, sc, newColor, oldColor);
            floodFillHelper(image, sr, sc + 1, newColor, oldColor);
            floodFillHelper(image, sr + 1, sc, newColor, oldColor);
            floodFillHelper(image, sr, sc - 1, newColor, oldColor);
        }
    }

    // echos obj, and only string[] and int[]
    public static void echo(Object o) {
        if (o.getClass().isArray()) {
            try {
                System.out.println(Arrays.toString((String[]) o));
            } catch (ClassCastException e) {
                System.out.println(Arrays.toString((int[]) o));
            }
        } else {
            System.out.println(o);
        }
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        return accountsMergeHelper(accounts, 0);
    }

    public static List<List<String>> accountsMergeHelper(List<List<String>> accounts, int index) {
        // IDEA: mutate the list in each recursion, return the list at the end

        // if only one list just return
        if (accounts.size() - 1 == index) {
            return accounts;
        }

        if (accounts.size() == 2) {
            int j = 1;
            List<String> temp = accounts.get(0);
            List<String> temp2 = accounts.get(1);
            if (containsItem(temp, temp2.get(j))) {
                accounts.remove(1);
                accounts.remove(0);
                accounts.add(0, mergeLists(temp, temp2));
                return accounts;
            }
        }

        // iterate through the rest of the list while making sure to modify the current
        for (int i = index; i < accounts.size() - 2; i++) {
            List<String> temp = accounts.get(i);

            // iter through one single users list
            int j = 1;
            while (j <= temp.size() - 1) {
                echo(accounts.get(i+1).get(j));
                List<String> temp2 = accounts.get(i+1);
                if (containsItem(temp, temp2.get(j))) {
                    accounts.remove(i+1);
                    accounts.remove(i);
                    accounts.add(i, mergeLists(temp, temp2));
                    echo(accounts);
                    break;
                }
                j++;
            }
        }

        return accounts;
    }

    public static ArrayList<String> mergeLists(List<String> first, List<String> second) {
        Set<String> set1 = new HashSet<String>(first);
        Set<String> set2 = new HashSet<String>(second);
        set1.addAll(set2);
        return new ArrayList(set1);

    }

    public static boolean containsItem(List<String> list, String elem) {
        for( String s : list ) {
            if (elem.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.left != null) {
            count += root.left.val + sumOfLeftLeaves(root.left);
        }
        if (root.right != null) {
            count += sumOfLeftLeaves(root.right);
        }
        return count;
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = -1;
        int counter = 0;
        for (int i : nums) {
            if (i == 0) {
                if (counter > max) {
                    max = counter;
                }
                counter = 0;
            } else {
                counter++;
            }
        }
        return (counter > max) ? counter : max;
    }

    public static int arrangeCoins(int n) {
        int level = 0;
        int counter = 0;
        while (n - level > 0) {
            level++;
            counter++;
            n -= level;
        }
        return counter;
    }


    public static void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.println(root.val);
            inOrderTraversal(root.right);
        }
    }


    public static int minimumAbsoluteDifference(int[] arr) {
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length - 1; i++) {
            int min = Math.abs(arr[i] - arr[i+1]);
            if (min == 0) {
                return 0;
            } else if (min < res) {
                res = min;
            }
        }

        return res;
    }

    public static int maximumToys(int[] prices, int k) {
        Arrays.sort(prices);
        int res = 0;
        for (int i=0; i<prices.length; i++) {
            if (k - prices[i] >= 0) {
                k = k - prices[i];
                res++;
            }
        }
        return res;
    }

    public static int maxMin(int k, int[] arr) {
        Arrays.sort(arr);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i+k-1 < arr.length; i=i+k-1) {
            int min = arr[i+k-1] - arr[i];
            if (res > min) {
                res = min;
            }
        }
        return res;
    }

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        String res = "";
        HashMap<Integer, String[]> hm = new HashMap<Integer, String[]>();
        for(int i=0; i<indexes.length; i++){
            String[] val = {sources[i], targets[i]};
            hm.put(i, val);
        }

        Arrays.sort(indexes);

        for (int i : indexes) {
            String sourceStr = sources[i];
            String subStr = S.substring(sourceStr.length());
            if (sourceStr.equals(subStr)) {
                res += targets[i];
                System.out.println(res);
            } else {
                res += subStr;
                System.out.println(res);
            }
        }
        return res;
    }

    public static String twoStrings(String s1, String s2) {
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        char[] characters = s1.toCharArray();
        for (Character c : characters) {
            if (!hm.containsKey(c)) {
                hm.put(c, 1);
            }
        }
        characters = s2.toCharArray();

        for (Character c : characters) {
            if (hm.containsKey(c)) {
                return "YES";
            }
        }
        return "NO";
    }


    // O(n)
    public static Hashtable<String, Integer> getOccurrence(String blob) {
        Hashtable<String, Integer> results = new Hashtable<String, Integer>();
        String[] listOfWords = blob.split("\\s+|\\s*,\\s*");

        for (String word : listOfWords) {
            if (results.containsKey(word)) {
                results.put(word, results.get(word)+1);
            } else {
                results.put(word, 1);
            }
        }

        return results;
    }

    public static Boolean isAllUniqueCharacters(String str) {
        Hashtable<Character, Integer> ht = new Hashtable<Character, Integer>();
        char[] characters = str.toCharArray();
        for (Character ch : characters) {
            if (!ht.containsKey(ch)) {
                ht.put(ch, 1);
            } else {
                return false;
            }
        }
        return true;
    }

    public static Boolean isPermutation(String str1, String str2) {
        Hashtable<Character, Integer> ht1 = getCharactersCount(str1);
        Hashtable<Character, Integer> ht2 = getCharactersCount(str2);
        Set<Character> set1 = ht1.keySet();
        for (Character ch : set1) {
            if (ht1.get(ch) != ht2.get(ch)) {
                return false;
            }
        }
        return true;
    }

    public static Hashtable<Character, Integer> getCharactersCount(String str) {
        Hashtable<Character, Integer> ht = new Hashtable<Character, Integer>();
        char[] characters = str.toCharArray();
        for (Character ch : characters) {
            ht.computeIfPresent(ch, (k,v) -> v + 1);
            ht.putIfAbsent(ch, 1);
        }
        return ht;
    }


}
