package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1726_로봇 {
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static int[][][] minCommands;
	static int M, N;
	static int[][] map;
	static int moved, totalMoved;
	public static void main(String[] args) throws Exception {
		
		// 1. 입력받기
		
		
		
		// 2. 출발 위치, 방향 큐에 넣기
		
		
		
		// 3. bfs 돌리기
		
		
		// 4. bfs 실행
		
		
		// 5. minCommands[endr][endc][endd] 출력하기
		
	}
	// 4. bfs 명령 메소드
	// 5. bfs 실행, 큐에서 r, c, d, count 꺼내기
	// 6-1. go k 의 경우, k = 1,2,3에 대해 모두 반복, 다음 위치 nr, nc, d 계산
	// minCommands[nr][nc][d]가 minCommands[r][c][d]+1보다 크면 갱신하고 큐에 
	// (nr, nc, d, count+1) 큐에 추가
	
	
	// 6-2. turn의 경우, left, right에 나뉨. left인 경우 r, c, nd 계산
	// minCommands[r][c][nd]> minCommancs[r][c][d]+1 이면 갱신하고 큐에 넣기
	
	
	
	
}