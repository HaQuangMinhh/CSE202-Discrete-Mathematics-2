package Set1;
import java.io.*;
import java.util.*;

public class EIMKF {
    static InputReader rd;
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);

        int nVertices = rd.nextInt();
        int nEdges = rd.nextInt();

        Vertex[] graph = readGraph(nVertices, nEdges);

        for (int i = 0; i < nVertices; i++) {
            Vertex v = graph[i];
            sb.append(v.id).append(" ").append(v.getDegree()).append(" ");
            List<Vertex> friends = v.adjacentVertices;
            Collections.sort(friends, Comparator.comparingInt(vx -> vx.id));
            for (Vertex friend : friends) {
                sb.append(friend.id).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static Vertex[] readGraph(int nVertices, int nEdges) {
        Vertex[] vertices = new Vertex[nVertices];
        for (int i = 0; i < nVertices; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < nEdges; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();

            vertices[u].addAdjacentVertex(vertices[v]);
            vertices[v].addAdjacentVertex(vertices[u]);
        }

        return vertices;
    }

    static class Vertex {
        public int id;
        public List<Vertex> adjacentVertices = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjacentVertex(Vertex v) {
            if (!adjacentVertices.contains(v)) {
                adjacentVertices.add(v);
            }
        }

        public int getDegree() {
            return adjacentVertices.size();
        }
    }

    static class InputReader {
        private byte[] inbuf = new byte[1 << 23];
        public int lenbuf = 0, ptrbuf = 0;
        public InputStream is;

        public InputReader(InputStream stream) throws IOException {
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

        public char nextChar() {
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
