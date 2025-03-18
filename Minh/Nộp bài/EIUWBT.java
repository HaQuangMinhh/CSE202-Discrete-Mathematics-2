import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EIUWBT {


    static InputReader rd;
    static StringBuilder sb = new StringBuilder();

    static long finalMin = Long.MAX_VALUE;
    static long left = Long.MIN_VALUE;
    static long right = Long.MIN_VALUE;
    static long minRoot = -1;

    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);

        int n = rd.nextInt();
        
        Vertex[] graph = readGraph(n, n - 1);
        
        for (int i = 1; i <= n; i++) {
            if (graph[i].adjacentVertices.size() == 2) {
                for (int j = 1; j <= n; j++) {
                    graph[j].visited = false;
                    graph[j].tempWeight = graph[j].weight;
                }
                dfs(graph[i]);
                
                long tw1 = graph[i].adjacentVertices.get(0).tempWeight;
                long tw2 = graph[i].adjacentVertices.get(1).tempWeight;
                long min = Math.abs(tw1 - tw2);
                
                boolean condition1 = min < finalMin ;
                boolean condition2 = min == finalMin ; 
                boolean condition3 = i < minRoot ; 

                if ( condition1 || condition2 && condition3 ) {
                    finalMin = min;
                    minRoot = i;
                    left = Math.min(tw1, tw2);
                    right = Math.max(tw1, tw2);
                }
            }
        }

        if (minRoot != -1) {
            System.out.println(minRoot + " " + left + " " + right);
        } else {
            System.out.println(-1);
        }

    }

    static void dfs(Vertex vertex) {
        vertex.visited = true;
        for (Vertex w : vertex.adjacentVertices) {
            if (!w.visited) {
                dfs(w);
                vertex.tempWeight += w.tempWeight;
            }
        }
    }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n + 1];
        
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i, rd.nextLong());
        }
        
        for (int i = 0; i < m; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();
            
            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }
        
        return vertices;
    }

    static class Vertex {
        int id;
        boolean visited;
        List<Vertex> adjacentVertices = new ArrayList<>();
        long weight;
        long tempWeight;

        public Vertex(int id, long weight) {
            this.id = id;
            this.weight = weight;
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
