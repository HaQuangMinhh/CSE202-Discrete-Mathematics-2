package Review_Set2;

import java.io.*;
import java.util.*;

class Main {
    static Scanner rd;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        rd = new Scanner(System.in);
        Vertex[] graph = readGraph();

        countConnectedComponents(graph);
        System.out.print(sb);
    }

    static Vertex[] readGraph() {
        int nVertices = rd.nextInt();
        int nEdges = rd.nextInt();

        Vertex[] vertices = new Vertex[nVertices];
        for (int i = 0; i < nVertices; ++i) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < nEdges; ++i) {
            int a = rd.nextInt();
            int b = rd.nextInt();

            vertices[a].addAdjecentVertex(vertices[b]);
            vertices[b].addAdjecentVertex(vertices[a]);
        }

        for (int i = 0; i < nVertices; i++) {
            vertices[i].adjecentVertices.sort((v1, v2) -> (v1.id - v2.id));
        }

        return vertices;
    }

    static void countConnectedComponents(Vertex[] graph) {
        for (Vertex vertex : graph) {
            if (!vertex.visited) {
                sb.append(vertex.id).append(" ");
                sb.append(dfs(vertex)).append("\n");
            }
        }
    }

    static int dfs(Vertex vertex) {
        vertex.visited = true;
        int count = 1;
        for (Vertex adj : vertex.adjecentVertices) {
            if (!adj.visited) {
                adj.visited = true;
                count += dfs(adj);
            }
        }
        return count;
    }

    static class Vertex {
        public int id;
        public boolean visited;
        public List<Vertex> adjecentVertices = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjecentVertex(Vertex vertex) {
            adjecentVertices.add(vertex);
        }
    }
}