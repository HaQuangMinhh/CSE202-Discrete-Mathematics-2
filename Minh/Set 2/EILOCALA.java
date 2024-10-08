import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class EILOCALA {
    

    static StringBuilder sb = new StringBuilder(); 
    static int maxWeight = Integer.MIN_VALUE ; 
    static int minV = Integer.MAX_VALUE ; 
    static Vertex highestVertex = null ; 

    public static void main(String[] args) {
        
        Vertex[] graph = readGraph() ; 
        
        dfs( graph[ graph.length - 1 ], 0 );

        int save1 = minV ; 
        for (Vertex vertex : graph ) {
            vertex.visited = false ; 
        }

        dfs(highestVertex , 0 ) ; 
        int save2 = minV ; 
        System.out.println( Math.min( save1 , save2 ) + " " + maxWeight );

    }
    public static void dfs ( Vertex v , int weight ) {
        v.visit(); 

        for ( Edge edge : v.adjList ) {
            Vertex v1 = edge.next ; 

            if ( v1.visited == false ) {
                if ( edge.weight + weight > maxWeight ) {
                    maxWeight = edge.weight + weight ; 
                    minV = v1.id ; 
                    highestVertex = v1 ; 
                } else if ( edge.weight + weight == maxWeight && v1.id < minV ) {
                    minV = v1.id ; 
                }
                dfs(v1, weight + edge.weight);
            }
        }

    }

    public static Vertex[] readGraph() {
        int numV = ni() ; 
        int numE = numV - 1 ; 
        Vertex[] graph = new Vertex[numV]; 

        for (int i = 0 ; i < graph.length ; i++) {
            graph[i] = new Vertex(i);
        }

        for (int i = 0 ; i < numE ; i++) {
            Vertex a = graph[ ni() ]; 
            Vertex b = graph[ ni() ]; 
            int weight = ni() ; 

            Edge e1 = new Edge( weight, b ); 
            Edge e2 = new Edge( weight, a ); 

            a.addAdj( e1 ); 
            b.addAdj( e2 );
        }
        return graph ; 

    }

    static class Edge {
        int weight ; 
        Vertex next ;
        
        public Edge( int weight , Vertex node ) {
            this.weight = weight;
            this.next = node ; 
        } 

    }

    static class Vertex {
        int id ; 
        boolean visited ; 
        List<Edge> adjList = new ArrayList<>();
        
        public Vertex(int id) {
            this.id = id;
            this.visited = false ; 
        } 

        public void visit() {
            this.visited = true ; 
        }

        public void addAdj ( Edge b ) {
            this.adjList.add(b); 
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
