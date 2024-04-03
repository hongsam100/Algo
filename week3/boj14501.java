
//퇴사
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj14501 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // N까지만 일 함

		int[] day = new int[N + 1];
		int[] pay = new int[N + 1];
		int[] income = new int[N + 2];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			day[i] = Integer.parseInt(st.nextToken()); // 상담 하는데 걸리는 일 수
			pay[i] = Integer.parseInt(st.nextToken()); // 돈
		}

		for (int i = N; i > 0; i--) {
			int next = i + day[i]; // 일 끝난 날짜

			if (next > N + 1) { // 일 끝난 날짜가 퇴사일+1 넘어서면
				// 일을 하지 못하므로 바로 다음 income값(더 앞쪽의 날짜)로 설정
				income[i] = income[i + 1];
			} else { // 일할 수 있는 날짜인 경우
				// 1. 일하지 않았을 때(income[i + 1])
				// 2. 일 했을 때 총 벌 수 있는 금액(pay[i] + income[next])
				// 위 두 경우 중 더 큰 값을 income에 넣어준다.
				income[i] = Math.max(income[i + 1], pay[i] + income[next]);
			}
		}

		System.out.println(income[1]);
	}

}

//7
//3 10
//5 20
//1 10
//1 20
//2 15
//4 40
//2 200