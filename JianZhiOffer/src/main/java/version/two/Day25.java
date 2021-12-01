package version.two;

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
 * @TIME 2021/12/1 16:47
 * @DESCRIPTION 
 **/
public class Day25 {

    public static void main(String[] args) {
        Day25 d=new Day25();
        d.relativeSortArray(new int[]{2,21,43,38,0,42,33,7,24,13,12,27,12,24,5,23,29,48,30,31}, new int[]{2,42,38,0,43,21});
    }
    /**
     * 剑指 Offer II 074. 合并区间
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int i = 0;
        int start = 0;
        int end = 0;
        List<int[]> list = new ArrayList<>();
        while (i < intervals.length){
            start = intervals[i][0];
            end = intervals[i][1];
            if (i == intervals.length-1){
                list.add(new int[]{start, end});
                break;
            }
            for (int j = i+1; j < intervals.length; j++) {
                int[] interval = intervals[j];
                if (end >= interval[0]){ // 区域有重叠
                    end = Math.max(end, interval[1]);
                    if (j == intervals.length-1){
                        list.add(new int[]{start, end});
                        break;
                    }
                } else {
                    list.add(new int[]{start, end});
                    i=j;
                    break;
                }
            }
        }
        int[][] ans = new int[list.size()][2];
        for (int j = 0; j < list.size(); j++) {
            ans[j] = list.get(j);
        }
        return ans;
    }

    /**
     * 剑指 Offer II 075. 数组相对排序
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr1){
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        int[] ans = new int[arr1.length];
        int index = 0;
        for (int i : arr2){
            for (int j = 0; j < map.get(i); j++) {
                ans[index++] = i;
            }
            map.remove(i);
        }
        List<Integer> tail = new ArrayList<>();
        for (int key : map.keySet()){
            for (int i = 0; i < map.get(key); i++) {
                tail.add(key);
            }
        }
        tail.sort(Comparator.comparingInt(a -> a));
        for (int i : tail){
            ans[index++] = i;
        }
        return ans;
    }
}
