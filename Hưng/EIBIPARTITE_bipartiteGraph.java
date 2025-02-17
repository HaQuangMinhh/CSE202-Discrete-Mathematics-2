import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EIBIPARTITE_bipartiteGraph {
    static InputReader sc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        int testcase = sc.nextInt();
        boolean isBipartite = false;
        for (int i = 0; i < testcase; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            List<Vertex> vertices = makeGraph(n, m);
            for (Vertex v : vertices) {
                if (v.signal == -1) {
                    isBipartite = bfs(v);
                    if (!isBipartite) {
                        break;
                    }
                }
            }
            if (isBipartite) {
                sb.append("Yes").append("\n");
            } else {
                sb.append("No").append("\n");
            }
        }
        System.out.println(sb);
    }

    static List<Vertex> makeGraph(int n, int m) {
        List<Vertex> vertices = new ArrayList<>();
        // Assign vertex a signal with -1 : (no signal)
        for (int i = 0; i < n; i++) {
            Vertex v = new Vertex(i);
            v.addSignal(-1);
            vertices.add(v);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices.get(u).addNeighbor(vertices.get(v));
            vertices.get(v).addNeighbor(vertices.get(u));
        }
        return vertices;
    }

    static boolean bfs(Vertex start) {
        Queue<Vertex> queue = new LinkedList<>();
        // using signal to know if the input is bipartite
        // -1: no signal, 1; right, 0: left
        queue.add(start);
        start.addSignal(1); // start with right
        while (!queue.isEmpty()) {
            Vertex u = queue.poll();
            for (Vertex v : u.neighbors) {
                if (v.signal == u.signal) {
                    return false;
                }
                if (v.signal == -1) { // if v dont have an signal, assign it
                    queue.add(v);
                    v.addSignal(1 - u.signal);
                }
            }
        }
        return true;
    }

    static class Vertex {
        int id;
        int signal;// differentiate to see if it is bipartite using sigal (-1: no signal, 1; right,
                   // 0: left)
        List<Vertex> neighbors = new ArrayList<>();// neighbor = adjacent

        public Vertex(int id) {
            this.id = id;
        }

        public void addSignal(int signal) {
            this.signal = signal;
        }

        public void addNeighbor(Vertex v) {
            neighbors.add(v);
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
