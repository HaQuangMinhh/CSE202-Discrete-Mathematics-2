package Review_Set2;

import java.io.*;
import java.util.*;

public class EIFBF {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nVertices = sc.nextInt();
        int nEdges = sc.nextInt();

        Vertex[] graph = readGraph(nVertices, nEdges);

        List<Vertex> component = new ArrayList<>();
        //boolean[]visited=new boolean[nVertices+1];
        for (Vertex w :graph) {
            if (!w.visited) {
                Vertex cc = new Vertex(0, "None");
                dfs(cc);
                component.add(cc);
            }
        }
        Collections.sort(component, (a, b) -> a.id - b.id);
        for (Vertex cc : component) {
            sb.append(cc.id).append(" ").append(cc.maleCount).append(" ").append(cc.femaleCount).append("\n");

        }
        System.out.println(sb);
    }

    static void dfs(Vertex v) {
        v.visited = true;
        if (v.gender.equals("Nam")) {
            v.maleCount++;
        } else {
            v.femaleCount++;
        }

        for (Vertex w : v.adjacentVertices) {
            if (!w.visited) {
                w.maleCount = v.maleCount;
                w.femaleCount = v.femaleCount;
                dfs(w);

                v.maleCount = w.maleCount;
                v.femaleCount = w.femaleCount;
            }
        }
    }

    static Vertex[] readGraph(int nVertice, int nEdges) {

        Vertex[] vertices = new Vertex[nVertice + 1];

        for (int i = 1; i <= nVertice; i++) {
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
