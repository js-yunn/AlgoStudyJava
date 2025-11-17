package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1113 {
	static long[][] map;
	// 공사 중인 도로 배열 정보(1은 공사중, 0은 통행 가능)
	static int[][] horizon; // 가로 도로 (x,y)~(x+1,y)
	static int[][] vertical; // 세로 도로 (x,y)~(x,y+1)
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new long[N+1][M+1];
		horizon = new int[N][M+1]; // 가로니까 x 좌표는 N까지만 필요함
		vertical = new int[N+1][M]; // 세로니까 y 좌표는 M까지만 필요함
		int K = Integer.parseInt(br.readLine());
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			if (y1 == y2) { // y 좌표가 같으면 가로 도로 정보임
				int minX = Math.min(x1, x2);
				horizon[minX][y1]= 1;
			}
			else { // x 좌표가 같으면 세로 도로 정보임
				int minY = Math.min(y1, y2);
				vertical[x1][minY]=1;
			}
		}
		// dp 시작
		map[0][0]=1;
		
		// 가로축 j=0 인 줄 초기화(가로로 공사하는 도로만 확인하면 됨)
		for (int i=1; i<=N; i++) {
			if (horizon[i-1][0]==0) {
				map[i][0]=map[i-1][0];
			}else {
				map[i][0]=0;
			}
		}
		
		// 세로축 i=0 인 줄 초기화(세로로 공사하는 도로만 확인하면 됨)
		for (int j=1; j<=M; j++) {
			if (vertical[0][j-1]==0) {
				map[0][j]= map[0][j-1];
			}else {
				map[0][j]=0;
			}
		}
		
		// 나머지 영역은 dp 대로 계산, (i-1,j)에서 오는 경로+(i,j-1)에서 오는 경로의 수 합치기 연산하면 됨
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=M; j++) {
				long left = 0;
				long up = 0;
				if (horizon[i-1][j]==0) { // 통행 가능한지 확인
					left = map[i-1][j];
				}
				if (vertical[i][j-1]==0) {
					up = map[i][j-1];
				}
				map[i][j]= left+up;
			}
		}
		
		System.out.println(map[N][M]);
		
	}
}

/*
백준 1577-도로의 개수

공사중인 도로 개수 k개
(a,b), (c,d)

처음 생각 - dp로 풀 수 있을 듯
i,j 도착하는 경우의 수 = (i-1,j)에서 오는 경로+(i,j-1)에서 오는 경로의 수 합치기 연산하면 됨
근데 공사 중인 도로 처리하는 방법: 더할 때 공사중인지 확인해서 아니면 더하고 공사 중이면 안 더하기

자료형은 long타입, 공사중인 도로 정보는 2차원 배열로 저장하기. 즉 2차원 배열을 2개 선언하기

문제 - 공사 정보 담는 배열을 1개에 다 담을수가 없어서 가로 도로, 세로 도로를 따로 받아야 함
*/
