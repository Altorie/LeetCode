package version.one;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

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
 * @TIME 2021/12/25 13:04
 * @DESCRIPTION 
 **/
public class Day10 {
    /**
     * 剑指 Offer 46. 把数字翻译成字符串
     * @param num
     * @return
     */
    public int translateNum(int num) {
        String str = num + "";
        int n = str.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n ; i++) {
            // i-1 作为一个数
            dp[i] = dp[i-1];
            // i-2i-1作为一个数
            int n1 = str.charAt(i-2) - 'a';
            int n2 = str.charAt(i-1) - 'a';
            if (n1!=0 && n1*10+n2 <= 26){
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }

    /**
     * 剑指 Offer 48. 最长不含重复字符的子字符串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n==0)return 0;
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        int l = 0;
        int len = 0;
        for (int r = 0; r < s.length(); r++) {
            len++;
            char c = s.charAt(r);
            map.put(c, map.getOrDefault(c, 0)+1);
            while (map.getOrDefault(c, 0) > 1){
                map.put(s.charAt(l), map.getOrDefault(s.charAt(l), 0)-1);
                l++;
                len--;
            }
            ans = Math.max(ans, len);
        }
        return ans;
    }
}
