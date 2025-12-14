package baekjoon;
import java.io.*;
import java.util.*;


public class Main_1209 {
	static class Node implements Comparable<Node>{
		int idx;
		int weight;
		
		public Node(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
			
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.weight-o.weight; // 가중치 오름차순 정렬
		}
		
	}
	
	static int N, M, X;
	static ArrayList<ArrayList<Node>> origin;
	static ArrayList<ArrayList<Node>> reversed;
	static final int MAX = 1000000000;
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 도로 수
		X = Integer.parseInt(st.nextToken()); // 파티 장소
		// 그래프 초기화
		origin = new ArrayList<>(); reversed = new ArrayList<>();
		for (int i=0 ;i<=N; i++) {
			origin.add(new ArrayList<>());
			reversed.add(new ArrayList<>());
		}
		
		// 도로 정보 입력
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// a->b (비용 c)
			origin.get(a).add(new Node(b, c));
			// b->a (비용 c) 역방향 그래프에 저장 
			reversed.get(b).add(new Node(a, c));
		}
		
		// 각 집에서 X로 가는 최단 거리 구하기 (역방향 그래프 사용)
		int[] toX = dijkstra(reversed);
		// X에서 각 집으로 가는 최단 거리 구하기
		int[] fromX = dijkstra(origin);
		
		// 왕복 시간 최댓값 구하기
		int answer = 0;
		for (int i=1; i<=N; i++) {
			if (toX[i]!=MAX && fromX[i]!=MAX) {
				answer = Math.max(answer, toX[i]+fromX[i]);
			}
		}
		System.out.println(answer);

	}
	static int[] dijkstra(ArrayList<ArrayList<Node>> list) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N+1];
		Arrays.fill(dist, MAX);
		dist[X]=0; // 시작점 거리 초기화
		pq.offer(new Node(X, 0));
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int nowIdx = now.idx;
			int nowDist = now.weight;
			
			if (nowDist>dist[nowIdx]) continue; // 현재 dist에 저장된 거리보다 크면 스킵
			for (Node next:list.get(nowIdx)) {
				if (dist[next.idx] > dist[nowIdx]+next.weight) {
					dist[next.idx] = dist[nowIdx]+next.weight;
					pq.offer(new Node(next.idx, dist[next.idx]));
				}
			}	
		}
		return dist;
	}

}

/*
백준 1238- 파티 (다익스트라 문제)

N명의 학생이 각자 파티 장소 X까지 왔다갔다 하는 최단거리 중 가장 큰 값을 구하는 문제
단방향 그래프
왔다갔다 해야하므로 다익스트라 두번씩 수행해야 함.
각 집 -> X : 역방향 그래프 만들어서 X-> 각 집으로 가도록 다익스트라 수행
X -> 각 집 : 입력받은대로 다익스트라 수행


*/