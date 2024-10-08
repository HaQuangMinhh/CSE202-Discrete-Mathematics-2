package Review;

import java.util.*;
import java.io.*;

public class EIMKF {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        int nVertices = sc.nextInt();
        int nEdges = sc.nextInt();

        Vertex[] graph = readGraph(nVertices, nEdges);
        for (int i = 0; i < nVertices; i++) {
            Vertex v = graph[i];
            sb.append(v.id).append(" ").append(v.getDegree()).append(" ");
            List<Vertex> friends = v.adjacentVertices;
            Collections.sort(friends, (a, b) -> a.id - b.id);
            for (Vertex friend : friends) {
                sb.append(friend.id).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static Vertex[] readGraph(int nVertices, int nEdges) {

        Vertex[] vertices = new Vertex[nVertices];
        for (int i = 0; i < nVertices; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < nEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjacentVertex(vertices[v]);
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

        public int getDegree() {
            return adjacentVertices.size();
        }
    }
}
