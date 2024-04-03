import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class MinCheol {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 후위표기식을 char로 받은 배열 생성, 연산에 사용할 stack
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		char[] c = st.nextToken().toCharArray(); 
		Stack<Double> stack = new Stack<>();

		// ABC 값 배열
		String[] ABC = new String[N]; 
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ABC[i] = st.nextToken();
		}
		
		String[] postfit = new String[c.length]; // 연산에 사용할 식(string) 배열
		Double[] postdouble = new Double[c.length]; // 연산에 사용할 식(double)배열
		// postfit: string(123*+45/-) : +-*/기호 사용
		// postdouble: double(123  45  ) : 12345 실수 계산에 사용
		for (int i = 0; i < c.length; i++) {
			if (c[i] >= 65 && c[i] <= 65 + N) { // char A 값이 65
				postfit[i] = ABC[c[i] - 65];
				postdouble[i] = Double.parseDouble(ABC[c[i] - 65]);
			} else
				postfit[i] = c[i] + "";
		}
		
//		System.out.println(Arrays.toString(postfit));
//		System.out.println(Arrays.toString(postdouble));

		double num1 = 0;
		double num2 = 0;
		double ans = 0;
		for (int i = 0; i < postfit.length; i++) { // 계산에서 stack 활용
			if (!(postfit[i].equals("+")) && !(postfit[i].equals("-")) && !(postfit[i].equals("*"))
					&& !(postfit[i].equals("/"))) {
				stack.push(postdouble[i]);
			} else {
				switch (postfit[i]) {
				case "+":
					num1 = stack.pop();
					num2 = stack.pop();
					ans = num2 + num1;
					stack.push(ans);
					break;
				case "-":
					num1 = stack.pop();
					num2 = stack.pop();
					ans = num2 - num1;
					stack.push(ans);
					break;
				case "*":
					num1 = stack.pop();
					num2 = stack.pop();
					ans = num2 * num1;
					stack.push(ans);
					break;
				case "/":
					num1 = stack.pop();
					num2 = stack.pop();
					ans = num2 / num1;
					stack.push(ans);
					break;
				}
			}
		}
		double answer = stack.pop();
		System.out.printf("%.2f", answer);
	}
}

//5
//ABC*+DE/-
//1
//2.24
//3.33
//4
//5.555