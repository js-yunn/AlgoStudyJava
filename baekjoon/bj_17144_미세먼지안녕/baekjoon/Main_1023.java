package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1023 {
    static int R, C, T; 
    static int[][] MAP; 
    static int[] CLEANER_R = new int[2]; // 공기청정기 위치

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // R, C, T 읽기
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        MAP = new int[R][C];
        int cleanerIdx = 0;
        
        // 1. 초기 맵 상태 입력 및 공기청정기 위치 저장
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
                if (MAP[i][j] == -1) {
                    CLEANER_R[cleanerIdx++] = i; 
                }
            }
        }

        // 2. T초 동안 먼지 확산 및 청정기 순환
        for (int t = 0; t < T; t++) {
            spread(); // 미세먼지 확산
            clean();  // 공기청정기 순환
        }

        // 3. 최종 남은 미세먼지 총량 출력
        System.out.println(get_dust());
    }

    // 미세먼지를 확산시키는 메서드
    static void spread() {
        int[][] next_map = new int[R][C];
        int[] dx = {-1, 1, 0, 0}; 
        int[] dy = {0, 0, -1, 1}; 

        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (MAP[x][y] > 0) { // 미세먼지가 있는 경우에만 확산
                    int amount = MAP[x][y] / 5; // 확산량 = 현재 먼지 / 5
                    int count = 0;
                    
                    // 4방향으로 확산
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        if (nx >= 0 && nx < R && ny >= 0 && ny < C && MAP[nx][ny] != -1) {
                            next_map[nx][ny] += amount;
                            count++;
                        }
                    }
                    // 현재 칸에 남은 먼지 = (원래 먼지) - (확산된 총량)
                    next_map[x][y] += MAP[x][y] - amount * count;
                }
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (MAP[i][j] != -1) { 
                    MAP[i][j] = next_map[i][j];
                }
            }
        }
    }

    // 공기청정기가 미세먼지를 순환시키는 메서드
    static void clean() {
        int r_up = CLEANER_R[0];   // 위쪽 공기청정기 행
        int r_down = CLEANER_R[1]; // 아래쪽 공기청정기 행

        // 1. 위쪽 공기청정기 순환 (반시계 방향)
        for (int i = r_up - 1; i > 0; i--) {
            MAP[i][0] = MAP[i-1][0];
        }
        for (int j = 0; j < C - 1; j++) {
            MAP[0][j] = MAP[0][j+1];
        }
        for (int i = 0; i < r_up; i++) {
            MAP[i][C-1] = MAP[i+1][C-1];
        }
        for (int j = C - 1; j > 1; j--) {
            MAP[r_up][j] = MAP[r_up][j-1];
        }
        MAP[r_up][1] = 0; 


        // 2. 아래쪽 공기청정기 순환 (시계 방향)
        for (int i = r_down + 1; i < R - 1; i++) {
            MAP[i][0] = MAP[i+1][0];
        }
        for (int j = 0; j < C - 1; j++) {
            MAP[R-1][j] = MAP[R-1][j+1];
        }
        for (int i = R - 1; i > r_down; i--) {
            MAP[i][C-1] = MAP[i-1][C-1];
        }
        for (int j = C - 1; j > 1; j--) {
            MAP[r_down][j] = MAP[r_down][j-1];
        }
        MAP[r_down][1] = 0; 
    }

    // 맵에 남아있는 총 미세먼지 양을 계산하는 메서도
    static int get_dust() {
        int total = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (MAP[i][j] > 0) { 
                    total += MAP[i][j];
                }
            }
        }
        return total;
    }
}


