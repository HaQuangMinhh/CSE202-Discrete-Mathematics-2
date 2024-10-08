package Set7;

import java.util.*;

class EIMINDISTA {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // Number of vertices
        int m = scanner.nextInt(); // Number of edges

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w)); // Since the graph is undirected
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> dist[a] - dist[b]);
        pq.add(0);

        while (!pq.isEmpty()) {
            int u = pq.poll();
            for (Edge e : graph.get(u)) {
                int v = e.to;
                int w = e.weight;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(v);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (dist[i] == INF || dist[i] == 0) { // Added condition to skip printing 0
                System.out.print("-1 ");
            } else {
                System.out.print(dist[i] + " ");
            }
        }
    }

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
