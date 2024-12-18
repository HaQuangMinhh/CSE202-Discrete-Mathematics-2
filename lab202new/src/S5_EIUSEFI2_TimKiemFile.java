import java.io.*;
import java.util.*;
public class S5_EIUSEFI2_TimKiemFile {
    static StringBuilder sb = new StringBuilder();
    static InputReader reader;

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        int n = reader.nextInt();
        List<Vertex> graph = readGraph(n);
        String rootName = reader.next();
        String keyWord = reader.next();
        for (Vertex vertex : graph) {
            if (vertex.file.equals(rootName)) {
                dfs(vertex, keyWord);
            }
        }
        System.out.println(sb);

    }

    static void dfs(Vertex vertex, String keyWord) {
        vertex.visited = true;
        for (Vertex w : vertex.adjacentVertices) {
            if (!w.visited) {
                dfs(w, keyWord);
                vertex.count += w.count;
                if (w.file.contains(keyWord) && w.adjacentVertices.size() == 1) {
                    vertex.count++;
                }
            }
        }
        if (vertex.count > 0) {
            sb.append(vertex.file).append(" ").append(vertex.count).append("\n");
        }
    }

    static List<Vertex> readGraph(int n) {
        HashMap<String, Vertex> hsTree = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            String a = reader.next();
            if (!hsTree.containsKey(a)) {
                Vertex A = new Vertex(a);
                hsTree.put(a, A);
            }
            String b = reader.next();
            if (!hsTree.containsKey(b)) {
                Vertex B = new Vertex(b);
                hsTree.put(b, B);
            }
            hsTree.get(a).addNeighbor(hsTree.get(b));
            hsTree.get(b).addNeighbor(hsTree.get(a));
        }
        List<Vertex> vertices = new ArrayList<Vertex>(hsTree.values());
        for (Vertex vertex : vertices) {
            vertex.adjacentVertices.sort((o1, o2) -> o1.file.compareToIgnoreCase(o2.file));
        }
        return vertices;
    }

    static class Vertex {
        String file;
        boolean visited;
        List<Vertex> adjacentVertices = new ArrayList<>();
        int count;

        public Vertex(String file) {
            this.file = file;
        }

        public void addNeighbor(Vertex v) {
            adjacentVertices.add(v);
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
