import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj12100_2048easy {
	static int N, max;
	static int[] turn;
	static int[][] arr, copy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		max = 0;
		turn = new int[5];
		arr = new int[N][N];
		copy = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		perm(0);
		System.out.println(max);
	}

	private static void perm(int idx) {
		if (idx == 5) {
//			System.out.println(Arrays.toString(turn));
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					copy[r][c] = arr[r][c];
				}
			}
			simul(turn);
			return;
		}
		for (int i = 0; i < 4; i++) {
			turn[idx] = i;
			perm(idx + 1);
		}

	}

	private static void simul(int[] turn) {
		for (int i = 0; i < 5; i++) {
			switch (turn[i]) {
			case 0: // 위로 push
				for (int c = 0; c < N; c++) {
					int numCnt = 0;
					int before = 0;
					int now = 0;
					for (int r = 0; r < N; r++) {
						now = copy[r][c]; // 현재 값
						copy[r][c] = 0; //현재 위치 값은 없어짐.
						if (now != 0) {
							if (before == now) { // 현재 값 = 이전 값이면
								numCnt--;
								copy[numCnt][c] *= 2;
								numCnt++;
								before = 0; // 현재값은 다신 안합쳐짐.
							} else {
								copy[numCnt][c] = now;
								before = now; // 현재 값은 이전 값이됨
								numCnt++;
							}
						}
					}
				}
				break;
			case 1: // 오른쪽
				for (int r = 0; r < N; r++) {
					int numCnt = 0;
					int before = 0;
					int now = 0;
					for (int c = 0; c < N; c++) {
						now = copy[r][c]; // 현재 값
						copy[r][c] = 0;
						if (now != 0) {
							if (before == now) { // 현재 값 = 이전 값이면
								numCnt--;
								copy[r][numCnt] *= 2;
								numCnt++;
								before = 0; // 현재값은 다신 안합쳐짐.
							} else {
								copy[r][numCnt] = now;
								before = now; // 현재 값은 이전 값이됨
								numCnt++;
							}
						}
					}
				}
				break;
			case 2: // 아래
				for (int c = 0; c < N; c++) {
					int numCnt = N - 1;
					int before = 0;
					int now = 0;
					for (int r = N - 1; r >= 0; r--) {
						now = copy[r][c]; // 현재 값
						copy[r][c] = 0;
						if (now != 0) {
							if (before == now) { // 현재 값 = 이전 값이면
								numCnt++;
								copy[numCnt][c] *= 2;
								numCnt--;
								before = 0; // 현재값은 다신 안합쳐짐.
							} else {
								copy[numCnt][c] = now;
								before = now; // 현재 값은 이전 값이됨
								numCnt--;
							}
						}
					}
				}
				break;
			case 3: // 왼쪽
				for (int r = 0; r < N; r++) {
					int numCnt = N - 1;
					int before = 0;
					int now = 0;
					for (int c = N - 1; c >= 0; c--) {
						now = copy[r][c]; // 현재 값
						copy[r][c] = 0;
						if (now != 0) {
							if (before == now) { // 현재 값 = 이전 값이면
								numCnt++;
								copy[r][numCnt] *= 2;
								numCnt--;
								before = 0; // 현재값은 다신 안합쳐짐.
							} else {
								copy[r][numCnt] = now;
								before = now; // 현재 값은 이전 값이됨
								numCnt--;
							}
						}
					}
				}
				break;
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
//				System.out.print(copy[r][c] + " ");
				max = Math.max(max, copy[r][c]);
			}
//			System.out.println();
		}
//		System.out.println();
	}
}
