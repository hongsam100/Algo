import java.util.*;
import java.io.*;

class SangYong {
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> r = new LinkedList<>();
        for(int i=1; i<=N; i++)
            q.offer(i);
        while(!q.isEmpty()){
            for(int j=0; j<K-1; j++){
                q.offer(q.poll());
            }
            r.offer(q.poll());
        }
        System.out.print("<");
        for(int i = 0; i<N-1; i++) {
            System.out.print(r.poll() + ", ");
        }
        System.out.print(r.poll());
        System.out.print(">");
	}
}