package com.sjy.base;

import java.util.*;

public class Day8 {
    public static void main(String[] args) {
        Day8 d = new Day8();
        List<String> list = new ArrayList<>();
        System.out.println(d.multiply("140", "721"));
    }

    /**
     * 49. 字母异位词分组
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        /**
         * 遍历到一个 word，现在 map中查。如果有，把它加入到 result的第 map.get(word)个list中
         * 如果没有，创建一个新的 list并把自己加入，同时获取这个list再 result中的 index。
         *      将word的所有异位词加入 map，键为词，值为index
         *
         * 但是内存超出限制
         */
//        Map<String, Integer> map = new HashMap<>();
//        for (String word : strs){
//            // 查找是否有本词的异位词存在于result中
//            if (map.containsKey(word)){// 存在
//                result.get(map.get(word)).add(word);
//            } else { // 不存在
//                List<String> temp = new ArrayList<>();
//                // 将自己加入结果
//                temp.add(word);
//                result.add(temp);
//                // 记录这一组异位词的位置
//                int index = result.size()-1;
//                // 将自己加入map
//                map.put(word, index);
//                // 将本词的异位词加入map
//                if (!word.equals("")){
//                    char[] chars = word.toCharArray();
//                    List<String> allWords = new ArrayList<>();
//                    getAllWords(chars, allWords, 0);
//                    for (String str : allWords){
//                        map.put(str, index);
//                    }
//                }
//            }
//        }
//        return result;
        /**
         * 如果两个单词是字母异位词，那么这两个单词的组成字母一定是一样的
         */
        Map<String, List<String>> map = new HashMap<>();
        for (String word : strs){
            // 把单词字母排序后组成的新单词作为 map的键
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String str = new String(chars);
            if (map.containsKey(str)){
                map.get(str).add(word);
            }else {
                List<String> list = new ArrayList<>();
                list.add(word);
                map.put(str, list);
            }
        }
        return new ArrayList<>(map.values());
    }
    public void getAllWords(char[] chars, List<String> list, int n){
        if (n == chars.length-1){
            list.add(new String(chars));
            return;
        }
        for (int i = n; i < chars.length; i++) {
            getAllWords(chars, list, n+1);
            char temp = chars[n];
            for (int j = n; j < chars.length-1; j++) {
                chars[j] = chars[j+1];
            }
            chars[chars.length-1] = temp;
        }
    }

    /**
     * 43. 字符串相乘
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)){
            return "0";
        }
        /**
         * 做加法
         */
//        int len1 = num1.length();
//        int len2 = num2.length();
//        if (len1 > len2){
//            return multiply(num2, num1);
//        }
//        String[] strs = new String[len1];
//        for (int i = len1 - 1; i >= 0 ; i--) {
//            StringBuilder sb = new StringBuilder();
//            int temp = num1.charAt(i)-48;
//            int add = 0;
//            for (int j = len2 - 1; j >= 0; j--) {
//                int value = temp * (num2.charAt(j)-48) + add;
//                sb.append(value % 10);
//                add = value / 10;
//            }
//            if (add!=0){
//                sb.append(add);
//            }
//            strs[len1-1-i]=sb.reverse().toString();
//        }
//        // 对 strs中的值错位相加
//        String ans = strs[0];
//        for (int i = 1; i < len1; i++) {
//            String down = strs[i];
//            StringBuilder sb = new StringBuilder();
//            for (int j = 0; j < i; j++) {
//                sb.append(ans.charAt(ans.length()-1-j));
//            }
//            int add = 0;
//            for (int j = ans.length()-1-i; j >=0 ; j--) {
//                int value = ans.charAt(j)-48 + down.charAt(down.length()-1-(ans.length()-1-i-j))-48+add;
//                sb.append(value % 10);
//                add = value / 10;
//            }
//            int index = down.length()-1-(ans.length()-1-i);
//            for (int j = index-1; j >= 0 ; j--) {
//                int value = down.charAt(j)-48 + add;
//                sb.append(value % 10);
//                add = value / 10;
//            }
//
//            if (add!=0){
//                sb.append(add);
//            }
//            ans = sb.reverse().toString();
//        }
//        return ans;
        /**
         * 做乘法
         * 创建长度为 m+nm+n 的数组 ansArr 用于存储乘积。对于任意 0 <= i < m 和 0 <= j < n，num1[i] * num2[j]的结果位于 ansArr[i+j+1]
         * 如果 ansArr[i+j+1]≥10，则将进位部分加到ansArr[i+j]。
         */
        int len1 = num1.length();
        int len2 = num2.length();
        int[] ansArr = new int[len1 + len2];
        for (int i = len1 -1; i >=0 ; i--) {
            for (int j = len2- 1; j >=0 ; j--) {
                int value = (num1.charAt(i)-48) * (num2.charAt(j) - 48);
                ansArr[i+j+1] += value;
            }
        }
        for (int i = len1+len2-1; i >=1 ; i--) {
            if (ansArr[i] >= 10){
                ansArr[i] %= 10;
                ansArr[i-1] += ansArr[i] / 10;
            }
        }
        int start = ansArr[0]==0?1:0;
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < len1+len2; i++) {
            sb.append(ansArr[i]);
        }
        return sb.toString();
    }
}
