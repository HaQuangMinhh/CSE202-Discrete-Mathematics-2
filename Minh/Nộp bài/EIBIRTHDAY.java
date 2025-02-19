import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EIBIRTHDAY {

    static InputReader rd ; 
    static StringBuilder sb = new StringBuilder() ; 
    public static void main(String[] args) throws IOException {
        rd = new InputReader (System.in); 

        int numPeople = rd.nextInt();  // n
        int relationships = rd.nextInt();   // m 
        int currentDate = rd.nextInt();  // d 
        int k = rd.nextInt(); // xem xét sinh nhật của các bạn bè trong vòng .... ngày      

        Vertex[] vertices = new Vertex[numPeople];  // 0 1 2 3 4 5  
        
        for ( int i = 0 ; i < numPeople ; i++ ) {
            vertices[i] = new Vertex(i, rd.nextInt()); 
        }

        for ( int i = 0 ; i < relationships ; i++ ) {
            int u = rd.nextInt(); 
            int v = rd.nextInt(); 

            vertices[u].addAdjecentVertices(vertices[v]);
            vertices[v].addAdjecentVertices(vertices[u]);
        }

        // Core
        for ( Vertex v : vertices ) {
            int count = 0 ; 
            for ( Vertex vertex : v.adjecentVertices ) {

                if ( currentDate + k > 365 ) {
                    
                    if ( vertex.birthday >= currentDate || vertex.birthday <=  ( currentDate +  k ) - 365  ) {
                        count++; 
                    }

                } else {
                    if ( currentDate <= vertex.birthday && vertex.birthday <= currentDate + k ) {
                        count++; 
                    }
                }
            }
            sb.append(count + " "); 
        }
        System.out.println(sb);
    }

    static class Vertex {
        public int id ; 
        public boolean visited ; 
        public int birthday ; 

        public List<Vertex> adjecentVertices = new ArrayList<>();

        public Vertex( int id , int birthday ) {
            this.id = id ; 
            this.birthday = birthday ; 
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
