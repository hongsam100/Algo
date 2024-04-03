import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//중앙값 구하기
public class boj2696_중앙값구하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			max = new PriorityQueue<>(Collections.reverseOrder());
			min = new PriorityQueue<>();

			int ansnum = 0; // 정답 갯수(10개가 될때마다 줄바꿈에 사용)

			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			sb.append((M + 1) / 2 + "\n");

			for (int r = 0; r < M; r++) {
				if (r % 10 == 0) {
					st = new StringTokenizer(br.readLine());
				}
				if (max.size() == min.size()) {
					max.offer(Integer.parseInt(st.nextToken()));
				} else {
					min.offer(Integer.parseInt(st.nextToken()));
				}

				if (r % 2 == 0) { // 여태까지의 시행횟수(홀수가 되면 que에 중앙값 넣을 예정)
					ansnum++;
					if (ansnum % 10 == 0) {
						sb.append(median() + "\n");
					} else {
						sb.append(median() + " ");
					}
				}

				
			}
			
			sb.delete(sb.length() - 1, sb.length()).append("\n"); // 마지막줄 공백 제거
		}
		sb.delete(sb.length() - 1, sb.length());
		System.out.println(sb);
	}

	static PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder()); // 값들을 넣을 우선순위 큐(넣을 때마다 순서 갱신)
	static PriorityQueue<Integer> min = new PriorityQueue<>(); // 값들을 넣을 우선순위 큐(넣을 때마다 순서 갱신)

	static int median() {
		if (!min.isEmpty()) {
			if (max.peek() > min.peek()) {
				int t1 = max.poll();
				int t2 = min.poll();

				max.offer(t2);
				min.offer(t1);
			}
		}
		return max.peek();

	}
}