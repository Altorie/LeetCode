package version.one;

import sun.awt.image.ImageWatched;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

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
 *
 * @AUTHOR zuo-zhenjun
 * @TIME 2022/1/1 14:16
 * @DESCRIPTION
 **/
public class Day17 {

    public static void main(String[] args) {
        Day17 d = new Day17();
        d.getLeastNumbers(new int[]{3, 2, 1}, 2);
    }

    /**
     * 剑指 Offer 40. 最小的k个数
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        quickSort(arr, 0, arr.length-1, k);
        return Arrays.copyOfRange(arr, 0, k);
    }
    private void quickSort(int[] arr, int start, int end, int k){
        if (start >= end)return;
        int l = start, r = end;
        int base = arr[l];
        while (l < r){
            while(l < r && arr[r] >= base)r--;
            while (l < r && arr[l] <=base)l++;
            if (l!=r){
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
        }
        arr[start] = arr[l];
        arr[l] = base;
        if (l > k-1)quickSort(arr, start, l-1, k);
        else quickSort(arr, l+1, end, k);
    }


    /**
     * 2022. 将一维数组转变成二维数组
     * @param original
     * @param m
     * @param n
     * @return
     */
    public int[][] construct2DArray(int[] original, int m, int n) {
        int len = original.length;
        if (len != m*n)return new int[][]{};
        int[][] ans = new int[m][n];
        for (int i = 0; i < len; i++) {
            int x = i / n;
            int y = i % n;
            ans[x][y] = original[i];
        }
        return ans;
    }
}
