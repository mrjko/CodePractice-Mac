package com.company;


import com.company.Practice.*;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.*;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Main {

    private static int COUNTER = 1;
    private static TreeNode prev = null;



    static class StringLengthComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length();
        }
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

//        for (Map.Entry<String, Integer> entry : test.entrySet()) {
//            String key = entry.getKey();
//            Integer value = entry.getValue();
//            System.out.println("key: " + key + ", value: " + value);
//        }




        echo(findLongestSubstring("aaabbb")); // aaabbb
        echo(findLongestSubstring("bbbbaaabbbbbbbbbaaaa")); // bbbbaaaa
        echo(findLongestSubstring("a")); // ""
        echo(findLongestSubstring("aabb")); // ""
        echo(findLongestSubstring("b")); // ""
        echo(findLongestSubstring("")); // ""


    }

    public static String findLongestSubstring(String s) {
        String res = "";

        for (int i = 0; i < s.length(); i++) {
            String curr = getCurrLongest(s, i);

            if (res.length() < curr.length()) {
                res = curr;
            }
        }

        return res;
    }

    public static boolean isVowel(char c) {
        return (c == 'a');
    }

    public static String getCurrLongest(String s, int index) {
        int count = 0;
        for (int i = index; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                return s.substring(index, i + 1);
            }
        }
        return "";
    }

    public static boolean doesPathExistForSum(TreeNode n, int sum) {
        if (sum == 0) return true;
        if ((n == null)) return false;

        return doesPathExistForSum(n.left, sum - n.val) || doesPathExistForSum(n.right, sum - n.val);
    }

