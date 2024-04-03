import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj21924_도시건설 {
	static int[] p; // 각 도시의 대표 도시를 기록할 배

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		p = new int[N + 1]; // 대표 정점 기록
		for (int i = 0; i < N + 1; i++) {
			p[i] = i;
		}
		int[][] roads = new int[M][3]; // 도로 값 기록(시작,끝,비용)
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			roads[i][0] = Integer.parseInt(st.nextToken());
			roads[i][1] = Integer.parseInt(st.nextToken());
			roads[i][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(roads, new Comparator<int[]>() { // 비용을 기준으로 sort
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
//		for (int[] a : roads) {
//			System.out.println(Arrays.toString(a));
//		}

		long ans = 0; // 정답 비용(사용하지 않을 도로 비용)
		for (int i = 0; i < M; i++) {
			if (findset(roads[i][0]) != findset(roads[i][1])) {// 대표도시가 다르면(서로 이어져있지 않은 집합이면)
				union(roads[i][0], roads[i][1]); // 결합
			} else {
				ans += roads[i][2]; // 최소비용으로 사용할거면 안 사용.
			}
		}
		// 모든 도로를 이용하여 도시를 연결함.
		
		// 모든 도로를 다 이용하였지만 모든 도시가 이어져 있지 않을 수 있음.
		p[1] = findset(1); // 대표 하나 설정.
		for (int i = 2; i < N + 1; i++) {
			p[i] = findset(i);
			if (p[i] != p[1]) { // 다른 대표가 있으면 -1 출력
				System.out.println(-1);
				return;
			}
		}
		System.out.println(ans); // 대표가 1개면 정답 출력
	}

	private static void union(int x, int y) { // 두 집합 결합
		p[findset(y)] = p[findset(x)]; // 대표 도시를 하나로 정리
	}

	private static int findset(int a) { // 대표 도시 찾기
		if (p[a] != a) { // 나 자신이 대표가 아니면
			p[a] = findset(p[a]); // 그 아닌 것의 대표 찾기
		}
		return p[a]; // 나 자신이 대표면 반환
	}
}
