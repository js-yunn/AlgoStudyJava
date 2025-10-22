package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1022 {
	static int N;
	static int L, R;
	static int[][] earth;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int bfs(int startX, int startY, boolean[][] visited, Queue<Point> union ) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(startX, startY));
		visited[startX][startY]=true;
		union.add(new Point(startX, startY));
		int totalPopulation = earth[startX][startY];
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for (int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if (nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if (visited[nx][ny]) continue;
				int diff = Math.abs(earth[now.x][now.y]-earth[nx][ny]);
				if (diff>=L && diff<=R) {
					q.add(new Point(nx, ny));
					visited[nx][ny]=true;
					union.add(new Point(nx,ny));
					totalPopulation += earth[nx][ny];
				}
			}
		}
		return totalPopulation;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		earth = new int[N][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				earth[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int days = 0;
		
		while (true) {
			boolean is_moved = false;
			boolean[][] visited = new boolean[N][N];
			
			// 모든 나라 순회, bfs 진행
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (!visited[i][j]) { // 아직 방문하지 않은 나라 bfs 탐색 시작
						Queue<Point> union = new LinkedList<>(); // 연합한 나라 좌표값 저장하는 union 큐
						int totalPopulation = bfs(i,j,visited, union);
						int countryCnt = union.size();
						// 연합한 나라 개수가 2개 이상이면 인구 이동시키기
						if (countryCnt > 1) {
							is_moved = true;
							int newPopulation = totalPopulation/countryCnt;
							for (Point p: union) { // 연합한 나라 모두 인구 갱신
								earth[p.x][p.y]= newPopulation;
							}
						}
					}
					
				}
			}
			
			if (!is_moved) break;
			days++;
		}
		System.out.println(days);
		/*
		 시뮬: 모든 나라 순회 
		 어떤 연합에도 속하지 않은 나라 발견하면 bfs 수행
		 bfs 4방탐색하면서 L~R 에 속하면 연합으로 묶기
		 묶기 전에 이미 방문한 곳인지 체크하기
		 연합할 때마다 방문 체크하고 연합 인구 수, 국가 개수 늘리기
		 
		 탐색 완료: 연합 내 모든 인구를 평균 인구수로 갱신하기, day 늘리기
		 
		 종료 조건: 연합 하나도 안하면 종료
		 */
		
		/*
		 수도코드
		 days = 0
		 while true:
		 	is_moved = false
		 	visited[N][N]
		 	for i in 0 to N-1:
		 		for j in 0 to N-1:
		 			if not visited[i][j]:
		 				union_list, population_sum, country_cnt = bfs(i,j)
		 				if country_cnt>1:
		 					is_moved = true
		 					new_population = population_sum/country_cnt
		 					updatePopulation(union_list, new_population)
		 	if not is_moved:
		 		break
		 	days+=1
		 return days
		 */
		
	}
}

