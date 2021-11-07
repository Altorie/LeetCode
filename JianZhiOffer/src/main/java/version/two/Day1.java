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
 * @TIME 2021/11/7 12:55
 * @DESCRIPTION 
 **/
public class Day1 {
    public static void main(String[] args) {
        Day1 d = new Day1();
        d.divide(-2147483648, -1);
        System.out.println(Integer.MIN_VALUE >> 1);
    }

    /**
     * 剑指 Offer II 001. 整数除法
     * 给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
     * @param a
     * @param b
     * @return
     */
    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b==-1){
            return Integer.MAX_VALUE;
        }
        boolean isNegative = (a > 0) ^ (b > 0);
        // 为什么要将 a b 都变为负数？因为32位表示的最大整数的负数不会溢出，但是最小整数的负数（Integer.MAX_VALUE + 1）会溢出
        a = a < 0? a : -a;
        b = b < 0? b : -b;
        int ans = 0;
        while (a <= b){
            int value = 1;
            int temp = b;
            // 指数增长
            while (temp > (Integer.MIN_VALUE >> 1) && a <= (temp << 1)){
                temp = temp << 1;
                value = value << 1;
            }
            a -= temp;
            ans += value;
        }
        return isNegative? -ans : ans;
    }


    /**
     * 剑指 Offer II 002. 二进制加法
     * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
     *
     * 输入为 非空 字符串且只包含数字 1 和 0。
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        if (len1 > len2){
           return addBinary(b, a);
        }
        StringBuffer sb = new StringBuffer();
        boolean overflow = false;
        for (int i = 1; i <= len1; i++) {
            int value = a.charAt(len1-i)-48 + b.charAt(len2-i)-48;
            if (overflow) value+=1;
            overflow = value >=2;
            value = value%2;
            sb.append(value);
        }
        for (int i = len2 - len1 - 1; i >=0 ; i--) {
            int value = b.charAt(len2-i)-48;
            if (overflow) value+=1;
            overflow = value >=2;
            value = value%2;
            sb.append(value);
        }
        if (overflow) sb.append(1);
        return sb.reverse().toString();
    }

    /**
     * 剑指 Offer II 003. 前 n 个数字二进制中 1 的个数
     * 给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        /**
         *
         */
//        if (n==0) return new int[]{0};
//        int[] ans = new int[n+1];
//        for (int i = 1; i <= n; i++) {
//            // 找到小于 i 的最大的 2的幂
//            int v = i-1;
//            v |= v>>>1;
//            v |= v>>>2;
//            v |= v>>>4;
//            v |= v>>>8;
//            v |= v>>>16;
//            v = (v+1);
//            if (v == i){
//                ans[i] = 1;
//            }else {
//                v=v>>1;
//                ans[i] = ans[v] + ans[i%v];
//            }
//        }
//        return ans;
        /**
         * 观察最低位。
         * 假设 a 的二进制最低位是 0 ，那么将 a 右移 1 位之后，a 与 a>>1 的二进制比特中 1的数目相等
         * 假设 a 的二进制最低位是 1 ，那么将 a 右移 1 位之后，a 比 a>>1 的二进制比特中 1的数目少 1
         */
            int[] ans = new int[n+1];
            for (int i = 1; i <= n ; i++) {
                ans[i] = ans[i>>1] + (i&1);
            }
            return ans;
    }
}
