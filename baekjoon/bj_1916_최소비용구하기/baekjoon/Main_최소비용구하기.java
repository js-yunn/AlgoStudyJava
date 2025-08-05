package baekjoon;
/*
 * 
5
8
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
1 5
 */

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	int to;
	int cost;
	
	public Node(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		// 우선순위 큐에서 비용 작은 노드가 먼저 오도록
		return this.cost-o.cost;
	}
}

public class Main_최소비용구하기 {
	static List<Node>[] list; // 간선 정보 저장
	static int[] dp; // 시작 도시에서 각 도시까지 최소 비용 저장
	static boolean[] check; // 방문 여부 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		list =(ArrayList<Node>[]) new ArrayList[N+1];
		dp = new int[N+1];
		check = new boolean[N+1];
		// 도시의 인접 리스트 초기화
		for (int i=1; i<N+1; i++) {
			list[i]=new ArrayList<>();
		}
		// 버스 정보 list에 저장
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()); // 출발 도시
			int to = Integer.parseInt(st.nextToken()); // 도착 도시
			int cost = Integer.parseInt(st.nextToken()); // 비용
			
			list[from].add(new Node(to, cost)); // 출발 도시 기준으로 간선 추가
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		dijkstra(start);
		System.out.println(dp[end]); 

	}
	// 다익스트라 알고리즘 구현
	static void dijkstra(int start) {
		Queue<Node> q = new PriorityQueue<>();
		Arrays.fill(dp, Integer.MAX_VALUE); // 모든 비용 무한대로 초기화
		dp[start]=0;
		q.add(new Node(start, 0)); // 시작 노드 큐에 삽입
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int to = node.to;
			
			if (check[to]) continue; // 이미 방문했다면 무시
			check[node.to] = true; // 방문 처리
			// 현재 도시와 연결된 모든 도시 탐색
			for (Node next: list[to]) {
				// 현재보다 더 적은 비용 발견 시 갱신
				if (dp[next.to] >= dp[to]+next.cost) {
					dp[next.to]=dp[to]+next.cost;
					q.add(new Node(next.to, dp[next.to])); // 큐에 추가
					
				}
			}
		}
	}

}
