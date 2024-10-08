package Set4;
import java.util.*;
import java.io.*;
public class EIBIRTHDAY {
	static StringBuffer sb = new StringBuffer();
	static InputReader rd;
	public static void main(String[] args) throws IOException {
		rd = new InputReader(System.in);
		int nVertices = rd.nextInt();
		int nEdges = rd.nextInt();

		int currentDay = rd.nextInt();
		int k = rd.nextInt();

		Vertex[] graph = readGraph(nVertices, nEdges);

		for (int i = 0; i < nVertices; i++) {
			if (!graph[i].visited)
				bfs(graph[i], currentDay, currentDay + k);
		}

		for (int i = 0; i < nVertices; i++) {
			sb.append(graph[i].gift).append(" ");
		}

		System.out.println(sb);
	}

	static Vertex[] readGraph(int nVertices, int nEdges) {
		Vertex[] v = new Vertex[nVertices];
		for (int i = 0; i < nVertices; i++) {
			v[i] = new Vertex(i);
			v[i].birth = rd.nextInt();
		}

		for (int i = 0; i < nEdges; i++) {
			int a = rd.nextInt();
			int b = rd.nextInt();

			v[a].addAdjecentVertex(v[b]);
			v[b].addAdjecentVertex(v[a]);
		}

		return v;
	}

	static void bfs(Vertex v, int current, int k) {
		Queue<Vertex> q = new ArrayDeque<>();
		q.add(v);
		v.visited = true;

		while (!q.isEmpty()) {
			Vertex temVertex = q.poll();
			for (Vertex adj : temVertex.adjencentVertices) {
				if (!adj.visited) {
					adj.visited = true;
					q.add(adj);
				}
				if ((adj.birth >= current && adj.birth <= k) || (k > 365 && adj.birth <= k - 365)) {
					temVertex.gift++;
				}
			}
		}

	}

	static class Vertex {
		public int id;
		public boolean visited = false;
		public int birth;
		public int gift = 0;
		public List<Vertex> adjencentVertices = new ArrayList<Vertex>();

		public Vertex(int id) {
			this.id = id;
		}

		public void addAdjecentVertex(Vertex v) {
			adjencentVertices.add(v);
		}

		public int getDegree() {
			return adjencentVertices.size();
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
