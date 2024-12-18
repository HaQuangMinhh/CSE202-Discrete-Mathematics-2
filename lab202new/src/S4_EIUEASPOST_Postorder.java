import java.io.*;
import java.util.*;

public class S4_EIUEASPOST_Postorder {
    static InputReader sc = new InputReader(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nNode = sc.nextInt();
        Node[] nodes = ReadTree(nNode);
        List<Integer> list = new ArrayList<>();
        PrintPostOrder(nodes[0], list);
        for (Integer integer : list) {
            sb.append(integer + " ");
        }
        System.out.println(sb);
    }

    static void PrintPostOrder(Node node, List<Integer> postOrder) {
        if (node == null) {
            return;
        } else {
            PrintPostOrder(node.Left, postOrder);
            PrintPostOrder(node.Right, postOrder);
            postOrder.add(node.Id);
        }
    }

    static Node[] ReadTree(int nNode) {
        Node[] nodes = new Node[nNode];
        for (int i = 0; i < nNode; i++) {
            nodes[i] = new Node(i + 1);
        }
        for (int i = 0; i < nNode; i++) {
            int leftIndex = sc.nextInt();
            nodes[i].Left = leftIndex > 0 ? nodes[leftIndex - 1] : null;
            int rightIndex = sc.nextInt();
            nodes[i].Right = rightIndex > 0 ? nodes[rightIndex - 1] : null;
        }

        return nodes;
    }

    static class Node {
        public int Id;
        public Node Left;
        public Node Right;

        public Node(int id) {
            this.Id = id;
        }

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
