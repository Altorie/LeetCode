package com.sjy.easy;

public class Day18 {
    public static void main(String[] args) {

    }

    /**
     * 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     *
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int ans = 0;
        // dp[i] 是结尾为 nums[i]的递增子序列的长度（必须包括nums[i]）
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            // dp[i] = max(dp[j]) + 1, nums[i] > nums[j]
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]){
                    max = Math.max(dp[j], max);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 376. 摆动序列
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
     *
     * 例如，[1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3)是正负交替出现的。
     *
     * 相反，[1, 4, 7, 2, 5]和[1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
     * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
     *
     * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        /**
         * 方法 1 ：动态规划
         * O(n^2)
         */
//        int len = nums.length;
//        int ans = 1;
//        // dp[i][j]是以 nums[i]结尾的最长摆动序列的长度
//        // j=0代表 nums[i-1] < nums[i], j=1代表 nums[i-1] > nums[i]
//        int[][] dp = new int[len][2];
//        dp[0][0] = 1;
//        dp[0][1] = 1;
//        for (int i = 1; i < len; i++) {
//            int max_0 = 0;
//            int max_1 = 0;
//            for (int j = 0; j < i; j++) {
//                if (nums[j] > nums[i]){//
//                    max_1 = Math.max(dp[j][0], max_1);
//                } else if (nums[j] < nums[i]){
//                    max_0 = Math.max(dp[j][1], max_0);
//                }
//            }
//            dp[i][0] = max_0+1;
//            dp[i][1] = max_1+1;
//            ans = Math.max(ans, Math.max(max_0, max_1)+1);
//        }
//        return ans;
        /**
         * 方法 2 ：动态规划
         * 选取的元素应该是每一个局部单调字串的最后一个，这样才能保证最长。
         * 如 2 3 [5] 4 [2] 3 6 [7] 5 [9]
         * 应该选 2 5 2 7 5 9
         * O(n)
         */
        int len = nums.length;
        int up = 1;
        int down = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i-1]){
                up = down+1;
            } else if (nums[i] < nums[i-1]){
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
}
