import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class EIMINDISTA {

    static InputReader rd = new InputReader(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int alt = 0;
        int n = rd.nextInt();
        int m = rd.nextInt();
        Vertex[] vertexs = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertexs[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();
            int length = rd.nextInt();

            vertexs[u].add(new Edge(length, vertexs[v]));
            vertexs[v].add(new Edge(length, vertexs[u]));
        }

        PriorityQueue<Vertex> pq = new PriorityQueue<>((v1, v2) -> Integer.compare(v1.distance, v2.distance));
        vertexs[0].distance = 0;
        pq.add(vertexs[0]);

        while (!pq.isEmpty()) {

            Vertex u = pq.poll();
            for (Edge v : u.adj) {
                alt = u.distance + v.distance;
                if (alt < v.end.distance) {
                    v.end.distance = alt;
                    pq.add(v.end);
                }
            }

        }

        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < n; i++) {
            if (vertexs[i].distance == Integer.MAX_VALUE) {
                sb.append("-1" + " ");
            } else {
                sb.append(vertexs[i].distance + " ");
            }
            
        }
        System.out.println(sb);

    }

    static class Vertex {
        int id;
        int distance = Integer.MAX_VALUE;
        List<Edge> adj = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void add(Edge v) {
            adj.add(v);
        }
    }

    static class Edge {
        Vertex end;
        int distance;

        public Edge(int distance, Vertex end) {
            this.distance = distance;
            this.end = end;
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
