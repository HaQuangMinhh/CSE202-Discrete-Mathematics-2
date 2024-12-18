import java.util.*;

public class Test {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] graph = readGraph(n, m);
        // List<int[]> components = new ArrayList<>();
        // for (int i = 1; i < graph.length; i++) {
        // if (!graph[i].visited) {
        // int[] countGender = new int[2];
        // int representative = dfs(graph[i], countGender);
        // components.add(new int[] { representative, countGender[0], countGender[1] });
        // }
        // }
        // components.sort((a, b) -> Integer.compare(a[0], b[0]));
        // for (int[] is : components) {
        // sb.append(is[0]).append(" ").append(is[1]).append("
        // ").append(is[2]).append("\n");
        // }
        for (int i = 1; i <= n; i++) {
            if (!graph[i].visited) {
                int[] countGender = new int[2];
                List<Vertex> components = new ArrayList<>();
                dfs(graph[i], countGender, components);
                for (Vertex vertex : components) {
                    vertex.countMale = countGender[0];
                    vertex.countFemale = countGender[1];
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            sb.append(graph[i].id).append(" ").append(graph[i].countMale).append(" ").append(graph[i].countFemale)
                    .append("\n");
        }
        System.out.println(sb);

    }

    static void dfs(Vertex vertex, int[] countGender, List<Vertex> components) {
        vertex.visited = true;
        components.add(vertex);
        if (vertex.gender.equals("Nam")) {
            countGender[0]++;
        } else {
            countGender[1]++;
        }
        for (Vertex w : vertex.adjacentVertices) {
            if (!w.visited) {
                dfs(w, countGender, components);
            }
        }
    }

    // static int dfs(Vertex vertex, int[] countGender) {
    // vertex.visited = true;
    // int highestId = vertex.id;
    // if (vertex.gender.equals("Nam")) {
    // countGender[0]++;
    // } else {
    // countGender[1]++;
    // }
    // for (Vertex w : vertex.adjacentVertices) {
    // if (!w.visited) {
    // highestId = Math.max(highestId, dfs(w, countGender));
    // }
    // }
    // return highestId;
    // }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i, sc.next());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }

        return vertices;
    }

    static class Vertex {
        int id;
        boolean visited;
        String gender;
        int countMale;
        int countFemale;
        List<Vertex> adjacentVertices = new ArrayList<>();

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
        }

        public void addNeighbor(Vertex vertex) {
            adjacentVertices.add(vertex);
        }

    }
}
