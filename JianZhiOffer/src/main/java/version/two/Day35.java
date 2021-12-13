package version.two;

import java.util.Arrays;

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
 * @TIME 2021/12/12 8:49
 * @DESCRIPTION 
 **/
public class Day35 {

    /**
     * 剑指 Offer II 103. 最少的硬币数目
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 你可以认为每种硬币的数量是无限的。
     *
     * 完全背包问题：amount 是背包容量，coins 数组是重量， 每个硬币的价值都为 1
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        Arrays.sort(coins);
        //
        int[] dp = new int[amount+1];
        // 想要凑成 amount，最多的情况是全用 1，所以结果不会超过 amount
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i = 1; i <= amount ; i++) {
            for (int j = 0;  j < n && coins[j] <= i ; j++) {
                dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
            }
        }
        return dp[amount] == amount+1 ? -1 : dp[amount];
    }

    /**
     * 剑指 Offer II 104. 排列的数目
     * 给定一个由 不同 正整数组成的数组 nums ，和一个目标整数 target 。请从 nums 中找出并返回总和为 target 的元素组合的个数。
     * 数组中的数字可以在一次排列中出现任意次，但是顺序不同的序列被视作不同的组合。
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[target+1];
        dp[0] = 1; // 和为 0 的组合数为1
        for (int i = 1; i <= target; i++) { // 遍历目标数
            for (int j = 0; j < n; j++) { // 遍历正整数
                if (i-nums[j]>=0){
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}
