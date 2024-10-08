package Review;

import java.io.*;
import java.util.*;

public class EIUDEG {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nVertices = sc.nextInt();
        int nEdges = sc.nextInt();
        Vertex[] graph = readGraph(nVertices, nEdges);

        for (int i = 1; i < graph.length; i++) {
            Vertex v = graph[i];
            sb.append(v.getDegree()).append(" ");
        }
        System.out.println(sb);
    }

    static Vertex[] readGraph(int nVertices, int nEdges) {

        Vertex[] vertices = new Vertex[nVertices+1];
        for (int i = 1; i <= nVertices; i++) {
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
        public List<Vertex> adjacentVertices = new ArrayList<Vertex>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjacentVertices(Vertex v) {
            adjacentVertices.add(v);
        }

        public int getDegree() {
            return adjacentVertices.size();
        }
    }
}
