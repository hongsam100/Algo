import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj2636_치즈 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] arr = new int[R][C];
		int time = 0;
		int cheese = 0;
		int lastcheese=0;
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 1)
					cheese++;	//입력받을 때 치즈 수를 기록
				arr[r][c] = tmp;
			}
		}

		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		Queue<Integer> Rs = new LinkedList<>();
		Queue<Integer> Cs = new LinkedList<>();
		while (cheese > 0) {
			time++;
			int[][] visited = new int[R][C];
			int willMelt = 0; //녹을 치즈 수(3으로 바꿀 예정)
			visited[0][0] = 1;//(0,0)은 무조건 공기. 여기서부터 bfs 시작, 바깥 공기부분 visited=1로저장
			Rs.add(0);
			Cs.add(0);
			while (!Rs.isEmpty()) {
				int tmpR = Rs.poll();
				int tmpC = Cs.poll();
				for (int i = 0; i < 4; i++) {
					int nr = tmpR + dr[i];
					int nc = tmpC + dc[i];
					if (nr >= 0 && nr < R && nc >= 0 && nc < C && visited[nr][nc] == 0 && arr[nr][nc] == 0) {
						Rs.add(nr);
						Cs.add(nc);
						visited[nr][nc] = 1;
					}
				}
			}
			// 바깥공기부분은(visited==1, arr==0)이 됨.
			// 안쪽공기부분(visited==0, arr==0)을 arr=2로 저장
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (arr[r][c] == 0 && visited[r][c] == 0) {
						arr[r][c] = 2;
					}
				}
			}
			// 치즈 중 바로 옆에 바깥공기(arr==0)인 곳 수 willMelt++, arr=3으로 저장
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (arr[r][c] == 1) {
						for (int i = 0; i < 4; i++) {
							int nr = r + dr[i];
							int nc = c + dc[i];
							if (arr[nr][nc] == 0) {
								arr[r][c] = 3;
							}
						}
					}
				}
			}
			// arr==3인 곳,arr==2인곳 0으로 변경 arr==3은 녹는 치즈 수도 계산
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (arr[r][c] == 2) {
						arr[r][c] = 0;
					} else if (arr[r][c] == 3) {
						arr[r][c] = 0;
						willMelt++;
					}
				}
			}
			lastcheese = cheese;
			cheese -= willMelt;
		}
		System.out.println(time);
		System.out.println(lastcheese);

	}
}
