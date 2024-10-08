package Set6;
import java.io.*;
import java.util.*;
 class Main {
	static InputReader rd;
	static StringBuilder sb = new StringBuilder(); 
	public static void main(String[] args) throws IOException {
		rd = new InputReader(System.in);
		int n = rd.nextInt(); 
		int m = rd.nextInt(); 

		Vertex[] units = new Vertex[n];
		for (int i = 0; i < n; i++) {
			units[i] = new Vertex(i);
		}
	
		for (int i = 0; i < n - 1; i++) {
			int a = rd.nextInt();
			int b = rd.nextInt();
			units[a].adj.add(units[b]);
		}
	
		while (rd.hasNext()) {
			int unitId = rd.nextInt();
			int trees = rd.nextInt();
			units[unitId].treesToPlant = trees;
		}
	
		dfs(units[m]);

		// Output the results sorted by administrative unit number
		Arrays.sort(units, Comparator.comparingInt(u -> u.id));
		for (Vertex unit : units) {
			System.out.println(unit.id + " " + unit.treesToPlant);
		}
	}

	
	static void dfs(Vertex unit) {
		for (Vertex subunit : unit.adj) {
			dfs(subunit);
			unit.treesToPlant += subunit.treesToPlant; 
		}
	}

	static class Vertex {
		int id;
		List<Vertex> adj;
		int treesToPlant;

		public Vertex(int id) {
			this.id = id;
			this.adj = new ArrayList<>();
			this.treesToPlant = 0;
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