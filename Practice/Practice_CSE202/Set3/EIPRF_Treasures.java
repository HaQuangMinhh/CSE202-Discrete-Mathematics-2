package Set3;
import java.io.*;
import java.util.*;
public class EIPRF_Treasures {
    static InputReader reader;
    static HashMap<Integer, String> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        StringBuilder sb = new StringBuilder();

        // int nPlaces = reader.nextInt();
        // int nSignboards  = reader.nextInt();
        int nPlaces = reader.nextInt();
		int nSignboards =reader.nextInt();
		
		Vertex[] graph = readGraph(nPlaces, nSignboards);
		
		dfs(graph[0], 0, "");
		
		for (var k : map.values()) {
			sb.append(k);
		}
		System.out.println(sb);
	}
	
	static Vertex[] readGraph(int nPlaces, int nSignboards) {
		Vertex[] vertices = new Vertex[nPlaces];

	    	for (int i = 0; i < nPlaces; i++) {
			vertices[i] = new Vertex(i);
		}

		for (int i = 0; i < nSignboards; i++) {
			int a = reader.nextInt();
			int b = reader.nextInt();
			
			vertices[a].addAdjecentVertices(vertices[b]);
		}

		for (int i = 0; i < vertices.length; i++) {
			vertices[i].adjecentVertices.sort((s1, s2) -> {
				return s1.id - s2.id;
			});
		}
		return vertices;
	}
	
	
	static void dfs(Vertex v, int count, String cycle) {
		v.visited = true;
		count ++;
		cycle += v.id + " ";
		
		for (Vertex w : v.adjecentVertices) {
			if (w.visited == false) {
				dfs(w, count, cycle);
			}
			if (count >= 3 && w.id == 0) {
				map.put(count, cycle);
				break;
			}
		}
	}
	
	static class Vertex {
		public int id;
		public List<Vertex> adjecentVertices = new ArrayList<>();
		public boolean visited;

		public Vertex(int id) {

			this.id = id;
		}

		public void addAdjecentVertices(Vertex vertex) {
			adjecentVertices.add(vertex);
		}

		public int getDegree() {
			return adjecentVertices.size();
		}

		@Override
		public String toString() {
			return id + " ";
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

