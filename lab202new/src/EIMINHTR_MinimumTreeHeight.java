import java.io.*;
import java.util.*;

public class EIMINHTR_MinimumTreeHeight {
    static StringBuilder sb = new StringBuilder();
    static InputReader reader;
    static int farthestVertexID;
    static int maxHeight = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        int n = reader.nextInt();
        Vertex[] graph = readGraph(n, n - 1);
        // Tìm đỉnh xa nhất từ đỉnh bất kỳ (ở đây chọn đỉnh 0)
        dfs(graph[0], 0);
        int firstEnd = farthestVertexID;

        // Reset trạng thái visited cho DFS tiếp theo
        for (Vertex vertex : graph) {
            vertex.visited = false;
        }

        // Tìm đỉnh xa nhất từ đỉnh vừa tìm được để xác định đường kính
        maxHeight = Integer.MIN_VALUE;
        dfs(graph[firstEnd], 0);
        int secondEnd = farthestVertexID;

        // Reset trạng thái visited một lần nữa
        for (Vertex vertex : graph) {
            vertex.visited = false;
        }

        // Chạy lại DFS từ hai đầu của đường kính để tìm đỉnh có chiều cao nhỏ nhất
        int[] heightsFromFirstEnd = new int[n];
        int[] heightsFromSecondEnd = new int[n];
        calculateHeights(graph[firstEnd], 0, heightsFromFirstEnd);
        calculateHeights(graph[secondEnd], 0, heightsFromSecondEnd);

        // Tìm chiều cao nhỏ nhất và các đỉnh tương ứng
        int minHeight = Integer.MAX_VALUE;
        List<Integer> optimalVertices = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int height = Math.max(heightsFromFirstEnd[i], heightsFromSecondEnd[i]);
            if (height < minHeight) {
                minHeight = height;
                optimalVertices.clear();
                optimalVertices.add(i);
            } else if (height == minHeight) {
                optimalVertices.add(i);
            }
        }

        // In kết quả
        Collections.sort(optimalVertices);
        for (int vertex : optimalVertices) {
            sb.append(vertex).append(" ");
        }
        sb.append("\n").append(minHeight);
        System.out.println(sb);
    }

    static void dfs(Vertex vertex, int height) {
        vertex.visited = true;
        for (Vertex neighbor : vertex.adjacentVertices) {
            if (!neighbor.visited) {
                if (height + 1 > maxHeight) {
                    maxHeight = height + 1;
                    farthestVertexID = neighbor.id;
                }
                dfs(neighbor, height + 1);
            }
        }
    }

    static void calculateHeights(Vertex vertex, int height, int[] heights) {
        vertex.visited = true;
        heights[vertex.id] = height;
        for (Vertex neighbor : vertex.adjacentVertices) {
            if (!neighbor.visited) {
                calculateHeights(neighbor, height + 1, heights);
            }
        }
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
        List<Vertex> adjacentVertices = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
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
