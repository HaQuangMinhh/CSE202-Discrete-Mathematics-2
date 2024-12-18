

import java.io.*;
import java.util.*;

public class S2_EIUDFS1_DFSDirectedGraph {
    static InputReader sc = new InputReader(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] graph = readGraph(n, m);
        dfs(graph[0]);
        System.out.println(sb);
    }

    static void dfs(Vertex vertex) {
        vertex.visited = true;
        sb.append(vertex.id + " ");
        for (Vertex w : vertex.adjacentVertices) {
            if (!w.visited) {
                dfs(w);
            }
        }
    }

    static Vertex[] readGraph(int nVertices, int nEdges) {
        Vertex[] vertices = new Vertex[nVertices];
        for (int i = 0; i < nVertices; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < nEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].addNeighbor(vertices[v]);
            // vertices[v].addNeighbor(vertices[u]);
        }
        for (Vertex vertex : vertices) {
            Collections.sort(vertex.adjacentVertices, (v1, v2) -> v1.id - v2.id);
        }
        return vertices;
    }

    static class Vertex {
        int id;
        boolean visited;
        List<Vertex> adjacentVertices = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            adjacentVertices.add(v);
        }

    }

    static class InputReader {

        StringTokenizer tokenizer;
        BufferedReader reader;
        String token;
        String temp;

        public InputReader(InputStream stream) {
            tokenizer = null;
            reader = new BufferedReader(new InputStreamReader(stream));
        }

        public InputReader(FileInputStream stream) {
            tokenizer = null;
            reader = new BufferedReader(new InputStreamReader(stream));
        }

        public String nextLine() throws IOException {
            return reader.readLine();
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    if (temp != null) {
                        tokenizer = new StringTokenizer(temp);
                        temp = null;
                    } else {
                        tokenizer = new StringTokenizer(reader.readLine());
                    }
                } catch (IOException e) {
                }
            }
            return tokenizer.nextToken();
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
