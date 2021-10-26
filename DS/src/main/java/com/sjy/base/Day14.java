package com.sjy.base;

import javafx.scene.effect.Bloom;

import java.util.Stack;

public class Day14 {
    public static void main(String[] args) {
        Day14 d = new Day14();
        d.findTheWinner(6, 5);
    }

    /**
     * 1249. 移除无效的括号
     * @param s
     * @return
     */
    public String minRemoveToMakeValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (chars[i]=='('){
                stack.push(i);
            }
            if (chars[i]==')' && stack.isEmpty()){// 当前位置替换为空
                chars[i] = '!';
            }
            if (chars[i]==')' && !stack.isEmpty()){
                stack.pop();
            }
        }
        int index;
        while (!stack.isEmpty()){
            index = stack.pop();
            chars[index] = '!';
        }
        StringBuilder sb = new StringBuilder();
        for(char c : chars){
            if (c!='!'){
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 1823. 找出游戏的获胜者
     * @param n
     * @param k
     * @return
     */
    public int findTheWinner(int n, int k) {
        /**
         * 法1：遍历
         */
//        int[] arr = new int[n+1];
//        int start = 1;
//        int lose = start;
//        for (int i = 0; i < n-1; i++) {
//            int count = 1;
//            while (count < k){
//                lose++;
//                if (lose == n+1) lose = 1;
//                if (arr[lose]==0){ // 没输
//                    count ++;
//                }
//            }
//            arr[lose] = 1;
//            start = lose;
//            while (arr[start]==1){ // 若该位置得人已经输了
//                start++;
//                if (start==n+1) start = 1;
//            }
//            lose = start;
//        }
//        while (arr[lose]==1){
//            lose++;
//            if (lose == n+1) lose = 1;
//        }
//        return lose;
        /**
         * 法2 动态规划
         */
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = (dp[i-1] + k) % (i+1);
            if (dp[i] == 0) dp[i] = i+1;
        }
        return dp[n-1];
        // 1 2 3 4 5
        // 3 4 5 1
    }
}
