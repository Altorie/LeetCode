package com.sjy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 拓扑排序
 */
public class TopologicalSort {

    /**
     * Kahn算法实现拓扑排序
     * 如果 t 依赖 s，即 t必须在 s之后，那么网络中有一条 s->t 的边。
     * 此时 t 的入度加 1.
     * 这就代表入度为 0 的节点不依赖其它节点，可以执行。
     * @param graph
     */
    public void topoSortByKahn(Graph graph) {
        // 这里假定构建 Graph 的时候一条边 s -> t表示依赖关系 t 依赖 s
        // 先统计每个节点的入度
        int[] inDegree = new int[graph.v];
        for (int i = 0; i < graph.v; i++) {
            for (int j = 0; j < graph.adj[i].size(); j++) {
                int node = graph.adj[i].get(j); // i -> node
                inDegree[node]+=1;
            }
        }
        // 不断寻找图中入度为 0 的点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.v; i++) {
            if (inDegree[i] == 0){
                queue.offer(i);
            }
        }
        // 遍历每个入度为 0 的节点
        while (!queue.isEmpty()){
            // 节点 n 可以先输出
            int n = queue.poll();
            System.out.print(n + " -> ");
            // 更新所有 n 可达的其它节点的入度
            for (int i : graph.adj[n]){
                inDegree[i] -= 1;
                if (inDegree[i] == 0){ // 如果更新后为 0，说明此时该节点也可以直接执行
                    queue.offer(i);
                }
            }
        }
    }

    /**
     * 利用深度优先遍历的方法拓扑排序
     *   边s->t表示，s依赖于t，t先于s
     * 基本思想：
     *   如果我们要遍历输出节点 s，必须要检测其是否有依赖的节点，即是否有出边。有则必须先输出依赖节点
     */
    public void topoSortByDFS(Graph graph){
        // 这里假定构建 Graph 的时候一条边 s -> t表示依赖关系 s 依赖 t
        // 构建记忆数组
        boolean[] visited = new boolean[graph.v];
        // 深度优先遍历
        for (int i = 0; i < graph.v; i++) {
            if (!visited[i]){
                visited[i] = true;
                dfs(i, graph.adj, visited);
            }
        }

    }

    private void dfs(int node, LinkedList<Integer>[] adj, boolean[] visited){
        for (int i : adj[node]){
            if (!visited[i]){
                visited[i] = true;
                dfs(i, adj, visited);
//                visited[i] = false;
            }
        }
        System.out.print( " -> " + node );
    }
}
