import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class EIKARY {
    


    public static void main(String[] args) {
        
        int n = ni() ; 
        int k = ni() ; 
        Vertex[] graph = readGraph(n); 

        if ( dfs(graph[0] , k + 1)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
    
    public static boolean dfs ( Vertex v , int k ) {
        v.visited = true ; 
        boolean flag = true ; 

        if ( v.id == 0 ) {
            if ( v.adjList.size() > k - 1 ) {
                return false ; 
            }
        } else if ( v.adjList.size() > k ) {
            return false ; 
        }

        for (Vertex adj : v.adjList ) {
            if ( adj.visited == false ) {
                flag = dfs(adj , k) ;

                if (flag == false ) {
                    return flag ; 
                }
            }
        }
        return flag ; 
    }

    public static Vertex[] readGraph (int n) {
        Vertex[] vertice = new Vertex[n];

        for (int i = 0 ; i < vertice.length ; i++ ) {
            vertice[i] = new Vertex(i);
        }

        for (int i = 0 ; i < n - 1 ; i++) {
            Vertex a = vertice[ ni() ]; 
            Vertex b = vertice[ ni() ]; 

            a.addAdj(b);
            b.addAdj(a);
        }
        return vertice ; 

    }

    static class Vertex {
        int id ; 
        boolean visited = false ; 

        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        } 

        public void addAdj ( Vertex v ) {
            this.adjList.add(v);
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
