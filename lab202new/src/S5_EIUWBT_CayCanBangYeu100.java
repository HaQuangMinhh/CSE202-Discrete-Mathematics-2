import java.io.*;
import java.util.*;
public class S5_EIUWBT_CayCanBangYeu100 {
    static Vertex temp = new Vertex(Integer.MAX_VALUE, 0);
    static long totalWeight;
    static StringBuilder sb = new StringBuilder();
    static InputReader reader;

    public static void main(String[] args) throws Throwable {
        reader = new InputReader(System.in);
        temp.left = Long.MAX_VALUE;
        int nVertex = reader.nextInt();
        Vertex[] arr = readGraph(nVertex);
        DFS(arr[2]);
        if (temp.id == Integer.MAX_VALUE) {
            sb.append("-1").append("\n");
        } else {
            long tw1 = Math.min(temp.left, temp.right);
            long tw2 = Math.max(temp.left, temp.right);
            sb.append(temp.id).append(" ").append(tw1).append(" ").append(tw2);
        }
        System.out.println(sb);

    }

    static long DFS(Vertex vertex) {
        long weightRoot = vertex.weight;
        vertex.visited = true;
        if (vertex.adjectVertices.size() == 2) {
            for (Vertex v : vertex.adjectVertices) {
                if (!v.visited) {
                    vertex.left += DFS(v);
                    vertex.weight += v.weight;
                }
            }
            vertex.right = totalWeight - vertex.left - weightRoot;
            long currentDiff = Math.abs(vertex.left - vertex.right);
            long tempDiff = Math.abs(temp.left - temp.right);
            if (currentDiff < tempDiff) {
                temp = vertex;
            } else if (currentDiff == tempDiff && vertex.id < temp.id) {
                temp = vertex;
            }
        } else {
            for (Vertex v : vertex.adjectVertices) {
                if (!v.visited) {
                    DFS(v);
                    vertex.weight += v.weight;
                }
            }
        }
        return vertex.weight;
    }

    public static Vertex[] readGraph(int n) {
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i, reader.nextInt());
            totalWeight += vertices[i].weight;
        }

        for (int i = 0; i < n - 1; i++) {
            int u = reader.nextInt();
            int v = reader.nextInt();
            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }
        return vertices;
    }

    static class Vertex {
        int id;
        List<Vertex> adjectVertices = new ArrayList<>();
        boolean visited;
        long weight;
        long left;
        long right;

        public Vertex(int id, long weight) {
            this.id = id;
            this.weight = weight;
        }
        public void addNeighbor(Vertex vertex){
            adjectVertices.add(vertex);
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
