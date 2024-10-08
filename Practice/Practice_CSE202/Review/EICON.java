package Review;

import java.io.*;
import java.util.*;

public class EICON {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nVertices = sc.nextInt();
        int nEdges = sc.nextInt();
        int nQueries = sc.nextInt();

        Vertex[] graph = readGraph(nVertices, nEdges);

        for (int i = 0; i < nQueries; i++) {
            int tempVer = sc.nextInt();
            int tempEdges = sc.nextInt();

            Vertex u = graph[tempVer];
            Vertex v = graph[tempEdges];

            if (u.adjacentVertices.contains(v)) {
                sb.append("Y").append("\n");
            } else {
                sb.append("N").append("\n");
            }

        }
        System.out.println(sb);
    }

    static Vertex[] readGraph(int nVertices, int nEdges) {

        Vertex[] vertices = new Vertex[nVertices + 1];
        for (int i = 1; i <= nVertices; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < nEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            // vertices[u].addAdjacentVertex(vertices[v]);
            vertices[v].addAdjacentVertex(vertices[u]);

        }
        return vertices;
    }

    static class Vertex {
        public int id;
        public List<Vertex> adjacentVertices = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjacentVertex(Vertex v) {
            adjacentVertices.add(v);
        }
    }
}
