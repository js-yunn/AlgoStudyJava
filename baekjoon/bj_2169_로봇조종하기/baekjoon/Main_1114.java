package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1114 {
	static int N, M;
	static int[][] map, dp, temp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dp = new int[N][M];
		temp = new int[2][M];
		
		for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
		// dp 실행 
		dp[0][0]=map[0][0]; // dp 시작점 설정
		for (int i=1; i<M; i++) {
			dp[0][i]=dp[0][i-1]+map[0][i];
		}
		
		for (int i=1; i<N; i++) {
			// 1-1. temp[0]
			temp[0][0]=dp[i-1][0]+map[i][0];
			for (int j=1; j<M; j++) {
				temp[0][j]=Math.max(temp[0][j-1], dp[i-1][j])+map[i][j];
			}
			
			// 1-2. temp[1]
			temp[1][M-1]=dp[i-1][M-1]+map[i][M-1];
			for (int j=M-2; j>=0; j--) {
				temp[1][j]= Math.max(temp[1][j+1], dp[i-1][j])+map[i][j];
			}
			
			// 2. 최대값 dp에 저장
			for (int j=0; j<M; j++) {
				dp[i][j]= Math.max(temp[0][j], temp[1][j]);
			}
		}
		System.out.println(dp[N-1][M-1]);;
	}
}
/*
백준 - 2169 로봇 조종하기

문제
로봇은 왼쪽, 오른쪽, 아래쪽으로 이동 가능하지만 위로는 이동 불가능함

풀이 방법
1. 왼쪽에서 오른쪽으로 오는 경우, 오른쪽에서 왼쪽으로 오는 경우를 배열에 저장해두고 두 값 중 최대값을 선택함
-> temp[0][j]는 왼쪽에서 오는 값, temp[1][j]는 오른쪽에서 오는 값
1-1. temp[0]의 경우 = Math.max(temp[0][j - 1], dp[i - 1][j]) + map[i][j]
	위에서 오는 값, 왼쪽에서 오는 값 중 최대값 저장
1-2. temp[1]의 경우 = Math.max(temp[1][j + 1], dp[i - 1][j]) + map[i][j]
	위에서 오는 값, 오른쪽에서 오는 값 중 최대값 저장
2. dp[i][j] = Math.max(temp[0][j], temp[1][j]) 로 dp 배열 값 업뎃

*/