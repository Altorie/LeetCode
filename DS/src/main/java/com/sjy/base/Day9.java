package com.sjy.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day9 {
    private static Map<Character, Integer> convert = new HashMap<Character, Integer>(){{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};

    public static void main(String[] args) {
        String str = "0123456789";
        System.out.println(str.substring(0, 1));
    }

    /**
     * 187. 重复的DNA序列
     * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。
     * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
     *
     * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
     * 输出：["AAAAACCCCC","CCCCCAAAAA"]
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        /**
         * 法 1：暴力
         * 超时
         */
//        List<String> ans = new ArrayList<>();
//        HashMap<String, Integer> map = new HashMap<>();
//        if (s.length() <= 10){
//            return ans;
//        }
//        for (int i = 0; i <= s.length()-1-10; i++) {
//            String target = s.substring(i, i + 10);
//            if (!map.containsKey(target)){
//                if (s.substring(i+1).contains(target)){
//                    ans.add(target);
//                    map.put(target, 1);
//                }
//            }
//
//        }
//        return ans;
        /**
         * 法 2：哈希表
         */
//        HashMap<String, Integer> map = new HashMap<>();
//        List<String> ans = new ArrayList<>();
//        for (int i = 9; i < s.length(); i++) {
//            // 当前字符串下标是 i-9, i
//            int start = i-9;
//            String target = s.substring(i-9, i+1);
//            // 把字符串加入到哈希表中，记录数量
//            map.put(target, map.getOrDefault(target, 0)+1);
//        }
//        for(String key : map.keySet()){
//            if (map.get(key) > 1){
//                ans.add(key);
//            }
//        }
//        return ans;
        /**
         * 法 3：哈希表 + 滑动窗口 + 位运算
         * 因为字串一共只包含四种字符，因此可以把字串看成一个四进制数
         */
        Map<Integer, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        int[] arr = new int[10]; // 存储 4 的幂
        for (int i = 0; i < 10; i++) {
            arr[i] = (int)Math.pow(4, i);
        }
        if (s.length() > 10){
            // 计算第一个字串的10进制值
            int key = 0;
            for (int i = 0; i < 10; i++) {
                key += convert.get(s.charAt(i))*arr[9-i];
            }
            map.put(key, 1);

            // 遍历后面的字串
            for (int i = 10; i < s.length(); i++) {
                // [i-9, i]
                key = 4 * (key - convert.get(s.charAt(i-10))*arr[9]) + convert.get(s.charAt(i));
                if (map.getOrDefault(key, 0) == 1){
                    list.add(s.substring(i-9, i+1));
                }
                map.put(key, map.getOrDefault(key, 0)+1);
            }
        }
        return list;
    }

    /**
     * 5. 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     *  abbaba
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        /**
         * 利用动态规划
         * dp[i][j] = true|false  代表字串 [i, j]是否为回文串
         */
//        int len = s.length();
//        if (len<2){
//            return s;
//        }
//        boolean[][] dp = new boolean[len][len];
//        char[] chars = s.toCharArray();
//        // 初始化，所有的dp[i][i]都是 true
//        for (int i = 0; i < len; i++) {
//            dp[i][i] = true;
//        }
//        int maxLen = 1;
//        int began = 0;
//        // 遍历字串的长度
//        for (int l = 2; l <= len; l++) {
//            for (int i = 0; i <= len-l; i++) {
//                int j = l+i-1;
//                // 判断 dp[i][j]
//                if (chars[i] != chars[j]){
//                    dp[i][j] = false;
//                } else {
//                  if (j-i < 2){ // 对于 aa 这样的字串
//                      dp[i][j] = true;
//                  } else {
//                      dp[i][j] = dp[i+1][j-1];
//                  }
//                }
//                if (dp[i][j] && l > maxLen){
//                    maxLen = l;
//                    began = i;
//                }
//            }
//        }
//        return s.substring(began, began+maxLen);

        /**
         * 中心扩散法
         */
//        int len = s.length();
//        if (len<2){
//            return s;
//        }
//        int maxLen = 1;
//        int begin = 0;
//        char[] chars = s.toCharArray();
//        for (int i = 0; i < len; i++) {
//            int l = i, r = i;
//            // 将 l 向右移动，直到chars[l]与chars[i]不同
//            while (l>=0 && chars[l] == chars[i]){
//                l--;
//            }
//            // 将 r 向右移动，直到chars[r]与chars[i]不同
//            while (r < len && chars[r]==chars[i]){
//                r++;
//            }
//            // 将 l与 r同时向两边扩散
//            while (l>=0 && r<len && chars[l]==chars[r]){
//                l--;
//                r++;
//            }
//            if (r-l-1 > maxLen){
//                maxLen = r-l-1;
//                begin = l+1;
//            }
//        }
//        return s.substring(begin, begin+maxLen);

        /**
         * 马拉车算法
         */
        int len = s.length();
        if (len<2){
            return s;
        }
        // 首先扩展原字符串，是回文子串长度为奇数
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (int i = 0; i < s.length()-1; i++) {
            sb.append(s.charAt(i));
            sb.append('#');
        }
        sb.append(s.charAt(len-1));
        sb.append('#');
        String string = sb.toString();
        char[] chars = string.toCharArray();
        int[] armArr = new int[string.length()]; // 存储每个元素作为回文中心时的臂长
        // 回文范围到达最远端的回文中心，一级他的臂长
        int j = -1;
        int j_arm_len = 0;
        // 最长回文字串的中心和臂长
        int center = 1;
        int max_arm_len = 0;
        int i = 1;
        while (i < string.length()-1){
            if (i >= j+j_arm_len){ // 不能利用之前的信息
                int l = i-1;
                int r = i+1;
                while (l >=0 && r < string.length()&&chars[l] == chars[r]){
                    l--;
                    r++;
                }
                // 更新变量
                j = i;
                j_arm_len = r-i-1;
                armArr[i] = j_arm_len;
                if (j_arm_len > max_arm_len){
                    center = j;
                    max_arm_len = j_arm_len;
                }
            } else { // 如果 i在 j的回文范围内
                int mirror = 2*j-i; // i 关于 j的镜像元素索引
                int mirror_arm_len = armArr[mirror];
                int l, r;
                if (i+mirror_arm_len >= j+j_arm_len){ // 如果 i 加上mirror的臂长超过 j的范围
                    r = j+j_arm_len;
                    l = 2*i-r;
                } else {
                    r = i + mirror_arm_len;
                    l = i - mirror_arm_len;
                }
                while (l >=0 && r < string.length()&&chars[l] == chars[r]){
                    l--;
                    r++;
                }
                int i_arm_len = r-i-1;
                armArr[i] = i_arm_len;
                if (i+i_arm_len > j+j_arm_len){
                    j = i;
                    j_arm_len = i_arm_len;
                }
                if (i_arm_len > max_arm_len){
                    center = i;
                    max_arm_len = i_arm_len;
                }
            }
            i++;
        }
        StringBuilder ans = new StringBuilder();
        for (int k = center - max_arm_len; k <= center+max_arm_len ; k++) {
            if (chars[k]!='#'){
                ans.append(chars[k]);
            }
        }
        return ans.toString();
    }
}
