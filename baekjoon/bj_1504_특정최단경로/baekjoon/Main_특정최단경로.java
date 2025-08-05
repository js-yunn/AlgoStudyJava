package baekjoon;
/*
4 6
1 2 3
2 3 3
3 4 1
1 3 5
2 4 5
1 4 4
2 3
 */
import java.util.*;
import java.io.*;

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

public class Main_특정최단경로 {
	static List<Node>[] list;
	static int[] dp;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		list = (ArrayList<Node>[]) new ArrayList[N+1];
		dp = new int[N+1];
		check = new boolean[N+1];
		
		// 인접 리스트 초기화
		for (int i=1; i<N+1; i++) {
			list[i]=new ArrayList<>();
		}
		// 간선 저장
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, cost));
			list[to].add(new Node(from, cost)); // 양방향 처리
		}
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		int start = 1;
		int end = N;
		
		int min_ans = 0;
		// 1->v1->v2->N
		int tmp = 0;
		tmp+=Dijkstra(start, v1);
		tmp+=Dijkstra(v1, v2);
		tmp+=Dijkstra(v2, end);
		min_ans = tmp;
		
		// 1->v2->v1->N
		tmp = 0;
		tmp+=Dijkstra(start, v2);
		tmp+=Dijkstra(v2, v1);
		tmp+=Dijkstra(v1, end);
		min_ans=Math.min(min_ans, tmp);
		System.out.println(min_ans);
		
	}
	public static int Dijkstra(int start, int end) {
		Queue<Node> q = new PriorityQueue<>();
		Arrays.fill(dp, Integer.MAX_VALUE);
		boolean[] check = new boolean[dp.length];
		dp[start]=0;
		q.add(new Node(start, 0));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int to = node.to;
			if (check[to]) continue;
			check[node.to]=true;
			for (int i=0; i<list[node.to].size(); i++) {
				Node next = list[node.to].get(i);
				
				if(dp[next.to]>=dp[to]+next.cost) {
					dp[next.to]=dp[to]+next.cost;
					q.offer(new Node(next.to, dp[next.to]));
				}
			}
		}
		return dp[end];
		
		
	}

}
