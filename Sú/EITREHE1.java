import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EITREHE1 {
    static StringBuilder sb = new StringBuilder();
    static InputReader rd = new InputReader(System.in);
    static int treeHeight = 0;
    public static void main(String[] args) {
        int n = rd.nextInt();
        Vertex[] vertices = readGraph(n, n - 1);
        dfs(vertices[0], 0);

        System.out.println(treeHeight);
    }

    public static void dfs(Vertex v, int level) {
        v.visited = true;
        treeHeight = Math.max(treeHeight, level);
        for (Vertex vertex : v.adjecVertices) {
            if (!vertex.visited) {
                dfs(vertex, level+1);
            }
        }
    }

    static class Vertex {
        int id;
        boolean visited = false;
        List<Vertex> adjecVertices = new ArrayList<Vertex>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addVertex(Vertex v) {
            adjecVertices.add(v);
        }

        public boolean checkExist(Vertex v) {
            boolean check = false;
            for (Vertex vertex : adjecVertices) {
                if (vertex.id == v.id) {
                    check = true;
                }
            }
            return check;
        }
    }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n];

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int v = rd.nextInt();
            int u = rd.nextInt();

            vertices[u].addVertex(vertices[v]);
            vertices[v].addVertex(vertices[u]);
        }

        return vertices;
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

        public InputReader(FileInputStream stream) {
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
