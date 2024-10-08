package Set6;
import java.io.*;
import java.util.*;
class Main {
    static class Person {
        static Scanner rd;
        static StringBuilder sb = new StringBuilder();
        public static void main(String[] args) throws IOException {
            rd = new Scanner(System.in);
            Vertex[] graph = readGraph();

            int[] commissions = new int[graph.length];
            calculateCommissions(graph[0], commissions);

            for (int i = 0; i < graph.length; i++) {
                sb.append(i).append(" ").append(commissions[i]).append("\n");
            }

            System.out.print(sb);
        }

        static Vertex[] readGraph() {
            int nVertices = rd.nextInt();
            Vertex[] vertices = new Vertex[nVertices];
            for (int i = 0; i < nVertices; i++) {
                int revenue = rd.nextInt();
                vertices[i] = new Vertex(i, revenue);
            }
            for (int i = 0; i < nVertices - 1; i++) {
                int a = rd.nextInt();
                int b = rd.nextInt();
                vertices[a].addChild(vertices[b]);
            }
            return vertices;
        }
        static int calculateCommissions(Vertex vertex, int[] commissions) {
            int commission = (int) (vertex.revenue * 0.15);
            for (Vertex child : vertex.children) {
                int childCommission = calculateCommissions(child, commissions);
                commission += childCommission / 2;
            }
            commissions[vertex.id] = commission;
            return commission;
        }

        static class Vertex {
            public int id;
            public int revenue;
            public List<Vertex> children = new ArrayList<>();

            public Vertex(int id, int revenue) {
                this.id = id;
                this.revenue = revenue;
            }

            public void addChild(Vertex vertex) {
                children.add(vertex);
            }
        }

    }
}