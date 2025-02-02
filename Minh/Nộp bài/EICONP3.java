import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EICONP3 {

    static InputReader rd ; 
    static StringBuilder sb = new StringBuilder() ; 
    static int countVertices ; 
    static int countDegree ; 

    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);

        int nVertices = rd.nextInt(); 
        int nEdges = rd.nextInt(); 

        Vertex[] graph = readGraph(nVertices, nEdges);

        for ( Vertex vertex : graph ) { // Duyệt các thành phần liên thông 
            countVertices = 0 ; 
            countDegree = 0 ; 

            if ( ! vertex.visited ) {
                dfs(vertex);
                sb.append(vertex.id).append(" ")
                .append(countVertices).append(" ")
                .append(countDegree / 2).append("\n");
            }
        }
        System.out.println(sb);

    }

    static void dfs (Vertex v ) {
        v.visited = true ; 
        countVertices++; // Đếm vertices trong tp liên thông 
        countDegree += v.adjecentVertices.size(); // Tổng các bậc của 1 đỉnh

        for ( Vertex vertex : v.adjecentVertices ) {  // Duyệt qua các đỉnh kề
            if ( ! vertex.visited ) {
                dfs(vertex);    // Đệ quy 
            }
        }

    }

    static Vertex[] readGraph ( int nVertices , int nEdges ) {

        Vertex[] vertices = new Vertex[nVertices];

        for ( int i = 0 ; i < nVertices ; i++ ) {
            vertices[i] = new Vertex(i); 
        }

        for ( int i = 0 ; i < nEdges ; i++ ) {
            int u = rd.nextInt(); 
            int v = rd.nextInt(); 

            vertices[u].addAdjecentVertices(vertices[v]);
            vertices[v].addAdjecentVertices(vertices[u]);
        }

        for ( Vertex v : vertices) {
            v.adjecentVertices.sort( (v1,v2) -> v1.id - v2.id);
        }

        return vertices ; 

    }
    
    static class Vertex {
        public int id ; 
        public boolean visited ; 

        public List<Vertex> adjecentVertices = new ArrayList<>(); 

        public void addAdjecentVertices (Vertex v) {
            adjecentVertices.add(v);
        }

        public Vertex( int id ){
            this.id = id ;
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
