import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj18405_경쟁적전염 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		for (int s = 0; s < S; s++) { // S초까지
			int[][] visited = new int[N][N]; // 방문 기록하는 visited 배열
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) { // 바이러스가 없는 자리면
					if (arr[r][c] == 0) {
						visited[r][c] = 1; // visited 1 추가
						for (int i = 0; i < 4; i++) {
							int nr = r + dr[i];
							int nc = c + dc[i];
							if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
								// 빈 자리 상하좌우에서 값을 가져옴. 단, visited가 0인 곳들만
								// 이미 값을 가져왔을 수도 있기 때문에 해당사항을 고려
								if (arr[r][c] != 0 && arr[nr][nc] != 0 && visited[nr][nc] == 0) {
									arr[r][c] = Math.min(arr[r][c], arr[nr][nc]);
								} else if (arr[nr][nc] != 0 && visited[nr][nc] == 0) {
									arr[r][c] = arr[nr][nc];
								}
							}
						}
					}

				}
			}
		}
//		for (int r = 0; r < N; r++) {
//			for (int c = 0; c < N; c++) {
//				System.out.print(arr[r][c]);
//			}
//			System.out.println();
//		}
		System.out.println(arr[X - 1][Y - 1]);
	}
}
