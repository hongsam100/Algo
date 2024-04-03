import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Dongkyu {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		int[] result = new int[N];
		
		int cnt = 0;
		while (!queue.isEmpty()) {
			for (int i = 0; i < K - 1; i++) {
				queue.offer(queue.poll());
			}
			result[cnt] = queue.poll();
			cnt++;
		}
		
//		System.out.print("<");
//		for (int i = 0; i < result.length; i++) {
//			if (i != result.length - 1) {
//				System.out.print(result[i] + ", ");
//			} else {
//				System.out.print(result[i]);
//			}
//		}
//		System.out.println(">");

		StringBuilder sb = new StringBuilder();
		
		sb.append("<");
		for (int i = 0; i < result.length; i++) {
			sb.append(result[i]);
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(">");
		
		System.out.println(sb);
	}
}
