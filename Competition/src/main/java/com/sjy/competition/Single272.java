package com.sjy.competition;

import java.util.ArrayList;
import java.util.List;

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
 * @TIME 2021/12/19 10:17
 * @DESCRIPTION 
 **/
public class Single272 {
    public static void main(String[] args) {
        new Single272().kIncreasing(new int[]{2,2,2,2,2,1,1,4,4,3,3,3,3,3}, 1);
    }

    /**
     * 5956. 找出数组中的第一个回文字符串
     * @param words
     * @return
     */
    public String firstPalindrome(String[] words) {
        for (String str : words){
            int l = 0;
            int r  =str.length()-1;
            boolean flag = true;
            while (l < r){
                if (str.charAt(l)!=str.charAt(r)){
                    flag = false;
                    break;
                }else {
                    l++;
                    r--;
                }
            }
            if (flag){
                return str;
            }
        }
        return "";
    }

    /**
     * 5957. 向字符串添加空格
     * @param s
     * @param spaces
     * @return
     */
    public String addSpaces(String s, int[] spaces) {
        StringBuffer sb = new StringBuffer();
        int start = 0;
        for (int i : spaces){
            sb.append(s.substring(start, i)).append(" ");
            start = i;
        }
        sb.append(s.substring(start, s.length()));
        return sb.toString();
    }

    /**
     * 5958. 股票平滑下跌阶段的数目
     * @param prices
     * @return
     */
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        // dp[i]：以 i 结尾的平滑下跌阶段的长度
        long[] dp = new long[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            if (prices[i]+1 == prices[i-1]) dp[i] = dp[i-1]+1;
        }
        long ans = 0;
        for (long num : dp){
            ans += num;
        }
        return ans;
    }

    /**
     * 5959. 使数组 K 递增的最少操作次数
     * @param arr
     * @param k
     * @return
     */
    public int kIncreasing(int[] arr, int k) {
        int ans = 0;
        for (int i = 0; i < k; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = i; j < arr.length; j+=k) {
                list.add(arr[j]);
            }
            ans += list.size() - increasing(list);
        }
        return ans;
    }
    private int increasing(List<Integer> list){ // 将一个数组变为递增的最少操作数，相当于找出这个数组的最长非递减子序列
        // 贪心 + 二分
        int len = 1;
        // d[i]: 长度为 i 的最长非递减子序列的末尾元素的最小值
        int[] d = new int[list.size()+1];
        d[len] = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) >= d[len]){
                d[++len] = list.get(i);
            } else {
                // 二分查找到第一个小于或等于 list.get(i) 的位置
                int l = 1;
                int r = len;
                int index = 0;
                while (l <= r){
                    int mid = l + (r-l)/2;
                    if (d[mid] >= list.get(i)){
                        r = mid-1;
                    } else {
                        if (d[mid+1] >= list.get(i)){
                            index = mid;
                            break;
                        } else {
                            l = mid+1;
                        }
                    }
                }
                if (d[index+1] == list.get(i))index++;
                d[index+1] = list.get(i);
            }
        }
        return len;
    }
}
