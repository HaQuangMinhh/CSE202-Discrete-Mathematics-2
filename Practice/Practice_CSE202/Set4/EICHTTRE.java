package Set4;
import java.io.*;
import java.util.*;
class Main {
    static InputReader rd;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);
        int T = rd.nextInt();  

        while (T-- > 0) {
            int n = rd.nextInt(); 
            int m = rd.nextInt(); 

            if (n == 0 || m != n - 1) {
                sb.append("NO\n");
                for (int i = 0; i < m; i++) {
                    rd.nextInt(); 
                    rd.nextInt(); 
                }
                continue;
            }

            Vertex[] graph = new Vertex[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new Vertex(i);
            }

            for (int i = 0; i < m; i++) {
                int u = rd.nextInt();
                int v = rd.nextInt();
                graph[u].addAdjacentVertex(graph[v]);
                graph[v].addAdjacentVertex(graph[u]);
            }

            if (isTree(graph, n)) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.print(sb.toString());
    }

    static boolean isTree(Vertex[] graph, int n) {
        boolean[] visited = new boolean[n];
        if (!dfs(graph[0], visited, -1)) {
            return false; 
        }
        for (boolean v : visited) {
            if (!v) return false; 
        }
        return true;
    }

    static boolean dfs(Vertex vertex, boolean[] visited, int parent) {
        visited[vertex.id] = true;
        for (Vertex adj : vertex.adjacentVertices) {
            if (!visited[adj.id]) {
                if (!dfs(adj, visited, vertex.id)) return false;
            } else if (adj.id != parent) {
                return false;
            }
        }
        return true;
    }

    static class Vertex {
        int id;
        List<Vertex> adjacentVertices;

        Vertex(int id) {
            this.id = id;
            this.adjacentVertices = new ArrayList<>();
        }

        void addAdjacentVertex(Vertex vertex) {
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