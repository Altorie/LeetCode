package com.sjy.base;

import java.util.HashMap;
import java.util.Map;

public class Day6 {
    public static void main(String[] args) {
        char a = '1';
        System.out.println((int)a);
    }

    /**
     * 给定两个字符串形式的非负整数 num1 和 num2，计算它们的和并同样以字符串形式返回。
     *
     * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger），也不能直接将输入的字符串转换为整数形式。
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 < len2){
            return addStrings(num2, num1);
        }
        StringBuilder sb = new StringBuilder();
        // 是否进位
        boolean flag = false;
        
        for (int i = len2-1; i >= 0; i--) {
            int sum = (int)(num2.charAt(i))-48 +(int)(num1.charAt(len1 - len2 +i))-48;
            if (flag){
                sum++;
            }
            flag = sum >= 10;
            sb.append(sum % 10);
        }

        for (int i = len1 - len2 - 1; i >= 0 ; i--) {
            int sum = (int)(num1.charAt(i))-48;
            if (flag){
                sum++;
            }
            flag = sum >= 10;
            sb.append(sum % 10);
        }
        if (flag){
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    /**
     * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
     * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int count = 0;
        for (Character c : map.keySet()){
            if (map.get(c) % 2 == 1){
                count++;
            }
        }
        if (count == 0){
            return s.length();
        }else {
            return s.length() - count + 1;
        }
    }
}
