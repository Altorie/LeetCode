package version.one;

import java.util.HashSet;
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
 * @TIME 2021/12/19 9:42
 * @DESCRIPTION 
 **/
public class Day4 {

    /**
     * 剑指 Offer 03. 数组中重复的数字
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums){
            if (set.contains(num))return num;
            set.add(num);
        }
        return 0;
    }

    /**
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        int firstIndex = -1; // 第一个小于target的元素下标
        while (l <= r){
            int mid = l + (r-l)/2;
            if (nums[mid] >= target){
                r = mid-1;
            } else {
                if (mid==nums.length-1 || nums[mid+1]==target){
                    firstIndex = mid;
                    break;
                }else {
                    l = mid+1;
                }
            }
        }
        l = 0;
        r = nums.length-1;
        int lastIndex = nums.length;
        while (l <= r){
            int mid = l + (r-l)/2;
            if (nums[mid] <= target){
                l = mid+1;
            } else {
                if (mid == 0 || nums[mid-1]==target){
                    lastIndex = mid;
                    break;
                } else {
                    r = mid-1;
                }
            }
        }
        return lastIndex-firstIndex-1;
    }

    /**
     * 剑指 Offer 53 - II. 0～n-1中缺失的数字
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        if (nums[0]!=0)return 0;
        if (nums[n-1]!=n)return n;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]+1)continue;
            return nums[i]-1;
        }
        return 0;
    }
}
