package Set6;
import java.io.*;
import java.util.*;
 class Main {
    static InputReader rd;
    static StringBuilder sb = new StringBuilder();
    static Map<String, Vertex> vertexMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);
        readGraph();
        String rootName = rd.next();
        String keyword  = rd.next();
        Vertex root = vertexMap.get(rootName);
        dfs(root, keyword);
        System.out.println(sb);
    }
    static void readGraph() {
        int n = rd.nextInt();

        for (int i = 0; i < n - 1; i++) {
            String nVertices = rd.next();
            String nEdges = rd.next();

            if (!vertexMap.containsKey(nVertices)) {
                vertexMap.put(nVertices, new Vertex(nVertices));
            }
            if (!vertexMap.containsKey(nEdges)) {
                vertexMap.put(nEdges, new Vertex(nEdges));
            }
            vertexMap.get(nVertices).adjecentVertices.add(vertexMap.get(nEdges));
            vertexMap.get(nEdges).adjecentVertices.add(vertexMap.get(nVertices));
            //vertexMap.get(nEdges).parent = vertexMap.get(nVertices);
        }
        for (Vertex vertex : vertexMap.values()) {
            Collections.sort(vertex.adjecentVertices, (v1, v2) -> v1.name.compareTo(v2.name));
        }

    }

    static void dfs(Vertex v, String keyword) {
        v.visited = true;
        for (Vertex w : v.adjecentVertices) {
            if (!w.visited) {
                if (w.name.contains(keyword) && w.getSize() == 1) {
                    v.count++;
                }
                dfs(w, keyword);
                v.count += w.count;
            }
        }
        if (v.count > 0) {
            sb.append(v.name).append(" ").append(v.count).append("\n");
        }
    }

    static class Vertex {

        String name;
        int count = 0;
        boolean visited;
        
        List<Vertex> adjecentVertices = new ArrayList<>();

        public Vertex(String name) {
            this.name = name;

        }

        public int getSize() {
            return adjecentVertices.size();
        }

        public void addAdjecentVertex(Vertex vertex) {
            adjecentVertices.add(vertex);
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
