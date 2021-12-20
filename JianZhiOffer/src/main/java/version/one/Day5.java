package version.one;

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
 * @TIME 2021/12/20 10:03
 * @DESCRIPTION 
 **/
public class Day5 {

    /**
     * 剑指 Offer 04. 二维数组中的查找
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m = matrix.length;
        if (m==0)return false;
        int n = matrix[0].length;
        int x = m-1;
        int y = 0;
        while (x >= 0 && y < n){
            if (matrix[x][y] == target)return true;
            if (matrix[x][y] > target){
                x--;
            } else {
                y++;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int l = 0;
        int r = numbers.length-1;
        while (l <= r){
            int mid = l + (r-l)/2;
            if (numbers[mid] > numbers[r]){
                l = mid+1;
            } else if (numbers[mid] < numbers[r]){
                r = mid; // 因为最小值也满足这个条件，不能让 r = mid-1
            } else {
                r--;
            }
        }
        return numbers[l];
    }

    /**
     * 剑指 Offer 50. 第一个只出现一次的字符
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        Deque<Character> queue = new ArrayDeque<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
            queue.addLast(s.charAt(i));
        }
        char ans = ' ';
        while (!queue.isEmpty()){
            Character pop = queue.pop();
            if (map.get(pop) == 1){
                ans = pop;
                break;
            }
        }
        return ans;
    }


    /**
     * 475. 供暖器
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        // 排序 + 二分
        Arrays.sort(heaters);
        // 找离每一个 house 最近的 heater
        int ans = 0;
        for (int house : houses){
            // 在 heaters 中寻找第一个大于等于 house的
            int l = 0;
            int r = heaters.length-1;
            // 如果没有大于等于的，设 index 的值为 heaters.length
            int index = heaters.length;
            while (l <= r){
                int mid = l + (r-l)/2;
                if (heaters[mid] < house ){
                    l = mid+1;
                } else {
                    if (mid== 0 || heaters[mid-1] < house){
                        index = mid;
                        break;
                    } else {
                        r = mid-1;
                    }
                }
            }
            if (index == heaters.length){ // 如果所有的暖气都小于 house，那最后一个暖气离 house 最近
                ans = Math.max(ans, house - heaters[heaters.length-1]);
            } else {
                // 离 house 最近的暖气要么是第一个大于等于它的，要么是这个大于等于它的前一个
                int temp = heaters[index] - house;
                if (index - 1 >= 0){
                    temp = Math.min(temp, house - heaters[index-1]);
                }
                ans = Math.max(ans, temp);
            }
        }
        return ans;
    }
}
