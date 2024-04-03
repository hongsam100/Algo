import java.util.*;
import java.io.*;

class SangYong {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] board = new int[N][N];

        int K = sc.nextInt();
        for(int i=0; i<K; i++){
            board[sc.nextInt()-1][sc.nextInt()-1] = 1;
        }

        int L = sc.nextInt();
        HashMap<Integer, String> map = new HashMap<>();
        for(int i=0; i<L; i++){
            map.put(sc.nextInt(), sc.next());
        }
        
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Deque<int[]> snake = new ArrayDeque<>();
        snake.offer(new int[]{0, 0});
        
        int curi = 0; int curj = 0; int time = 0; int i = 0;
        boolean hit = false;
        while(true){
            int dx = dir[i][0]; int dy = dir[i][1];
            curi += dx; curj += dy;
            time++;

            if(curi<0 || curi>N-1 || curj<0 || curj>N-1)
                break;
            
            for(int[] pos : snake){
                if(pos[0] == curi && pos[1] == curj){
                    hit = true;
                    break;
                }
            }
            if(hit)
                break;

            snake.offerFirst(new int[]{curi, curj});
            if(board[curi][curj] == 0){
                snake.pollLast();
            }
            else if(board[curi][curj] == 1){
                board[curi][curj] = 0;
            }
            
            if(map.containsKey(time)){
                String command = map.get(time);
                if(command.equals("D")){
                    i = (i + 1) % 4;
                }
                else if(command.equals("L")){
                    i = (i + 3) % 4;
                }
            }
        }
        System.out.print(time);
	}
}