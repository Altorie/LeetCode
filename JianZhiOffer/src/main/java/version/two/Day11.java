package version.two;

import java.util.*;

/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 * @AUTHOR zuo-zhenjun
 * @TIME 2021/11/17 10:25
 * @DESCRIPTION 
 **/
public class Day11 {

    /**
     * 剑指 Offer II 033. 变位词组
     * 给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。
     * 注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // 把排序后的字符串作为 key，与这个 key是变位词的 字符串的列表为 value
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!map.containsKey(key)){
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            } else {
                map.get(key).add(str);
            }
        }
        List<List<String>> ans = new ArrayList<>(map.values());
        return ans;
    }

    /**
     * 剑指 Offer II 034. 外星语言是否排序
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order) {
        // 字符的顺序
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        for (int i = 0; i < words.length-1; i++) {
            String word1 = words[i];
            String word2 = words[i+1];
            int len = Math.min(word1.length(), word2.length());
            boolean flag = true;
            for (int j = 0; j < len; j++) {
                if (map.get(word1.charAt(j)) < map.get(word2.charAt(j))){
                    flag = false;
                    break;
                }
                if (map.get(word1.charAt(j)) > map.get(word2.charAt(j)))return false;
            }
            if (flag && word1.length() > word2.length()){
                return false;
            }
        }
        return true;
    }

    /**
     * 剑指 Offer II 035. 最小时间差
     * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
     *
     * 实际上是一个基数排序
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        // 一共 1440 个分钟点
        boolean[] arr = new boolean[1440];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (String str : timePoints) {
            int h = 10*(str.charAt(0)-48) + str.charAt(1) - 48;
            int m = 10*(str.charAt(3)-48) + str.charAt(4) - 48;
            int p = h*60 + m;
            min = Math.min(p, min);
            max = Math.max(p, max);
            if (arr[p]){ // 时间点 p 已经出现过
                return 0;
            }
            arr[p] = true;
        }
        int ans = Math.min(max- min, 1440-min+max);
        for (int i = min+1; i < 1440; i++) {
            if (arr[i]){
                ans = Math.min(ans, i-min);
                min = i;
            }
        }
        return ans;
    }
}
