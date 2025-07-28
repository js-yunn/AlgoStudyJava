
import java.io.*;
import java.util.*;

public class Main {
    static final int[] dx = { -1, 0, 1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int map[][];
    static boolean v[][];
    static int now_x, now_y;
    static int M, N, K;
    static int count;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("baekjoon\\bj_1012_유기농배추\\res\\input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // testcase
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            v = new boolean[N][M];

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = 1;
            }
            count = 0;
            for (int j = 0; j < N; j++) {
                for (int m = 0; m < M; m++) {
                    if (map[j][m] == 1 && v[j][m] == false) {
                        count++;
                        DFS(m, j);
                    }
                }
            }
            sb.append(count).append('\n');

        }
        System.out.println(sb);

    }

    public static void DFS(int x, int y) {
        v[y][x] = true;
        for (int i = 0; i < 4; i++) {
            now_x = x + dx[i];
            now_y = y + dy[i];
            if (now_x < 0 || now_x >= M || now_y < 0 || now_y >= N) // 범위 체크
                continue;
            if (v[now_y][now_x]) // 방문한 적 있는지 체크
                continue;
            if (map[now_y][now_x] == 1) { // 배추가 심어져있다면 DFS
                DFS(now_x, now_y);
            }
        }
    }

}