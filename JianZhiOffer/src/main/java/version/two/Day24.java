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
 * @TIME 2021/12/1 11:02
 * @DESCRIPTION 
 **/
public class Day24 {

    public static void main(String[] args) {
        Day24 d = new Day24();
        d.minEatingSpeed(new int[]{312884470}, 968709470);
    }

    /**
     * 剑指 Offer II 072. 求平方根
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        double start = 0;
        double end = x;
        while (true){
            double mid = start + (end-start) / 2;
            if (mid*mid < x){
                start = mid;
            } else if (mid*mid - x <= 0.001){
                return (int)mid;
            } else {
                end = mid;
            }
        }
    }

    /**
     * 剑指 Offer II 073. 狒狒吃香蕉
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        // 易知，吃香蕉的速度最小为 1，最大为 piles 中的最大值
        int min = 1;
        int max = 0;
        for (int pile : piles){
            max = Math.max(max, pile);
        }
        while (true){
            int mid = min + (max - min) /2;
            int time = getHour(piles, mid);
            if (time > h) { // 速度太慢
                min = mid +1;
            } else {
                if (mid == 1 || getHour(piles, mid-1) > h){
                    return mid;
                } else {
                    max = mid - 1;
                }
            }
        }
    }
    private int getHour(int[] piles, int k){
        int ans = 0;
        for (int pile : piles){
            ans += (pile-1) / k + 1;
        }
        return ans;
    }
}
