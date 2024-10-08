package Set3;
import java.io.*;
import java.util.*;
class CayThuMuc {
    static InputReader reader;
    static StringBuilder sb ;
    static String dash = "---";
    public static void main(String[] args) throws IOException {
         sb = new StringBuilder();
        reader = new InputReader(System.in);

        int n = reader.nextInt();
        HashMap<String, Vertex> vertices = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            String first = reader.next();
            String last = reader.next();

            if (!vertices.containsKey(first)) {
                vertices.put(first, new Vertex(first));
            }

            if (!vertices.containsKey(last)) {
                vertices.put(last, new Vertex(last));
            }

            Vertex u = vertices.get(first);
            Vertex v = vertices.get(last);

            u.addAdjacentVertex(v);
            v.addAdjacentVertex(u);
        }

        for (String key : vertices.keySet()) {
            vertices.get(key).adjacentVertices.sort((v1, v2) -> {
                return v1.id.compareToIgnoreCase(v2.id);
            });
        }

        String root =reader.next();
        String level = "-";

        dfs(vertices.get(root), level);

        System.out.println(sb);
    }

    static void dfs(Vertex vertex, String level) {
        vertex.visited = true;

        sb.append(level);
        sb.append(vertex.id);
        sb.append("\n");
        level = level.concat(dash);

        for (Vertex next : vertex.adjacentVertices) {
            if (!next.visited) {
                dfs(next, level);
            }
        }
    }

    static class Vertex {
        String id;
        List<Vertex> adjacentVertices = new ArrayList<>();
        boolean visited;

        public Vertex(String id) {
            this.id = id;
        }

        public void addAdjacentVertex(Vertex vertex) {
            if (adjacentVertices.contains(vertex)) {
                return;
            }

            this.adjacentVertices.add(vertex);
        }

        public int getDegree() {
            return adjacentVertices.size();
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
