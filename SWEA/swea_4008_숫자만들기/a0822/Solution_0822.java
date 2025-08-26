package a0822;
import java.util.*;
import java.io.*;

public class Solution_0822 {
    static int[] number; 
    static int[] operator; 
    static int N, minVal, maxVal; 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            
            number = new int[N];
            operator = new int[4]; // 0:덧셈, 1:뺄셈, 2:곱셈, 3:나눗셈
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                operator[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                number[i] = Integer.parseInt(st.nextToken());
            }

            minVal = Integer.MAX_VALUE;
            maxVal = Integer.MIN_VALUE;

            // DFS 1부터 탐색 시작.
            dfs(1, number[0]); 

            System.out.println("#" + t + " " + (maxVal - minVal)); // 최대 최소 차이 출력
        }
    }

    public static void dfs(int depth, int sum) {
        if (depth == N) { // 모든 경우의 수 탐색이 끝나면 maxVal, minVal 갱신
            minVal = Math.min(minVal, sum);
            maxVal = Math.max(maxVal, sum);
            return;
        }

        // 4가지 연산자(+, -, *, /)를 순회하며 탐색
        for (int i = 0; i < 4; i++) {
            if (operator[i] == 0) {
                continue; // 남아있는 연산자가 없으면 다음 연산자로
            }
            // 백트래킹
            operator[i]--;

            if (i == 0) { // 덧셈
                dfs(depth + 1, sum + number[depth]);
            } else if (i == 1) { // 뺄셈
                dfs(depth + 1, sum - number[depth]);
            } else if (i == 2) { // 곱셈
                dfs(depth + 1, sum * number[depth]);
            } else { // 나눗셈
                dfs(depth + 1, sum / number[depth]);
            }

            // 백트래킹 복원
            operator[i]++;
        }
    }
}