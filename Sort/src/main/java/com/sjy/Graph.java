package com.sjy;


import java.util.LinkedList;

public class Graph {
    int v; // 顶点的个数
    LinkedList<Integer> adj[]; // 邻接表

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) { // s先于t，边s->t
        adj[s].add(t);
    }


}
