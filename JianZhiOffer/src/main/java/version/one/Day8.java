package version.one;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
 * @TIME 2021/12/23 9:28
 * @DESCRIPTION 
 **/
public class Day8 {

    public static void main(String[] args) {
        Day8 d= new Day8();
        d.longestDupSubstring("banana");
    }

    /**
     * 剑指 Offer 10- I. 斐波那契数列
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n <= 1) return n;
        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = (a + b)%1000000007;
            a = b;
            b = temp;
        }
        return b;
    }

    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n <= 1) return 1;
        int a = 1;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = (a + b)%1000000007;
            a = b;
            b = temp;
        }
        return b;
    }

    /**
     * 剑指 Offer 63. 股票的最大利润
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length==0)return 0;
        int min = prices[0];
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans = Math.max(prices[i] - min, ans);
            min = Math.min(min, prices[i]);
        }
        return ans;
    }

    /**
     * 1044. 最长重复子串
     * 给你一个字符串 s ，考虑其所有 重复子串 ：即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。
     *
     * 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。
     * @param s
     * @return
     */
    public String longestDupSubstring(String s) {
        /**
         * 1 字符串哈希 + 二分
         */
        // 对每个字符编码
        int n = s.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i) - 'a';
        }

        Random random = new Random();
        // 生成两个进制
        int a1 = random.nextInt(75)+26;
        int a2 = random.nextInt(75)+26;
        // 生成两个模
        int mod1 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
        int mod2 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;

        // 二分查找，找的是子串的长度
        int l = 1, r = n-1;
        int length = 0;
        int start = -1;
        while (l <= r){
            int m = l + (r-l)/2;
            int index = check(arr, m, a1, a2, mod1, mod2);
            if (index!=-1){ // 说明这个长度存在重复子串，可以尝试更长的
                l = m+1;
                length = m;
                start = index;
            } else {
                r = m-1;
            }
        }
        if (length!=0){
            return s.substring(start, start+length);
        } else {
            return "";
        }
    }
    private int check(int[] arr, int m, int a1, int a2, int mod1, int mod2){// 是否存在长度为 m 的重复子串
        int n = arr.length;
        // 计算基于随机生成的两个进制的哈希值
        long h1 = 0;
        long h2 = 0;
        for (int i = 0; i < m; i++) {
            h1 = (h1*a1%mod1+arr[i])%mod1;
            h2 = (h2*a2%mod2+arr[i])%mod2;
            if (h1 < 0)h1 += mod1;
            if (h2 < 0 )h2 += mod2;
        }
        Set<Long> set = new HashSet<>();
        set.add(h1*mod2 + h2);

        // 计算两个幂值
        long aL1 = pow(a1, m, mod1);
        long aL2 = pow(a2, m, mod2);
        for (int start = 1; start <= n-m; start++) {
            h1 = (h1 * a1 % mod1 - arr[start-1]*aL1%mod1 + arr[start+m-1])%mod1;
            h2 = (h2 * a2 % mod2 - arr[start-1]*aL2%mod2 + arr[start+m-1])%mod2;
            if (h1<0)h1+=mod1;
            if (h2<0)h2+=mod1;
            if (set.contains(h1*mod2+h2)){
                return start;
            }else {
                set.add(h1*mod2+h2);
            }
        }
        return -1;
    }
    private long pow(int a, int m, int mod){ // 计算 a^(m-1)
        long ans = 1;
        long contribute = a;
        while (m > 0) {
            if (m % 2 == 1) {
                ans = ans * contribute % mod;
                if (ans < 0) {
                    ans += mod;
                }
            }
            contribute = contribute * contribute % mod;
            if (contribute < 0) {
                contribute += mod;
            }
            m /= 2;
        }
        return ans;
    }
}
