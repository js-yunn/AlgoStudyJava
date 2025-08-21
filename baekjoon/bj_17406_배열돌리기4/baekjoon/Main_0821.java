package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0821 {
	static int N, M, K;
	static int[][] original;
	static int[][] arr;
	

	public static void main(String[] args) throws Exception{
		/*
		 * (1,2)부터(5,6) 까지 시계 방향으로 한 칸씩 돌리기 연산
		 * K 개의 회전 입력값 들어오고, 순서는 상관없음
		 * k는 1부터 6까지, 6개 입력 들어오면 총 720가지 모두 탐색해야 함
		 * 
		 * 
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		original = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j=0; j<M; j++) {
				
			}
		}
		
	}

}
