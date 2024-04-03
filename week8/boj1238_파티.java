import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj1238_파티 {
	static class Node { // 도로 기록할 노드: 도착점,가중치
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	static int N, M, X, ans;
	static List<Node>[] adj; // List의 배열
	static int[] dist, go;
	static final int inf = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 집 수
		M = Integer.parseInt(st.nextToken()); // 도로 수
		X = Integer.parseInt(st.nextToken()); // 목적지

		adj = new ArrayList[N + 1]; // 배열 크기 생성
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>(); // 배열의 내용물(List) 초기화, 출발점에서의 도로 기록
		}
		for (int i = 0; i < M; i++) {
			// 모든 도로를 기록, adj[출발점] = 도로 노드로 기록
			st = new StringTokenizer(br.readLine());
			adj[Integer.parseInt(st.nextToken())]
					.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		go = new int[N + 1]; // 갈 때의 최소경로 기록
		ans = 0;
		for (int i = 1; i <= N; i++) { // 모든 집에서 출발하는 다익스트라 수행
			dist = new int[N + 1];
			Arrays.fill(dist, inf); // 다익스트라 구현을 위한 초기화
			dijkstra(i, X, 0); // (출발지,도착지,0:갈 때의 명령어)) 다익스트라
		}
//		System.out.println("갈 때" + Arrays.toString(go));

		// 파티장소(X)에서 출발하는 다익스트라 수행
		dist = new int[N + 1];
		Arrays.fill(dist, inf); // 초기화
		dijkstra(X, 1, 1); // (출발지:파티장소,도착지:아무거나,1: 올 때의 명령어) 다익스트라

		// 갈 때의 최소경로 go[] + 올 때의 다익스트라 결과물 dist[] = 각 집에서 파티장소의 최소경로
		for (int i = 1; i <= N; i++) {
			ans = Math.max(ans, go[i] + dist[i]); // 최소값 갱신
		}
		System.out.println(ans);
	}

	private static void dijkstra(int start, int finish, int com) { // 다익스트라
		int[] visited = new int[N + 1]; // 집 별로 방문 기록
		dist[start] = 0; // 출발지 거리 입력
		for (int i = 1; i <= N; i++) {
			
			int min = inf;
			int idx = -1;
			for (int j = 1; j <= N; j++) {
				if (visited[j] == 0 && min > dist[j]) {
					min = dist[j];
					idx = j;
				}
			} // dist[]배열에서 가장 작은 값을 idx로 설정
			
			visited[idx] = 1; // idx집을 방문 한 것으로 처리
			
			for (Node node : adj[idx]) { // idx에서 출발-도착한 집들 이동비용 최솟값 갱신
				if (visited[node.v] == 0 && dist[node.v] > dist[idx] + node.w) {
					dist[node.v] = dist[idx] + node.w;
				}
			}
			
			if (idx == finish && com == 0) { 
				go[start] = dist[idx];
				return;
			}
			// com==0: 갈 때의 명령어. 각각의 집에서 목적지(파티장소)에 도착하면 go배열에 기록하고 종료.
			// com==1: 올 때의 명령어. 파티장소에서 모든 집까지를 구해야 하므로 다익스트라를 끝까지 실행
		}
//		System.out.println("올 떄" + Arrays.toString(dist));
	}
}
