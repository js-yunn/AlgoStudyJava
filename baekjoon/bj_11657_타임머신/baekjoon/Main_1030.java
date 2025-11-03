package baekjoon;
import java.io.*;
import java.util.*;

class Bus{
	int start;
	int end;
	int cost;
	public Bus(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
}

public class Main_1030 {
	static int N, M;
	static Bus[] edges;
	static long[] dist;
	static final long INF = Long.MAX_VALUE;

	// 벨만-포드 알고리즘
	public static boolean bellmanFord() {
		dist[1] = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				Bus currentEdge = edges[j];
				
				// 현재 간선의 출발 노드, 도착 노드, 비용
				int startNode = currentEdge.start;
				int endNode = currentEdge.end;
				int edgeCost = currentEdge.cost;
				
				// 출발 노드까지 가는 경로가 존재하는 경우
				if (dist[startNode] != INF) {
					if (dist[startNode] + edgeCost < dist[endNode]) {
						dist[endNode] = dist[startNode] + edgeCost;
						// N번째 반복에서 갱신이 발생하면 음수 사이클 존재
						if (i == N) {
							return true; // 음수 사이클 발견, true 리턴
						}
					}
				}
			}
		}
		
		return false; // 음수 사이클 없음, false 리턴
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new Bus[M]; // 간선 정보 배열
		dist = new long[N + 1]; // 1번부터 N번까지 사용
		
		// 최단 거리 테이블 무한대로 초기화
		Arrays.fill(dist, INF); 
		
		// 간선 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[i] = new Bus(a, b, c);
		}
		
		// 벨만-포드
		boolean hasNegativeCycle = bellmanFord();
		
		// 결과 출력
		if (hasNegativeCycle) {
			// 음수 간선 순환 발생 시 -1 출력
			System.out.println("-1");
		} else {
			// 2번 도시부터 N번 도시까지의 최단 시간 출력
			StringBuilder sb = new StringBuilder();
			for (int i = 2; i <= N; i++) {
				// 경로가 없는 경우
				if (dist[i] == INF) {
					sb.append("-1\n");
				} else {
					sb.append(dist[i] + "\n");
				}
			}
			System.out.print(sb.toString());
		}
	}
}
/*
음수 간선이 있으니까 다익스트라가 아니라 벨만 포드 알고리즘

1. 1번 노드에서 출발
2. 최단 거리 테이블 초기화
3. V번 반복
	3-1. 모든 간선 E개를 하나씩 확인
	3-2. 각 간선을 거쳐 다른 노드로 가는 비용을 계산해서 최단 거리 테이블 갱신
음수 간선 순환 발생하는지 체크, 있으면 -1 리턴
*/