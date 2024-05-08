import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj6987_월드컵 {
	static boolean stop;
	static int[][] arr;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < 4; t++) {
			ans = 0;
			stop = false;
			arr = new int[6][3];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 6; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
				arr[i][2] = Integer.parseInt(st.nextToken());
			}
			check();
			test(0, 1, 0);
			sb.append(ans + " ");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}

	private static void test(int team1, int team2, int num) {
//		System.out.println(num);
		if (stop)
			return;
		if (num == 15) {
			ans = 1;
			return;
		}
		for (int i = team1; i < 5; i++) {
			for (int j = team2; j < 6; j++) {
				for (int a = 0; a < 3; a++) {
					if (arr[i][a] > 0 && arr[j][2 - a] > 0) {
						arr[i][a]--;
						arr[j][2 - a]--;
						if (team2 != 5) {
							test(i, j + 1, num + 1);
						} else {
							test(i + 1, i + 2, num + 1);
						}
						arr[i][a]++;
						arr[j][2 - a]++;
					}
				}
			}
		}

	}

	private static void check() {
		int win = 0;
		int draw = 0;
		int lose = 0;
		for (int i = 0; i < 6; i++) {
			win += arr[i][0];
			draw += arr[i][1];
			lose += arr[i][2];
		}
		if (draw % 2 == 1) {
			stop = true;
			return;
		}
		if (win + draw / 2 != 15 || lose + draw / 2 != 15) {
			stop = true;
			return;
		}

	}
}
