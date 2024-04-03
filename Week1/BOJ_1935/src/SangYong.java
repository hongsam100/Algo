import java.util.*;
import java.io.*;

class SangYong {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String str = sc.next();
        
        double[] arr = new double[N];
        for(int i=0; i<N; i++){
            arr[i] = sc.nextDouble();
        }

        Stack<Double> s = new Stack<>();
        for(int i=0; i<str.length(); i++){
            char cc = str.charAt(i);
            if('A' <= cc && cc <= 'Z'){
                s.push(arr[cc - 'A']);
            }
            else{
                if(cc == '+')
                    s.push(s.pop() + s.pop());
                if(cc == '-')
                    s.push(-s.pop() + s.pop());
                if(cc == '*')
                    s.push(s.pop() * s.pop());
                if(cc == '/')
                    s.push(1 / s.pop() * s.pop());
            }
        }
        System.out.printf("%.2f", s.pop());
    }
}