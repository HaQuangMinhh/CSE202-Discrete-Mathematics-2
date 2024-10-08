package Review_Set2;

import java.io.*;
import java.util.*;

public class EIUDFS2 {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nVertice = sc.nextInt();
        int nEdges = sc.nextInt();

        Vertex[] graph = readGraph(nVertice, nEdges);
        dfs(graph[0]);
        System.out.println(sb);
    }

    static void dfs(Vertex v) {

        v.visited = true;
        sb.append(v.id).append(" ");

        Collections.sort(v.adjacentVertices, (a, b) -> a.id - b.id);
        for (Vertex w : v.adjacentVertices) {
            if (!w.visited) {
                dfs(w);
            }
        }
    }

    static Vertex[] readGraph(int nVertice, int nEdges) {

        Vertex[] vertices = new Vertex[nVertice];
        for (int i = 0; i < nVertice; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < nEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjacentVertices(vertices[v]);
            vertices[v].addAdjacentVertices(vertices[u]);
        }
        return vertices;
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
