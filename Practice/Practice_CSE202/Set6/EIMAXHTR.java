package Set6;
import java.util.*;
import java.io.*;
class Main {
    static InputReader rd;
    static StringBuilder sb = new StringBuilder();
    static int maxLevel = 0;
    static int farthestNode = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);
        Vertex[] graph = readGraph();

        dfs(graph[0]);
        
        int node = farthestNode;
        for (int i = 0; i < graph.length; i++) {
            graph[i].visited = false;
            graph[i].level=0;
        }
       

        dfs(graph[farthestNode]);

        farthestNode = Math.min(farthestNode, node);
        sb.append(farthestNode).append(" ").append(maxLevel);
        sb.append(maxLevel);

        System.out.println(sb);
    }    
    static void dfs(Vertex vertex) {
        vertex.visited = true;
        for (Vertex adjacentVertex : vertex.adjecentVertices) {
            if (!adjacentVertex.visited) {
                adjacentVertex.visited = true;
                adjacentVertex.level = vertex.level + 1;
                if (adjacentVertex.level > maxLevel) {
                    maxLevel = adjacentVertex.level;
                    farthestNode = adjacentVertex.id;
                }else if (maxLevel == adjacentVertex.level) {
                    farthestNode = Math.min(farthestNode, adjacentVertex.id);
                }
              
                dfs(adjacentVertex);
            }
        }
    }

    static Vertex[] readGraph() {
        int nVertices = rd.nextInt();
        int nEdges = nVertices - 1;

        Vertex[] vertices = new Vertex[nVertices];
        for (int i = 0; i < nVertices; ++i) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < nEdges; ++i) {
            int a = rd.nextInt();
            int b = rd.nextInt();

            vertices[a].addAdjecentVertex(vertices[b]);
            vertices[b].addAdjecentVertex(vertices[a]);
        }

        return vertices;
    }

    static class Vertex {
        public int id;
        public int level = 0;
        public boolean visited;
        public List<Vertex> adjecentVertices = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjecentVertex(Vertex vertex) {
            adjecentVertices.add(vertex);
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