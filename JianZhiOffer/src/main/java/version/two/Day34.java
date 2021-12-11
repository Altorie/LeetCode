package version.two;

import java.util.Arrays;
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
 * @TIME 2021/12/11 11:19
 * @DESCRIPTION 
 **/
public class Day34 {

    public static void main(String[] args) {
        Day34 d = new Day34();
        d.findTargetSumWays(new int[]{0,0,0,0,0,0,0,1}, 1);
    }

    /**
     * 剑指 Offer II 100. 三角形中最小路径之和
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(m-1).size();
        int[][] dp = new int[m][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < m; i++) {
            for (int j = 0; j <= i; j++) {
                if (j > i-1 ) {
                    dp[i][j] = dp[i-1][j-1];
                } else if (j == 0){
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]);
                }
                dp[i][j] += triangle.get(i).get(j);
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i : dp[m-1]){
            ans = Math.min(ans, i);
        }
        return ans;
    }

    /**
     * 剑指 Offer II 101. 分割等和子集
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i : nums){
            sum += i;
        }
        if (sum %2 != 0)return false;
        int target = sum / 2;
        // dp[i][j] : nums[0:i] 中是否有和为 j 的子序列
        int[][] dp = new int[nums.length][target+1];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 1; // 一定有何为 0 的子序列
        }
        if (nums[0] <= target) dp[0][nums[0]] = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < target+1; j++) {
                dp[i][j] = dp[i-1][j];
                if (dp[i][j] == 0 && j-nums[i] >= 0){
                    dp[i][j] = dp[i-1][j-nums[i]];
                }
            }
        }
        return dp[nums.length-1][target]==1;
    }

    /**
     * 剑指 Offer II 102. 加减的目标值
     * 给定一个正整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     * 转化为一个 0-1 背包问题
     *  假设表达式中，添加 ‘-’ 的元素的和为 neg，那么添加 ‘+’ 的元素的和为 sum-neg，根据题目要求，有
     *      (sum-neg) - neg = target
     *      neg = (sum-target)/2
     *  也就是需要在 nums 中找出一个子序列，和为 neg
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int i : nums){
            sum += i;
        }
        if (target < -sum || target > sum || (sum-target)%2!=0)return 0;
        int neg = (sum-target)/2;
        // dp[j]: 和为 j 的子序列的数目
        int[] dp = new int[neg+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) { // 选择前 i 个数
            for (int j = neg; j >= 0 ; j--) {
                if (j-nums[i-1] >= 0){
                    dp[j] += dp[j-nums[i-1]];
                }
            }
        }
        return dp[neg];
    }
}
