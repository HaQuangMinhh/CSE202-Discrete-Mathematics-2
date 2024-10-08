package Set7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class SampleMath2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Vertex[] vertices = readGraph(sc);

		printPrim(vertices);
	}
    private int findSpanningTreeUsing(Vertex[]graph){
        for(Vertex vertex:graph){
            vertex.cost=Integer.MAX_VALUE;
            vertex.visited=false;
        }
        //chon 1 dinh bta ki de xuat
        graph[0].cost=0;
        graph[0].previous=graph[0];

        int countVisites=graph.length;
        while (countVisites>0) {
            Vertex min=findUnvistedMinCout(graph);
            if(min==null)return -1;
            count--;

            for(Edge e: min.adjacentVertices){
                if(e.endpoint.cost>e.weight){
                    e.endpoint.cost=e.weight;
                    e.endpoint.previous=min;
                }
            }
        }

    }
    private Vertex findUnvistedMinCout(Vertex[]graph){
        int min=Integer.MAX_VALUE;
        int id=-1;
        for(Vertex vertex:graph){
            if(!vertex.visited){
                if(vertex.cost<min){
                    id=vertex.id;
                min=vertex.cost;
                }
            }
        }
        return graph[id];
    }

	private static void printPrim(Vertex[] vertices) {
		PriorityQueue<Vertex> q = new PriorityQueue<SampleMath2.Vertex>();
		vertices[0].cost = 0;
		for (Vertex v : vertices) {
			q.add(v);
		}
		int count = 0;
		int total = 0;
		while (count < vertices.length - 1 && q.size() > 0) {
			Vertex min = q.remove();
			if (min.visited)
				continue;
			count++;
			total += min.cost;
			min.visited = true;
			
			if (min.previous != null) {
				System.out.println(min.id + " " + min.previous.id + " " + min.cost);
			}

		
			for (Edge e : min.adjacentVertices) {
				if (e.endpoint.visited)
					continue;
				if (e.endpoint.previous == null || e.endpoint.cost > e.weight) {
					e.endpoint.cost = e.weight;
					e.endpoint.previous = min;
					
					q.add(e.endpoint);
				}
			}
		}

	}

//	static int dfs(Vertex vertex) {
//		int total = 0;
//		
//	}

	static Vertex[] readGraph(Scanner sc) {
		int n = sc.nextInt();
		Vertex[] vertices = new Vertex[n];
		for (int i = 0; i < n; i++) {
			vertices[i] = new Vertex(i);
		}

		int m = sc.nextInt();
		for (int i = 0; i < m; i++) {
			Vertex u = vertices[sc.nextInt()];
			Vertex v = vertices[sc.nextInt()];
			int w = sc.nextInt();
			u.addVertex(v, w);
			v.addVertex(u, w);
		}

		return vertices;
	}

	static class Vertex implements Comparable<Vertex> {
		public int id;
		public boolean visited = false;
		public int cost = Integer.MAX_VALUE;
		public Vertex previous = null;
		public List<Edge> adjacentVertices = new ArrayList<Edge>();
		// public HashMap<Integer, Vertex> adjacentVertices = new HashMap<Integer,
		// Vertex>();

		public Vertex(int id) {
			this.id = id;
		}

		public void addVertex(Vertex vertex, int w) {
			adjacentVertices.add(new Edge(vertex, w));
		}

		@Override
		public int compareTo(SampleMath2.Vertex o) {
			return this.cost - o.cost;
		}
	}

	static class Edge {
		public int weight;
		public Vertex endpoint;

		public Edge(Vertex endpoint, int w) {
			this.endpoint = endpoint;
			this.weight = w;
		}
	}
}
