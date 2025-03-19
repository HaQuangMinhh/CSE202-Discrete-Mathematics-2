import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;

public class EIMINSPAN {

    static InputReader reader;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        int n = reader.nextInt();
        int m = reader.nextInt();
        Vertex[] graph = readGraph(n, m);
        // Prim
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.distance, o2.distance));
        long rs = 0;
        int countN = n;
        pq.add(new Edge(graph[0], 0));
        while (!pq.isEmpty()) {
            Edge w = pq.remove();
            if (!w.end.visited) {
                w.end.visited = true;
                countN--;
                rs += w.distance;
                for (Edge e : w.end.listEdge) {
                    if (!e.end.visited) {
                        pq.add(e);
                    }
                }
            }
        }
        if (countN == 0) {
            sb.append(rs);
        } else {
            sb.append(-1);
        }
        System.out.println(sb);
    }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = reader.nextInt();
            int v = reader.nextInt();
            long distance = reader.nextLong();
            vertices[u].addNeighbor(vertices[v], distance);
            vertices[v].addNeighbor(vertices[u], distance);
        }
        return vertices;
    }

    static class Vertex {
        int id;
        boolean visited;
        List<Edge> listEdge = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v, long cost) {
            listEdge.add(new Edge(v, cost));
        }

    }

    static class Edge {
        Vertex end;
        long distance;

        public Edge(Vertex end, long distance) {
            this.end = end;
            this.distance = distance;
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
            while (!isSpaceChar(b) && b != ' ') {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) {
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
