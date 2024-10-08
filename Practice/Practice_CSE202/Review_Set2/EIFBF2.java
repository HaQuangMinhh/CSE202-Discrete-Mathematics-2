package Review_Set2;

import java.io.*;
import java.util.*;

public class EIFBF2 {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nVertices = sc.nextInt();
        int nEdges = sc.nextInt();

        Vertex[] graph = readGraph(nVertices, nEdges);

        for (int i = 1; i <= nVertices; i++) {
            Vertex v = graph[i];
            if (!v.visited) {
                dfs(v, v);
            }
        }

        for (int i = 1; i <= nVertices; i++) {
            Vertex v = graph[i];
            sb.append(v.id).append(" ").append(v.maleCount).append(" ").append(v.femaleCount).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(Vertex v, Vertex root) {
        v.visited = true;
        if (v.gender.equals("Nam")) {
            root.maleCount++;
        } else {
            root.femaleCount++;
        }

        v.maleCount = root.maleCount;
        v.femaleCount = root.femaleCount;

        for (Vertex w : v.adjacentVertices) {
            if (!w.visited) {
                dfs(w, root);
            }
        }
    }

    static Vertex[] readGraph(int nVertices, int nEdges) {
        Vertex[] vertices = new Vertex[nVertices + 1];

        for (int i = 1; i <= nVertices; i++) {
            vertices[i] = new Vertex(i, sc.next());
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
        public boolean visited;
        public int id;
        public String gender;
        public List<Vertex> adjacentVertices = new ArrayList<>();
        public int maleCount = 0;
        public int femaleCount = 0;

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
        }

        public void addAdjacentVertices(Vertex v) {
            adjacentVertices.add(v);
        }
    }
}
