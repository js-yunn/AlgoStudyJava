package baekjoon;
import java.io.*;
import java.util.*;

/*
 * 백준 11437 LCA 
 * 문제 풀이: 트리에서 두 노드의 최소 공통 조상(LCA)를 찾는 알고리즘을 구현하는 문제임.
 * DFS 알고리즘을 사용해 트리를 초기화하는 init 메소드를 구현하고, 
 * LCA 메소드에서 a,b 노드의 깊이를 같게 맞춘 후 부모 노드로 하나씩 올라가며 LCA를 찾도록 풀이함.
 */
public class Main_0915 {
	static List<Integer>[] tree;
	static int[] parent, depth;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		depth = new int[N+1];
		tree = new ArrayList[N+1];
		for (int i=1; i<=N; i++) tree[i]= new ArrayList<>();
		StringTokenizer st = null;
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a); // 양쪽에 추가
		}
		init(1,1,0); // 트리의 깊이, 부모 정보 초기화
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(LCA(a,b)).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
		
	}
	static void init(int cur, int d, int pa) {
		depth[cur]=d; // 현재 노드의 깊이 depth 배열에 저장
		parent[cur]=pa; // 현재 노드의 부모 저장
		for (int nxt: tree[cur]) {
			if (nxt!=pa) {
				init(nxt, d+1, cur); // 자식 노드에 대해 재귀 호출
			}
		}
	}
	static int LCA(int a , int b) {
		int ad = depth[a]; // a의 깊이
		int bd = depth[b]; // b의 깊이
		// 두 노드 a,b의 깊이 ad,bd를 같게 맞춤
		while(ad>bd) {
			a=parent[a];
			ad--;
		}
		while (bd>ad) {
			b=parent[b];
			bd--;
		}
		// 두 노드가 같을 때까지 부모로 한 칸씩 올라감
		while (a!=b) {
			a=parent[a];
			b=parent[b];
		}
		return a; // 최소 공통 조상 리턴
	}

}
