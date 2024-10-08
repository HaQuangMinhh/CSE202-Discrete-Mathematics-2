package Review_set3;

import java.io.*;
import java.util.*;

public class BFS1 {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nVertices = sc.nextInt();
        int nEdges = sc.nextInt();
        Vertex[] graph = readGraph(nVertices, nEdges);
        bfs(graph[0]);
        System.out.println(sb);
    }

    static void bfs(Vertex v) {
        Queue<Vertex> q = new ArrayDeque<>();
        q.add(v);
        v.visited = true;
        sb.append(v.id + " ");

        while (!q.isEmpty()) {
            Vertex w = q.poll();
            for (Vertex adj : w.adjacentVertices) {
                if (!adj.visited) {
                    q.add(adj);
                    adj.visited = true;
                    sb.append(adj.id + " ");
                }
            }
        }
    }

    static Vertex[] readGraph(int nVertices, int nEdges) {
        Vertex[] vertice = new Vertex[nVertices];
        for (int i = 0; i < nVertices; i++) {
            vertice[i] = new Vertex(i);
        }
        for (int i = 0; i < nEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertice[u].addAdjacentVertices(vertice[v]);
        }
        for (int i = 0; i < nVertices; i++) {
            vertice[i].adjacentVertices.sort((a, b) -> a.id - b.id);
        }
        return vertice;
    }

    static class Vertex {
        public int id;
        public boolean visited;
        public List<Vertex> adjacentVertices = new ArrayList<Vertex>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjacentVertices(Vertex v) {
            adjacentVertices.add(v);
        }
    }

}
