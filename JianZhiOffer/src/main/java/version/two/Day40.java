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
 * @TIME 2021/12/16 8:59
 * @DESCRIPTION 
 **/
public class Day40 {

    /**
     * 剑指 Offer II 117. 相似的字符串
     *
     * @param strs
     * @return
     */
    public int numSimilarGroups(String[] strs) {
        // 构建一个有向图
        Map<String, List<String>> graph = new HashMap<>();
        // 存放 str 在 list 中的索引
        Map<String, Integer> map = new HashMap<>();
        for (String str : strs) {
            if (!graph.containsKey(str)) {
                graph.put(str, new ArrayList<>());
            }
        }
        // 存放去重后的 str
        List<String> list = new ArrayList<>(graph.keySet());
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), i);
        }
        for (int i = 0; i < list.size(); i++) {
            for (String s : list) {
                if (isSimilar(list.get(i), s)) {
                    graph.get(list.get(i)).add(s);
                }
            }
        }

        boolean[] visited = new boolean[list.size()];
        // 计算联通分量
        int ans = 0;
        Deque<String> queue = new ArrayDeque<>();
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]){ // 没遍历过
                ans++;
                queue.addLast(list.get(i));
                visited[i] = true;
                while (!queue.isEmpty()){ // 广度优先遍历
                    String node = queue.pop();
                    for (String nbr : graph.get(node)){
                        if (!visited[map.get(nbr)]){
                            visited[map.get(nbr)] = true;
                            queue.addLast(nbr);
                        }
                    }
                }
            }
        }
        return ans;
    }
    private boolean isSimilar(String str1, String str2){ // 判断两个字符串是否相似
        int diff = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i))diff++;
        }
        return diff <=2; //交换两个字母的位置，即最多有两个位置不同
    }


    /**
     * 剑指 Offer II 118. 多余的边
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        /**
         * 1 暴力
         * 先构建图，然后倒序遍历每一条边。
         *      从图中删去，广度搜索，如果连通，是答案
         */
//        int n = edges.length; // 节点是从 1 到 n
//        int[][] graph = new int[n+1][n+1];
//        for (int[] edge : edges){
//            graph[edge[0]][edge[1]] = 1;
//            graph[edge[1]][edge[0]] = 1;
//        }
//        Deque<Integer> queue = new ArrayDeque<>();
//        for (int i = n-1; i >=0 ; i--) {
//            int[] edge = edges[i];
//            // 删除这条边
//            graph[edge[0]][edge[1]] = 0;
//            graph[edge[1]][edge[0]] = 0;
//            boolean[] visited = new boolean[n+1];
//            queue.addLast(edge[0]);
//            visited[edge[0]] = true;
//            int sum = 0;
//            while (!queue.isEmpty()){
//                Integer node = queue.pop();
//                sum++;
//                for (int j = 1; j <= n ; j++) {
//                    if (graph[node][j]==1 && !visited[j]){
//                        visited[j] = true;
//                        queue.addLast(j);
//                    }
//                }
//            }
//            if (sum == n){
//                return edge;
//            }
//            // 加回这条边
//            graph[edge[0]][edge[1]] = 1;
//            graph[edge[1]][edge[0]] = 1;
//        }
//        return null;

        /**
         * 2 并查集
         * 假设一开始所有的节点都不相连，各自属于一个连通分量
         * 遍历每一条边，连接这条边两个端点所属的连通分量。如果这两个端点属于相同连通分量，说明这条边的插入导致了环
         */
        int n = edges.length;
        int[] parent = new int[n+1]; // 每个节点的父节点
        // 初始状态，每个节点独属于一个连通片，父节点指向自己
        for (int i = 1; i <= n ; i++) {
            parent[i] = i;
        }
        // 这里要正序遍历，才能返回最后出现的边
        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            int root1 = findRoot(edge[0], parent);
            int root2 = findRoot(edge[1], parent);
            // 判断两个端点是否属于一个连通片
            if (root1 == root2){
                return edge;
            }
            // 合并
            parent[root1] = root2;
        }
        return null;
    }
    private int findRoot(int node, int[] parent){ // 寻找 node 所属连通片的根
        while (parent[node]!=node){
            node = parent[node];
        }
        return node;
    }


    /**
     * 剑指 Offer II 119. 最长连续序列
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, new HashSet<>());
        }
        for (int num : nums){
            if (map.containsKey(num-1)){
                map.get(num-1).add(num);
            }
            if (map.containsKey(num+1)){
                map.get(num+1).add(num);
            }
        }
        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        int ans = 0;
        for (int num : nums){
            if (!visited.contains(num)){
                visited.add(num);
                int l = 0;
                queue.addLast(num);
                while (!queue.isEmpty()){
                    Integer node = queue.pop();
                    l++;
                    for (int nbr : map.get(node)){
                        if (!visited.contains(nbr)){
                            visited.add(nbr);
                            queue.addLast(nbr);
                        }
                    }
                }
                ans = Math.max(ans, l);
            }
        }
        return ans;
    }





}
