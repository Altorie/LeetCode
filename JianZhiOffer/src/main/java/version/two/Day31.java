package version.two;

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
 * @TIME 2021/12/6 20:34
 * @DESCRIPTION 
 **/
public class Day31 {
    public static void main(String[] args) {
        Day31 d= new Day31();
        d.lenLongestFibSubseq(new int[]{1,2,3,4,5,6,7,8});
    }

    /**
     * 剑指 Offer II 091. 粉刷房子
     * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
     * 0 : 红色
     * 1 : 蓝色
     * 2 : 绿色
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        int n = costs.length;
        // dp[i][0] : 当第 i 栋 涂红色时，整体最低的花费
        int[][] dp = new int[n][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int i = 1; i < n; i++) {
            // 涂红色，之前只能是蓝色或绿色
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][0];
            // 涂蓝色
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i][1];
            // 涂绿色
            dp[i][2] = Math.min(dp[i-1][1], dp[i-1][0]) + costs[i][2];
        }
        return Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));
    }

    /**
     * 剑指 Offer II 092. 翻转字符
     * @param s
     * @return
     */
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        // dp[i][0] : 把第 i 个字符变成 1，0 到 i 是单调递增的最小翻转数
        int[][] dp = new int[n][2];
        dp[0][0] = s.charAt(0) == '0'? 0 : 1;
        dp[0][1] = s.charAt(0) == '1'? 0 : 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '0'){
                dp[i][0] = dp[i-1][0];
                dp[i][1] = 1 + Math.min(dp[i-1][0], dp[i-1][1]);
            } else {
                dp[i][0] = dp[i-1][0] + 1;
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]);
            }
        }
        return Math.min(dp[n-1][0], dp[n-1][1]);
    }

    /**
     * 剑指 Offer II 093. 最长斐波那契数列
     * @param arr
     * @return
     */
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        // dp[i][j]: 以第 i 个元素和第 j 个元素结尾的斐波那契数列长度
        int[][] dp = new int[n][n];
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }
        for (int i = 1; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                // 找出 arr[k] + arr[i] = arr[j]
                // 在 [0, i-1] 的范围内寻找 arr[k] = arr[j] - arr[i];
                // 两种方法：使用哈希表记录每个元素的索引    利用二分查找（由于题目中给出的额数组严格递增）
                int k = map.getOrDefault(arr[j]-arr[i], -1);
                if (k!=-1 && k < i){ // k 存在且 k < i
                    if (dp[k][i] == 0){
                        dp[i][j] = Math.max(dp[i][j], 3);
                    } else {
                        dp[i][j] = Math.max(dp[i][j], dp[k][i]+1);
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
}
