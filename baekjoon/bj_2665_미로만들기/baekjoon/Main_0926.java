package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0926 {
	static int N; 
	static int[][] map;
	static int[][] dist;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	private static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dist = new int[N][N]; // 최소 방 바꾸는 횟수 저장
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j]= input.charAt(j)-'0';
				dist[i][j]= Integer.MAX_VALUE;
			}
		}
		bfs();
		System.out.println(dist[N-1][N-1]);
		br.close();
	}
	
	private static void bfs() {
		Deque<Node> dq = new LinkedList<>(); //양쪽에 추가삭제하도록 덱 사용
		dq.addFirst(new Node(0,0));
		dist[0][0]=0;
		
		while (!dq.isEmpty()) {
			Node now = dq.pollFirst();
			for (int d=0; d<4; d++) {
				int nx = now.x+dx[d];
				int ny = now.y+dy[d];
				if (nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if (map[nx][ny]==0) { // 검은 방인 경우
					if (dist[nx][ny]>dist[now.x][now.y]+1) {
						dist[nx][ny]= dist[now.x][now.y]+1;
						dq.addLast(new Node(nx, ny)); // 가중치 1이라 덱 뒤에 추가
					}
				}
				else { // 흰 방인 경우
					if (dist[nx][ny]>dist[now.x][now.y]) {
						dist[nx][ny]=dist[now.x][now.y];
						dq.addFirst(new Node(nx, ny)); // 가중치 0이라 덱 앞에 추가
					}
				}
				
			}
		}
	}
}
