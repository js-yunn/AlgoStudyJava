package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1111 {
	// 간선 정보 클래스
	static class Edge{
		int to;
		int weight;
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	static int N;
	static ArrayList<Edge>[] al;
	static boolean[] visited;
	static int maxDiameter=0; // 최장 지름
	static int farNode=0; // 현재 탐색에서 가장 먼 노드 번호 저장용

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		if (N==1) {
			System.out.println(0);
			return;
		}
		
		al = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			al[i]= new ArrayList<>();
		}
		for (int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			// 트리에 넣기- 양방향!!
			al[parent].add(new Edge(child, weight));
			al[child].add(new Edge(parent, weight));
		}
		// 1번 노드에서 가장 먼 노드 찾기 (첫번째 dfs)
		visited = new boolean[N+1];
		dfs(1, 0);
		
		// 현재 farNode에서 다시 가장 먼 노드 찾기 (두번째 dfs, 트리 최장 지름 구해짐)
		visited = new boolean[N+1];
		dfs(farNode, 0);
		
		System.out.println(maxDiameter);
	}
	
	static void dfs(int node, int nowWeight) {
		visited[node]= true;
		if (nowWeight > maxDiameter) {
			maxDiameter = nowWeight;
			farNode = node;
		}
		for (Edge e:al[node]) {
			if (!visited[e.to]) {
				dfs(e.to, nowWeight+e.weight);
			}
		}
	}

}
/*
백준 1967- 트리의 지름

처음 생각: 유니온 파인드, 자식부터 루트까지 올라온 값 중 최대값 2개 찾아서 지름 구하기
유니온 파인드는 mst, 사이클 감지, 그룹화 문제에서 효율적이지만
가중치(거리)가 얼마인지는 고려하지 않아서 최단 거리 문제에는 적합하지 않음

다음 생각: dfs로 해서 시작점으로부터 가장 먼 노드 찾기
1. 시작점 설정
2. 시작 노드로부터 모든 노드까지의 거리 계산하면서 탐색
3. 최대 거리 노드 갱신, 그 노드를 시작점으로 재설정
4. 새로운 시작점으로부터 다시 DFS 탐색 수행
5. 가장 멀리 떨어진 노드 찾고 두 노드 사이 거리가 트리의 지름임

NullPointerException 발생 -> N=1일 때 처리를 안 함!!!
*/