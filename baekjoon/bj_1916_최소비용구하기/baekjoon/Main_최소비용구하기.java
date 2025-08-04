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
		return this.cost-o.cost;
	}
}

public class Main_최소비용구하기 {
	static List<Node>[] list;
	static int[] dp;
	static boolean[] check;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		list =(ArrayList<Node>[]) new ArrayList[N+1];
		dp = new int[N+1];
		check = new boolean[N+1];
		for (int i=1; i<N+1; i++) {
			list[i]=new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list[from].add(new Node(to, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		dijkstra(start);
		System.out.println(dp[end]);

	}
	
	static void dijkstra(int start) {
		Queue<Node> q = new PriorityQueue<>();
		Arrays.fill(dp, Integer.MAX_VALUE);
		q.add(new Node(start, 0));
		dp[start]=0;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int to = node.to;
			if (check[to]) continue;
			
			check[node.to] = true;
			for (Node next: list[to]) {
				if (dp[next.to] >= dp[to]+next.cost) {
					dp[next.to]=dp[to]+next.cost;
					q.add(new Node(next.to, dp[next.to]));
					
				}
			}
		}
	}

}
