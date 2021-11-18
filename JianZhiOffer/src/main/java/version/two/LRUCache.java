package version.two;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @TIME 2021/11/16 13:32
 * @DESCRIPTION 
 **/
public class LRUCache {
    private int capacity;
    private int size;
    private LRUNode dumpy = new LRUNode(0,0); // 虚拟头结点
    private Map<Integer, Integer> map = new HashMap<>();
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * @param key
     * @return
     */
    public int get(int key) {
        if (map.getOrDefault(key, 0) == 0){ // 不存在
            return -1;
        }
        LRUNode p = dumpy;
        while (p.next.key!=key){
            p = p.next;
        }
        LRUNode target = p.next;
        p.next = target.next;
        target.next = dumpy.next;
        dumpy.next = target;
        return target.value;
    }

    /**
     * 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
     * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if ( size == 0 || map.getOrDefault(key, 0) == 0){ // 关键字不存在
            LRUNode node = new LRUNode(key, value);
            node.next = dumpy.next;
            dumpy.next = node;
            map.put(key, 1);
            if (size < capacity){
                size++;
            } else { // 把最后一个删除
                LRUNode p = dumpy;
                while (p.next.next!=null){
                    p = p.next;
                }
                map.put(p.next.key, 0);
                p.next = null;
            }
        } else {
            get(key);
            dumpy.next.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(3));
        System.out.println(lRUCache.get(4));
    }
}
class LRUNode{
    int key;
    int value;
    LRUNode next;

    public LRUNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}