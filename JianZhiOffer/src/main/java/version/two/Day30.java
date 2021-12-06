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
 * @TIME 2021/12/6 11:16
 * @DESCRIPTION 
 **/
public class Day30 {

    public static void main(String[] args) {

    }

    /**
     * 剑指 Offer II 088. 爬楼梯的最少成本
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int a = 0;
        int b = 0;
        for (int i = 2; i <= len; i++) {
            int temp = Math.min(a + cost[i-2], b + cost[i-1]);
            a = b;
            b = temp;
        }
        return b;
    }


    /**
     * 剑指 Offer II 089. 房屋偷盗
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int len = nums.length;
        int a = 0;
        int b = nums[0];
        for (int i = 1; i < len; i++) {
            int val = Math.max(a+nums[i], b);
            a = b;
            b = val;
        }
        return b;
    }

    /**
     * 剑指 Offer II 090. 环形房屋偷盗
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        int len = nums.length;
        int a = 0;
        int b = nums[0];
        for (int i = 1; i < len-1; i++) {
            int val = Math.max(a+nums[i], b);
            a = b;
            b = val;
        }
        int ans = b;
        if (len>1){
            a = 0;
            b = nums[1];
            for (int i = 2; i < len; i++) {
                int val = Math.max(a+nums[i], b);
                a = b;
                b = val;
            }

        }
        return Math.max(ans, b);
    }
}
