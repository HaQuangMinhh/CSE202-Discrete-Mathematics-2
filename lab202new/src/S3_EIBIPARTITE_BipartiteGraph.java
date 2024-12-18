import java.io.*;
import java.util.*;

public class S3_EIBIPARTITE_BipartiteGraph {
    static StringBuilder sb = new StringBuilder();
    static InputReader reader;

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        int T = reader.nextInt();
        for (int i = 0; i < T; i++) {
            int n = reader.nextInt();
            int m = reader.nextInt();
            Vertex[] graph = readGraph(n, m);
            boolean isBipartite = true;
            for (Vertex vertex : graph) {
                if (!vertex.visited) {
                    if (!bfs(vertex)) {
                        isBipartite = false;
                        break;
                    }
                }
            }
            if (isBipartite) {
                sb.append("Yes\n");
            } else {
                sb.append("No\n");
            }
        }
        System.out.println(sb);
    }

    static boolean bfs(Vertex vertex) {
        vertex.color = 1;
        Queue<Vertex> q = new ArrayDeque<>();
        q.add(vertex);
        vertex.visited = true;
        while (!q.isEmpty()) {
            Vertex w = q.remove();
            for (Vertex vertex2 : w.adjacentVertices) {
                if (!vertex2.visited) {
                    vertex2.color = w.color * -1;
                    vertex2.visited = true;
                    q.add(vertex2);
                } else if (vertex2.color == w.color) {
                    return false;
                }
            }
        }
        return true;
    }
   
    static Vertex[] readGraph(int nVertices, int nEdges) {
        Vertex[] vertices = new Vertex[nVertices];
        for (int i = 0; i < nVertices; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < nEdges; i++) {
            int u = reader.nextInt();
            int v = reader.nextInt();
            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }
        return vertices;
    }

    static class Vertex {
        int id;
        boolean visited;
        int color;
        List<Vertex> adjacentVertices = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex vertex) {
            adjacentVertices.add(vertex);
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
