import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class Dongkyu {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		String calfor = st.nextToken();
		HashMap<String, Double> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map.put(Character.toString(65 + i), Double.parseDouble(st.nextToken()));
		}
		
		double result = calculate(calfor, map);
		
		System.out.printf("%.2f", result);
		
	}
	
	public static double calculate(String calfor, HashMap<String, Double> map) {
		Stack<Double> stack = new Stack<>();
		
		for (int i = 0; i < calfor.length(); i++) {
			char token = calfor.charAt(i);
			
			if (token != '+' && token != '-' && token != '*' && token != '/') {
				stack.push(map.get(Character.toString(token)));
			} else {
				double op2 = stack.pop();
				double op1 = stack.pop();
				
				switch (token) {
				case '*':
					stack.push(op1 * op2);
					break;
				case '/':
					stack.push(op1 / op2);
					break;
				case '+':
					stack.push(op1 + op2);
					break;
				case '-':
					stack.push(op1 - op2);
					break;
				}
			}
		}
		
		return stack.pop();
	}
}
