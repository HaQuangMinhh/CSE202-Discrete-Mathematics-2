import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class EICHTTRE_tree {
    
    static int NumVertices = 0 ; 
    static int NumEdges = 0 ; 
    static StringBuilder sb = new StringBuilder(); 
    public static void main(String[] args) throws IOException {
        int tc = ni() ; 
        while ( tc --> 0 ) {
            int nVertices = ni() ; 
            int nEdges = ni() ; 

            Vertex[] graph = readGraph(nVertices, nEdges); 

            if ( !(nVertices - 1 == nEdges)  ) {
                sb.append("NO\n" );
                continue ; 
            } 

            dfs(graph[0]);
            if ( isConnected(graph) ) {
                sb.append("YES\n"); 
            } else  {
                sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }

    static void dfs (Vertex v) {
        v.visited() ; 
        for (Vertex vertex : v.adjList ) {
            if (vertex.isVisited == false ) {
                dfs(vertex);
            }
        }
    }

    static boolean isConnected ( Vertex[] graph ) {
        
        for (Vertex vertex : graph ){
            if ( vertex.isVisited == false ) {
                return false ; 
            }
        }
        return true ; 
    }
    public static Vertex[] readGraph( int nVertices, int nEdges) {
        
        Vertex[] graph = new Vertex[nVertices]; 

        for (int i = 0 ; i < nVertices ; i++) {
            graph[i] = new Vertex(i);
        }

        for (int i = 0 ; i < nEdges ; i++) {
            int a = ni() ; 
            int b = ni() ; 

            graph[a].addAdjecentVertex(graph[b]);
            graph[b].addAdjecentVertex(graph[a]);
        }
        return graph ; 

    }

    static class Vertex {
        public int id ; 
        public boolean isVisited = false ; 

        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id ; 
        }

        void addAdjecentVertex ( Vertex v ) {
            adjList.add(v);
        }

        public void visited() {
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
