

import java.io.*;
import java.util.*;

public class S1_EIFACEBOOK_FacebookFriend {
    static InputReader sc = new InputReader(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] graph = readGraph(n, m);
        for (int i = 1; i <= n; i++) {
            sb.append(graph[i].count).append(" ");
        }
        System.out.println(sb);

    }

    static Vertex[] readGraph(int nVertices, int nEdges) {
        Vertex[] vertices = new Vertex[nVertices + 1];
        for (int i = 1; i < nVertices + 1; i++) {
            vertices[i] = new Vertex(i, sc.next());
        }
        for (int i = 0; i < nEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            if (!vertices[u].adjacentVertices.contains(vertices[v])) {
                vertices[u].addNeighbor(vertices[v]);
                vertices[v].addNeighbor(vertices[u]);
                if (!vertices[u].gender.equals(vertices[v].gender)) {
                    vertices[u].count++;
                    vertices[v].count++;
                }
            }

        }
        // for (Vertex vertex : vertices) {
        // Collections.sort(vertex.adjacentVertices, (v1, v2) -> v1.id - v2.id);
        // }
        return vertices;
    }

    static class Vertex {
        int id;
        String gender;
        int count;
        // boolean visited;
        List<Vertex> adjacentVertices = new ArrayList<>();

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
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
