import java.io.*;
import java.util.*;

public class JiHwan {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        sb.append("<");
        for (int i = 1; i <= n; i++) {queue.offer(i);}

        while (!queue.isEmpty()) {
            for (int i = 0; i < k-1; i++) {
                queue.offer(queue.poll());
            }
            sb.append(queue.poll());

            if (!queue.isEmpty()) {
                sb.append(", ");
            }
        }

        sb.append(">");
        System.out.println(sb);
    }
}