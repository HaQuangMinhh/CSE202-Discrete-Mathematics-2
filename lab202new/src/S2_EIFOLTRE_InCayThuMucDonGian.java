import java.io.*;
import java.util.*;

public class S2_EIFOLTRE_InCayThuMucDonGian {
    static StringBuilder sb = new StringBuilder();
    static InputReader reader;

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        int n = reader.nextInt();
        List<Vertex> graph = readGraph(n);
        String root = reader.next();
        int level = 0;
        for (Vertex vertex : graph) {
            if (vertex.id.equals(root)) {
                dfs(vertex, level);
            }
        }
        System.out.println(sb);
    }

    static void dfs(Vertex vertex, int level) {
        vertex.visited = true;
        for (int i = 0; i < level; i++) {
            sb.append("---");
        }
        level++;
        sb.append("-");
        sb.append(vertex.id).append("\n");
        for (Vertex w : vertex.adjacentVertices) {
            if (!w.visited) {
                dfs(w, level);
            }
        }
    }

    static List<Vertex> readGraph(int nVertices) {
        HashMap<String, Vertex> tree = new HashMap<>();
        for (int i = 1; i < nVertices; i++) {
            String u = reader.next();
            if (tree.get(u) == null) {
                Vertex U = new Vertex(u);
                tree.put(u, U);
            }
            String v = reader.next();
            if (tree.get(v) == null) {
                Vertex V = new Vertex(v);
                tree.put(v, V);
            }
            tree.get(u).addNeighbor(tree.get(v));
            tree.get(v).addNeighbor(tree.get(u));
        }
        List<Vertex> vertices = new ArrayList<>(tree.values());
        for (Vertex vertex : vertices) {
            Collections.sort(vertex.adjacentVertices, (v1, v2) -> v1.id.compareToIgnoreCase(v2.id));
        }
        return vertices;
    }

    static class Vertex {
        String id;
        boolean visited;
        List<Vertex> adjacentVertices = new ArrayList<>();

        public Vertex(String id) {
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
