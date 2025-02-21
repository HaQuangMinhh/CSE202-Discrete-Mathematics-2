import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class EIFBF2 {

    static InputReader sc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = makeGraph(n, m);
        Set<Integer> connected = new HashSet<Integer>();
        for (Vertex i : vertices) {
            int malesCount = 0;
            int femaleCount = 0;
            connected = new HashSet<>();
            if (!i.isVisited) {
                bfs(i, connected);
                for (Integer j : connected) {
                    if (vertices[j].gender.equalsIgnoreCase("Nam")) {
                        malesCount++;
                    } else {
                        femaleCount++;
                    }
                }
                for (Integer j : connected) {
                    vertices[j].malesCount = malesCount;
                    vertices[j].femaleCount = femaleCount;
                }
            }
        }

        for (Vertex i : vertices) {
            sb.append(i.id + 1).append(" ").append(i.malesCount).append(" ").append(i.femaleCount).append("\n");
        }

        System.out.println(sb);
    }

    public static Vertex[] makeGraph(int n, int m) {
        Vertex[] listOfVertex = new Vertex[n];
        for (int i = 0; i < listOfVertex.length; i++) {
            String gender = sc.next();
            listOfVertex[i] = new Vertex(i, gender);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            listOfVertex[u].addNeighbor(listOfVertex[v]);
            listOfVertex[v].addNeighbor(listOfVertex[u]);
        }
        for (Vertex i : listOfVertex) {
            i.adjacentVertices.sort((v1, v2) -> Integer.compare(v1.id, v2.id));
        }
        return listOfVertex;
    }

    public static void dfs(Vertex v) {
        v.isVisited = true;
        sb.append(v.id).append(" ");
        for (Vertex i : v.adjacentVertices) {
            if (!i.isVisited) {
                dfs(i);
            }
        }

    }

    public static void bfs(Vertex v, Set<Integer> set) {
        Queue<Vertex> queue = new ArrayDeque<Vertex>();
        queue.add(v);
        v.isVisited = true;
        while (!queue.isEmpty()) {
            Vertex w = queue.poll();
            set.add(w.id);
            for (Vertex i : w.adjacentVertices) {
                if (!i.isVisited) {
                    queue.add(i);
                    i.isVisited = true;
                }
            }
        }
    }

    public static class Vertex {
        public int id;
        public boolean isVisited;
        public String gender;
        public int malesCount;
        public int femaleCount;
        public List<Vertex> adjacentVertices = new ArrayList<Vertex>();

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
            this.isVisited = false;
            if (this.gender.equalsIgnoreCase("Nam")) {
                this.malesCount = 1;
                this.femaleCount = 0;
            } else {
                this.malesCount = 0;
                this.femaleCount = 1;
            }
        }

        public void addNeighbor(Vertex vertex) {
            this.adjacentVertices.add(vertex);
        }

        public void countGender() {
            for (Vertex v : this.adjacentVertices) {
                if (v.gender.equalsIgnoreCase("Nam")) {
                    this.malesCount++;
                } else {
                    this.femaleCount++;
                }
            }
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
            while (!isSpaceChar(b) && b != ' ') {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) {
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
