package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1203 {
    static final int left = 1000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][][] dp = new long[N + 1][10][1024]; //[길이][마지막 숫자][비트마스크]
      
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1; // 초기값 설정
        }

        // DP 실행
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < 1024; k++) {
                    
                    if (dp[i][j][k] == 0) continue;

                    // 다음 숫자가 j-1인 경우 (0보다 크거나 같아야 함)
                    if (j - 1 >= 0) {
                        int nextMask = k | (1 << (j - 1)); // 기록 합치기
                        dp[i + 1][j - 1][nextMask] = (dp[i + 1][j - 1][nextMask] + dp[i][j][k]) % left;
                    }
 
                    // 다음 숫자가 j+1인 경우 (9보다 작거나 같아야 함)
                    if (j + 1 <= 9) {
                        int nextMask = k | (1 << (j + 1));
                        dp[i + 1][j + 1][nextMask] = (dp[i + 1][j + 1][nextMask] + dp[i][j][k]) % left;
                    }
                }
            }
        }

        // 결과 구하기
        long sum = 0;
        for (int j = 0; j <= 9; j++) {
            sum = (sum + dp[N][j][1023]) % left;
        }

        System.out.println(sum);
    }
}
