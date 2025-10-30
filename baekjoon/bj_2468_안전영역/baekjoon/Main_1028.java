package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1028 {
	static int N;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int maxSafe = 1; // 최대 안전 영역 개수, 비가 안 오면 1임
	

	public static void main(String[] args) throws Exception {
		// 1. 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		int minH = 101; // 최소 높이
		int maxH = 0; // 최대 높이
		for (int i=0; i<N; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
				minH = Math.min(minH, arr[i][j]);
				maxH = Math.max(maxH, arr[i][j]);
			}
		}
		
		// 2. 물 높이를 minH부터 maxH까지 반복
		for (int h=minH; h<maxH; h++) {
			// 3. 각 h마다 visited와 nowSafe 초기화시키기
			visited = new boolean[N][N];
			int nowSafe = 0;
			
			// 4. 지도 전체를 순회하면서 안전 영역 찾기
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (arr[i][j]>h && !visited[i][j]) {
						nowSafe++;
						dfs(i, j, h);
					}
				}
			}
			// 5. 현재 h에서 안전 영역 개수 nowSafe랑 maxSafe 비교해서 최대 개수 갱신하기
			maxSafe = Math.max(maxSafe, nowSafe);
		}
		System.out.println(maxSafe);
		
		

	}
	
	public static void dfs(int r, int c, int h) {
		visited[r][c]=true;
		for (int i=0; i<4; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if (nr>=0 && nr<N && nc>=0 && nc<N) {
				if (!visited[nr][nc] && arr[nr][nc]>h) {
					dfs(nr, nc, h);
				}
			}
		}
	}

}
/*
지역 높이의 최대값, 최소값 찾아서 최소값~최대값까지 순차적으로 검사하면서 
현재 높이에서의 안전 영역 개수 구하기
dfs 탐색 진행, 안전 영역 개수 카운트는 밖에서 하기
maxcount랑 현재 탐색 완료 후 nowcount랑 비교해서 max값 갱신하기
마지막에 maxcount 출력하기
*/