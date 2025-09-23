package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0922 {
	private static int dx[]= {0,0,1,-1};
	private static int dy[]= {1,-1,0,0};
	private static int N;
	private static int[][] map;
	private static boolean[][] visited;
	private static int count;
	private static int[] apartNum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		apartNum = new int[N*N];
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j]= input.charAt(j)-'0';
			}
		} // 입력 끝
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]==1 && !visited[i][j]) {
					count++;
					dfs(i,j); // dfs 호출
				}
			}
		} // dfs 완료
		Arrays.sort(apartNum); // 오름차순 정렬
		
		System.out.println(count); // 총 단지 개수 출력
		for (int i=0; i<N*N; i++) {
			if (apartNum[i]==0) continue;
			else System.out.println(apartNum[i]); // 단지내 집 수 출력
		}
		
	}
	private static void dfs(int x, int y) {
		visited[x][y]=true;
		apartNum[count]++;
		
		for (int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if (nx>=0 && nx<N && ny>=0 && ny<N) {
				if (map[nx][ny]==1 && !visited[nx][ny]) {
					dfs(nx, ny);
				}
			}
		}
	}
}
