package Set1;
import java.io.*;
import java.util.*;

class EIUSUFBF {
    static InputReader rd;

    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);
        int N = rd.nextInt();  
        int M = rd.nextInt(); 
        int L = rd.nextInt(); 

        Vertex[] students = readGraph(N, M);

        StringBuilder sb = new StringBuilder();
        for (Vertex student : students) {
            List<Vertex> suggestions = student.getFriendSuggestions(L);
            Collections.sort(suggestions, (v1, v2) -> Integer.compare(v1.id, v2.id)); 
            
            sb.append(student.id); 
            for (Vertex v : suggestions) {
                sb.append(" ").append(v.id); 
            }
            sb.append("\n"); 
        }
        System.out.print(sb.toString()); 
    }

    static Vertex[] readGraph(int N, int M) throws IOException {
        Vertex[] vertices = new Vertex[N];
        for (int i = 0; i < N; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < M; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();
            vertices[u].addAdjacentVertex(vertices[v]);
            vertices[v].addAdjacentVertex(vertices[u]);
        }

        return vertices;
    }

    static class Vertex {
        int id;
        List<Vertex> adjacentVertices = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }

        void addAdjacentVertex(Vertex v) {
            adjacentVertices.add(v);
        }

        List<Vertex> getFriendSuggestions(int minFriends) {
            List<Vertex> suggestions = new ArrayList<>();
            for (Vertex friend : adjacentVertices) {
                if (friend.adjacentVertices.size() < minFriends) {
                    suggestions.add(friend);
                }
            }
            return suggestions;
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
            while ((b = readByte()) != -1 && isSpaceChar(b));
            return b;
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
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
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
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
