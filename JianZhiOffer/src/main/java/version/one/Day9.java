package version.one;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
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
 * @TIME 2021/12/24 8:35
 * @DESCRIPTION 
 **/
public class Day9 {

    public static void main(String[] args) {
        Day9 d = new Day9();
        d.eatenApples(new int[]{2, 1, 10}, new int[]{2, 10, 1});
    }
    /**
     * 剑指 Offer 42. 连续子数组的最大和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = nums[i];
            if (dp[i] + dp[i-1] > dp[i])dp[i] += dp[i-1];
        }
        int ans = Integer.MIN_VALUE;
        for (int i : dp){
            ans = Math.max(ans, i);
        }
        return ans;
    }

    /**
     * 剑指 Offer 47. 礼物的最大价值
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = grid[i-1][j-1]+ Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m][n];
    }

    /**
     * 1705. 吃苹果的最大数目
     * @param apples
     * @param days
     * @return
     */
    public int eatenApples(int[] apples, int[] days) {
        int n = apples.length;
        int ans = 0;
        // Pair<苹果腐烂的前一天， 苹果的数量>
        // 按照苹果腐烂时间构建小顶堆
        PriorityQueue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<>(n, Comparator.comparingInt(Pair::getKey));
        for (int i = 0; i < n; i++) {
            if (apples[i]!=0){ // 加入今天长出的苹果
                priorityQueue.offer(new Pair<>(i+days[i]-1, apples[i]));
            }
            // 寻找没过期的苹果
            Pair<Integer, Integer> target = null;
            while (!priorityQueue.isEmpty() && priorityQueue.peek().getKey() < i){
                priorityQueue.poll();
            }
            target = priorityQueue.poll();
            if (target!=null){
                ans++;
                if (target.getValue()-1 > 0){ // 吃了一个还有剩余
                    priorityQueue.add(new Pair<>(target.getKey(), target.getValue()-1));
                }
            }
        }
        int day = n;
        while (true){
            while (!priorityQueue.isEmpty() && priorityQueue.peek().getKey() < day){
                priorityQueue.poll();
            }
            Pair<Integer, Integer> target = priorityQueue.poll();
            if (target==null){
                break;
            }
            ans++;
            if (target.getValue()-1 > 0){ // 吃了一个还有剩余
                priorityQueue.add(new Pair<>(target.getKey(), target.getValue()-1));
            }
            day++;
        }
        return ans;
    }
}
