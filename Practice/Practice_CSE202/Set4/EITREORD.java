package Set4;
import java.io.*;
import java.util.*;
    class EIUEASPORD {
        static InputReader rd;
        static StringBuilder sb = new StringBuilder();
        public static void main(String[] args) throws IOException {
            rd = new InputReader(System.in);
    
            int n = rd.nextInt();  //  nodes
            int[] preorder = new int[n];
            int[] inorder = new int[n];
            
            for (int i = 0; i < n; i++) {
                preorder[i] = rd.nextInt();
            }
            for (int i = 0; i < n; i++) {
                inorder[i] = rd.nextInt();
            }
    
            TreeNode root = buildTree(preorder, inorder);
            printPostOrder(root);
            System.out.println(sb.toString());
        }
    
        static TreeNode buildTree(int[] preorder, int[] inorder) {
            Map<Integer, Integer> inorderMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                inorderMap.put(inorder[i], i);
            }
            return buildTreeRecursive(preorder, inorderMap, new int[]{0}, 0, inorder.length - 1);
        }
    
        static TreeNode buildTreeRecursive(int[] preorder, Map<Integer, Integer> inorderMap, int[] preIndex, int inStart, int inEnd) {
            if (inStart > inEnd) return null;
    
            TreeNode root = new TreeNode(preorder[preIndex[0]++]);
    
            if (inStart == inEnd) return root;
    
            int inIndex = inorderMap.get(root.id);
    
            root.left = buildTreeRecursive(preorder, inorderMap, preIndex, inStart, inIndex - 1);
            root.right = buildTreeRecursive(preorder, inorderMap, preIndex, inIndex + 1, inEnd);
    
            return root;
        }
    
        static void printPostOrder(TreeNode node) {
            if (node == null) return;
            printPostOrder(node.left);
            printPostOrder(node.right);
            sb.append(node.id).append(" ");
        }
    
        static class TreeNode {
            int id;
            TreeNode left;
            TreeNode right;
    
            TreeNode(int id) {
                this.id = id;
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