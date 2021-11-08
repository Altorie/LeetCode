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
 * @TIME 2021/11/8 8:52
 * @DESCRIPTION 
 **/
public class Day2 {
    public static void main(String[] args) {
        System.out.println(450*200/1500);
    }

    /**
     * 剑指 Offer II 004. 只出现一次的数字
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        /**
         * 依次确定每一个二进制位
         * 由于其他元素都出现了三次，因此数组和的第 i 个二进制位对 3 取余就是 答案元素的第 i 个二进制位
         *
         * 推广：一个整数数组，只有一个数字出现 m 次，其他数字都出现 n 次
         *      将这些数的第 i 位累加起来，如果和是 n 的整数倍，那么只出现一次数字的第 i 位一定是 0；否则是 1
         */
//        int ans = 0;
//        for (int i = 0; i < 32; i++) {
//            int total = 0;
//            for (int num : nums){ // 求所有数第 i 位的和
//                total += (num >> i) & 1;
//            }
//            if (total % 3 != 0){ // 如果第 i 位是 1
//                ans |= 1 << i;
//            }
//        }
//        return ans;

        /**
         * 牛逼解法
         */
        int a = 0;
        int b = 0;
        for (int num : nums) {
            b = b^num & ~a;
            a = a^num & ~b;
        }
        return b;
    }

    /**
     * 剑指 Offer II 005. 单词长度的最大乘积
     * 给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。
     * 假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        int len = words.length;
        // 设计一个 Hash 函数，计算字符串的 hash 值
        // 一个 26 位的二进制串，a ~ z 对应 1 ~ 26 位。如果存在某个字符，则对应 bit 设置为 1
        int[] wordHash = new int[len];
        for (int i = 0; i < len; i++) {
            int a = 0;
            for (char c : words[i].toCharArray()){
                a |= 1 << (c-'a');
            }
            wordHash[i] = a;
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if ((wordHash[i]&wordHash[j])==0){ // 没有重复字符
                    ans = Math.max(ans, words[i].length()*words[j].length());
                }
            }
        }
        return ans;
    }

    /**
     * 剑指 Offer II 006. 排序数组中两个数字之和
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length-1;
        while (true){
            if (numbers[left] + numbers[right] > target){
                right--;
            } else if (numbers[left] + numbers[right] < target){
                left++;
            } else {
                return new int[]{left, right};
            }
        }
    }
}
