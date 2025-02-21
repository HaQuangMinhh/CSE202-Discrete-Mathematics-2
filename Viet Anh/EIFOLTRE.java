import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EIFOLTRE {


    public static void main(String[] args) {
        HashMap<String, Vertex> vertices = new HashMap<>();

        int n = sc.nextInt();

        for (int i = 0; i < n - 1; i++) {
            String u = sc.next();
            String v = sc.next();

            Vertex vertexU = vertices.get(u);
            Vertex vertexV = vertices.get(v);

            if (vertexU == null) {
                vertexU = new Vertex(u);
                vertices.put(u, vertexU);
            }
            if (vertexV == null) {
                vertexV = new Vertex(v);
                vertices.put(v, vertexV);
            }

            vertexU.addNeighbor(vertexV);
            vertexV.addNeighbor(vertexU);

        }
        for (Map.Entry<String, Vertex> entry : vertices.entrySet()) {
            entry.getValue().adj.sort((s1, s2) -> {
                return s1.id.compareToIgnoreCase(s2.id);
            });
        }

        dfs(vertices.get(sc.next()), 0);
        System.out.println(sb);
    }

    static void dfs(Vertex v, int level) {
        v.visited = true;
        sb.append("-");
        for (int i = 0; i < level; i++) {
            sb.append("---");
        }
        sb.append(v.id).append("\n");

        for (Vertex vertex : v.adj) {
            if (!vertex.visited) {
                dfs(vertex, level + 1);
            }
        }

    }

    static class Vertex {

        boolean visited;
        String id;
        List<Vertex> adj = new ArrayList<>();

        public Vertex(String id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            adj.add(v);
        }

    }

}
