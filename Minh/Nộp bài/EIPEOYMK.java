import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;

public class EIPEOYMK {

    static InputReader rd;
    static StringBuilder sb = new StringBuilder();
    static HashMap < Integer, List<Vertex> > hsMap = new HashMap<>();  // Save danh sách các đỉnh theo cấp độ level 
                // Key : Integer ( level ) , Value : List<Vertex> : danh sách các đỉnh có cùng cấp độ 
                // cái hsMap là cách u một bước : f( 1, u ) --> nằm trong hsMap.get(1)
                //              cách u hai bước : f( 2, u ) --> nằm trong hsMap.get(2);


    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);
        
        int nVertices = rd.nextInt(); 
        int nEdges = rd.nextInt(); 

        Vertex[] graph = readGraph(nVertices, nEdges);

        // Đọc node ( nút ) , và truy vấn
        int node = rd.nextInt(); 
        int query = rd.nextInt(); 

        // Gọi hàm bfs 
        bfs(graph[node]);

        for ( int i = 0 ; i < query ; i++ ) {  // chạy query 
            int ki = rd.nextInt();
            List<Vertex> list = hsMap.get(ki); 
            
            if ( list == null ) {
                sb.append("-1").append("\n");
            } else {
                list.sort( (v1,v2) -> v1.id - v2.id );  // sort tăng dần 

                for ( Vertex vertex : list ) {
                    sb.append(vertex.id).append(" ");
                }
                sb.append("\n");
            }

        }
        
        System.out.println(sb);


    }

    static void bfs ( Vertex v) {

        Queue<Vertex> q = new ArrayDeque<>(); 
        q.add(v);
        v.visited = true;
        v.level = 0 ; // đặt level = 0 cho thằng đầu tiên. 
        
        while ( ! q.isEmpty() ) {
            Vertex vertex = q.poll(); 

            for ( Vertex adj : vertex.adjecentVertices ) {
                if ( ! adj.visited ) {
                    adj.level = vertex.level + 1 ; // đặt level + 1 cho vertex

                    // Lưu danh sách đỉnh có cùng cấp độ ( level ) từ đỉnh gốc u vào 1 cái HashMap
                    List<Vertex> list = hsMap.get( adj.level );
                    
                    if ( list == null ) { // check trong list có contain 
                        list = new ArrayList<>();  // nếu chưa có danh sách thì create
                        hsMap.put( adj.level , list) ; // thêm danh sách của adj vào list 
                    }
                    list.add(adj); // thêm đỉnh vào list
                    adj.visited = true ; 
                    q.add(adj);

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
            vertices[v].addAdjecentVertices(vertices[u]);
        }

        for ( Vertex v : vertices ) {
            Collections.sort(v.adjecentVertices , (v1,v2) -> v1.id - v2.id);
        }

        return vertices; 

    }

    static class Vertex {
        public int id ; 
        public boolean visited ; 
        public int level ; 

        public List<Vertex> adjecentVertices = new ArrayList<>(); 

        public void addAdjecentVertices (Vertex v ) {
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
