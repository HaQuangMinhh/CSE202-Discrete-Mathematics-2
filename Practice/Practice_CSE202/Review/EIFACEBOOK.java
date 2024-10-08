package Review;

import java.io.*;
import java.util.*;

public class EIFACEBOOK {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        int nVertice = sc.nextInt();
        int nEdges = sc.nextInt();

        Vertex[] graph = readGraph(nVertice, nEdges);

        for (int i = 1; i <= nVertice; i++) {
            Vertex v = graph[i];
            sb.append(v.count).append(" ");

        }
        System.out.println(sb);
    }

    static Vertex[] readGraph(int nVertices, int nEdges) {

        Vertex[] vertices = new Vertex[nVertices + 1];
        for (int i = 1; i <= nVertices; i++) {
            vertices[i] = new Vertex(i, sc.next());
        }
        for (int i = 0; i < nEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[v].addAdjacentVertex(vertices[u]);
            vertices[u].addAdjacentVertex(vertices[v]);
        }
        return vertices;
    }

    static class Vertex {
        public int id;
        public String gender;
        public int count = 0;
        public List<Vertex> adjencentVertices = new ArrayList<>();

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
        }

        public void addAdjacentVertex(Vertex v) {
            if (!adjencentVertices.contains(v)) {
                adjencentVertices.add(v);
                if (!v.gender.equalsIgnoreCase(this.gender)) {
                    count++;
                }
            }
        }
    }

}
