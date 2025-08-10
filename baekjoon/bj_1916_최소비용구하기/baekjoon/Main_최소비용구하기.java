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

/*class Node implements Comparable<Node>{
	int to;
	int cost;
	
	public Node(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		// �슦�꽑�닚�쐞 �걧�뿉�꽌 鍮꾩슜 �옉�� �끂�뱶媛� 癒쇱� �삤�룄濡�
		return this.cost-o.cost;
	}
}*/

public class Main_理쒖냼鍮꾩슜援ы븯湲� {
	static List<Node>[] list; // 媛꾩꽑 �젙蹂� ���옣
	static int[] dp; // �떆�옉 �룄�떆�뿉�꽌 媛� �룄�떆源뚯� 理쒖냼 鍮꾩슜 ���옣
	static boolean[] check; // 諛⑸Ц �뿬遺� ���옣

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		list =(ArrayList<Node>[]) new ArrayList[N+1];
		dp = new int[N+1];
		check = new boolean[N+1];
		// �룄�떆�쓽 �씤�젒 由ъ뒪�듃 珥덇린�솕
		for (int i=1; i<N+1; i++) {
			list[i]=new ArrayList<>();
		}
		// 踰꾩뒪 �젙蹂� list�뿉 ���옣
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()); // 異쒕컻 �룄�떆
			int to = Integer.parseInt(st.nextToken()); // �룄李� �룄�떆
			int cost = Integer.parseInt(st.nextToken()); // 鍮꾩슜
			
			list[from].add(new Node(to, cost)); // 異쒕컻 �룄�떆 湲곗��쑝濡� 媛꾩꽑 異붽�
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		dijkstra(start);
		System.out.println(dp[end]); 

	}
	// �떎�씡�뒪�듃�씪 �븣怨좊━利� 援ы쁽
	static void dijkstra(int start) {
		Queue<Node> q = new PriorityQueue<>();
		Arrays.fill(dp, Integer.MAX_VALUE); // 紐⑤뱺 鍮꾩슜 臾댄븳��濡� 珥덇린�솕
		dp[start]=0;
		q.add(new Node(start, 0)); // �떆�옉 �끂�뱶 �걧�뿉 �궫�엯
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int to = node.to;
			
			if (check[to]) continue; // �씠誘� 諛⑸Ц�뻽�떎硫� 臾댁떆
			check[node.to] = true; // 諛⑸Ц 泥섎━
			// �쁽�옱 �룄�떆�� �뿰寃곕맂 紐⑤뱺 �룄�떆 �깘�깋
			for (Node next: list[to]) {
				// �쁽�옱蹂대떎 �뜑 �쟻�� 鍮꾩슜 諛쒓껄 �떆 媛깆떊
				if (dp[next.to] >= dp[to]+next.cost) {
					dp[next.to]=dp[to]+next.cost;
					q.add(new Node(next.to, dp[next.to])); // �걧�뿉 異붽�
					
				}
			}
		}
	}

}
