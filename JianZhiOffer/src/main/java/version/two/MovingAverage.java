package version.two;

import java.util.ArrayDeque;
import java.util.Deque;

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
 * @TIME 2021/11/20 13:37
 * @DESCRIPTION
 *      剑指 Offer II 041. 滑动窗口的平均值
 *      给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。
 **/
public class MovingAverage {

    private int capacity;
    private Deque<Integer> queue = new ArrayDeque<>();
    private double average;

    /**
     * 用窗口大小 size 初始化对象
     * @param size
     */
    public MovingAverage(int size) {
        this.capacity = size;
    }

    /**
     * 往滑动窗口增加一个整数，请计算并返回数据流中最后 size 个值的移动平均值
     * @param val
     * @return
     */
    public double next(int val) {
        int size = queue.size();
        if (size < capacity){
            queue.addLast(val);
            average = (average*size + val) / (size+1);
        } else {
            average = (average*size + val - queue.removeFirst()) / size;
            queue.addLast(val);
        }
        return average;
    }
}
