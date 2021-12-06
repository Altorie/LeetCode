package version.two;

import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;

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
 * @TIME 2021/12/5 13:36
 * @DESCRIPTION 
 **/
public class Day29 {

    public static void main(String[] args) {
        Day29 d = new Day29();
        d.restoreIpAddresses("25525511135");
    }

    /**
     * 剑指 Offer II 085. 生成匹配的括号
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('(', n); //
        map.put(')', 0);
        generateParenthesis_backtrack(n, 0, new StringBuffer(), ans, map);
        return ans;
    }
    private void generateParenthesis_backtrack(int n, int index, StringBuffer sb, List<String> ans, Map<Character, Integer> map){
        if (index == n * 2){
            ans.add(sb.toString());
            return;
        }
        if (map.get('(') > 0){
            sb.append('(');
            // ( 可使用次数减 1
            map.put('(', map.get('(')-1);
            // ) 可使用次数加 1
            map.put(')', map.get(')')+1);
            generateParenthesis_backtrack(n, index+1, sb, ans, map);
            sb.deleteCharAt(sb.length()-1);
            // 回溯
            map.put('(', map.get('(')+1);
            map.put(')', map.get(')')-1);
        }
        if (map.get(')') > 0){
            sb.append(')');
            map.put(')', map.get(')')-1);
            generateParenthesis_backtrack(n, index+1, sb, ans, map);
            sb.deleteCharAt(sb.length()-1);
            map.put(')', map.get(')')+1);
        }
    }

    /**
     * 剑指 Offer II 086. 分割回文子字符串
     * 给定一个字符串 s ，请将 s 分割成一些子串，使每个子串都是 回文串 ，返回 s 所有可能的分割方案。
     *
     * 回文串 是正着读和反着读都一样的字符串。
     * @param s
     * @return
     */
    public String[][] partition(String s) {
        List<String[]> ans = new ArrayList<>();
        partition_backtrack(s, 0, new ArrayList<>(), ans);
        String[][] strs = new String[ans.size()][];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = ans.get(i);
        }
        return strs;
    }
    private void partition_backtrack(String s, int start, List<String> list, List<String[]> ans){
        if (start == s.length()){
            String[] temp = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                temp[i] = list.get(i);
            }
            ans.add(temp);
        }
        for (int end = start; end < s.length(); end++) {
            if (isHuiWen(s, start, end)){
                list.add(s.substring(start, end+1));
                partition_backtrack(s, end+1, list, ans);
                list.remove(list.size()-1);
            }
        }
    }
    private boolean isHuiWen(String s, int start, int end){
        while (start < end){
            if (s.charAt(start) != s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    /**
     * 剑指 Offer II 087. 复原 IP
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        restoreIpAddresses_backtrack(s, 0, 0, new StringBuffer(), ans);
        return ans;
    }
    private void restoreIpAddresses_backtrack(String s, int start, int num, StringBuffer sb, List<String> ans){
        if (start == s.length() && num == 4){ // 扫描结束，且有四个整数
            sb.deleteCharAt(sb.length()-1);
            ans.add(sb.toString());
            sb.append('.');
            return;
        }
        if (start < s.length() && num >= 4)return; // 扫描还未结束，已有四个整数
        if (start>=s.length() && num <4)return;// 扫描已结束，还未满四个整数

        if (s.charAt(start) == '0'){ // 排除前导 0
            sb.append(0).append('.');
            restoreIpAddresses_backtrack(s, start+1, num+1, sb, ans);
            sb.delete(sb.length()-2, sb.length());
        } else {
            int min = Math.min(s.length(), start+3);
            int count = 2;
            for (int end = start; end < min; end++) {
                int i = Integer.parseInt(s.substring(start, end+1));
                if (i <= 255){
                    sb.append(i).append('.');
                    restoreIpAddresses_backtrack(s, end+1, num+1, sb, ans);
                    sb.delete(sb.length()-count, sb.length());
                    count++;
                }
            }

        }
    }
}
