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

/*class Node implements Comparable<Node>{
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
	
}*/

public class Main_�듅�젙理쒕떒寃쎈줈 {
	static int INF = 200_000_100;
	static int N, M;
	static List<Node>[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// �씤�젒 由ъ뒪�듃 珥덇린�솕
		list = new ArrayList[N+1];
		for (int i=1; i<N+1; i++) {
			list[i]=new ArrayList<Node>();
		}
		
		// 媛꾩꽑 ���옣
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, cost));
			list[to].add(new Node(from, cost)); // �뼇諛⑺뼢 泥섎━
		}
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		int start = 1;
		int end = N;
		
		// 1->v1->v2->N
		int tmp1 = 0;
		tmp1+=Dijkstra(start, v1);
		tmp1+=Dijkstra(v1, v2);
		tmp1+=Dijkstra(v2, end);
		
		// 1->v2->v1->N
		int tmp2 = 0;
		tmp2+=Dijkstra(start, v2);
		tmp2+=Dijkstra(v2, v1);
		tmp2+=Dijkstra(v1, end);
		
		int min_ans = (tmp1>=INF && tmp2>=INF) ? -1: Math.min(tmp1, tmp2);
		
		System.out.println(min_ans);
		
	}
	public static int Dijkstra(int start, int end) {
		Queue<Node> q = new PriorityQueue<>();
		int[] distance = new int[N+1];
		for (int i=0; i<=N; i++) {
			distance[i]=INF;
		}
		
		q.add(new Node(start, 0));
		distance[start]=0;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int to = node.to;
			int dist = node.cost;
			if (distance[to]<dist) continue;
			//check[node.to]=true;
			for (Node next: list[to]) {
				int nextNum = next.to;
				int nextDist = next.cost;
				int cost = dist+nextDist;
				if (cost<distance[nextNum]) {
					distance[nextNum]=cost;
					q.add(new Node(nextNum, cost));
				}
			}
		}
		return distance[end];
		
		
	}

}
