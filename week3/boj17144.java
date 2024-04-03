import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj17144 {

	static int[][] arr;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우
	static int R, C, T;
	static int[] cleaners = new int[2]; // 공기청정기가 있는 행

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[R][C];

		// 미세먼지와 공기청정기 입력. 단, 청정기자리는 cleaners[]로 기록
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				if (arr[r][c] == -1) {
					cleaners[1] = r;
				}
			}
		}
		cleaners[0] = cleaners[1] - 1;

		// T초동안 T번 확산과 통풍 진행
		for (int time = 0; time < T; time++) {
			diffusion(arr);
			air(arr);
		}

//		// 확인용 출력
//		System.out.println();
//		for (int r = 0; r < R; r++) {
//			for (int c = 0; c < C; c++) {
//				System.out.print(arr[r][c] + " ");
//			}
//			System.out.println();
//		}

		// 미세먼지 합 출력
		int sum = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sum += arr[r][c];
			}
		}
		System.out.println(sum + 2);

	}

	////////////// 확산
	static void diffusion(int[][] arr) {
		// 원래 배열에 확산값을 바로 더하면 다음값이 변하므로 새 배열 new arr2 생성(원래값의 /5)
		int arr2[][] = new int[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				arr2[r][c] = arr[r][c] / 5;
			}
		}
		
		// 조건에 맞는 상하좌우에 몇 번 확산될지(out) 기록하고 arr2값을 더해서 확산을 연산 
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				int out = 0;
				for (int i = 0; i < 4; i++) {
					int nr = r + dir[i][0];
					int nc = c + dir[i][1];
					if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
						if (!((nc == 0) && (nr == cleaners[0] || nr == cleaners[1]))) {
							arr[nr][nc] += arr2[r][c];
							out++;
						}
					}
				}
				arr[r][c] -= arr2[r][c] * out;	// 확산되어 빠진만큼 원래 자리값은 뺌
			}
		}
	}

	/////////////// 공기청정기(2개 나눠서)
	//공기청정기가 들어오는 line을 먼저 계산
	static void air(int[][] arr) {
		// 윗쪽 시계방향
		int upclean = cleaners[0];
		int downclean = cleaners[1];
		for (int i = upclean - 2; i >= 0; i--) {
			arr[i + 1][0] = arr[i][0];
		}
		for (int i = 1; i < C; i++) {
			arr[0][i - 1] = arr[0][i];
		}
		for (int i = 1; i <= upclean; i++) {
			arr[i - 1][C - 1] = arr[i][C - 1];
		}
		for (int i = C - 2; i >= 1; i--) {
			arr[upclean][i + 1] = arr[upclean][i];
		}
		arr[upclean][1] = 0;
		// 아래 시계방향
		for (int i = downclean + 2; i < R; i++) {
			arr[i - 1][0] = arr[i][0];
		}
		for (int i = 1; i < C; i++) {
			arr[R - 1][i - 1] = arr[R - 1][i];
		}
		for (int i = R - 2; i >= downclean; i--) {
			arr[i + 1][C - 1] = arr[i][C - 1];
		}
		for (int i = C - 2; i >= 1; i--) {
			arr[downclean][i + 1] = arr[downclean][i];
		}
		arr[downclean][1] = 0;
	}

}