package Review;

import java.io.*;
import java.util.*;

public class EIUSUFBF {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        int nVertiecs = sc.nextInt();
        int nEdges = sc.nextInt();
        int nQueries = sc.nextInt();

        Vertex[] graph = readGraph(nVertiecs, nEdges);

        for(Vertex w:graph){
            List<Vertex>suggestion=w.getFriendSuggestion(nQueries);
            
            Collections.sort(suggestion, (a, b) -> a.id - b.id);
            sb.append(w.id);
            for(Vertex v:suggestion){
                sb.append(" ").append(v.id);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static Vertex[] readGraph(int nVertice, int nEdges) {

        Vertex[] vertices = new Vertex[nVertice];
        for (int i = 0; i < nVertice; i++) {
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

        List<Vertex> getFriendSuggestion(int minAdjacient) {
            List<Vertex> suggestion = new ArrayList<>();
            for (Vertex w : adjacentVertices) {
                if (w.adjacentVertices.size() < minAdjacient) {
                    suggestion.add(w);
                }
            }
            return suggestion;
        }
    }
}
