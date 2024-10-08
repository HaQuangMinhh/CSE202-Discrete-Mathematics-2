import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class EIPRF_Treasures {
    
    static StringBuilder sb = new StringBuilder(); 
    public static void main(String[] args) {
        Vertex[] graph = readGraph( ni(), ni() ); 
        dfs(graph[0], "");
    }

    static void dfs (Vertex vertex , String tempString ) {
        tempString += vertex.id + " "; 
        
        vertex.visited(); 
        
        for ( Vertex v : vertex.adjacentVertices ) {
            if ( !v.isVisited ) {
                dfs( v , tempString ); 
        
            } else if ( v.id == 0 && tempString.length() >= 6 ) {
                System.out.println(tempString);
            }
        }
    }

    static Vertex[] readGraph(int nVertices , int nEdges) {
        Vertex[] vertices = new Vertex[nVertices]; 

        for (int i = 0 ; i < vertices.length ; i++) {
            vertices[i] = new Vertex(i);
        }

        // Edges
        for (int i = 0 ; i < nEdges ; i++ ) {
            int u = ni() ; 
            int v = ni() ; 

            vertices[u].addAdj(vertices[v]);
        }
        return vertices ; 

    }

    static class Vertex {
        int id ; 
        boolean isVisited ; 
        List<Vertex> adjacentVertices = new ArrayList<>() ;
        
        public Vertex(int id) {
            this.id = id;
            this.isVisited = false ; 
        } 

        public void addAdj (Vertex v ) {
            adjacentVertices.add(v); 
        }

        public void visited() {
            isVisited = true ; 
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
