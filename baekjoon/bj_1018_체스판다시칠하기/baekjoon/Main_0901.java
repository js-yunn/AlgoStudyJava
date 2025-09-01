package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0901 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] board = new char[N][M];
		for (int i=0; i<N; i++) {
			String line = br.readLine();
			for (int j=0; j<M; j++) {
				board[i][j]=line.charAt(j);
			}
		}
		int minAnswer = Integer.MAX_VALUE;
		// 8*8 크기 판
		for (int i=0; i<N-7; i++) {
			for (int j=0; j<M-7; j++) {
				int white = 0; // 시작 칸 흰색일 때 다시 칠해야하는 칸 수
				int black = 0; 
				for (int x=i; x<i+8; x++) {
					for (int y=j; y<j+8; y++) {
						if ((x+y)%2==0) {
							if (board[x][y]=='B') white++; // 흰색이어야함
							else black++;
						} else {
							if (board[x][y]=='W') white++; // 검은색이어야함
							else black++;
						}
					}
				}
				int nowMin = Math.min(white, black);
				minAnswer = Math.min(minAnswer, nowMin);
			}
		}
		System.out.println(minAnswer);
	}
}
