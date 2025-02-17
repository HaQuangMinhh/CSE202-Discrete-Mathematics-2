import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class EIBIPARTITE {
    static StringBuilder sb = new StringBuilder();
    static InputReader rd = new InputReader(System.in);
    static boolean check = true;

    public static void main(String[] args) {
        int t = rd.nextInt();
        for (int i = 0; i < t; i++) {
            int n = rd.nextInt();
            int m = rd.nextInt();

            Vertex[] vertices = readGraph(n, m);

            for(Vertex v : vertices) {
                if (!v.visited) {
                    v.group = 1;
                    // dfs(v);
                    bfs(v);
                }
                if (!check) {
                    break;
                }
            }

            if (check) {
                sb.append("Yes");
            } else {
                sb.append("No");
            }
            ;
            sb.append("\n");

            check = true;
        }

        System.out.println(sb);
    }

    public static void dfs(Vertex v) {
        v.visited = true;
        for (Vertex vertex : v.adjecVertices) {
            if (!vertex.visited) {
                if (v.group == 1) {
                    vertex.group = 2;
                } else {
                    vertex.group = 1;
                }
                dfs(vertex);
            } else {
                if (v.group == vertex.group) {
                    check = false;
                    break;
                }
            }
        }
    }

    static void bfs(Vertex v){
        Queue<Vertex> q = new ArrayDeque<>();

        v.distance = 0;
        q.add(v);
        v.visited = true;

        while (!q.isEmpty()) {
            Vertex current = q.poll();

            for (Vertex n : current.adjecVertices) {
                if (!n.visited) {
                    n.distance = current.distance + 1;
                    n.parent = current;
                    q.add(n);
                    n.visited = true;
                    if (current.group == 1) {
                        n.group = 2;
                    } else {
                        n.group = 1;
                    }
                } else {
                    if (current.group == n.group) {
                        check = false;
                        break;
                    }
                }
            }
        }
    }

    static class Vertex {
        int id;
        boolean visited = false;
        int group = 0;
        int distance = -1;
        Vertex parent;
        List<Vertex> adjecVertices = new ArrayList<Vertex>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addVertex(Vertex v) {
            adjecVertices.add(v);
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
