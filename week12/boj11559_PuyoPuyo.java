import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj11559_PuyoPuyo {
	static int ans, num;
	static int[] dr, dc;
	static char[][] arr;
	static Queue<Integer> que, delete;
	static boolean B;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new char[12][6];
		for (int r = 0; r < 12; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int c = 0; c < 6; c++) {
				arr[r][c] = str.charAt(c);
			}
		}
		dr = new int[] { -1, 0, 1, 0 };
		dc = new int[] { 0, 1, 0, -1 };
		que = new LinkedList<>();
		delete = new LinkedList<>();

		ans = 0;
		B = true;
		while (B) {
			bomb();
			down();
		}
		System.out.println(ans);
	}

	private static void down() {
		for (int c = 0; c < 6; c++) {
			int next = 11;
			for (int r = 11; r >= 0; r--) {
				if (arr[r][c] != '.') {
					char tmp = arr[r][c];
					arr[r][c] = '.';
					arr[next--][c] = tmp;

				}
			}
		}
	}

	private static void bomb() {
		visited = new boolean[12][6];
		int nowBomb = 0;

		for (int r = 11; r >= 0; r--) {
			for (int c = 0; c < 6; c++) {
				int num = 0;
				char now = '.';
				if (arr[r][c] != '.' && !visited[r][c]) {
					que.add(r);
					que.add(c);
					delete.add(r);
					delete.add(c);
					visited[r][c] = true;
					now = arr[r][c];
					num++;
				}

				while (!que.isEmpty()) {
					int R = que.poll();
					int C = que.poll();
					for (int i = 0; i < 4; i++) {
						int nr = R + dr[i];
						int nc = C + dc[i];
						if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6) {
							if (arr[nr][nc] == now && !visited[nr][nc]) {
								visited[nr][nc] = true;
								que.add(nr);
								que.add(nc);
								delete.add(nr);
								delete.add(nc);
								num++;
							}
						}
					}
				}

				if (num < 4) {
					delete.removeAll(delete);
				} else {
					while (!delete.isEmpty()) {
						arr[delete.poll()][delete.poll()] = '.';
					}
					nowBomb++;
				}

			}
		}
		if (nowBomb == 0) {
			B = false;
		} else {
			ans++;
		}
	}
}
