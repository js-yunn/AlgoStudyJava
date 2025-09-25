package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0925 {
	static int N, M, minTime;
	static int[][] map;
	static ArrayList<Virus> virusMap = new ArrayList<>();
	static int virusCnt;
	static int[] selected;
	static int emptyCnt = 0; // 초기 입력된 빈칸 개수
	static private class Virus {
		int x, y, time;

		public Virus(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time= time;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		minTime = 2501;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
				if (map[i][j]==2) { // 바이러스 위치 저장
					virusMap.add(new Virus(i,j,0));
				}else if (map[i][j]==0) { // 빈칸 개수 저장
					emptyCnt++;
				}
			}
		}
		
		virusCnt = virusMap.size();
		selected = new int[M]; // M개의 바이러스 선택
		if (emptyCnt == 0) { // 빈칸 없으면 0 출력
			System.out.println(0);
		}else {
			comb(0,0);
			if (minTime == 2501) System.out.println(-1);
			else System.out.println(minTime);
		}
		br.close();
	}
	
	private static void comb(int cnt, int start) {
		if (cnt == M) {
			// m개 조합대로 bfs 돌리기
			minTime = Math.min(minTime, bfs(selected));
			return;
		}
		for (int i=start; i<virusCnt; i++) {
			selected[cnt]=i;
			comb(cnt+1, i+1);
		}
	}
	
	private static int bfs(int[] selected) {
		Queue<Virus> q = new LinkedList<>();
		int [][] visited = new int[N][N]; // 방문 시간 기록 (-1: 미방문)
		for (int i=0; i<N; i++) {
			Arrays.fill(visited[i], -1);
		}
		// 조합에서 선택된 바이러스는 모두 큐에 넣고 방문 시간 0으로 초기값 설정
		for (int idx : selected) {
			Virus v = virusMap.get(idx);
			q.add(new Virus(v.x, v.y, 0));
			visited[v.x][v.y]=0;
		}
		
		int maxTime = 0; // 최종적으로 바이러스 퍼지는 데 걸리는 시간
		int spreadCnt = 0; // 바이러스 퍼진 빈칸 개수
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		while (!q.isEmpty()) {
			Virus now = q.poll();
			if (map[now.x][now.y]==0) { // 빈칸이면 maxTime 값 갱신
				maxTime = Math.max(maxTime, now.time);
				spreadCnt++;
			}
			for (int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if (nx>=0 && nx<N && ny>=0 && ny<N && 
					visited[nx][ny]==-1 && map[nx][ny]!=1) {
					visited[nx][ny]=now.time+1; // 방문 시간 기록
					q.add(new Virus(nx, ny, now.time+1)); // 큐에 추가
				}
			}
		}
		if (spreadCnt != emptyCnt) { // 전부 도달이 불가능하면 2501 리턴
			return 2501;
		}
		return maxTime; // 전부 도달 가능하면 maxTime 리턴
		
		
	}

}