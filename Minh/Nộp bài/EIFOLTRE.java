import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class EIFOLTRE {

    static InputReader rd ; 
    static StringBuilder sb = new StringBuilder() ; 
    public static void main(String[] args) throws IOException {
        rd = new InputReader (System.in); 
        
        Map<String , Vertex> graph = readGraph(); 
        
        dfs( graph.get(rd.next()) , 0 );

        System.out.println(sb);
    }

    static void dfs ( Vertex v , int rank ) {
        v.visited = true ; 

        sb.append("-");
        for ( int i = 0 ; i < rank ; i++ ) {
            sb.append("---");
        }
        sb.append(v.name + "\n"); 
        
        v.setLevel(rank);
        for ( Vertex vertex : v.adjecentVertices ) {
            if ( ! vertex.visited ) {
                dfs( vertex , rank + 1);
            }
        }
    }

    static HashMap<String, Vertex>  readGraph() {

        HashMap<String, Vertex> vertices = new HashMap<>(); 

        int nVertices = rd.nextInt();   // 6
        
        for ( int i = 0 ; i < nVertices - 1 ; i++ ) {
            String name1 = rd.next(); 
            String name2 = rd.next(); 

            // Create đối tượng cho từng Đỉnh . kiểm tra tồn tại 
            Vertex a = vertices.getOrDefault(name1, new Vertex(name1 )); 
            Vertex b = vertices.getOrDefault(name2, new Vertex(name2 ));
            
            // Thêm các đỉnh vào nhau 
            a.addAdjecentVertices(b);
            b.addAdjecentVertices(a);

            // Update đồ thị
            vertices.put(name1, a);
            vertices.put(name2, b);
        }
        return vertices ; 
    }

    static class Vertex {
        public String name ; 
        public int level ; 
        public boolean visited ; 

        public Set<Vertex> adjecentVertices = new TreeSet<>( (v1,v2) -> {
            return v1.name.compareToIgnoreCase(v2.name); 
        }); 

        public Vertex ( String name  ) {
            this.name = name ; 
            
        }

        public void addAdjecentVertices ( Vertex v ) {
            adjecentVertices.add(v);
     
        } 

        public int getLevel() {
            return level;
        }
        public void setLevel(int level) {
            this.level = level;
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
