import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class EIUEASPOST {
    static StringBuilder sb = new StringBuilder();
    static InputReader rd = new InputReader(System.in);
    public static void main(String[] args) {
        int n = rd.nextInt();
        Node[] nodes = readTree(n);
        PrintPostOrder(nodes[1]);
        System.out.println(sb);
    }

    static void PrintPostOrder(Node root){
        if (root.left != null) {
            PrintPostOrder(root.left);
        }
        if (root.right!=null) {
            PrintPostOrder(root.right);
        }
        sb.append(root.id).append(" ");
    }

    public static class Node {
        public int id;
        public Node left;
        public Node right;

        public Node(int id) {
            this.id = id;
        }
    }

    public static Node[] readTree (int n) {
        Node[] nodes = new Node[n + 1];

        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 1; i < nodes.length; i++) {
            int left = rd.nextInt();
            int right = rd.nextInt();

            nodes[i].left = left > 0 ? nodes[left] : null;
            nodes[i].right = right > 0 ? nodes[right] : null;
        }

        return nodes;
    }

    static class InputReader {
        StringTokenizer tokenizer;
        BufferedReader reader;
        String token;
        String temp;

        public InputReader(InputStream stream) {
            tokenizer = null;
            reader = new BufferedReader(new InputStreamReader(stream));
        }

        public InputReader(FileInputStream stream) {
            tokenizer = null;
            reader = new BufferedReader(new InputStreamReader(stream));
        }

        public String nextLine() throws IOException {
            return reader.readLine();
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    if (temp != null) {
                        tokenizer = new StringTokenizer(temp);
                        temp = null;
                    } else {
                        tokenizer = new StringTokenizer(reader.readLine());
                    }
                } catch (IOException e) {
                }
            }
            return tokenizer.nextToken();
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
