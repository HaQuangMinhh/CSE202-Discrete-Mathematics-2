import java.util.ArrayList;
import java.util.List;

public class EICONP1 {


    public static void main(String[] args) {
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i, false);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }

        for (Vertex vertex : vertices) {

            if (!vertex.visited) {
                count = 1;
                sb.append(vertex.id).append(" ");
                dfs(vertex, vertices);
                sb.append(count).append("\n");
            }
        

        }
        System.out.println(sb);

    }

    static int count;

    static void dfs(Vertex v, Vertex[] vertices) {

        v.visited = true;

        for (Vertex vertex : vertices[v.id].adjacentList) {
            if (!vertex.visited) {
                count++;
                dfs(vertex, vertices);
            }
        }

    }

    static class Vertex {
        int id;
        boolean visited;
        List<Vertex> adjacentList = new ArrayList<>();

        public Vertex(int id, boolean visited) {
            this.id = id;
            this.visited = visited;
        }

        public void addNeighbor(Vertex v) {
            adjacentList.add(v);
        }

    }


}
