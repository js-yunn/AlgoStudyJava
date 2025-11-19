package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1121 {
	static int N;
	static int[][] forest;
	static int[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		forest = new int[N][N];
		visited = new int[N][N];
		
		for (int i=0 ;i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				forest[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int maxResult = 0; // 이동할 수 있는 최대 칸 수
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				maxResult = Math.max(maxResult, dfs(i,j));
			}
		}
		System.out.println(maxResult);

	}

	private static int dfs(int x, int y) {
		if (visited[x][y]!=0) {
			return visited[x][y]; // 이미 방문한 곳이면 리턴
		}
		visited[x][y]=1;
		
		for (int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if (nx>=0 && nx<N && ny>=0 && ny<N && forest[nx][ny]>forest[x][y]) {
				visited[x][y]=Math.max(visited[x][y], dfs(nx, ny)+1); // visited[x][y] 최장경로 갱신
			}
		}
		return visited[x][y];
	}

}

/*
이동 칸 수에 시작하는 칸도 포함함
dfs를 해야하는데 완전 탐색으로 하면 무조건 메모리 터짐 -> visited 배열을 정수로 선언하기
dfs 진행하면서 최장 경로 갱신 : Math.max(visited[x][y], dfs(nx,ny)+1)
이미 방문한 곳이면 visited[x][y] 리턴하기
아니면 상하좌우로 돌면서 visited(x,y) 최장 경로 갱신
*/