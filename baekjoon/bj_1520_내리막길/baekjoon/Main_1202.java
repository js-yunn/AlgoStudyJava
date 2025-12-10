package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1202 {
    static int M, N;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 순서
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); 
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; // 방문하지 않음도 표시하기 위해 -1로 초기화시킴
            }
        }

        System.out.println(dfs(0, 0));
    }

    public static int dfs(int x, int y) {
        // 1. 도착 지점에 도달하면 경로 1개 리턴하기
        if (x == M - 1 && y == N - 1) {
            return 1;
        }

        // 2. 이미 방문한 적이 있다면 dp 값 리턴하기
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        // 3. 탐색 시작
        dp[x][y] = 0; // 현재 위치 0으로 방문 처리하고 누적시키기
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 맵 범위를 벗어나지 않고, 현재 높이보다 낮은 곳으로만 이동
            if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                if (map[x][y] > map[nx][ny]) {
                    dp[x][y] += dfs(nx, ny);
                }
            }
        }

        return dp[x][y];
    }
}
