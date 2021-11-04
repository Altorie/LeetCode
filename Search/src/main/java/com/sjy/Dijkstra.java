package com.sjy;

import java.util.LinkedList;
import java.util.Queue;

public class Dijkstra {
    private LinkedList<Edge>[] adj;// 有向有权图的邻接表表示
    private int v;// 网络中节点的数量

    public Dijkstra(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }
    public void addEdge(int s, int t, int w) { // 添加一条边
         this.adj[s].add(new Edge(s, t, w));
    }

    private class Edge{
        int sid; //边的起始节点id
        int tid; //边的终止节点id
        int weight; //边的权重

        public Edge(int sid, int tid, int weight) {
            this.sid = sid;
            this.tid = tid;
            this.weight = weight;
        }
    }

    /**
     * 计算节点 s 到其它所有节点的最短距离
     * @param s
     */
    private void dijkstra(int s){
        int[] distances = new int[v];
        for (int i = 0; i < v; i++) { // 初始化最短距离为 MAX
            distances[i] = Integer.MAX_VALUE;
        }
        distances[s] = 0; // 到自己的距离为 0
        int[] preNode = new int[v]; // 最短路径上的前驱节点
        preNode[s] = -1; // 其实节点 s 没有前驱节点

        Queue<Integer> queue =new LinkedList<>();
        queue.offer(s);
        // 广度优先遍历其它节点
        while (!queue.isEmpty()){
            int node = queue.poll();
            for (Edge e : adj[node]) { // 遍历从 node 出发的所有边
                int nbr = e.tid; // node -> nbr
                // 更新到节点 nbr 的最短距离
                if (distances[node]+e.weight < distances[nbr]){
                    distances[nbr] = distances[node]+e.weight;
                    // nbr 的前驱节点是 node
                    preNode[nbr] = node;
                    // 因为 nbr的距离改变了，所以它的邻居的距离也要重新计算。将 nbr 加入队列
                    queue.offer(nbr);
                }
            }
        }
    }


}
