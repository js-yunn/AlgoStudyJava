package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0829 {
	static int K, W, H;
	static int minMove;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int[] hdx = {-2,-1,1,2,2,1,-1,-2};
	static int[] hdy = {-1,-2,-2,-1,1,2,2,1};
	static class Node {
		int x, y;
		int jump;
		int dist;
		public Node(int x, int y, int jump, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.jump = jump;
			this.dist = dist;
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W][K+1];
		
		for (int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j=0; j<W; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs());

	}
	static int bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0,0,0,0));
		visited[0][0][0]=true;
		while (!q.isEmpty()) {
			Node now = q.poll();
			int x = now.x;
			int y = now.y;
			if (x==H-1 && y==W-1) return now.dist;
			//원숭이처럼 이동 먼저
			for (int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if (nx<0 || ny<0 || nx>=H || ny>=W || visited[nx][ny][now.jump]) {
					continue;
				}
				if (map[nx][ny]==0) {
					q.offer(new Node(nx, ny, now.jump, now.dist+1));
					visited[nx][ny][now.jump]=true;
				}
			}
			// 말처럼 이동하는 횟수 남으면 이동
			if (now.jump<K) {
				for (int i=0; i<8; i++) {
					int nx = x+hdx[i];
					int ny = y+hdy[i];
					if (nx<0 || ny<0 || nx>=H || ny>=W || visited[nx][ny][now.jump+1]) {
						continue;
					}
					if (map[nx][ny]==0) {
						q.offer(new Node(nx, ny, now.jump+1, now.dist+1));
						visited[nx][ny][now.jump+1]=true;
					}
				}
			}
		}
		return -1;
	}

}
