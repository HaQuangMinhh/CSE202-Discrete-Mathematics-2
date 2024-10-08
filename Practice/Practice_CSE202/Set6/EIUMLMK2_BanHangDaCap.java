package Set6;
import java.io.*;
import java.util.*;

public class EIUMLMK2_BanHangDaCap {
    static InputReader reader ;
    static StringBuilder sb ;
	public static void main(String[] args) throws IOException {
       sb = new StringBuilder();
        reader = new InputReader(System.in);
		int n = reader.nextInt();
		Vertex[] vertexs = new Vertex[n];
		for (int i = 0; i < n; i++) {
			vertexs[i] = new Vertex(i);
		}
		for (int i = 1; i < n; i++) {
			int u = reader.nextInt();
			int v = reader.nextInt();
			vertexs[u].addList(vertexs[v]);
			vertexs[v].addList(vertexs[u]);
		}
		for (Vertex v : vertexs) {
			v.maxMoney = reader.nextDouble();
		}
		vertexs[0].buy = reader.nextDouble();
		dfs(vertexs[0]);

		if (vertexs[0].buy > vertexs[0].maxMoney) {
			vertexs[0].people = 0;
		}

		dfs2(vertexs[0]);

		StringBuffer stringBuffer = new StringBuffer();
		for (Vertex vertex : vertexs) {
			stringBuffer.append(vertex.people + " ");
		}

		System.out.println(stringBuffer);
	}
	static void dfs(Vertex vertex) {
		vertex.visited = true;
		for (Vertex v : vertex.list) {
			if (!v.visited) {
				v.buy = Math.floor(vertex.buy + vertex.buy * 0.1);
				dfs(v);
				vertex.people += (v.people);
			}
		}
	}
	static void dfs2(Vertex vertex) {
		vertex.visited = false;

		for (Vertex v : vertex.list) {
			if (v.visited) {
				if (vertex.people == 0) {
					v.people = 0;
				} else {
					if (v.buy <= v.maxMoney) {
						vertex.people -= v.people;
					} else {
						v.people = 0;
					}
				}
				dfs2(v);
			}
		}
	}
    static class Vertex {
		int id;
		boolean visited;
		double maxMoney;
		List<Vertex> list = new ArrayList<>();
		double buy;
		int people = 1;
		boolean isbuy;

		public Vertex(int id) {
			this.id = id;
		}

		public void addList(Vertex p) {
			if (!list.contains(p)) {
				list.add(p);
			}
		}

		@Override
		public String toString() {
			return id + " " + people + " " + buy;
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