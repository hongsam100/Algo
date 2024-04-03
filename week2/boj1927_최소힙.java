// 최소 힙

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj1927_최소힙 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	//N번 명령
		
		//우선순위 큐(pq) 생성, Collections.reverseOrder() 하면 큰 수 부터 출력
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			int cmd = Integer.parseInt(br.readLine());
			if (cmd > 0) {	// 명령이 자연수면 pq에 추가
				pq.add(cmd);
			} else { // 아니면 poll. poll할 수 없으면 0 출력

				if (!pq.isEmpty()) {
					System.out.println(pq.poll());
				} else {
					System.out.println(0);
				}

			}
		}

	}
}
