package Set7;

import java.util.*;
class EIKARY  {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);       
        int n = scanner.nextInt();
        int k = scanner.nextInt();        
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }        
        for (int i = 0; i < n - 1; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            tree.get(u).add(v);
            tree.get(v).add(u);
        }     
        boolean[] visited = new boolean[n];
       
        Queue<Integer> queue = new ArrayDeque<>();

        visited[0] = true;
               
        while (!queue.isEmpty()) {
            int node = queue.poll();//loại bỏ và trả về phần tưr đầu tiên trong hàng đợi
            int childrenCount = 0;
            for (int child : tree.get(node)) {
                if (!visited[child]) {
                    visited[child] = true;
                    queue.add(child);// thêm phần thhuwr vào cuối
                    childrenCount++;
                }
            }
           
            if (childrenCount > k) {
                System.out.println("No");
                return;
            }
        }        
        System.out.println("Yes");
    }
}