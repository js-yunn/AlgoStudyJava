package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1029 {
	static int R, C;
	static char[][] board;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static boolean[] isUsed = new boolean[26]; // A~Z 사용 여부 체크용
	static int maxDistance = 0;

	public static void main(String[] args) throws Exception {
		// 1. 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		for (int i=0; i<R; i++) {
			String input = br.readLine();
			for (int j=0; j<C; j++) {
				board[i][j]=input.charAt(j);
			}
		}
		// 2. dfs 돌리기, 내부에서 distance 비교하기
		dfs(0, 0, 1);
		
		// 3. maxDistance 출력
		System.out.println(maxDistance);
	}
	
	public static void dfs(int r, int c, int d) {
		isUsed[board[r][c]-'A'] = true; // 현재 칸 알파벳 사용 체크
		maxDistance = Math.max(d, maxDistance); // 최대 거리 갱신
		
		for (int i=0; i<4; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			// 범위 체크 -> isUsed 체크 -> 백트래킹
			if (nr>=0 && nr<R && nc>=0 && nc<C) {
				if (!isUsed[board[nr][nc]-'A']) {
					dfs(nr, nc, d+1);
					
					isUsed[board[nr][nc]-'A']= false;
				}
			}
		}
		isUsed[board[r][c]-'A'] = false;
	}

}

/*
세로 R, 가로 C칸
1행 1열에 말 놓여있음.
말은 4방으로 이동하는데 새로 이동한 칸에 적힌 알파벳은 지금까지 지난 알파벳과 달라야 함.
말이 (1,1)에서 최대 몇 칸을 지날 수 있는지 구하기

처음엔 큐로 말이 지나간 칸 저장해서 하나하나 확인하려 했는데 그러면 시간 복잡도 O(L)
큐가 아니라 알파벳 크기 26만큼 boolean isused 배열 만들어서 비교하면 시간 복잡도 O(1)
dfs로 풀기

*/