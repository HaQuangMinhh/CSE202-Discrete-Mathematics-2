package Set2;
import java.io.*;
import java.util.*;
class Main {
    static InputReader rd;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);
        Vertex[] graph = readGraph();

        dfs(graph[0]);  
        System.out.print(sb);
    }
    static Vertex[] readGraph() {
        int nVertices = rd.nextInt();
        int nEdges = rd.nextInt();

        Vertex[] vertices = new Vertex[nVertices];
        for (int i = 0; i < nVertices; ++i) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < nEdges; ++i) {
            int a = rd.nextInt();
            int b = rd.nextInt();

            vertices[a].addAdjacentVertex(vertices[b]);
            vertices[b].addAdjacentVertex(vertices[a]);  
        }

        
        // for (Vertex vertex : vertices) {
        //     Collections.sort(vertex.adjacentVertices, (v1, v2) -> v1.id - v2.id);
        // }
        return vertices;
    }

    static void dfs(Vertex vertex) {
        vertex.visited = true;
        sb.append(vertex.id).append(" ");
       
        Collections.sort(vertex.adjacentVertices, (a, b) -> a.id - b.id);
        for (Vertex adjacentVertex : vertex.adjacentVertices) {
            if (!adjacentVertex.visited) {
                dfs(adjacentVertex);
            }
        }
    }
    // static void dfs(Vertex start) {
    //     if (start.visited) return;
        
    //     Stack<Vertex> stack = new Stack<>();
    //     stack.push(start);

    //     while (!stack.isEmpty()) {
    //         Vertex v = stack.pop();
    //         if (!v.visited) {
    //             v.visited = true;
    //             sb.append( v.id).append("\n");

                
    //             Collections.sort(v.adjacentVertices, Comparator.comparingInt(vx -> vx.id));
    //             Collections.reverse(v.adjacentVertices);

    //             for (Vertex w : v.adjacentVertices) {
    //                 if (!w.visited) {
    //                     stack.push(w);
    //                 }
    //             }
    //         }
    //     }
    // }

    static class Vertex {
        public int id;
        public boolean visited = false;
        public List<Vertex> adjacentVertices = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjacentVertex(Vertex vertex) {
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