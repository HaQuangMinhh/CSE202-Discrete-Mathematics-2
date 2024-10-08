package Set7;

import java.io.*;
import java.util.*;

class Main {
    static InputReader reader;
    static StringBuilder sb;
    static long total = 0;

    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        reader = new InputReader(System.in);

        int n = reader.nextInt();

        Vertex[] vertexs = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertexs[i] = new Vertex(i, reader.nextInt());
        }

        for (int i = 1; i < n; i++) {
            int u = reader.nextInt();
            int v = reader.nextInt();

            vertexs[u].addList(vertexs[v]);
            vertexs[v].addList(vertexs[u]);
        }

        total = vertexs[1].weight;
        dfs(vertexs[1]);

        for (int i = 1; i <= n; i++) {
            vertexs[i].visited = false;
        }

        for (int i = 1; i <= n; i++) {
            if (vertexs[i].adjacentVertices.size() == 1) {
                dfsLeft(vertexs[i]);
                break;
            }
        }

        long min = Long.MAX_VALUE;
        int node = 0;
        String result = "-1";

        for (int i = 1; i <= n; i++) {
            if (vertexs[i].adjacentVertices.size() != 2) {
                continue;
            }
            long right = total - vertexs[i].total;
            long left = vertexs[i].total - vertexs[i].weight;
            long temp = Math.abs(right - left);

            if (min > temp) {
                min = temp;
                result = i + " ";
                node = i;

                if (right < left) {
                    result += (right + " " + left);
                } else {
                    result += (left + " " + right);
                }
            } else {
                if (min == temp && i < node) {
                    result = i + " ";
                    node = i;
                    if (right < left) {
                        result += (right + " " + left);
                    } else {
                        result += (left + " " + right);
                    }
                }
            }
        }
        System.out.println(result);
    }

    static void dfs(Vertex vertex) {
        vertex.visited = true;
        for (Vertex v : vertex.adjacentVertices) {
            if (!v.visited) {
                total += v.weight;
                dfs(v);
            }
        }
    }

    static void dfsLeft(Vertex vertex) {
        vertex.visited = true;
        for (Vertex v : vertex.adjacentVertices) {
            if (!v.visited) {
                dfsLeft(v);
                vertex.total += (v.total);
            }
        }
    }
    static class Vertex {
        int id;
        boolean visited;
        List<Vertex> adjacentVertices = new ArrayList<>();
        int weight;
        long total = 0;

        public Vertex(int id, int weight) {
            this.id = id;
            this.weight = weight;
            this.total = weight;
        }
        public void addList(Vertex v) {         
                adjacentVertices.add(v);
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