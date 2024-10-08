package Set2;

import java.io.*;
import java.util.*;

class EIFBF2 {
    static InputReader rd;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);
        sb = new StringBuilder();

        int nVertices = rd.nextInt();
        int nEdges = rd.nextInt();

        Vertex[] graph = readGraph(nVertices, nEdges);

        boolean[] visited = new boolean[nVertices + 1];
        int[] componentId = new int[nVertices + 1];
        List<ConnectedComponent> components = new ArrayList<>();
        int componentCount = 0;

        for (int i = 1; i <= nVertices; i++) {
            if (!visited[i]) {
                ConnectedComponent cc = new ConnectedComponent();
                dfs(i, graph, cc, visited, componentId, componentCount);
                components.add(cc);
                componentCount++;
            }
        }

        for (int i = 1; i <= nVertices; i++) {
            ConnectedComponent cc = components.get(componentId[i]);
            sb.append(i).append(" ").append(cc.maleCount).append(" ").append(cc.femaleCount).append("\n");
        }

        System.out.print(sb.toString());
    }

    static Vertex[] readGraph(int nVertices, int nEdges) throws IOException {
        Vertex[] graph = new Vertex[nVertices + 1];
        for (int i = 1; i <= nVertices; i++) {
            String gender = rd.next();
            graph[i] = new Vertex(i, gender);
        }

        for (int i = 0; i < nEdges; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();
            graph[u].addAdjacentVertex(graph[v]);
            graph[v].addAdjacentVertex(graph[u]);
        }

        return graph;
    }

    static void dfs(int vertex, Vertex[] graph, ConnectedComponent cc, boolean[] visited, int[] componentId, int componentCount) {
        visited[vertex] = true;
        componentId[vertex] = componentCount;
        if (graph[vertex].gender.equals("Nam")) {
            cc.maleCount++;
        } else {
            cc.femaleCount++;
        }
        for (Vertex neighbor : graph[vertex].adjacentVertices) {
            if (!visited[neighbor.id]) {
                dfs(neighbor.id, graph, cc, visited, componentId, componentCount);
            }
        }
    }

    static class Vertex {
        int id;
        String gender;
        List<Vertex> adjacentVertices = new ArrayList<>();

        Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
        }

        void addAdjacentVertex(Vertex v) {
            if (!adjacentVertices.contains(v)) {
                adjacentVertices.add(v);
            }
        }
    }

    static class ConnectedComponent {
        int maleCount = 0;
        int femaleCount = 0;
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String next() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}
