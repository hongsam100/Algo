import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Snake {
	int r = 0;
	int c = 0;

	Snake(int r, int c) {
		this.c = c;
		this.r = r;
	}
}

public class MinCheol {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 보드, 사과 배치
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][N];
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
		}

//		for (int i = 0; i < N; i++) { //
//			for (int j = 0; j < N; j++) {
//				System.out.print(arr[i][j]);
//			}
//			System.out.println("");
//		}
//		System.out.println("------------");

		// 방향전환
		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int[] dr = { 0, 1, 0, -1 }; // +하면 우회전 -하면 좌회전
		int[] dc = { 1, 0, -1, 0 };
		int idx = L * 4;

		Queue<Snake> sk = new LinkedList<>(); // 뱀 몸 전체
		Snake snake = new Snake(0, 0); // 뱀 머리
		sk.offer(new Snake(0, 0)); // 뱀 몸통 추가
		int sec = 0;

		Queue<Integer> time = new LinkedList<>(); // x초에 방향전환할 시간 queue
		Queue<String> direction = new LinkedList<>(); // 전환할 방향 queue
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			time.offer(Integer.parseInt(st.nextToken()));
			direction.offer(st.nextToken());
		}

		start: while (true) {
			try {
				while (sec != time.peek()) { // 회전할 시간이 아니면 전진
					snake.r = snake.r + dr[idx % 4];
					snake.c = snake.c + dc[idx % 4];
					sec++;

					if (snake.r >= N || snake.r < 0 || snake.c >= N || snake.c < 0) {
						break start; // 머리가 밖으로 나가면 종료
					}

					boolean a = false; // 몸통 하나씩을 머리 좌표와 일치하는지 확인
					for (int i = 0; i < sk.size(); i++) {
						Snake s1 = sk.poll();
						if (s1.r == snake.r && s1.c == snake.c) {
							a = true;
						}
						sk.offer(s1);
					}

					// 머리가 몸통이랑 겹치면 종료, 아니면 전체에 전체에 추가
					if (a) {
						break start;
					} else {
						sk.offer(new Snake(snake.r, snake.c));
					}

					// 머리가 사과랑 닿으면 사과삭제,몸통삭제 안함
					if (arr[snake.r][snake.c] == 1) {
						arr[snake.r][snake.c] = 0;
					} else
						sk.poll(); // 안닿아으면 몸통(꼬리) 삭제
				}

				time.poll();	// 회전할 시간이 되면 방향 전환
				String dir = direction.poll();
				if (dir.equals("D")) {
					idx += 1; // 우회전
				} else {
					idx -= 1;
				}
			} catch (Exception e) { // poll할게 없을 때도 해야함(복붙)
				snake.r = snake.r + dr[idx % 4];
				snake.c = snake.c + dc[idx % 4];
				sec++;

				if (snake.r >= N || snake.r < 0 || snake.c >= N || snake.c < 0) {
					break start; // 머리가 밖으로 나가면 종료
				}

				boolean a = false;
				for (int i = 0; i < sk.size(); i++) {
					Snake s1 = sk.poll();
					if (s1.r == snake.r && s1.c == snake.c) {
						a = true;
					}
					sk.offer(s1);
				}

				if (a) {
					break start; // 머리가 몸통이랑 겹치면 종료
				} else {
					sk.offer(new Snake(snake.r, snake.c)); // 머리 전체에 추가
				}

				if (arr[snake.r][snake.c] == 1) { // 머리가 사과랑 닿으면 사과삭제,몸통삭제X
					arr[snake.r][snake.c] = 0;
				} else
					sk.poll(); // 안닿아으면 몸통(꼬리) 삭제
			}

		}
		System.out.println(sec);

	}

}