//    I can be placed before V (5) and X (10) to make 4 and 9.
//    X can be placed before L (50) and C (100) to make 40 and 90.
//    C can be placed before D (500) and M (1000) to make 400 and 900.

    public static int romanToInt(String s) {
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i);
            int next = '~';
            if (i+1 < s.length()) {
                next = s.charAt(i+1);
            }

            if (curr == 'I') {
                if (next == 'V') {
                    res += 4;
                    i++;
                } else if (next == 'X') {
                    res += 9;
                    i++;
                } else {
                    res += 1;
                }
            }

            if (curr == 'X') {
                if (next == 'L') {
                    res += 40;
                    i++;
                } else if (next == 'C') {
                    res += 90;
                    i++;
                } else {
                    res += 10;
                }
            }

            if (curr == 'C') {
                if (next == 'D') {
                    res += 400;
                    i++;
                } else if (next == 'M') {
                    res += 900;
                    i++;
                } else {
                    res += 100;
                }
            }

            if (curr == 'L') {
                res += 50;
            }

            if (curr == 'V') {
                res += 5;
            }

            if (curr == 'D') {
                res += 500;
            }

            if (curr == 'M') {
                res += 1000;
            }

        }


        return res;
    }

    public static String getHint(String secret, String guess) {
        HashSet<Character> set = new HashSet<>();
        HashMap<Character, Integer> map = new HashMap<>();

        int bullCount = 0;
        int cowCount = 0;

        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bullCount++;
                map.computeIfPresent(guess.charAt(i), (k,v) -> v + 1);
                map.putIfAbsent(guess.charAt(i), 1);
            } else {
                if (secret.contains(guess.subSequence(i,i+1))) {
                    if (map.containsKey(guess.charAt(i)) || set.contains(guess.charAt(i))) {
                        if (!set.contains(guess.charAt(i))) {
                            cowCount++;
                        }
                        set.add(guess.charAt(i));
                    } else {
                        cowCount++;
                        set.add(guess.charAt(i));
                    }
                }

            }

        }

        return bullCount + "A" + cowCount + "B";
    }

    public static boolean isToeplitzMatrix(int[][] matrix) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                int key = r-c;
                if (map.containsKey(key)) {
                    if (matrix[r][c] == map.get(key)) {
                        return false;
                    }
                }
                map.putIfAbsent(key, matrix[r][c]);
            }
        }

        return true;
    }

    static int DIRECTION = 4;
    static int[] dirR = new int[]{1, -1, 0,  0};
    static int[] dirC = new int[]{0,  0, 1, -1};

    public static class Coordinate {
        int row;
        int col;
        char val;
        int steps;

        public Coordinate(int r, int c, char v, int s) {
            this.row = r;
            this.col = c;
            this.val = v;
            this.steps = s;
        }
    }

    public static Coordinate findStartCoord(char[][] maze) {
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                if (maze[r][c] == 's') {
                    Coordinate start = new Coordinate(r, c, 's', 0);
                    return start;
                }
            }
        }
        return null;
    }

    public static boolean isValidCoordinate(int r, int c, char[][] maze) {
        if (r < 0 || c < 0) return false;
        if (r >= maze.length || c >= maze[r].length) return false;
        if (maze[r][c] == '#') return false;

        return true;
    }

    // returns -1 if no path, else shortest number of steps
    public static int solveMaze(char[][] maze) {
        LinkedList<Coordinate> queue = new LinkedList<Coordinate>();
        Coordinate start = findStartCoord(maze);
        queue.add(start);

        while (!queue.isEmpty()) {
            Coordinate curr = queue.poll();

            if (curr.val == 'e') { return curr.steps; }

            for (int i = 0; i < DIRECTION; i++) {
                int newr = curr.row + dirR[i];
                int newc = curr.col + dirC[i];
                maze[curr.row][curr.col] = '#';

                if (isValidCoordinate(newr, newc, maze)) {
                    echo("is valid: " + newr + " , " + newc);
                    queue.add(new Coordinate(newr, newc, maze[newr][newc], curr.steps + 1));
                }
            }

        }

        return -1;
    }

    public static boolean validColor(int[][] graph, int[] colors, int color, int node) {
        if (colors[node] != 0) {
            return colors[node] == color;
        }
        colors[node] = color;
        for (int next : graph[node]) {
            if (!validColor(graph, colors, -color, next)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {              //This graph might be a disconnected graph. So check each unvisited node.
            if (colors[i] == 0 && !validColor(graph, colors, 1, i)) {
                return false;
            }
        }
        return true;
    }

    public static Map<String, String> mapShare(Map<String, String> map) {
        if (map.containsKey("c")) {
            map.remove("c");
        }

        if (map.containsKey("a") && map.containsKey("b")) {
            map.put("b", map.get("a"));
        }

        return map;
    }

    public static boolean makeBricks(int small, int big, int goal) {
        if (goal > small + big * 5)
            return false;
        else
            return goal % 5 <= small;
    }

    public static String decompress(String str) {
        StringBuilder res = new StringBuilder();
        StringBuilder loop = new StringBuilder();
        int start = -1;
        int openBrackets = 0;

        for (int i = 0; i < str.length(); i++) {

            if (Character.isDigit(str.charAt(i))) {
                if (openBrackets == 0) {
                    loop.append(str.charAt(i));
                }
            } else if (str.charAt(i) == '[') {
                if (start == -1) {
                    start = i + 1;
                }
                openBrackets++;
            } else if (str.charAt(i) == ']') {
                openBrackets--;
                if (openBrackets == 0) {
                    String substring = decompress(str.substring(start, i));
                    int loopAmt = Integer.parseInt(loop.toString());
                    while (loopAmt > 0) {
                        loopAmt--;
                        res.append(substring);
                    }
                    loop.setLength(0);
                    start = -1;
                }
            } else {
                if (openBrackets == 0) {
                    res.append(str.charAt(i));
                }
            }
        }

        return res.toString();
    }

    public static boolean canBalance(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        int leftSide = 0;
        int rightSide = 0;

        while (start <= end) {
            if (leftSide < rightSide) {
                leftSide += nums[start];
                start++;
            } else {
                rightSide += nums[end];
                end--;
            }
        }

        return (leftSide == rightSide && nums.length != 1);
    }

    public static int getNumberAtIndex(String str, int index) {
        StringBuilder sb = new StringBuilder();

        while (index < str.length() && Character.isDigit(str.charAt(index))) {
            sb.append(str.charAt(index));
            index++;
        }

        return Integer.parseInt(sb.toString());
    }

    public static int sumNumbers(String str) {
        int res = 0;
        int i = 0;

        while (i < str.length()) {
            if (Character.isDigit(str.charAt(i))) {
                Integer num = getNumberAtIndex(str, i);
                res += num;
                i += num.toString().length();
            } else {
                i++;
            }
        }

        return res;
    }

    public static String withoutString(String base, String remove) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < base.length(); i++) {
            if (Character.toLowerCase(remove.charAt(0)) == Character.toLowerCase(base.charAt(i))) {
                String substring;
                if (remove.length() < base.length() - i) {
                    substring = base.substring(i, i + remove.length());
                } else {
                    substring = base.substring(i, base.length());
                }
                if (!substring.toLowerCase().equals(remove.toLowerCase())) {
                    sb.append(substring);
                }
                i += remove.length() - 1;
                continue;
            }
            sb.append(base.charAt(i));
        }

        return sb.toString();
    }

    public static String stringSplosion(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i <= s.length()) {
            sb.append(s.substring(0, i));
            i++;
        }

        return sb.toString();
    }

    public static boolean isSubsequentWord(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] < b[i]) return false;
        }
        return true;
    }

    public static String longestSubsequentWord(String input, String[] words) {
        int[] inputCount = new int[26];
        int[] wordCount = new int[26];
        Arrays.fill(inputCount, 0);
        Arrays.fill(wordCount, 0);

        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());

        for (Character c : input.toCharArray()) {
            inputCount[(int)Character.toLowerCase(c) - 97] = inputCount[(int)Character.toLowerCase(c) - 97] + 1;
        }

        for (String word : words) {
            for (Character c : word.toCharArray()) {
                wordCount[(int)Character.toLowerCase(c) - 97] = wordCount[(int)Character.toLowerCase(c) - 97] + 1;
            }

            if (isSubsequentWord(inputCount, wordCount)) { return word; }
            Arrays.fill(wordCount,  0);

        }

        return "";
    }

    public static boolean isBorderCoord(char[][] board, int r, int c) {
        return (r == 0 || r == board.length - 1) || (c == 0 || c == board[r].length - 1);
    }

    public static void fillFromBorder(char[][] board, int r, int c) {
            // if coord is O, do the flip and propagate
        if ((r < board.length && r >= 0) && (c < board[r].length && c >= 0)) {
            if (board[r][c] == 'O') {
                board[r][c] = 'X';
                // fill from all directions
                fillFromBorder(board, r + 1, c);
                fillFromBorder(board, r - 1, c);
                fillFromBorder(board, r, c + 1);
                fillFromBorder(board, r, c - 1);
            }
        }
    }

    public static char[][] copyOf2DArray(char[][] board) {
        char[][] copy = new char[board.length][];
        for (int row = 0; row < board.length; row++) {
            copy[row] = new char[board[row].length];
            for (int col = 0; col < board[row].length; col++) {
                copy[row][col] = board[row][col];
            }
        }
        return copy;
    }

    public static void solve(char[][] board) {
        // fill from borders
        char[][] copy = copyOf2DArray(board);

        for (int r = 0; r < copy.length; r++) {
            for (int c = 0; c < copy[r].length; c++) {
                if (isBorderCoord(copy, r, c)) {
                    fillFromBorder(copy, r, c);
                }
            }
        }

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (copy[r][c] == 'O') {
                    board[r][c] = 'X';
                }
            }
        }

    }

    public static class Pair {
        public String word;
        public Integer count;

        public Pair(String word, Integer count) {
            this.word = word;
            this.count = count;
        }

    }

    public static List<String> getNextWordsNotVisited(String word, List<String> wordList, List<String> visited) {
        List<String> res = new ArrayList<>();
        int i;
        boolean foundDiff;
        boolean isNextWord;
        for (String s : wordList) {
            i = 0;
            foundDiff = false;
            isNextWord = true;
            // each word check if the there is one letter diff, if so add into list
            for (char c : s.toCharArray()) {
                if (word.charAt(i) != c) {
                    if (!foundDiff) {
                        foundDiff = true;
                    } else {
                        isNextWord = false;
                    }
                }
                i++;
            }
            if (isNextWord && !visited.contains(s)) res.add(s);
        }

        return res;
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        List<String> visited = new ArrayList<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 1));
        int counter;

        while (visited.size() != wordList.size() && !queue.isEmpty()) {
            Pair curr = queue.poll();
            counter = curr.count;
            List<String> nexts = getNextWordsNotVisited(curr.word, wordList, visited);
            for (String s : nexts) {
                visited.add(s);
                if (s == endWord) {
                    return counter + 1;
                }
                queue.add(new Pair(s, counter + 1));
            }
        }

        return 0;
    }

    public static int reachNumber(int target) {
        return reachNumberHelper(target, 0,0, 0);
    }

    public static int reachNumberHelper(int target, int currPos, int currSteps, int counter) {
        if (currPos + currSteps == target) { // || (currPos - currSteps < target)) {
            return counter;
        }

        if (Math.abs(target - currSteps) > 0) {
            return Integer.MAX_VALUE;
        }

        currSteps++;
        counter++;

        return Math.min(reachNumberHelper(target, currPos + currSteps, currSteps, counter),
                        reachNumberHelper(target, currPos - currSteps, currSteps, counter));

    }

    public static ListNode sortList(ListNode head) {
        ListNode curr = head;
        while (curr.next != null) {
            ListNode next = curr.next;
            if (next.val < curr.val) {
                ListNode temp = next.next;
                next.next = curr;
                curr.next = temp;
            } else {
                curr = curr.next;
            }
        }
        return curr;
    }

    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int i = 0, res = 0;
        for (int house : houses) {
            while (i < heaters.length - 1 && heaters[i] + heaters[i + 1] <= house * 2) {
                i++;
            }
            res = Math.max(res, Math.abs(heaters[i] - house));
        }

        return res;
    }

    public static void fillMap(int[] houses, int heater, int[][] map, int index) {
        int left = heater - 1;
        int leftCounter = 0;
        int right = heater + 1;
        int rightCounter = 0;
        while (left >= 0) {
            map[index][left] = ++leftCounter;
            left--;
        }
        while (right <= houses.length - 1) {
            map[index][right] = ++rightCounter;
            right++;
        }

        map[index][heater] = 0;
    }

    public static boolean checkPerfectNumber(int num) {
        int i = num - 1;
        int sum = 0;

        while (i > 0) {
            if (num % i == 0)
                sum += i;
            i--;
        }

        return (sum == num);
    }

    public static int lengthOfLastWord(String s) {
        if (s == "") {
            return 0;
        }
        String[] arr = s.split(" ");
        return arr[arr.length - 1].length();
    }

    public static int numSquares(int n, int[] memo) {
        int start = (int) Math.floor(Math.sqrt(n));
        int min = Integer.MAX_VALUE;
        while (start > 0) {
            int res = numSquaresHelper(n, start, memo);
            if (memo[n] == -1 || memo[n] > res) {
                memo[n] = res;
            }
            min = Math.min(res, min);
            start--;
        }
        return min;
    }

    public static int numSquares(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return numSquares(n, memo);
    }

    public static int numSquaresHelper(int n, int start, int[] memo) {

        int curr = n - (start * start);

        if (curr == 0) {
            return 1;
        } else if (curr < 0) {
            return numSquaresHelper(n, start - 1, memo);
        } else {
            if (memo[curr] != -1) {
                return 1 + memo[curr];
            }
            return 1 + numSquares(curr, memo);
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

    public static void flatten(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        //sort on starting time
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        int i = 0;
        while (i < intervals.size() - 1) {
            // if the next starting time is before ending time for current we merge
            Interval curr = intervals.get(i);
            Interval next = intervals.get(i+1);
            if (next.start <= curr.end) {
                // remove curr and next, add new interval at curr position
                int endValue = (curr.end > next.end) ? curr.end : next.end;
                Interval merged = new Interval(curr.start, endValue);
                intervals.remove(i);
                intervals.remove(i);
                intervals.add(i, merged);
            } else {
                i++;
            }
        }

        return intervals;
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
