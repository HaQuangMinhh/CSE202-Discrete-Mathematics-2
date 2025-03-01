import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;

public class EILOCAL2 {

    StringBuilder sb = new StringBuilder(); 
    static InputReader rd ; 

    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);

        int n = rd.nextInt(); 
        Vertex[] graph = readGraph(n, n - 1);
        System.out.println( bfs(graph[0]) );

    }

    static Vertex[] readGraph ( int nVertices , int nEdges ) {
        Vertex[] vertices = new Vertex[nVertices];

        for ( int i = 0; i < nVertices ; i++ ) {
            vertices[i] = new Vertex(i);
        }

        for ( int i = 0 ; i < nEdges ; i++ ) {
            int u = rd.nextInt(); 
            int v = rd.nextInt(); 

            int w = rd.nextInt(); //  Weight

            vertices[u].addAdjecentVertices(vertices[v], w);
            vertices[v].addAdjecentVertices(vertices[u], w);
        }
        return vertices ; 
    }

    static int bfs ( Vertex v ) {
        Queue<Vertex> q = new ArrayDeque<>(); 

        v.visited = true ; 
        q.add(v);

        int max = 0 ; 
        while ( ! q.isEmpty() ) {
            Vertex vertex = q.poll(); 

            for ( Edge e : vertex.adjacentVertices ) {
                if ( ! e.endPoint.visited ) {

                    e.endPoint.distance = e.weight + vertex.distance ; 
                    e.endPoint.visited = true ; 
                    q.add(e.endPoint);

                    max = Math.max(max, e.endPoint.distance);
                }
            }
        }
        return max ; 
    }

    static class Edge {
        public int weight ; // Trọng số của cạnh ( Độ dài dây )
        public Vertex endPoint ; // Đỉnh đích của cạnh 
        
        public Edge(Vertex endPoint , int weight ) {
            this.weight = weight;
            this.endPoint = endPoint;
        }
    }

    static class Vertex {
        public int id ; 
        public boolean visited ; 
        public int distance ;       // Khoảng cách từ gốc (ID = 0) đến đỉnh này 
        
        List<Edge> adjacentVertices = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        } 
        
        public void addAdjecentVertices ( Vertex v , int weight ) {
            Edge edge = new Edge(v, weight);
            adjacentVertices.add(edge);
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
