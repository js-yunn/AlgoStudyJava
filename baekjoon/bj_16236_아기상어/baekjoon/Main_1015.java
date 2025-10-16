package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1015 {
	static int N;
	static int[][] map;
	static int size = 2; // 아기 상어의 현재 크기
	static int eatCnt = 0; // 현재 먹은 물고기 수
	static int babyR, babyC; // 아기 상어의 위치
	static int totalTime = 0; // 총 이동 시간(정답)
	
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	
	static class Fish implements Comparable<Fish>{
		int r, c;
		int dist;
		public Fish(int r, int c, int dist) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
		@Override
		public int compareTo(Fish o) {
			if (this.dist != o.dist) { // 1순위: 거리
				return this.dist-o.dist;
			}
			if (this.r!=o.r){ // 2순위: 행
				return this.r-o.r;
			}
			return this.c-o.c; // 3순위: 열
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if (map[i][j]==9) {
					babyR = i; babyC = j;
					map[i][j]= 0; // 이제 아기 상어 위치는 빈 칸으로 두기
				}
			}
		} // 입력 완료
		// 시뮬 시작
		while (true) {
			Fish next = findFish(babyR, babyC);
			if (next == null) break; // 더 이상 먹을 물고기 없으면 종료
			// 물고기 먹고 상태 업뎃
			totalTime += next.dist;
			babyR = next.r;
			babyC = next.c;
			map[babyR][babyC]=0; // 원래 있던 물고기 먹고 빈 칸 처리
			eatCnt ++;
			// 아기 상어 성장 조건 업뎃
			if (eatCnt == size) {
				size++;
				eatCnt=0;
			}
		}
		System.out.println(totalTime);
	}
	
	// 현재 위치에서 먹을 수 있는 가장 가까운 물고기 BFS로 탐색
	private static Fish findFish(int startR, int startC) {
		Queue<int[]> q = new ArrayDeque<>();
		int[][] dist = new int [N][N]; // 시작점부터 최단 거리
		q.offer(new int[] {startR, startC});
		
		PriorityQueue<Fish> candidates = new PriorityQueue<>();
		
		// BFS 시작
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int r = now[0];
			int c = now[1];
			int d = dist[r][c];
			
			for (int i=0; i<4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				// 1. 경계 조건 확인
				if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
				// 2. 이미 방문한 곳인지 확인
				if (dist[nr][nc]!=0) continue;
				// 3. 이동 가능한지 물고기 크기 확인
				if (map[nr][nc] > size) continue;
				// 4. 이동 가능, 이동 처리
				dist[nr][nc]= d+1;
				// 물고기 크기가 작으면 먹이 후보에 추가
				if (map[nr][nc]!=0 && map[nr][nc]<size) {
					candidates.offer(new Fish(nr, nc, d+1));
				}
				q.offer(new int[] {nr, nc});
			}
		}
		return candidates.poll();
	}
}

/*
 * 아기 상어 크기는 2
 * 자기보다 큰 물고기 칸은 지나갈 수 없음.
 * 자기보다 작은 물고기는 먹음.
 * 자기랑 같은 물고리는 먹을 수 없지만 지나갈 수는 있음.
 * 
 * 이동 방법
 * 1. 먹을 수 있는 물고기가 1마리면 그 물고기를 먹으러 감.
 * 2. 1마리 이상이면 가장 거리가 가까운 물고기를 먹으러 감.
 * 2-1. 거리가 같은 물고기가 많다면, 가장 위에 있는 물고기,
 * 							것도 여러마리면 가장 왼쪽에 있는 물고기 먹으러 감.
 * 
 * 아기 상어는 자기 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가함.
 * 크기 3인 아기 상어는 물고기 3마리 먹으면 크기 4 됨.
 * 
 */