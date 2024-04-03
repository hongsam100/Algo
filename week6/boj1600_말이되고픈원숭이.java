import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1600_말이되고픈원숭이2 {
    private static class Node {
        int x, y, jump, time;

        public Node(int x, int y, int jump, int time) {
            this.x = x;
            this.y = y;
            this.jump = jump;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] arr = new int[R][C];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dr = { -1, 0, 1, 0 };
        int[] dc = { 0, 1, 0, -1 };
        int[] jdr = { 1, -1, 1, -1, 2, -2, 2, -2 };
        int[] jdc = { 2, 2, -2, -2, 1, 1, -1, -1 };
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0, 0, K, 0));

        while (!que.isEmpty()) {
            Node tmp = que.poll();
            // 방문한 위치를 표시
            arr[tmp.x][tmp.y] = 1;
            if (tmp.x == R - 1 && tmp.y == C - 1) {
                System.out.println(tmp.time);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = tmp.x + dr[i];
                int nc = tmp.y + dc[i];
                if (nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] == 0) {
                    que.add(new Node(nr, nc, tmp.jump, tmp.time + 1));
                }
            }
            if (tmp.jump > 0) {
                for (int i = 0; i < 8; i++) {
                    int nr = tmp.x + jdr[i];
                    int nc = tmp.y + jdc[i];
                    if (nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] == 0) {
                        que.add(new Node(nr, nc, tmp.jump - 1, tmp.time + 1));
                    }
                }
            }
        }
        System.out.println(-1);
    }
}
