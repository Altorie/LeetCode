package version.two;

import javafx.util.Pair;

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
 * @TIME 2021/12/12 22:03
 * @DESCRIPTION 
 **/
public class Day37 {

    public static void main(String[] args) {
        Day37 d = new Day37();
        d.openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202");
    }

    /**
     * 剑指 Offer II 108. 单词演变
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        /**
         * 1 广度优先搜索
         */
        int n = wordList.size();
        int target = -1;
        for (int i = 0; i < n; i++) {
            if (wordList.get(i).equals(endWord)){
                target = i;
            }
        }
        if (target==-1)return 0;
        wordList.add(beginWord);
        int[][] graph = new int[n+1][n+1];
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n+1; j++) {
                if (isConnect(wordList.get(i), wordList.get(j))){
                    graph[i][j] = 1;
                    graph[j][i] = 1;
                }
            }
        }
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[n] = 0; // 到自己的距离为 0
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(n);
        while (!queue.isEmpty()){
            Integer pop = queue.pop();
            if (pop == target)break;
            for (int i = 0; i < n+1; i++) {
                if (graph[pop][i]==1 &&distance[pop]+1 < distance[i]){
                    distance[i] = distance[pop] + 1;
                    queue.addLast(i);
                }
            }
        }
        if (distance[target] == Integer.MAX_VALUE)return 0;
        return distance[target]+1;

        /**
         * 2 双广度优先搜索
         */
    }
    private boolean isConnect(String str1, String str2){ // 判断两个字符串是否只有一个字符不一样
        int diff = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)){
                diff++;
            }
        }
        return diff <= 1 ;
    }

    /**
     * 剑指 Offer II 109. 开密码锁
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)){
            return 0;
        }
        int capacity = (int) (deadends.length / 0.75);
        Set<String> set = new HashSet<>(capacity);
        set.addAll(Arrays.asList(deadends));
        if (set.contains("0000"))return -1;
        // 双向广度优先搜索
        Set<String> startSet = new HashSet<>(); // 从 0000 开始经过的节点
        Deque<Pair<String, Integer>> startQueue = new ArrayDeque<>();
        startQueue.addLast(new Pair<>("0000", 0));
        startSet.add("0000");

        Set<String> targetSet = new HashSet<>(); // 从 target 开始经过的节点
        Deque<Pair<String, Integer>> targetQueue = new ArrayDeque<>();
        targetQueue.addLast(new Pair<>(target, 0));
        targetSet.add(target);

        Map<Character, List<Character>> map = new HashMap<Character, List<Character>>(){{
            put('0', Arrays.asList('9', '1'));
            put('1', Arrays.asList('0', '2'));
            put('2', Arrays.asList('1', '3'));
            put('3', Arrays.asList('2', '4'));
            put('4', Arrays.asList('3', '5'));
            put('5', Arrays.asList('4', '6'));
            put('6', Arrays.asList('5', '7'));
            put('7', Arrays.asList('6', '8'));
            put('8', Arrays.asList('7', '9'));
            put('9', Arrays.asList('8', '0'));
        }};

        while (!startQueue.isEmpty() && !targetQueue.isEmpty()){
            // 从 0000 开始
            for (int i = 0; i < startQueue.size(); i++) {
                Pair<String, Integer> pop = startQueue.pop();
                // 计算邻居
                List<String> nbrs = getNbrs(pop.getKey(), map);
                for (String nbr : nbrs){ // 遍历每一个邻居
                    if (targetSet.contains(nbr)){// 从 target 开始的广度搜索已经访问过
                        return pop.getValue()*2 + 1;
                    }
                    if (!startSet.contains(nbr) && !set.contains(nbr)){ // 即没访问过，也不在deadEnds中
                        startSet.add(nbr);
                        startQueue.addLast(new Pair<>(nbr, pop.getValue()+1));
                    }
                }
            }

            // 从 target 开始
            for (int i = 0; i < targetQueue.size(); i++) { // 遍历一层
                Pair<String, Integer> pop = targetQueue.pop();
                // 计算邻居
                List<String> nbrs = getNbrs(pop.getKey(), map);
                for (String nbr : nbrs){
                    if (startSet.contains(nbr)){
                        return (pop.getValue()+1)*2 ;
                    }
                    if (!targetSet.contains(nbr) && !set.contains(nbr)){
                        targetSet.add(nbr);
                        targetQueue.addLast(new Pair<>(nbr, pop.getValue()+1));
                    }
                }

            }
        }

//        while (!startQueue.isEmpty()){
//            for (int i = 0; i < startQueue.size(); i++) {
//                Pair<String, Integer> pop = startQueue.pop();
//                // 计算邻居
//                List<String> nbrs = getNbrs(pop.getKey(), map);
//                for (String nbr : nbrs){ // 遍历每一个邻居
//                    if (nbr.equals(target)){// 从 target 开始的广度搜索已经访问过
//                        return pop.getValue() + 1;
//                    }
//                    if (!startSet.contains(nbr) && !set.contains(nbr)){ // 即没访问过，也不在deadEnds中
//                        startSet.add(nbr);
//                        startQueue.addLast(new Pair<>(nbr, pop.getValue()+1));
//                    }
//                }
//            }
//        }
        return -1;
    }

    private List<String> getNbrs(String str, Map<Character, List<Character>> map){
        List<String> strs = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < 4; i++) { // 遍历密码的每一位
            char c = chars[i];
            List<Character> list = map.get(c); // 该位可以变成什么
            for (char change : list){
                chars[i] = change;
                strs.add(new String(chars));
            }
            chars[i] = c;
        }
        return strs;
    }

    /**
     * 剑指 Offer II 110. 所有路径
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // 深度优先搜索
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        path.add(0);
        visited[0] = true;
        dfs(0, graph, visited, path, ans);
        return ans;
    }
    private void dfs(int node, int[][] graph, boolean[] visited, List<Integer> path, List<List<Integer>> ans){
        if (node == graph.length-1){
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i : graph[node]) {
            if (!visited[i]){ // 没访问过的邻居
                path.add(i);
                visited[i] = true;
                dfs(i, graph, visited, path, ans);
                visited[i] = false;
                path.remove(path.size()-1);
            }
        }
    }
}
