
import java.io.*;
import java.util.*;;

public class S1_EICON_IsConnected {
    static InputReader sc = new InputReader(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nVertices = sc.nextInt();
        int nEdges = sc.nextInt();
        int q = sc.nextInt();
        Vertex[] graph = readGraph(nVertices, nEdges);
        for (int i = 0; i < q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            // boolean flag = false;
            // for (Vertex ve : graph[a].adjacentVertices) {
            // if (ve.id == b) {
            // flag = true;
            // break;
            // }
            // }
            if (graph[a].adjacentVertices.contains(graph[b])) {
                sb.append("Y\n");
            } else {
                sb.append("N\n");
            }
        }
        System.out.println(sb);
    }

    static Vertex[] readGraph(int nVertices, int nEdges) {
        Vertex[] vertices = new Vertex[nVertices + 1];
        for (int i = 1; i <= nVertices; ++i) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < nEdges; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            // Đồ thị vô hướng nên cạnh a-b nghĩa là: a kề của b,
            // b kề của a
            // vertices[a].addNeighbor(vertices[b]);
            vertices[v].addNeighbor(vertices[u]);
        }

        return vertices;
    }

    static class Vertex {
        int id;
        List<Vertex> adjacentVertices = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex vertex) {
            adjacentVertices.add(vertex);
        }

        // public int getDegree() {
        // return adjacentVertices.size();
        // }

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
