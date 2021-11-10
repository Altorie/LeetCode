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
 * @TIME 2021/11/10 8:54
 * @DESCRIPTION 
 **/
public class Day4 {
    public static void main(String[] args) {

    }

    /**
     * 剑指 Offer II 010. 和为 k 的子数组
     * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
     *
     * 有负数存在，所以无法使用滑动窗口。因为不能保证增加一个元素时子数组和一定增大。
     * 使用前缀和
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int ans = 0;
        // 记录每个前缀和的出现次数
        Map<Integer, Integer> map = new HashMap<>();
        // 为了匹配起始元素为第一个元素的子数组
        map.put(0, 1);
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            // 寻找前缀和为 sum-k 的出现次数
            ans += map.getOrDefault(sum-k, 0);
            // 将sum加入map
            // 这一步必须后执行。为了避免k=0的情况下多算一次
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return ans;
    }

    /**
     * 剑指 Offer II 011. 0 和 1 个数相同的子数组
     * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        /**
         * 子数组的 0 和 1 的数量相同，代表把 0 替换成 -1 则子数组和为 0
         * 转换成了求和为 0的最长子数组
         */
        int ans = 0;
        // 键存储前缀和，值存储该前缀和第一次出现的下标
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(sum,-1);// 为了匹配从第一个元素开始的子数组
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==0){
                sum -= 1;
            } else {
                sum += 1;
            }
            if (map.containsKey(sum)){
                ans = Math.max(ans, i-map.get(sum));
            } else {
                map.put(sum,i);
            }
        }
        return ans;
    }


    /**
     * 剑指 Offer II 012. 左右两边子数组的和相等
     * 给你一个整数数组 nums ，请计算数组的 中心下标 。
     *
     * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
     *
     * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
     *
     * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        // 由于要返回最靠近左边的，所以先计算右侧元素的和数组，再从左侧开始遍历
        int[] rightSum = new int[nums.length];
        for (int i = nums.length-2; i >= 0; i--) {
            rightSum[i] = rightSum[i+1] + nums[i+1];
        }
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == rightSum[i])return i;
            leftSum += nums[i];
        }
        return -1;
    }
}

/**
 * 剑指 Offer II 013. 二维子矩阵的和
 */
class NumMatrix {

    private int[][] preSum; // 前缀和数组
    public NumMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        preSum = new int[rows+1][cols+1];
        for (int i = 1; i <= rows; i++) {
            int temp=0;
            for (int j = 1; j <= cols; j++) {
                preSum[i][j] = preSum[i-1][j] + temp + matrix[i-1][j-1];
                temp += matrix[i-1][j-1];
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // 将原数组中的位置映射到前缀和数组中去
        row1+=1;
        col1+=1;
        row2+=1;
        col2+=1;
        return preSum[row2][col2] + preSum[row1-1][col1-1]-preSum[row2][col1-1]-preSum[row1-1][col2];
    }
}
