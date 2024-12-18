import java.io.*;
import java.util.*;
public class S3_EIFBF_FaceBookFriendsV1 {
    static StringBuilder sb = new StringBuilder();
    static InputReader reader;
    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        int n = reader.nextInt();
        int m = reader.nextInt();
        Vertex[] graph = readGraph(n, m);
        List<int[]> components = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!graph[i].visited) {
                int[] genderCount = new int[2];
                int representative = dfs(graph[i], genderCount);
                components.add(new int[] { representative, genderCount[0], genderCount[1] });
            }
        }
        components.sort((a, b) -> Integer.compare(a[0], b[0]));
        for (int[] c : components) {
            sb.append(c[0]).append(" ").append(c[1]).append(" ").append(c[2]).append("\n");
        }
        System.out.println(sb);

    }

    static int dfs(Vertex vertex, int[] countGender) {
        vertex.visited = true;
        if (vertex.gender.equals("Nam")) { 
            countGender[0]++;
        } else {
            countGender[1]++;
        }
        int highestId = vertex.id;
        // sb.append(vertex.id).append(" ");
        for (Vertex w : vertex.adacentVertices) {
            if (!w.visited) {
                highestId = Math.max(highestId, dfs(w, countGender));
            }
        }
        return highestId;
    }

    static Vertex[] readGraph(int nVertices, int nEdges) {
        Vertex[] vertices = new Vertex[nVertices + 1];
        for (int i = 1; i <= nVertices; i++) {
            vertices[i] = new Vertex(i, reader.next());
        }
        for (int i = 0; i < nEdges; i++) {
            int u = reader.nextInt();
            int v = reader.nextInt();
            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }
        return vertices;
    }

    static class Vertex {
        int id;
        String gender;
        boolean visited;
        List<Vertex> adacentVertices = new ArrayList<>();

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
        }

        public void addNeighbor(Vertex vertex) {
            adacentVertices.add(vertex);
        }

    }

    static class InputReader {
        private byte[] inbuf = new byte[2 << 23];
        public int lenbuf = 0, ptrbuf = 0;
        public InputStream is;

        public InputReader(InputStream stream) throws IOException {

            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = System.in;
            lenbuf = is.read(inbuf);
        }

        public InputReader(FileInputStream stream) throws IOException {
            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = stream;
            lenbuf = is.read(inbuf);
        }

        public boolean hasNext() throws IOException {
            if (skip() >= 0) {
                ptrbuf--;
                return true;
            }
            return false;
        }

        public String nextLine() throws IOException {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!isSpaceChar(b) && b != ' ') { // when nextLine, ()
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b
                                        // != ' ')
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

        private double nextDouble() {
            return Double.parseDouble(next());
        }

        public Character nextChar() {
            return skip() >= 0 ? (char) skip() : null;
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
