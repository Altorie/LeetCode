package com.sjy.study;

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
 * @TIME 2021/12/18 13:58
 * @DESCRIPTION 
 **/
public class Solution4 {
    List<String> ans;
    /**
     * 332. 重新安排行程
     * 相当于一个变体的深搜
     *
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        // 起点 -> （终点，数量）
        // 利用 TreeSet 排序
        // 数量代表有几张 起点 -> 终点 的机票
        Map<String, TreeMap<String, Integer>> map = new HashMap<>();
        for (List<String> ticket : tickets){
            String start = ticket.get(0);
            String end = ticket.get(1);
            if (!map.containsKey(start)){
                map.put(start, new TreeMap<>());
            }
            if (!map.containsKey(end)){
                map.put(end, new TreeMap<>());
            }
            map.get(start).put(end, map.get(start).getOrDefault(end, 0)+1);
        }
        List<String> list = new ArrayList<>();
        list.add("JFK");
        dfs("JFK", map, tickets.size()+1, list);
        return list;
    }
    private boolean dfs(String start, Map<String, TreeMap<String, Integer>> map, int size, List<String> list){
        if (list.size() == size){ // 找到结果
            return true;
        }
        TreeMap<String, Integer> nbrs = map.get(start);
        for (String nbr : nbrs.keySet()){
            int num = nbrs.get(nbr);
            if (num > 0){
                list.add(nbr);
                nbrs.put(nbr, num-1);
                boolean flag = dfs(nbr, map, size, list);
                if (flag)return true;
                // 回溯
                list.remove(list.size()-1);
                nbrs.put(nbr, num);
            }
        }
        return false;
    }


    /**
     * 51. N 皇后
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {

    }

}
