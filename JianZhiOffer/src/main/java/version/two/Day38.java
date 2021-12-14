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
 * @TIME 2021/12/14 8:32
 * @DESCRIPTION 
 **/
public class Day38 {

    private int max_length;

    public static void main(String[] args) {
        Day38 d =  new Day38();
        d.findOrder(2, new int[][]{new int[]{1,0}});
    }

    /**
     * 剑指 Offer II 111. 计算除法
     * 本质上是构造一个有向加权图。equations 中是边，values 中是边的权重
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap(); // 模拟图的邻接表
        for (int i = 0; i < equations.size(); i++) {
            List<String> list = equations.get(i);
            if (graph.containsKey(list.get(0))){
                graph.get(list.get(0)).put(list.get(1), values[i]);
            } else {
                Map<String, Double> m = new HashMap<>();
                m.put(list.get(1), values[i]);
                graph.put(list.get(0), m);
            }

            if (graph.containsKey(list.get(1))){
                graph.get(list.get(1)).put(list.get(0), 1 / values[i]);
            } else {
                Map<String, Double> m = new HashMap<>();
                m.put(list.get(0), 1 / values[i]);
                graph.put(list.get(1), m);
            }
        }
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {

            Deque<Pair<String, Double>> queue = new ArrayDeque<>();
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            Set<String> set = new HashSet<>();
            queue.push(new Pair<>(start, 1d));
            set.add(start);
            while (!queue.isEmpty()){
                Pair<String, Double> pop = queue.pop();
                if (pop.getKey().equals(end) && graph.containsKey(pop.getKey())){
                    ans[i] = pop.getValue();
                    break;
                }
                // 遍历邻居
                Map<String, Double> nbrs = graph.get(pop.getKey());
                if (nbrs!=null){
                    for (String nbr : nbrs.keySet()){
                        if (!set.contains(nbr)){ // 未访问过
                            set.add(nbr);
                            queue.addLast(new Pair<>(nbr, pop.getValue() * nbrs.get(nbr)));
                        }
                    }
                }
            }
            if (ans[i] == 0d) ans[i] = -1;
        }
        return ans;
    }


    /**
     * 剑指 Offer II 112. 最长递增路径
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] arr = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        int ans = 0;
        // 计算以每一个元素为起点的最长递增路径
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0){
                    visited[i][j] = true;
                    dfs(matrix, visited, i, j, arr);
                    visited[i][j] = false;
                }
                ans = Math.max(ans, arr[i][j]);
            }

        }
        return ans;
    }
    private int dfs(int[][] matrix, boolean[][] visited, int m, int n, int[][] arr){
        int ans = 0;
        if (m+1<matrix.length && !visited[m+1][n] && matrix[m+1][n] > matrix[m][n]){ // 右
            visited[m+1][n] = true;
            int right = 0;
            // 若右边已计算过
            if (arr[m+1][n]!=0){ // 剪枝
                right = arr[m+1][n];
            } else {
                // 这一步把以 (m+1, n) 为起点的最长递增路径计算出来了
                // 使用arr数组保存
                // 下一次再计算到这个位置时直接从arr中取即可
                right = dfs(matrix, visited, m+1, n, arr);
                arr[m+1][n] = right;
            }
            ans = Math.max(ans, right);
            visited[m+1][n] = false;
        }
        if (n+1 < matrix[0].length && !visited[m][n+1] && matrix[m][n+1] > matrix[m][n]){ // 下
            visited[m][n+1] = true;
            int down = 0;
            // 若下方已计算过
            if (arr[m][n+1]!=0){ // 剪枝
                down = arr[m][n+1];
            } else {
                down = dfs(matrix, visited, m , n+1, arr);
                arr[m][n+1] = down;
            }
            ans = Math.max(ans, down);
            visited[m][n+1] = false;
        }
        if (m-1 >=0 && !visited[m-1][n] && matrix[m-1][n] > matrix[m][n]){ // 上
            visited[m-1][n] = true;
            int up = 0;
            if (arr[m-1][n] != 0){
                up = arr[m-1][n];
            } else {
                up = dfs(matrix, visited, m-1, n, arr);
                arr[m-1][n] = up;
            }
            ans = Math.max(ans, up);
            visited[m-1][n] = false;
        }
        if (n-1 >= 0 && !visited[m][n-1] && matrix[m][n-1] > matrix[m][n]){
            visited[m][n-1] = true;
            int left = 0;
            if (arr[m][n-1] !=0){
                left = arr[m][n-1];
            } else {
                left = dfs(matrix, visited, m, n-1, arr);
                arr[m][n-1] = left;
            }
            ans = Math.max(ans, left);
            visited[m][n-1] = false;
        }
        arr[m][n] = ans+1;
        return ans+1;
    }

    /**
     * 剑指 Offer II 113. 课程顺序
     *
     * 拓扑排序
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int a = prerequisite[0];
            int b = prerequisite[1];
            // 构建一个有向图：bi -> ai
            if (graph.containsKey(b)) {
                graph.get(b).add(a);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(a);
                graph.put(b, list);
            }
            // 统计每个节点的入度
            inDegree[a] += 1;
        }

        List<Integer> seq = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>();
        // 先把所有入度为 0 的节点加入队列
        for (int i = 0; i< numCourses;i++){
            if (inDegree[i]==0)queue.addLast(i);
        }

        while (!queue.isEmpty()){
            // 取出一个入度为 0 的节点，加入 seq
            Integer pop = queue.pop();
            seq.add(pop);
            if (seq.size() >= numCourses) {
                int[] ans = new int[numCourses];
                for (int i = 0; i < numCourses; i++) {
                    ans[i] = seq.get(i);
                }
                return ans;
            }
            // 遍历 pop 的所有邻居
            List<Integer> nbrs = graph.get(pop);
            if (nbrs!=null){
                for (int nbr : nbrs){ // nbr 的入度 -1
                    inDegree[nbr] -= 1;
                    if (inDegree[nbr] == 0){ // 说明 nbr 的前置课程全部学完
                        queue.addLast(nbr);
                    }
                }
            }
        }
        return new int[]{};
    }
}
