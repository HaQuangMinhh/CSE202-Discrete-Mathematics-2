package Set5;

import java.io.*;
import java.util.*;

class Main {
    static InputReader rd;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);
        int n = rd.nextInt();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();
            int w = rd.nextInt();
            adj.get(u).add(new Edge(v, w));
            adj.get(v).add(new Edge(u, w));
        }

        int[] result1 = bfs(0, adj);
        int farthestNode = result1[0];

        int[] result2 = bfs(farthestNode, adj);
        int maxLength = result2[1];

        int farthestNodeWithMinID = result2[0];
        System.out.println(farthestNodeWithMinID + " " + maxLength);
    }

    private static int[] bfs(int start, List<List<Edge>> adj) {
        int n = adj.size();
        int[] distances = new int[n];
        Arrays.fill(distances, -1);
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        distances[start] = 0;

        int farthestNode = start;
        int maxDistance = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            int currentDistance = distances[node];

            for (Edge edge : adj.get(node)) {
                if (distances[edge.node] == -1) {
                    distances[edge.node] = currentDistance + edge.weight;
                    queue.add(edge.node);

                    if (distances[edge.node] > maxDistance) {
                        maxDistance = distances[edge.node];
                        farthestNode = edge.node;
                    } else if (distances[edge.node] == maxDistance) {
                        farthestNode = Math.min(farthestNode, edge.node);
                    }
                }
            }
        }

        return new int[] { farthestNode, maxDistance };
    }

    static class Edge {
        int node, weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static class InputReader {
        private byte[] inbuf = new byte[1 << 23];
        public int lenbuf = 0, ptrbuf = 0;
        public InputStream is;

        public InputReader(InputStream stream) throws IOException {
            is = stream;
            lenbuf = is.read(inbuf);
        }

        public boolean hasNext() throws IOException {
            return ptrbuf < lenbuf;
        }

        public String nextLine() throws IOException {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!isSpaceChar(b) && b != ' ') {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!isSpaceChar(b)) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        private int readByte() {
            if (lenbuf == -1)
                throw new InputMismatchException();
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0)
                    return -1;
            }
            return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        private int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b))
                ;
            return b;
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

        public long nextLong() {
            long num = 0;
            int b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }
    }
}
