import java.io.*;
import java.util.*;

public class EIBIRTHDAY_BirthdayGifts {
    static InputReader sc = new InputReader(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int d = sc.nextInt();
        int k = sc.nextInt();
        Vertex[] graph = readGraph(n, m);
        for (Vertex vertex : graph) {
            int count = 0;
            for (Vertex vertex2 : vertex.adjacentVertices) {
                if (isWithRange(d, k, vertex2.DOB)) {
                    count++;
                }
            }
            sb.append(count).append(" ");
        }
        System.out.println(sb);
    }

    static boolean isWithRange(int start, int range, int date) {
        if (range == 0) {
            return false;
        }
        if (start + range <= 365) {
            return date >= start && date <= start + range;
        } else {
            int adjustedEnd = (start + range) % 365;
            return (date >= start && date <= 365) || (date >= 0 && date <= adjustedEnd);
        }
    }

    static Vertex[] readGraph(int nVertices, int nEdges) {
        Vertex[] vertices = new Vertex[nVertices];
        for (int i = 0; i < nVertices; i++) {
            vertices[i] = new Vertex(i);
            vertices[i].DOB = sc.nextInt();
        }
        for (int i = 0; i < nEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }
        for (Vertex vertex : vertices) {
            Collections.sort(vertex.adjacentVertices, (v1, v2) -> v1.id - v2.id);
        }
        return vertices;
    }

    static class Vertex {
        int id;
        int DOB;

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
