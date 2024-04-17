import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1516_게임개발 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken()) + 1; // 건물 수. 0번자리는 안씀
		int[] times = new int[N]; // 건설 시간
		int[] ans = new int[N]; // 실제 걸리는 시간(선행 시간+건설 시간)
		boolean[][] seq = new boolean[N][N]; // 선행 건물들 ([1][2]: 2를 짓기 위해 1을 지어야 함)
		Queue<Integer> que = new LinkedList<>(); // 바로 건설 가능한 건물
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int pre = 0; // 선행 건물 수
			times[i] = Integer.parseInt(st.nextToken());
			while (true) {
				// -1이면 넘어감. 아니면 정리.
				int a = Integer.parseInt(st.nextToken());
				if (a != -1) {
					pre++;
					seq[a][i] = true;
				} else
					break;
			}
			// 선행 건물이 0개면 건설 대기(que)
			if (pre == 0) {
				que.add(i);
			}
		} // 입력 종료

		while (!que.isEmpty()) {
			// 큐에 들어간 건물 = 바로 지어질 수 있는 건물.
			// 걸리는 시간: 선행 시간+건설 시간
			int now = que.poll();
			ans[now] += times[now];

			// 방금 지은 건물은 선행건물에서 삭제
			test: for (int c = 1; c < N; c++) {
				if (seq[now][c]) {
					// 방금 지은 건물은 행 방향으로 false로 바꿔 줌.
					// seq[선행건물][건물] > false로 갱신. 선행건물의 시간도 ans에 넣어줌.
					// 단, 가장 오래 걸리는 선행건물만 저장.
					seq[now][c] = false;
					ans[c] = Math.max(ans[now], ans[c]);
					// 해금된 건물(열) 중 선행 조건이 없으면 큐에 넣어줌.
					for (int r = 1; r < N; r++) {
						if (seq[r][c]) {
							// 선행 건물이 남았으면 건너뜀.
							continue test;
						}
					}
					que.add(c);
				}
			}
		}
		
		for (int i = 1; i < N; i++) {
			sb.append(ans[i] + "\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);

	}
}
