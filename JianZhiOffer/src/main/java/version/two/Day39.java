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
 * @TIME 2021/12/15 8:55
 * @DESCRIPTION 
 **/
public class Day39 {

    public static void main(String[] args) {
        Day39 d = new Day39();
        d.alienOrder(new String[]{"wrt","wrf","er","ett","rftt"});
    }

    /**
     * 剑指 Offer II 114. 外星文字典
     * @param words
     * @return
     */
    public String alienOrder(String[] words) {
        int n = words.length;
        Map<Character, Set<Character>> map = new HashMap<>(); // 模拟图的邻接表
        Map<Character,Integer> inDegree = new HashMap<>();
        for (String word : words){
            for (int i = 0; i < word.length(); i++) {
                inDegree.put(word.charAt(i), 0);
            }
        }
        for (int i = 0; i < n-1; i++) {
            String s1 = words[i];
            String s2 = words[i+1];
            // 排除 [abc，ab]这样的情况
            if (s1.length() > s2.length() && s1.startsWith(s2)) return "";
            int j =0;
            while (j< s1.length() && j < s2.length()){
                if (s1.charAt(j) == s2.charAt(j)){
                    j++;
                }else {
                    char from = s1.charAt(j);
                    char to = s2.charAt(j);
                    if (map.containsKey(from) && map.get(from).contains(to))break;
                    if (!map.containsKey(from)){
                        Set<Character> set = new HashSet<>();
                        set.add(to);
                        map.put(from, set);
                    } else {
                        map.get(from).add(to);
                    }
                    inDegree.put(to, inDegree.get(to)+1);
                    break;
                }
            }
        }
        Deque<Character> queue = new ArrayDeque<>();
        // 加入所有入度为 0 的节点
        for (Character c : inDegree.keySet()){
            if (inDegree.get(c)==0){ // 加入
                queue.addLast(c);
            }
        }
        StringBuffer sb = new StringBuffer();
        while (!queue.isEmpty()){
            Character pop = queue.pop();
            sb.append(pop);
            // 修改 pop 邻居的入度
            Set<Character> nbrs = map.get(pop);
            if (nbrs!=null){
                for (char nbr : nbrs){
                    int in = inDegree.get(nbr);
                    in -= 1;
                    inDegree.put(nbr, in);
                    if (in == 0){
                        queue.addLast(nbr);
                    }
                }

            }
        }
        if (sb.length() == inDegree.size()) return sb.toString();
        return "";
    }

    /**
     * 剑指 Offer II 115. 重建序列
     * @param org
     * @param seqs
     * @return
     */
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int n = org.length;
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[n+1];
        Arrays.fill(inDegree, -1); // 避免在org中的数字不在seqs出现的情况

        for (List<Integer> list : seqs) {
            // 对元素本身合法性进行检验
            for (int i : list){
                if (i < 1 || i > n)return false;
                if (inDegree[i]==-1)inDegree[i] = 0;
                if (!graph.containsKey(i))graph.put(i, new HashSet<>());
            }
            for (int i = 0; i < list.size()-1; i++) {
                int from = list.get(i);
                int to = list.get(i+1);
                // 初始化图
                if (!graph.get(from).contains(to)) {
                    graph.get(from).add(to);
                    inDegree[to] ++;
                }
            }
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < n+1; i++) {
            if (inDegree[i] == 0)queue.addLast(i);
        }
        for (int i : org){
            if (queue.size()!=1)return false;
            Integer pop = queue.pop();
            if (pop!=i)return false;
            Set<Integer> nbrs = graph.get(pop);
            if (nbrs!=null){
                for (int nbr : nbrs){
                    inDegree[nbr] -= 1;
                    if (inDegree[nbr] == 0){
                        queue.addLast(nbr);
                    }
                }
            }
        }
        return true;
    }

    /**
     * 剑指 Offer II 116. 省份数量
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int ans = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i<n; i++){
            if (!visited[i]){
                ans++;
                queue.addLast(i);
                visited[i] = true;
                while (!queue.isEmpty()){
                    Integer pop = queue.pop();
                    for (int j = 0; j < n; j++) {
                        if (isConnected[pop][j]==1 && !visited[j]){
                            queue.addLast(j);
                            visited[j] = true;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
