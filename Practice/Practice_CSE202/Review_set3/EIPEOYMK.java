package Review_set3;

import java.io.*;
import java.util.*;

public class EIPEOYMK {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        int nVertices=sc.nextInt();
        int nEdges=sc.nextInt();

        Vertex[]graph=readGraph(nVertices, nEdges);
        Vertex u=graph[sc.nextInt()];

        bsf(u);

        HashMap<Integer,ArrayList<Vertex>>map=new HashMap<Integer,ArrayList<Vertex>>();
        for(int k=0;k<nVertices;k++){
            ArrayList<Vertex>list=map.getOrDefault(graph[k].level,null);
            if(list==null){
                list=new ArrayList<Vertex>();
            }
            list.add(graph[k]);
            map.put(graph[k].level, list);
        }
        int q=sc.nextInt();
        for(int i=0;i<q;i++){
            int test=sc.nextInt();
            if(map.getOrDefault(test, null)!=null){
                for(Vertex w:map.get(test)){
                    sb.append(w.id).append(" ");
                }
                sb.append("\n");
            }else{
                sb.append("-1").append("\n");
            }
        }
        System.out.println(sb);
    }

    static void bsf(Vertex v) {
        Queue<Vertex> q = new ArrayDeque<Vertex>();
        q.add(v);
        v.level = 0;
        v.visited = true;

        while (!q.isEmpty()) {
            Vertex vertex = q.poll();
            for (Vertex w : vertex.adjacentVertices) {
                if (!w.visited) {
                    w.visited = true;
                    w.level = vertex.level + 1;
                    q.add(w);
                }
            }
        }
    }

    static Vertex[] readGraph(int nVertice, int nEdges) {

        Vertex[] vertices = new Vertex[nVertice];
        for (int i = 0; i < nVertice; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < nEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjacentVertices(vertices[v]);
            vertices[v].addAdjacentVertices(vertices[u]);
        }
        for (int j = 0; j < nVertice; j++) {
            vertices[j].adjacentVertices.sort((a, b) -> a.id - b.id);
        }
        return vertices;
    }

    static class Vertex {
        public int id;
        public boolean visited;
        public int level;
        public List<Vertex> adjacentVertices = new ArrayList<Vertex>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjacentVertices(Vertex v) {
            adjacentVertices.add(v);
        }
    }
}
