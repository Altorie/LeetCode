package version.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
 * @TIME 2021/12/31 10:11
 * @DESCRIPTION 
 **/
public class Day16 {
    public static void main(String[] args) {
        Day16 d = new Day16();
        System.out.println(d.compare(32, 2));
        d.minNumber(new int[]{1,2,4,8,16,32,64,128,256,512});
    }
    /**
     * 剑指 Offer 45. 把数组排成最小的数
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums)list.add(num);
        Collections.sort(list, this::compare);
        StringBuilder sb = new StringBuilder();
        for (int i : list)sb.append(i);
        return sb.toString();
    }
    private int compare(int a, int b){
        if (a==b)return 0;
        String str1 = a+"";
        String str2 = b+"";
        return (str1+str2).compareTo(str2+str1);
    }

    /**
     * 剑指 Offer 61. 扑克牌中的顺子
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = n-1;
        int need = 0;
        int min = nums[n-1];
        for (int i = n-2; i >=0 ; i--) {
            if (nums[i] == 0)break;
            if (nums[i]== min)return false;
            need += min - nums[i] -1;
            min = nums[i];
            count--;
        }
        return count >= need;
    }

    /**
     * 507. 完美数
     * @param num
     * @return
     */
    public boolean checkPerfectNumber(int num) {
        if (num==1)return false;
        int sum = 1;
        for (int i = 2; i*i <= num; i++) {
            if (num % i == 0){
                sum += i;
                if (i*i != num)sum += num/i;
            }
        }
        return sum == num;
    }
}
