package version.one;

public class Solution {
    public static void main(String[] args) {
        System.out.println(100/33);
    }


    /**
     * 剑指 Offer 43. 1～n 整数中 1 出现的次数
     * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
     *
     * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
     *
     * 题解：
     *      遍历 n 的每一位，求得 1 在该位上出现的次数，最后累加
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        String num = n+"";
        int ans = 0;
        // 从个位开始遍历
        for (int i = 0; i < num.length(); i++) {
            // 当前位数
            long target = (int)Math.pow(10, i);
            // 每 range 个数会出现target个1
            long range = target * 10;
            // 有多少个range
            long a = n / range;
            ans += a * target;
            // 余数
            long b = n % range;
            if( b < target){

            } else if(b >= target && b < 2*target) {
                ans += b - target +1;
            } else {
                ans += target;
            }
        }
        return ans;
    }
}
