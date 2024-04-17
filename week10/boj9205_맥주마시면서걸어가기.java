import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj9205_맥주마시면서걸어가기 {
	private static class Convin {
		int r, c;

		Convin(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N;
	static String ans;
	static Convin[] conviniences;
	static boolean[] visited;
	static Convin start, end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			conviniences = new Convin[N]; // 편의점 위치 정보 넣을 배열
			visited = new boolean[N]; // 편의점 방문 여부
			ans = "sad"; // 정답(기본적으로 sad)
			st = new StringTokenizer(br.readLine());

			// 출발, 도착,편의점 정보 입력.
			start = new Convin(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				conviniences[i] = new Convin(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			end = new Convin(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			bfs(start); // 시작점에서 bfs시작
			System.out.println(ans);
		}
	}

	private static void bfs(Convin tmp) {
		// 현위치~도착점 거리가 1000이면 happy 반환
		if (Math.abs(tmp.r - end.r) + Math.abs(tmp.c - end.c) <= 1000) {
			ans = "happy";
			return;
		}
		// 아니면 방문하지 않은 거리 1000이내의 편의점 bfs
		for (int i = 0; i < N; i++) {
			if ((Math.abs(tmp.r - conviniences[i].r) + Math.abs(tmp.c - conviniences[i].c) <= 1000) && !visited[i]) {
				visited[i] = true;
				bfs(conviniences[i]);
			}
		}

	}

}
