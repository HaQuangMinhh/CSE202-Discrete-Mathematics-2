package Set3;
import java.io.*;
import java.util.*;
 class EITRGROUP {
    static InputReader rd;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in); 

        int employeeCount = rd.nextInt(); 
        int managementRelations = rd.nextInt(); 

        Vertex[] employeeGraph = readGraph(employeeCount, managementRelations);
        boolean[] hasManager = new boolean[employeeCount];
        for (Vertex v : employeeGraph) {
            for (int subordinate : v.adjacentVertices) {
                hasManager[subordinate] = true;
            }
        }

        int[] depthMemoization = new int[employeeCount];
        Arrays.fill(depthMemoization, -1); 

        int maxDepth = 0;
        for (int i = 0; i < employeeCount; i++) {
            if (!hasManager[i]) { 
                maxDepth = Math.max(maxDepth, dfs(i, employeeGraph, depthMemoization));
            }
        }

        sb.append(maxDepth); 
        System.out.println(sb.toString()); 
    }

    static Vertex[] readGraph(int nVertices, int nEdges) throws IOException {
        Vertex[] vertices = new Vertex[nVertices];
        for (int i = 0; i < nVertices; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < nEdges; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();

            vertices[u].addAdjacentVertices(v);
        }

        return vertices;
    }

    public static int dfs(int employeeId, Vertex[] vertices, int[] memo) {
        if (memo[employeeId] != -1) {
            return memo[employeeId];
        }
        int maximumDepth = 1;
        for (int subordinate : vertices[employeeId].adjacentVertices) {
            maximumDepth = Math.max(maximumDepth, dfs(subordinate, vertices, memo) + 1);
        }  
        memo[employeeId] = maximumDepth;
        return maximumDepth;
    }

    static class Vertex {
        int id;
        List<Integer> adjacentVertices;

        public Vertex(int id) {
            this.id = id;
            this.adjacentVertices = new ArrayList<>();
        }

        void addAdjacentVertices(int subordinateId) {
            adjacentVertices.add(subordinateId);
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