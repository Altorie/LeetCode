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
    private LRUNode head;
    private Map<Integer, LRUNode> map = new HashMap<>();
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * @param key
     * @return
     */
    public int get(int key) {
        if (map.getOrDefault(key, null) == null){ // 不存在
            return -1;
        }
        // 存在
        LRUNode node = map.get(key);
        if (node != head){ // 如果 get 的节点不是头结点，需要将 node 的位置调整到 head
            // 把 node 删除
            node.pre.next = node.next;
            node.next.pre = node.pre;
            // 把 node 插入 tail 和 head 之间
            LRUNode tail = head.pre;
            tail.next = node;
            node.next = head;
            head.pre = node;
            node.pre = tail;
            head = node;
        }
        return head.value;
    }

    /**
     * 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
     * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if ( map.getOrDefault(key, null) == null){ // 关键字不存在
            // 创建节点对象
            LRUNode node = new LRUNode(key, value);
            if (size == 0){
                head = node;
                head.pre = head;
                head.next = head;
            }
            // 将节点插入链表头
            node.pre = head.pre;
            head.pre = node;
            node.pre.next = node;
            node.next = head;
            head = node;
            // map中记录
            map.put(key, node);
            if (size < capacity){
                size++;
            } else { // 把最后一个删除
                // 在 map 中删除
                map.put(head.pre.key, null);
                // 在链表中删除
                head.pre = head.pre.pre;
                head.pre.next = head;
            }
        } else {
            get(key);
            head.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(3);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {2=2, 1=1}
        lRUCache.put(3, 3); // 缓存是 {3=3, 2=2, 1=1}
        lRUCache.put(4, 4); // 缓存是 {4=4, 3=3, 2=2}
        System.out.println(lRUCache.get(4));
        System.out.println(lRUCache.get(3));
        System.out.println(lRUCache.get(2));
        System.out.println(lRUCache.get(1));
        lRUCache.put(5, 5);
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(2));
        System.out.println(lRUCache.get(3));
        System.out.println(lRUCache.get(4));
        System.out.println(lRUCache.get(5));
    }
}
class LRUNode{
    int key;
    int value;
    LRUNode pre;
    LRUNode next;

    public LRUNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}