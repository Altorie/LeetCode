package version.two;
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
 * @TIME 2021/11/29 10:04
 * @DESCRIPTION 
 **/
public class Day23 {

    /**
     * 剑指 Offer II 068. 查找插入位置
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
        while (start <= end){
            int mid = start + (end - start) / 2;
            if (nums[mid] > target){
                end = mid-1;
            } else if (nums[mid] < target){
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return start;
    }

    /**
     * 剑指 Offer II 069. 山峰数组的顶部
     *
     * 二分。
     * 如果 mid 的值比两侧都大，那么 mid 就是山顶元素
     * 如果 mid 比其右侧小，说明 山顶元素 在 mid 和 r 之间
     * 如果 mid 比其左侧大，说明 山顶元素 在 l 和 mid 之间
     * 注意边界条件
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int l = 0;
        int r = arr.length-1;
        while (true){
            int mid = l + (r-l)/2;
            if ((mid==0 || arr[mid] > arr[mid-1]) && (arr[mid] > arr[mid+1] || mid == arr.length-1)){
                return mid;
            } else if (arr[mid] < arr[mid+1]){
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
    }

    /**
     * 剑指 Offer II 070. 排序数组中只出现一次的数字
     * 给定一个只包含整数的有序数组 nums ，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length-1;
        if (l==r || nums[l]!=nums[l+1])return nums[l];
        if (nums[r]!=nums[r-1])return nums[r];
        l++;
        r--;
        while (true){
            int mid = l + (r-l)/2;
            if (nums[mid]!=nums[mid-1] && nums[mid]!=nums[mid+1]){
                return nums[mid];
            } else if (nums[mid] == nums[mid-1]){
                // 如果 target 在 mid 左边，那么 0 到 mid 一共有奇数个元素
                if (mid%2==0){ // mid+1 是奇数，相当于 mid 是偶数
                    r = mid-1;
                } else {
                    l = mid + 1;
                }
            } else { // nums[mid] == nums[mid+1]
                if (mid%2!=0){
                    r = mid-1;
                } else {
                    l = mid+1;
                }
            }
        }
    }
}
