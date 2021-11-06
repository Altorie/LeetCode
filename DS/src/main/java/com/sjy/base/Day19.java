package com.sjy.base;

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
 * @TIME 2021/11/6 11:49
 * @DESCRIPTION 
 **/
public class Day19 {
    /**
     * 997. 找到小镇的法官
     * 在一个小镇里，按从 1 到 n 为 n 个人进行编号。传言称，这些人中有一个是小镇上的秘密法官。
     *
     * 如果小镇的法官真的存在，那么：
     *      小镇的法官不相信任何人。
     *      每个人（除了小镇法官外）都信任小镇的法官。
     *      只有一个人同时满足条件 1 和条件 2 。
     *
     * 法官的出度为 0， 其他所有人的出度大于 0
     * 法官的入度为 n-1
     * @param n
     * @param trust
     * @return
     */
    public int findJudge(int n, int[][] trust) {
        int[] outDegrees = new int[n+1];
        int[] inDegrees = new int[n+1];
        for(int[] edge : trust){
            outDegrees[edge[0]] +=1;
            inDegrees[edge[1]] += 1;
        }
        int judge = -1;
        for (int i = 1; i <= n; i++) {
            if (outDegrees[i]==0 && judge==-1){
                judge = i;
            } else if (outDegrees[i]==0 && judge!=-1){
                break;
            }
        }
        if (judge!=-1){
            return inDegrees[judge]==n-1?judge:-1;
        }
        return judge;
    }

    /**
     * 1557. 可以到达所有点的最少点数
     * 给你一个 有向无环图， n个节点编号为 0到 n-1，以及一个边数组 edges，其中 edges[i] = [fromi, toi]表示一条从点 fromi 到点 toi 的有向边。
     * 找到最小的点集使得从这些点出发能到达图中所有点。题目保证解存在且唯一。
     *
     * 本质上是找入度为 0 的点
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (List<Integer> edge:edges){ // 找出所有入度不为0的点
            set.add(edge.get(1));
        }
        for (int i = 0; i < n; i++) {
            if (!set.contains(i)){
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 841. 钥匙和房间
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean ans = true;
        boolean[] flag = new boolean[rooms.size()];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(0);
        flag[0] = true;
        while (!queue.isEmpty()){
            int room = queue.pollFirst();
            for(int nextRoom : rooms.get(room)){
                if (!flag[nextRoom]){
                    queue.addLast(nextRoom);
                    flag[nextRoom] = true;
                }
            }
        }
        for (boolean b : flag){
            ans &= b;
        }
        return ans;
    }
}

