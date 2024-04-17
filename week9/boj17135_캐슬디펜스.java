import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj17135_캐슬디펜스 {
	private static class Node {
		int r, c, distance;

		public Node(int r, int c, int distance) {
			this.r = r;
			this.c = c;
			this.distance = distance;
		}
	}

	static int N, M, D, ans, num;
	static int[] shooter, dr, dc;
	static int[][] arr, copy;
	static Queue<Node> que;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		shooter = new int[3];
		ans = 0;
		arr = new int[N][M];
		copy = new int[N][M];
		dr = new int[] { 0, -1, 0 }; // bfs 왼쪽먼저
		dc = new int[] { -1, 0, 1 };
		que = new LinkedList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		comb(0, 0); // 궁수 위치의 조합
		System.out.println(ans);
	}

	private static void comb(int sidx, int idx) {
		if (sidx >= 3) { // 궁수 위치의 조합이 만들어지면
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					copy[r][c] = arr[r][c];
				}
			}
			simul(shooter); // 시뮬레이션 실행
			return;
		}
		if (idx == M) {
			return;
		}
		shooter[sidx] = idx;
		comb(sidx + 1, idx + 1);
		comb(sidx, idx + 1);

	}

	private static void simul(int[] shooter) {
		num = 0;
		// 적이 오는 것 대신 매 초마다 성이 앞으로 전진
		for (int time = N; time >= 0; time--) {
			for (int i = 0; i < 3; i++) { // 궁수별로 bfs
				que.add(new Node(time, shooter[i], 0));
				bfs(time);
				que.removeAll(que);
			}

			// 쏜 적 정산 (화살에 맞은 적, copy[r][c]==2를 제거)
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (copy[r][c] == 2) {
						copy[r][c] = 0;
						num++;
					}
				}
			}

		}
		// 다 끝내고 ans 갱신
		ans = Math.max(ans, num);
	}

	private static void bfs(int r) {
		// 초기실행은 배열 밖에 있어서 한 번 실행
		Node first = que.poll();
		for (int i = 0; i < 3; i++) {
			int nr = first.r + dr[i];
			int nc = first.c + dc[i];
			if (nr >= 0 && nc >= 0 && nc < M && nr < r) {
				que.add(new Node(nr, nc, first.distance + 1));
			}
		}

		while (!que.isEmpty()) {
			Node now = que.poll();
			// 적이 있으면 쏘고 멈춤
			if (copy[now.r][now.c] == 1 || copy[now.r][now.c] == 2) {
				copy[now.r][now.c] = 2;// 쏠 적이 겹칠 수도 있어서 2로 표시(visited같은것)
				return;
			}
			if (now.distance < D) { // 사거리가 남았으면 bfs
				for (int i = 0; i < 3; i++) {
					int nr = now.r + dr[i];
					int nc = now.c + dc[i];
					if (nr >= 0 && nc >= 0 && nc < M && nr < r) {
						que.add(new Node(nr, nc, now.distance + 1));
					}
				}
			}
		}
	}
}
