import java.io.*;
import java.util.Stack;

public class JiHwan {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        Stack<Double> stackDouble = new Stack<>();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) - 'A' >= 0 && str.charAt(i) - 'A' <= 25) {
                stackDouble.add((double) arr[str.charAt(i) - 'A']);
            } else {
                if (str.charAt(i) == '+') {
                    stackDouble.add(stackDouble.pop() + stackDouble.pop());
                } else if (str.charAt(i) == '-') {
                    stackDouble.add(-(stackDouble.pop() - stackDouble.pop()));
                } else if (str.charAt(i) == '*') {
                    stackDouble.add(stackDouble.pop() * stackDouble.pop());
                } else if (str.charAt(i) == '/') {
                    stackDouble.add((1 / stackDouble.pop()) * stackDouble.pop());
                }
            }
        }
        System.out.printf("%.2f", stackDouble.pop());
    }
}
