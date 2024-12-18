
import java.io.*;
import java.util.*;
public class S4_EILOCAL2_LocalAreaNetworkEasy {
    static StringBuilder sb = new StringBuilder();
    static InputReader reader;

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        int n = reader.nextInt();
        Vertex[] graph = readGraph(n, n - 1);
       // System.out.println(bfs(graph[0]));
        System.out.println(bfs(graph[0]));
    }

    static int bfs(Vertex vertex) {
        Queue<Vertex> q = new ArrayDeque<>();
        q.add(vertex);
        vertex.visited = true;
        int max = 0;
        while (!q.isEmpty()) {
            Vertex w = q.remove();
            for (Edge e : w.adjacentVertices) {
                if (!e.endpoint.visited) {
                    e.endpoint.distance = e.weight + w.distance;
                    e.endpoint.visited = true;
                    q.add(e.endpoint);
                    max = Math.max(max, e.endpoint.distance);
                }
            }
        }
        return max;
    }

    static int dfs(Vertex vertex) {
        vertex.visited = true;
        int max = vertex.distance;
        for (Edge e : vertex.adjacentVertices) {
            if (!e.endpoint.visited) {
                e.endpoint.distance = e.weight + vertex.distance;
                max = Math.max(max, dfs(e.endpoint));
            }
        }
        return max;
    }

    static Vertex[] readGraph(int nVertices, int nEdges) {
        Vertex[] vertices = new Vertex[nVertices];
        for (int i = 0; i < nVertices; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < nEdges; i++) {
            int u = reader.nextInt();
            int v = reader.nextInt();
            int w = reader.nextInt();
            vertices[u].addNeighbor(vertices[v], w);
            vertices[v].addNeighbor(vertices[u], w);
        }
        // for (Vertex vertex : vertices) {
        // Collections.sort(vertex.adjacentVertices, (v1, v2) -> v1.id - v2.id);
        // }
        return vertices;
    }

    static class Edge {
        public int weight;
        public Vertex endpoint;

        public Edge(Vertex endpoint, int w) {
            this.endpoint = endpoint;
            this.weight = w;
        }
    }

    static class Vertex {
        int id;
        boolean visited;
        int distance;
        List<Edge> adjacentVertices = new ArrayList<Edge>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex vertex, int weight) {
            Edge edge = new Edge(vertex, weight);
            adjacentVertices.add(edge);
        }
    }

    static class InputReader {
        private byte[] inbuf = new byte[2 << 23];
        public int lenbuf = 0, ptrbuf = 0;
        public InputStream is;

        public InputReader(InputStream stream) throws IOException {

            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = System.in;
            lenbuf = is.read(inbuf);
        }

        public InputReader(FileInputStream stream) throws IOException {
            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = stream;
            lenbuf = is.read(inbuf);
        }

        public boolean hasNext() throws IOException {
            if (skip() >= 0) {
                ptrbuf--;
                return true;
            }
            return false;
        }

        public String nextLine() throws IOException {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!isSpaceChar(b) && b != ' ') { // when nextLine, ()
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b
                                        // != ' ')
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        private int readByte() {
            if (lenbuf == -1)
                throw new InputMismatchException();
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0)
                    return -1;
            }
            return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        private double nextDouble() {
            return Double.parseDouble(next());
        }

        public Character nextChar() {
            return skip() >= 0 ? (char) skip() : null;
        }

        private int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b))
                ;
            return b;
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

        public long nextLong() {
            long num = 0;
            int b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }
    }
}
