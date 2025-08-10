package baekjoon;
import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	int x, y, cost;
	public Node(int x, int y, int cost) {
		this.x=x;
		this.y=y;
		this.cost=cost;
	}
	@Override
	public int compareTo(Node o) {
		return this.cost-o.cost;
	}
	
}

public class Main_bj_4485_녹색옷젤다 {
	static int[][] map;
	static int[][] dist;
	static int index;
	static int T;
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1};
	
	static void dijkstra() {
		Queue<Node> q = new PriorityQueue<>();
		q.offer(new Node(0,0,0));
		while (!q.isEmpty()) {
			Node now = q.poll();
			int cost = now.cost;
			if (dist[now.x][now.y] < cost) continue;
			// 4방 탐색
			for (int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if (nx<0||nx>=T||ny<0||ny>=T) continue;
				int ncost = dist[now.x][now.y]+map[nx][ny];
				// ncost가 현재 dist[nx][ny]에 저장된 값보다 작다면 ncost로 갱신
				if (ncost<dist[nx][ny]) {
					dist[nx][ny]=ncost;
					q.offer(new Node(nx, ny, ncost));
				}
			}
		}
	}
	

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("bj_4485_녹색옷젤다/res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		index = 1;
		
		while (T!=0) {
			map = new int[T][T];
			dist = new int[T][T];
			// 맵 입력받기
			for (int i=0; i<T;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<T;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			// 다익스트라 알고리즘 수행
			dist[0][0]=map[0][0];
			dijkstra();
			
			// 최소 금액 출력
			System.out.println("Problem "+index+": "+dist[T-1][T-1]);
			
			// 다음 테스트케이스 크기 입력
			st = new StringTokenizer(br.readLine());
			T = Integer.parseInt(st.nextToken());
			index++;
		}

	}

}
