package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1204 {
    static int N, K, L;
    static int[][] map;
    static Map<Integer, Character> moves = new HashMap<>();
    static int[] dx = {0, 1, 0, -1}; // 동 서 남 북
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        K = Integer.parseInt(br.readLine()); // 사과 개수만큼 사과 자리 입력
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 1; // 사과 자리에 1 입력
        }

        L = Integer.parseInt(br.readLine()); // 뱀의 방향 바꾸는 정보 입력
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            moves.put(x, c);
        }

        System.out.println(simul());
    }

    static int simul() {
        Deque<int[]> snake = new ArrayDeque<>();
        snake.offer(new int[]{0, 0}); // 시작 위치
        map[0][0] = 2; // 뱀의 몸통
        
        int time = 0;
        int dir = 0; // 초기 방향은 동쪽이니 0
        
        int curX = 0;
        int curY = 0;

        while (true) {
            time++;
            // 다음 머리 위치 계산
            int nextX = curX + dx[dir];
            int nextY = curY + dy[dir];

            // 종료 조건 1: 벽에 부딪힘
            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
                return time;
            }
            // 종료 조건 2: 자기 몸에 부딪힘
            if (map[nextX][nextY] == 2) {
                return time;
            }

            // 종료 조건 아니면 이동하기
            if (map[nextX][nextY] == 1) {
                // 사과가 있는 경우: 머리만 늘림
                map[nextX][nextY] = 2;
                snake.offerFirst(new int[]{nextX, nextY});
            } else {
                // 사과가 없는 경우: 머리 늘리고 꼬리 줄임
                map[nextX][nextY] = 2;
                snake.offerFirst(new int[]{nextX, nextY});
                
                int[] tail = snake.pollLast(); // 꼬리 제거
                map[tail[0]][tail[1]] = 0; // 맵에서도 지움
            }

            // 현재 머리 위치 업데이트
            curX = nextX;
            curY = nextY;

            // 방향 바꾸는지 확인
            if (moves.containsKey(time)) {
                char rotate = moves.get(time);
                if (rotate == 'D') {
                    dir = (dir + 1) % 4; // 오른쪽으로 90도
                } else {
                    dir = (dir + 3) % 4; // 왼쪽으로 90도 회전
                }
            }
        }
    }
}
