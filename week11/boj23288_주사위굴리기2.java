import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj23288_주사위굴리기2 {
	static int N, M, K, dir, score, diceR, diceC;
	static int[] dr, dc, dice;
	static int[][] map, scoreMap, roll;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dir = 1004;
		dr = new int[] { 0, 1, 0, -1 };
		dc = new int[] { 1, 0, -1, 0 };
		dice = new int[] { 6, 3, 5, 4, 2, 1 }; // {bottom,dir0,dir1,dir2,dir3,dir4,top}
		roll = new int[][] { { 1, 5, 2, 0, 4, 3 }, { 2, 1, 5, 3, 0, 4 }, { 3, 0, 2, 5, 4, 1 }, { 4, 1, 0, 3, 5, 2 } }; // 우하좌상
																														// 주사위
																														// 모양
		map = new int[N][M];
		scoreMap = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		getscoreMap();
		score = 0;
		diceR = 0;
		diceC = 0;
		for (int i = 0; i < K; i++) {
			roll();
		}
		System.out.println(score);
	}

	private static void roll() {
		int nowDir = dir % 4;
		int nr = diceR + dr[nowDir % 4];
		int nc = diceC + dc[nowDir % 4];
		if (!(nr >= 0 && nr < N && nc >= 0 && nc < M)) {
			dir += 2;
			nowDir = dir % 4;
			nr = diceR + dr[nowDir % 4];
			nc = diceC + dc[nowDir % 4];
		}
		diceR = nr;
		diceC = nc;
		int[] tmp = new int[6];
		for (int i = 0; i < 6; i++) {
			tmp[i] = dice[roll[nowDir][i]];
		}
		dice = tmp;
//		System.out.println(nr + "," + nc + " " + scoreMap[diceR][diceC]);
		score += scoreMap[diceR][diceC];
		if (dice[0] > map[diceR][diceC]) {
			dir++;
		} else if (dice[0] < map[diceR][diceC]) {
			dir--;
		}

	}

	private static void getscoreMap() {
		boolean[][] visited = new boolean[N][M];
		Queue<Integer> que = new LinkedList<>();
		Queue<Integer> que2 = new LinkedList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {

				if (!visited[r][c]) {
					visited[r][c] = true;
					que.add(r);
					que.add(c);
					que2.add(r);
					que2.add(c);
					int num = 1;
					while (!que.isEmpty()) {
						int nowR = que.poll();
						int nowC = que.poll();
						for (int i = 0; i < 4; i++) {
							int nr = nowR + dr[i];
							int nc = nowC + dc[i];
							if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == map[r][c]
									&& !visited[nr][nc]) {
								visited[nr][nc] = true;
								que.add(nr);
								que.add(nc);
								que2.add(nr);
								que2.add(nc);
								num++;
							}
						}
					}
					int tmp = num * map[r][c];
					while (!que2.isEmpty()) {
						scoreMap[que2.poll()][que2.poll()] = tmp;
					}
				}

			}
		}

//		for (int r = 0; r < N; r++) {
//			for (int c = 0; c < M; c++) {
//				System.out.print(scoreMap[r][c] + " ");
//			}
//			System.out.println();
//		}
	}
}
