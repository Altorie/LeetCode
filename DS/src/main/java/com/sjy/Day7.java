package com.sjy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {
    public static void main(String[] args) {
        Day7 d = new Day7();
//        System.out.println(d.wordPattern("abba", "dog cat cat dog"));
        System.out.println(d.partitionLabels("ababcbacadefegdehijhklij"));
    }

    /**
     *
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        String[] strs = s.split(" ");
        if (strs.length != pattern.length()){
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char key = pattern.charAt(i);
            if (!map.containsKey(key)){
                for(String value : map.values()){
                    if (value.equals(strs[i])){
                        return false;
                    }
                }
                map.put(key, strs[i]);
            } else if (!map.get(key).equals(strs[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
     * 返回一个表示每个字符串片段的长度的列表。
     *
     * 输入：S = "ababcbacadefegdehijhklij"
     * 输出：[9,7,8]
     * 解释：
     * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
     * 每个字母最多出现在一个片段中。
     * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        int len = s.length();
        int start = 0;
        int end = len - 1;
        int position = 0;
        // 记录每一个字符最后一次出现的位置
        Map<Character, Integer> map = new HashMap<>();
        for (int i = end; i >= start ; i--) {
            if (!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), i);
            }
        }

        while (position < end) {
            position = map.get(s.charAt(start));
            for (int i = start+1; i < position; i++) {
                position = Integer.max(map.get(s.charAt(i)), position);
            }
            list.add(position - start + 1);
            start = position + 1;
        }
        return list;
    }

}
