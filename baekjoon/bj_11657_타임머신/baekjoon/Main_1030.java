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
	static int N, M; // N: 도시의 개수(노드), M: 버스 노선의 개수(간선)
	static Bus[] edges; // 모든 간선 정보를 저장할 배열
	static long[] dist; // 최단 거리 테이블 (시간이 음수가 될 수 있으므로 long 사용)
	static final long INF = Long.MAX_VALUE; // 무한대를 나타내는 큰 값

	// 벨만-포드 알고리즘
	public static boolean bellmanFord() {
		// 1번 노드에서 1번 노드로 가는 비용은 0으로 초기화
		dist[1] = 0;
		
		// N-1번 반복 (최단 경로가 N-1개의 간선으로 이루어질 수 있기 때문)
		for (int i = 1; i <= N; i++) {
			// 모든 간선 M개를 하나씩 확인 (3-1)
			for (int j = 0; j < M; j++) {
				Bus currentEdge = edges[j];
				
				// 현재 간선의 출발 노드
				int startNode = currentEdge.start;
				// 현재 간선의 도착 노드
				int endNode = currentEdge.end;
				// 현재 간선의 비용
				int edgeCost = currentEdge.cost;
				
				// 출발 노드까지 가는 경로가 존재하는 경우 (무한대가 아닌 경우)
				if (dist[startNode] != INF) {
					// 갱신 (3-2)
					// (출발 노드까지의 최단 거리 + 간선 비용) < 도착 노드까지의 현재 최단 거리
					if (dist[startNode] + edgeCost < dist[endNode]) {
						dist[endNode] = dist[startNode] + edgeCost;
						
						// N번째 반복에서 갱신이 발생하면 음수 사이클 존재
						if (i == N) {
							return true; // 음수 사이클 발견
						}
					}
				}
			}
		}
		
		return false; // 음수 사이클 없음
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new Bus[M]; // 간선 정보 배열
		dist = new long[N + 1]; // 1번부터 N번까지 사용
		
		// 최단 거리 테이블 무한대로 초기화 (2)
		Arrays.fill(dist, INF); 
		
		// 간선 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[i] = new Bus(a, b, c);
		}
		
		// 벨만-포드 실행
		boolean hasNegativeCycle = bellmanFord();
		
		// 4. 결과 출력
		if (hasNegativeCycle) {
			// 음수 간선 순환 발생 시 -1 출력
			System.out.println("-1");
		} else {
			// 2번 도시부터 N번 도시까지의 최단 시간 출력
			StringBuilder sb = new StringBuilder();
			for (int i = 2; i <= N; i++) {
				// 경로가 없는 경우 (INF)는 -1 출력
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
/*
 * 지선아
 * 공부하지 마
 * 공부같은 거 하지 말자 
 * 탈출하자 
 * 아직도 한 쪽 안필해ㅛㅅㄷ가 
 * */
 