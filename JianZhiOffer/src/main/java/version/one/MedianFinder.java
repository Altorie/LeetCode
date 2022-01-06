package version.one;

import java.util.Comparator;
import java.util.PriorityQueue;

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
 * @TIME 2022/1/6 20:46
 * @DESCRIPTION
 *      剑指 Offer 41. 数据流中的中位数
 *      利用一个大根堆和一个小根堆实现
 **/
public class MedianFinder {

    // 大顶堆存较小的部份
    PriorityQueue<Integer> small = new PriorityQueue<>((a, b)-> b - a);
    // 小顶堆存较大的部份
    PriorityQueue<Integer> big = new PriorityQueue<>();
    // 数据量
    int size = 0;
    public MedianFinder() {

    }

    public void addNum(int num) {
        size++;
        if (small.isEmpty())small.add(num);
        else {
            Integer top = small.peek();// small中最大的
            if (num <= top)small.add(num);
            else big.add(num);
        }
    }

    public double findMedian() {
        int small_size = (size+1) / 2;
        while (small.size() > small_size){
            big.add(small.poll());
        }
        while (small.size() < small_size){
            small.add(big.poll());
        }
        if (size % 2 != 0)return small.peek();
        return (small.peek()+big.peek())/2d;
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        mf.findMedian();
    }
}
