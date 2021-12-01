package version.two;

import java.util.Random;

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
 * @TIME 2021/11/30 15:49
 * @DESCRIPTION 剑指 Offer II 071. 按权重生成随机数
 **/
public class Solution {
    int[] sum;
    Random random = new Random();
    int max;
    public Solution(int[] w) {
        int len = w.length;
        sum = new int[len];
        sum[0] = w[0]-1; // 因为 Random.nextInt(a)的范围是[0, a)，所以
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i-1] + w[i];
        }
        max = sum[len-1];
    }

    public int pickIndex() {
        int i = random.nextInt(max+1);
        return searchInsert(sum, i);
    }

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
}
