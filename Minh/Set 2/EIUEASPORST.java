import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EIUEASPORST {
    
    static InputReader rd ; 
    static StringBuilder sb = new StringBuilder(); 
    static int count = 0 ; 
    static int temp = count ; 
    public static void main(String[] args) {
        rd = new InputReader(System.in);
		
        int n = rd.nextInt();
		Vertex[] vertices = new Vertex[n + 1];
		
        for (int i = 1; i <= n; i++) {
			vertices[i] = new Vertex(i);
		}
		for (int i = 1; i <= n; i++) {
			int left = rd.nextInt();
			
            vertices[i].left = left > 0 ? vertices[left] : null;
			int right = rd.nextInt();
			
            vertices[i].right = right > 0 ? vertices[right] : null;
		}
		
        PrintPostOrder(vertices[1]);
		
        System.out.println(sb);
    }

    static void PrintPostOrder ( Vertex v ) {
        if ( v.left != null ) {
            PrintPostOrder(v.left) ; 
        }

        if (v.right != null ) {
            PrintPostOrder(v.right) ; 
        }

        sb.append(v + " ");
        v.mark = true ; 
    }

    static class Vertex {
        int id ; 
        public Vertex left ; 
        public Vertex right ;
        boolean mark ; 
        
        public Vertex(int id) {
            this.id = id;
            mark = false ; 
        } 

        public String toString() {
            return id + " "; 
        }
    }


    static class InputReader {
        StringTokenizer tokenizer;
        BufferedReader reader;
        String token;
        String temp;

        public InputReader(InputStream stream) {
            tokenizer = null;
            reader = new BufferedReader(new InputStreamReader(stream));
        }

        public String nextLine() throws IOException {
            return reader.readLine();
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    if (temp != null) {
                        tokenizer = new StringTokenizer(temp);
                        temp = null;
                    } else {
                        tokenizer = new StringTokenizer(reader.readLine());
                    }
                } catch (IOException e) {
                }
            }
            return tokenizer.nextToken();
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }


}
