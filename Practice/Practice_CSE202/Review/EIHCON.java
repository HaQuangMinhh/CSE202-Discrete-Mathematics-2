package Review;

import java.util.*;
import java.io.*;

public class EIHCON {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        int nVertices = sc.nextInt();
        int nEdges = sc.nextInt();
        int nQueries = sc.nextInt();

        Vertex[] graph = readGraph(nVertices, nEdges);

        for (int i = 0; i < nQueries; ++i) {
            int tempver = sc.nextInt();
            int tempEdges = sc.nextInt();

            Vertex u = graph[tempver];
            Vertex v = graph[tempEdges];

            if (u.adjecentVertices.contains(v)) {
                sb.append("Y").append("\n");
            } else {
                boolean found = false;
                for (Vertex t : u.adjecentVertices) {
                    if (t.adjecentVertices.contains(v)) {
                        sb.append("Y").append("\n");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    sb.append("N").append("\n");
                }
            }
        }
        System.out.print(sb.toString());
    }
    static Vertex[] readGraph(int nVertices, int nEdges) {
        Vertex[] vertices = new Vertex[nVertices + 1];
        for (int i = 1; i <= nVertices; ++i) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < nEdges; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[v].addAjecentVertex(vertices[u]);
        }

        return vertices;
    }

    static class Vertex {
        public int id;
       public List<Vertex> adjecentVertices = new ArrayList<Vertex>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAjecentVertex(Vertex v) {
            adjecentVertices.add(v);
        }

    }
}
