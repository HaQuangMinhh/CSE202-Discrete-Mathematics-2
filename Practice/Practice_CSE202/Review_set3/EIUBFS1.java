package Review_set3;

import java.io.*;
import java.util.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        Vertex[] graph = readGraph();

        bfs(graph[0]);
        System.out.print(sb);
    }

    static Vertex[] readGraph() {
        int nVertices = sc.nextInt();
        int nEdges = sc.nextInt();

        Vertex[] vertices = new Vertex[nVertices];
        for (int i = 0; i < nVertices; ++i) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < nEdges; ++i) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            vertices[a].addAdjecentVertex(vertices[b]);

        }
        for (int i = 0; i < nVertices; i++) {
            vertices[i].adjecentVertices.sort((v1, v2) -> v1.id - v2.id);
        }
        return vertices;
    }

    static void bfs(Vertex v) {
        Queue<Vertex> q = new ArrayDeque<>();
        q.add(v);
        v.visited = true;
        sb.append(v.id + " ");

        while (!q.isEmpty()) {
            Vertex vertex = q.poll();
            for (Vertex adj : vertex.adjecentVertices) {
                if (!adj.visited) {
                    q.add(adj);
                    adj.visited = true;
                    sb.append(adj.id + " ");
                }
            }
        }
    }

    static class Vertex {
        public int id;
        public boolean visited = false;
        public List<Vertex> adjecentVertices = new ArrayList<Vertex>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjecentVertex(Vertex vertex) {
            adjecentVertices.add(vertex);
        }

    }
}
