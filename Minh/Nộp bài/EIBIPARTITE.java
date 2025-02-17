import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;

public class EIBIPARTITE {

    static InputReader rd ; 
    static StringBuilder sb = new StringBuilder() ; 
    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);

        int t = rd.nextInt(); 

        for ( int i = 0 ; i < t ; i++ ) {
            int nVertices = rd.nextInt(); 
            int nEdges = rd.nextInt(); 

            Vertex[] graph = readGraph(nVertices, nEdges);

            // Kiểm tra BIPARTITE

            boolean isBipartite = true ; // cho đại là true trước 
            for ( Vertex vertex : graph ) {     // Duyệt đỉnh đồ thị 
                if ( !vertex.visited ) {  // Nếu đỉnh chưa duyệt 
                    if ( ! bfs(vertex) ) { // Gọi bfs to check 
                        isBipartite = false ; 
                        break; 
                    }
                }
            }

            // In Answer
            if ( isBipartite ) {
                sb.append("Yes\n");
            } else {
                sb.append("No\n");
            }
        }
        System.out.println(sb);

    }

    static boolean bfs ( Vertex v  ) {

        Queue<Vertex> q = new ArrayDeque<>(); 
        
        v.color = 1 ; // gán vào đầu tiên là 1 
        q.add(v);
        v.visited = true ; 

        while ( ! q.isEmpty() ) {   // Duyệt đồ thị bằng BFS
            Vertex vertex = q.poll();       // Lấy 1 đỉnh ra khỏi hàng đợi 

            for ( Vertex vertex2 : vertex.adjecentVertices ) { // Duyệt đỉnh kề 

                if ( ! vertex2.visited ) {
                    vertex2.color = vertex.color * -1 ; // tô màu đối lập
                    vertex2.visited = true ; 

                    q.add(vertex2); // Đẩy vào hàng đợi 
                } else if ( vertex2.color == vertex.color ) { // Nếu 2 đỉnh cùng màu 
                    return false ;  // Khong phải thì false
                }
            }
        }
        return true ; // phải thì true
    }

    static Vertex[] readGraph( int nVertices , int nEdges )  {
        Vertex[] vertices = new Vertex[nVertices];

        for ( int i = 0 ; i < nVertices ; i++ ) {
            vertices[i] = new Vertex(i); // Mỗi đỉnh có 1 id tương ứng
        }

        for ( int i = 0 ; i < nEdges ; i++ ) {
            int u = rd.nextInt();
            int v = rd.nextInt(); 

            vertices[u].addAdjecentVertices(vertices[v]);
            vertices[v].addAdjecentVertices(vertices[u]);
        }

        return vertices ; 
    }

    static class Vertex {
        public int id ; 
        public boolean visited ; 
        
        public int color ; 

        public List<Vertex> adjecentVertices = new ArrayList<>(); 

        public Vertex (int id)  {
            this.id = id ; 
        }

        public void addAdjecentVertices ( Vertex v ) {
            adjecentVertices.add(v);
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
