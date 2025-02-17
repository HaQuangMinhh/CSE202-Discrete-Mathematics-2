import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;

public class EITRGROUP {

    static InputReader rd ; 
    static StringBuilder sb = new StringBuilder() ; 
    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);

        int quantityEmployee = rd.nextInt(); 
        int relationships = rd.nextInt(); // số quan hệ giữa employee và manager

        Vertex[] graph = readGraph(quantityEmployee, relationships); 

        for ( Vertex v : graph ) {  // Duyệt đồ thị 
            if ( v.p == null  ) { // không có cấp trên sẽ = null
                bfs(v, 1); // người không có cấp trên , sẽ đặt đặt level 1 
            }
        }

        // Khởi tạo biến max
        int max = 0 ; // lưu level lớn nhất
        for ( Vertex v : graph ) {
            max = Math.max(max, v.level); // cập nhật max để lấy level lớn nhất 
        }

        System.out.println(max);
    }


    static void bfs ( Vertex v , int level ) {

        Queue<Vertex> q = new ArrayDeque<>(); 

        q.add(v);
        v.visited = true ; 
        v.level = level ; // gán level ban đầu 

        while ( !q.isEmpty() ) {
            Vertex vertex = q.poll(); // Lấy đỉnh đầu tiên trong hàng đợi để xử lí 

            for ( Vertex vertex2 : vertex.adjecentVertices ) {
                if ( ! vertex2.visited ) {
                    vertex2.level = vertex.level +1 ;  // gán level của nó bằng level của vertex + 1 

                    vertex2.visited = true; 
                    q.add(vertex2);
                }
            }
        }
    }

    static Vertex[] readGraph( int nVertices , int nEdges ) {
        Vertex[] vertices = new Vertex[nVertices];

        for ( int i = 0 ; i < nVertices ; i++ ) {
            vertices[i] = new Vertex(i);
        }

        for ( int i = 0 ; i < nEdges ; i++ ) {
            int u = rd.nextInt(); 
            int v = rd.nextInt(); 

            vertices[u].addAdjecentVertices(vertices[v]);

            // Gán p ( đỉnh cha ) của v là u 
            vertices[v].p = vertices[u] ; // u là cha của v 
        }
        return vertices ; 
    }

    static class Vertex {
        public int id ; 
        public boolean visited ; 
        public Vertex p ;  // Đỉnh cha quản lý trong cây , hỗ trợ xây dựng duyệt bfs, 

        public int level ; // cấp bậc của đỉnh trong cây 

        public List<Vertex> adjecentVertices = new ArrayList<>(); 

        public void addAdjecentVertices ( Vertex v )  {
            adjecentVertices.add(v);
        }

        public Vertex ( int id ) {
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
