import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj20057_마법사상어와토네이도 {
	static int N, dirr, out;
	static int[] dr, dc;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		dr = new int[] { 0, 1, 0, -1, 0, 1, 0, -1, 0, 1, 0, -1 };
		dc = new int[] { -1, 0, 1, 0, -1, 0, 1, 0, -1, 0, 1, 0 };
		int R = N / 2;
		int C = N / 2;
		int idx = 2;
		int dir = 0;
		out = 0;
		a: while (true) {
			dirr = dir % 4;
			for (int i = 0; i < idx / 2; i++) {
				R = R + dr[dirr];
				C = C + dc[dirr];
				wind(R, C, dirr + 4);
//				System.out.println("wind" + R + " " + C);
//				for (int r = 0; r < N; r++) {
//					for (int c = 0; c < N; c++) {
//						System.out.print(arr[r][c] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
				if (R == 0 && C == 0)
					break a;
			}
			dir++;
			idx++;
		}
		System.out.println(out);
	}

	private static void wind(int R, int C, int dir) {
		int remain = arr[R][C];

		int[] nrs = { dr[dir] * 2, dr[dir - 1] * 2, dr[dir - 1], dr[dir - 1] + dr[dir], dr[dir - 1] - dr[dir], dr[dir + 1] * 2, dr[dir + 1], dr[dir + 1] + dr[dir], dr[dir + 1] - dr[dir] };
		int[] ncs = { dc[dir] * 2, dc[dir - 1] * 2, dc[dir - 1], dc[dir - 1] + dc[dir], dc[dir - 1] - dc[dir], dc[dir + 1] * 2, dc[dir + 1], dc[dir + 1] + dc[dir], dc[dir + 1] - dc[dir]  };
		double[] rate = { 0.05, 0.02, 0.07, 0.1, 0.01, 0.02, 0.07, 0.1, 0.01 };
		for (int i = 0; i < 9; i++) {
			int nr = R + nrs[i];
			int nc = C + ncs[i];
			int tmp = (int) (Math.floor(arr[R][C] * rate[i]));
			remain -= tmp;
			if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
				arr[nr][nc] += tmp;
			} else {
				out += tmp;
			}
		}
		
		int nr = R + dr[dir];
		int nc = C + dc[dir];
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
			arr[R + dr[dir]][C + dc[dir]] += remain;
		} else {
			out += remain;
		}
		arr[R][C] = 0;
	}
}
