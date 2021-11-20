package version.two;

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
 * @AUTHOR zuo-zhenjun
 * @TIME 2021/11/19 10:16
 * @DESCRIPTION 
 **/
public class Day13 {

    public static void main(String[] args) {

    }

    /**
     * 剑指 Offer II 039. 直方图最大矩形面积
     * 给定非负整数数组 heights，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积
     *
     * 柱状图勾勒出的矩形的高度，一定是某一个柱子的高度。
     * 因此只要计算出一每一个柱子为高度的矩形面积，从中取最大值就是结果
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        // 计算一某个柱子为高的矩形面积，只需获取其宽度
        // 宽度等于该柱子左右两边高度小于它的柱子索引差
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        stack.push(0); // 方便代码编写
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] < heights[stack.getFirst()]) { // 如果当前柱子高度别之前的小
                while (stack.getFirst() != -1 && heights[i] < heights[stack.getFirst()]) {
                    int height = heights[stack.pop()];
                    int width = i - stack.getFirst() - 1;
                    ans = Math.max(ans, height * width);
                }
            }
            stack.push(i);
        }
        // 计算单调栈里剩余柱子
        // 这些柱子的右边没有比它更小的
        while (stack.getFirst() != -1) {
            int height = heights[stack.pop()];
            int width = heights.length - stack.getFirst() - 1;
            ans = Math.max(ans, height*width);
        }
        return ans;
    }

    /**
     * 剑指 Offer II 040. 矩阵中最大的矩形
     * 给定一个由 0 和 1 组成的矩阵 matrix ，找出只包含 1 的最大矩形，并返回其面积。
     *
     * 可以将每一行作为底，向上构造直方图。计算每个直方图的最大矩形面积
     * @param matrix
     * @return
     */
    public int maximalRectangle(String[] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix[0].length();
        int[] heights = new int[n];
        int ans = 0;
        for (String str : matrix) {
            // 构造直方图
            for (int i = 0; i < n; i++) {
                if (str.charAt(i) == '1'){
                    heights[i] +=1;
                } else {
                    heights[i] = 0;
                }
            }
            ans = Math.max(largestRectangleArea(heights), ans);
        }
        return ans;
    }
}
