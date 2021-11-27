package version.two;

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
 * @TIME 2021/11/27 9:53
 * @DESCRIPTION 
 **/
public class KthLargest {
    private PriorityQueue<Integer> queue; // 小顶堆实现的优先级队列
    private int capacity;
    public KthLargest(int k, int[] nums) {
        capacity = k;
        queue = new PriorityQueue<>(k);
        for (int num : nums) {
            if (queue.size() < k){
                queue.offer(num);
                continue;
            }
            if (queue.peek() < num){
                queue.poll();
                queue.offer(num);
            }
        }
    }

    public int add(int val) {
        if (queue.size() < capacity){
            queue.offer(val);
        } else if (queue.peek() < val){
            queue.poll();
            queue.offer(val);
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        KthLargest obj = new KthLargest(3, new int[]{5,-1});
        obj.add(2);
        obj.add(1);
        obj.add(-1);
        obj.add(3);
        obj.add(4);
    }
}
