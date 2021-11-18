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
 * @TIME 2021/11/16 11:44
 * @DESCRIPTION
 *      剑指 Offer II 030. 插入、删除和随机访问都是 O(1) 的容器
 **/
public class RandomizedSet {
    // 保存值在数组中的索引（不存在用 -1 表示）
    private Map<Integer, Integer> map = new HashMap<>();
    // 保存要插入的元素
    List<Integer> list = new ArrayList<>();

    /** Initialize your data structure here. */
    public RandomizedSet() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.getOrDefault(val,-1) != -1)return false;
        list.add(val);
        map.put(val, list.size()-1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        // 这个值不存在
        if (map.getOrDefault(val, -1) == -1)return false;
        // 删除元素的索引
        int index = map.get(val);
        // 和末尾元素交换
        list.set(index, list.get(list.size()-1));
        // 删除
        list.remove(list.size()-1);
        // 更新末尾元素的索引
        map.put(list.get(list.size()-1), index);
        map.put(val, -1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
