import java.io.*;
import java.util.*;

public class JiHwan {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine()); // 보드의 크기
        int k = Integer.parseInt(br.readLine()); // 사과의 개수
        int[][] arr = new int[n][n]; // 보드
        int time = 0; // 걸리는 시간
        Queue<Integer> queue = new LinkedList<>();

        int[] dx = {1, 0, -1, 0}; // 델타 배열
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < k; i++) { // 사과의 위치
            st = new StringTokenizer(br.readLine());
            int appleY = Integer.parseInt(st.nextToken());
            int appleX = Integer.parseInt(st.nextToken());
            arr[appleY-1][appleX-1] = 4; // 사과가 있을 경우 4로 표시
        }

        int L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 정보 개수
        int[] go = new int[L+1];
        int[] command = new int[L+1]; // D = 1/-1

        for (int i = 0; i < L; i++) { // 뱀의 방향 정보 변환
            st = new StringTokenizer(br.readLine());
            go[i] = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            if (direction.equals("D")) command[i] = 1; // 시계방향
            else command[i] = -1; // 반시계방향
        }

        int startY = 0; // 시작 Y좌표
        int startX = 0; // 시작 X좌표
        int dirNum = 100; // 방향을 전환하기 위해 임의의 숫자를 둠
        int goNum = 0;

        queue.offer(1); // 시작지점 주어짐
        while (true) {
            if (time == go[goNum]) {dirNum += command[goNum++];}

            startY += dy[dirNum % 4];
            startX += dx[dirNum % 4];
            time ++;

            if (!(startY >= 0 && startY < n && startX >= 0 && startX < n) || queue.contains(startY * n + startX + 1)) {
                break;
            } else if (arr[startY][startX] == 4) {
                arr[startY][startX] = 0;
                queue.offer(startY * n + startX + 1);
            } else {
                queue.offer(startY * n + startX + 1);
                queue.poll();
            }
        }
        System.out.println(time);
    }
}