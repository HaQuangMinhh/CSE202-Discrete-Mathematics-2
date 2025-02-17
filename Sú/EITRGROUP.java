import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EITRGROUP {
    static StringBuilder sb = new StringBuilder();
    static InputReader rd = new InputReader(System.in);
    static int treeHeight = 0;

    public static void main(String[] args) {
        int n = rd.nextInt();
        int m = rd.nextInt();
        Vertex[] vertices = readGraph(n, m);

        Vertex boss = vertices[0];
        for (Vertex vertex : vertices) {
            if (vertex.isBoss == 1) {
                boss = vertex;
                break;
            }
        }

        dfs(boss, 0);
        System.out.println(treeHeight + 1);
    }

    static void dfs(Vertex v, int level){
        v.visited = true;
        treeHeight = Math.max(treeHeight, level);
        for (Vertex vertex : v.adjacentVertex) {
            if (!vertex.visited) {
                dfs(vertex, level + 1);
            }
        }
    }

    static class Vertex{
        int id;
        boolean visited = false;
        int isBoss = -1;
        List<Vertex> adjacentVertex = new ArrayList<Vertex>();

        public Vertex(int id){
            this.id = id;
        }

        public void addVertex(Vertex v){
            adjacentVertex.add(v);
        }
    }

    public static Vertex[] readGraph (int n, int m) {
        Vertex[] vertices = new Vertex[n];

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();
            vertices[u].addVertex(vertices[v]);
            vertices[v].isBoss = 0;
            if (vertices[u].isBoss == -1) {
                vertices[u].isBoss = 1;
            }
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
