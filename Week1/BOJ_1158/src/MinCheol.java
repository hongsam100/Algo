import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MinCheol {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 1~N que에 담기
		Queue<Integer> queue = new LinkedList<>();
		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			queue.add(i + 1);
		}

		int K = Integer.parseInt(st.nextToken());
		sb.append("<");
		while (!(queue.isEmpty())) { // 큐가 빌 때까지
			for (int i = 0; i < K - 1; i++) { // K번째가 아닌건 맨 뒤로 이동
				int a = queue.poll();
				queue.add(a);
			}
			if (!(queue.size() == 1)) { // K번째 pop 후 출력, 마지막 원소는","없이 출력
				sb.append(queue.poll()).append(", ");
			} else
				sb.append(queue.poll());
		}
		sb.append(">");
		System.out.println(sb);

	}
}
