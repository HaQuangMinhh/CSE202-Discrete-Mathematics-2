package Review_set3;

import java.io.*;
import java.util.*;

public class BFS2 {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nVertices = sc.nextInt();
        int nEdges = sc.nextInt();
        Vertex[] grap = readGraph(nVertices, nEdges);
        bfs(grap[0]);
        System.out.println(sb);
    }

    static void bfs(Vertex v) {
        Queue<Vertex> q = new ArrayDeque<>();
        q.add(v);
        v.visted = true;
        sb.append(v.id + " ");

        while (!q.isEmpty()) {
            Vertex w = q.poll();
            for (Vertex adj : w.adjacentVertices) {
                if (!adj.visted) {
                    q.add(adj);
                    adj.visted = true;
                    sb.append(adj.id + " ");
                }
            }

        }

    }

    static Vertex[] readGraph(int nvertices, int nEdges) {
        Vertex[] vertiecs = new Vertex[nvertices];
        for (int i = 0; i < nvertices; i++) {
            vertiecs[i] = new Vertex(i);
        }
        for (int i = 0; i < nEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertiecs[u].addAdjacentVertices(vertiecs[v]);
            vertiecs[v].addAdjacentVertices(vertiecs[u]);
        }
        for (int i = 0; i < nvertices; i++) {
            vertiecs[i].adjacentVertices.sort((a, b) -> a.id - b.id);
        }
        return vertiecs;
    }

    static class Vertex {
        public int id;
        public boolean visted;
        public List<Vertex> adjacentVertices = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjacentVertices(Vertex v) {
            adjacentVertices.add(v);
        }

    }
}