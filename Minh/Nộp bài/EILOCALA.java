import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;

public class EILOCALA {

    static InputReader rd;
    static StringBuilder sb = new StringBuilder();

    static int maxDistance = 0;
    static int farthestID = 0;
    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);

        int n = rd.nextInt();
        Vertex[] graph = readGraph(n, n - 1);
        bfs(graph[0]);
        
        int end = farthestID;
        for (Vertex vertex : graph) {
            vertex.visited = false;
            vertex.distance = 0;
        }

        maxDistance = 0;

        bfs(graph[end]);
        
        int smallestId = Math.min(farthestID, end);
        System.out.println(smallestId + " " + maxDistance);
    }

    static void bfs(Vertex vertex) {
        Queue<Vertex> q = new ArrayDeque<>();
        q.add(vertex);
        vertex.visited = true;
        while ( ! q.isEmpty() ) {
            Vertex w = q.poll();

            for (Edge e : w.adjAcentVertices ) {

                if ( ! e.endpoint.visited ) {
                    e.endpoint.visited = true;
                    e.endpoint.distance = e.weight + w.distance;
                    q.add(e.endpoint);

                    boolean condition1 = e.endpoint.distance > maxDistance ; 
                    boolean condition2 = e.endpoint.distance == maxDistance ; 
                    boolean condition3 = e.endpoint.id < farthestID ; 

                    if ( condition1 || condition2 && condition3 ) {

                        maxDistance = e.endpoint.distance;
                        farthestID = e.endpoint.id;
                    }
                }
            }
        }
    }

    static Vertex[] readGraph(int nVertices, int nEdges) {
        Vertex[] vertices = new Vertex[nVertices];

        for (int i = 0; i < nVertices; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < nEdges; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();

            int w = rd.nextInt();

            vertices[u].addAdjecentVertices(vertices[v], w);
            vertices[v].addAdjecentVertices(vertices[u], w);
        }
        return vertices;
    }

    static class Edge {
        public int weight;
        public Vertex endpoint;

        public Edge(Vertex endpoint, int weight) {
            this.weight = weight;
            this.endpoint = endpoint;
        }
    }

    static class Vertex {
        public int id;
        public boolean visited;
        public int distance;

        public List<Edge> adjAcentVertices = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjecentVertices(Vertex vertex, int weight) {
            Edge edge = new Edge(vertex, weight);
            adjAcentVertices.add(edge);
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
