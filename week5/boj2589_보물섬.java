import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj2589_보물섬 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] arr = new char[R][C];
		for (int r = 0; r < R; r++) {
			String a = br.readLine();
			for (int c = 0; c < C; c++) {
				arr[r][c] = a.charAt(c);
			}
		}

		int[] dr = { -1, 0, 1, 0 };		
		int[] dc = { 0, 1, 0, -1 };
		int dis = 0;	// 시작점에서부터의 거리
		int max = 0;	// 시작점에서부터의 
		Queue<Integer> Rs = new LinkedList<>();
		Queue<Integer> Cs = new LinkedList<>();

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {

				if (arr[r][c] == 'L') {	//(0,0)부터 그 지역이 땅 L 이면
					int[][] visited = new int[R][C];	// visited 배열 (0: 미방문, 1:방문)
					int[][] distance = new int[R][C];	// distance 배열 (시작점으로부터의 거리)
					visited[r][c] = 1;	//시작점은 방문함
					distance[r][c] = 0;	//시작점은 거리 0
					Rs.add(r);	//큐에 넣음
					Cs.add(c);
					while (!Rs.isEmpty()) {
						int tmpR = Rs.poll();	//큐에 값을 꺼내서
						int tmpC = Cs.poll();
						for (int i = 0; i < 4; i++) {
							int nr = tmpR + dr[i];	//상하좌우로 새 좌표값 생성
							int nc = tmpC + dc[i];
							dis = distance[tmpR][tmpC] + 1;	//아래의 조건에 맞으면 distance[][]에 넣을 값. 기존 방향보다 시작점에서 거리 1 멀음
							if (nr >= 0 && nr < R && nc >= 0 && nc < C && visited[nr][nc] == 0 && arr[nr][nc] == 'L') {
								// 새 좌표값이 땅이고, 방문한적 없고, 배열을 벗어나지 않으면
								Rs.add(nr); // 새 좌표값 큐에 넣음
								Cs.add(nc);
								visited[nr][nc] = 1; // visited 1로 방문 기록
								distance[nr][nc] = dis; // 이 시작점으로부터의 거리 기록
							}
						}
					}
					max = Math.max(max, dis); 
					// 해당 반복문이 끝나면 distance값은 시작점으로부터 가장 멀리있는 값이 될 것
					// 시작점들 중 가장 큰 값으로 갱신
				}

			}
		}
		System.out.println(max-1); // while문 때문에 실제보다 1 크게나와서 뺌
	}
}
