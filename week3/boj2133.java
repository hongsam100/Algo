import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//타일 채우기
public class boj2133 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[31];
		arr[0] = 1;
		arr[2] = 3;
		for (int i = 2; i <= 15; i++) {
			arr[i * 2] = arr[i * 2 - 2] * 4 - arr[i * 2 - 4];
		}
		System.out.println(arr[N]);
		
		// 홀수일 때는 0개.
		// 짝수일 때는 [n]가지 = 4[n-2]가지 - [n-4] 가지이다
	}

}