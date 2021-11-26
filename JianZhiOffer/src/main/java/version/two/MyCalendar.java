package version.two;

import java.util.Map;
import java.util.TreeMap;

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
 * @TIME 2021/11/26 16:50
 * @DESCRIPTION 
 **/
public class MyCalendar {

    private TreeMap<Integer, Integer> map = new TreeMap<>();

    public MyCalendar() {

    }

    public boolean book(int start, int end) {
        Integer floorKey = map.floorKey(start);
        Integer ceilingKey = map.ceilingKey(start);
        if (floorKey!=null && map.get(floorKey) > start){ // 有前区间，且前区间结束时间比 start 大
            return false;
        }
        if (ceilingKey!=null && ceilingKey < end){
            return false;
        }
        map.put(start, end);
        return true;
    }
}
