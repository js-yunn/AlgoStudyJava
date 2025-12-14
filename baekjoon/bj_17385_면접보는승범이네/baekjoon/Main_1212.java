package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1212 {
	static class Node implements Comparable<Node> {
        int idx;
        long cost;

        public Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
        	return Long.compare(this.cost, o.cost); // cost가 long 타입이라 long compare 씀
        }
    }

	static int N, M, K;
    static ArrayList<ArrayList<Node>> graph; 
    static long[] dist; // long 타입
    static final long MAX = Long.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); 
        M = Integer.parseInt(st.nextToken()); 
        K = Integer.parseInt(st.nextToken()); 
        
        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        dist = new long[N + 1];
        Arrays.fill(dist, MAX);

        // 역방향으로 간선 저장하기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(v).add(new Node(u, c));
        }
        
        // 면접장 정보 거리 0으로 해서 pq에 넣기
        PriorityQueue<Node> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int interview = Integer.parseInt(st.nextToken());
            // 면접장 거리 0으로 pq에 넣음
            dist[interview] = 0;
            pq.offer(new Node(interview, 0));
        }
		
        dijkstra(pq);
        
        int maxIdx = -1;
        long maxDist = -1;
        for (int i=1; i<=N; i++) {
        	if (dist[i]!=MAX && dist[i]>maxDist) {
        		maxDist = dist[i];
        		maxIdx = i;
        	}
        }
        System.out.println(maxIdx);
        System.out.println(maxDist);

	}
	
	private static void dijkstra(PriorityQueue<Node> pq) {
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int now = current.idx;
            long nowCost = current.cost;

            if (dist[now] < nowCost) continue;

            for (Node next : graph.get(now)) {
                if (dist[next.idx] > dist[now] + next.cost) {
                    dist[next.idx] = dist[now] + next.cost;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }

}

/*
백준 17835 - 면접보는 승범이네 (다익스트라)
파티랑 비슷하게 역방향으로 그래프 저장해서 면접장->각 도시 다익스트라 돌리기
면접장이 여러개니까 다익스트라 pq에 면접장 도시 거리는 0으로 넣고 시작함

*/