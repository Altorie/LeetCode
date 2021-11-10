package version.two;

import java.lang.reflect.Array;
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
 * @TIME 2021/11/9 15:51
 * @DESCRIPTION 
 **/
public class Day3 {
    public static void main(String[] args) {
        int r = 0;
        while (r < 10 ){
            System.out.println(++r);
        }
    }

    /**
     * 剑指 Offer II 007. 数组中和为 0 的三个数
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i-1])continue; // 是重复的
            int target = -nums[i];
                int l = i+1;
                int r = len-1;
                while (l < r){
                    if (nums[l] + nums[r] < target){
                        l++;
                    } else if(nums[l] + nums[r] > target){
                        r--;
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        ans.add(list);
                        // 之后跳过相同的元素
                        while (l<r && nums[l]==nums[l++]){

                        }
                        while (l<r && nums[r]==nums[r--]){

                        }
                    }
                }
        }
        return ans;
    }

    /**
     * 剑指 Offer II 008. 和大于等于 target 的最短子数组
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int sum = nums[0];
        int r = 0;
        while (r < len-1 && sum < target){
            sum += nums[++r];
        }
        if (sum < target){
            return 0;
        }
        int l = 0;
        int ans = r-l; // 由于 l 和 r 都是索引，所以长度其实是 r-l+1
        while (r < len){
            while (sum-nums[l] >= target){
                sum -= nums[l];
                l++;
            }
            ans = Math.min(ans, r-l);
            if (r==len-1)break;
            sum += nums[++r];
        }
        return ans+1;
    }

    /**
     * 剑指 Offer II 009. 乘积小于 K 的子数组
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0;
        int l=0,r=0;
        int prod = 1;
        for (; r < nums.length; r++) {
            prod *= nums[r];
            while (l <= r && prod >= k){
                prod/=nums[l++];
            }
            if (l <= r){ // l 超过 r的情况代表 nums[r] > k
                ans += r-l+1;
            }
        }
        return ans;
    }
}
