import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class EIBIRTHDAY {
    
    static StringBuilder sb = new StringBuilder() ; 
    public static void main(String[] args) {
    
        int nVertices = ni() ; 
        int nEdges = ni() ; 
        int day = ni() ; 
        int k = ni() ; 
        int end = day + k ; 
        if ( end > 365 ) {
            end = end - 365 ; 
        }
        Vertex[] graph = readGraph(nVertices , nEdges); 

        for (Vertex vertex : graph ) {
            int count = 0 ; 
            
            for (Vertex vertex2 : vertex.adjList ) {
                int date = vertex2.dayOfBirth ; 

                if ( end < day ) {
                    if ( !(date > end && date < day ) ) {
                        count++; 
                    } 
                } else {
                    if (date >= day && date <= end) {
                        count++ ; 
                    }
                }
            }
            sb.append(count + " "); 
        }
        System.out.println(sb);
    }

    public static Vertex[] readGraph(int nVertices , int nEdges ) {
        Vertex[] graph = new Vertex[nVertices ] ; 

        for (int i = 0 ; i < nVertices ; i++) {
            graph[i] = new Vertex(i);
        }

        for (int i = 0 ; i < nVertices ; i++ ) {
            graph[i].setDayOfBirth( ni() );
        }

        for (int i = 0 ; i < nEdges ; i++) {
            int a = ni() ; 
            int b = ni() ; 

            graph[a].addEdge(graph[b]);
            graph[b].addEdge(graph[a]);
        }
        return graph ; 
    }

    static class Vertex {
        int id ; 
        int dayOfBirth ; 
        boolean isVisited = false ; 
        List<Vertex> adjList = new ArrayList<>();
        
        public Vertex(int id) {
            this.id = id;
        } 
        
        public void setDayOfBirth ( int dayOfBirth ) {
            this.dayOfBirth = dayOfBirth ; 
        }

        public void addEdge(Vertex v) {
            adjList.add(v); 
        }
        
        public void Visited() {
            this.isVisited = true ; 
        }
        

    }

    static InputStream is = System.in;
    static byte[] inbuf = new byte[1 << 24];
    static int lenbuf = 0, ptrbuf = 0;

    static int readByte() {
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

    static boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    static int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b))
            ;
        return b;
    }

    static double nd() {
        return Double.parseDouble(ns());
    }

    static char nc() {
        return (char) skip();
    }

    static String ns() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    static char[] ns(int n) {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while (p < n && !(isSpaceChar(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    static int ni() {
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

    static long nl() {
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
