package baekjoon;
import java.util.*;
import java.io.*;

public class Main_0807 {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken()); 
		List<List<Integer>> map = new ArrayList<>();
		
		for (int i=0; i<=N; i++) { // 도시 개수만큼 리스트 생성
			map.add(new ArrayList<>());
		}
		for (int i=0; i<M; i++) { // 각 도시마다 간선 추가
			st =new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map.get(a).add(b); // 단방향 간선이므로 하나만 추가
		}
		
		int[] distance = new int[N+1]; // 거리 배열 -1로 초기화
		Arrays.fill(distance, -1);
		distance[X]=0;
		
		Queue<Integer> q = new LinkedList<>();
		q.add(X);
		
		while(!q.isEmpty()) {
			int now = q.poll();
			for (int next: map.get(now)) {
				if(distance[next] ==-1) {
					distance[next]=distance[now]+1;
					q.add(next);
				}
			}
		}
		boolean flag = false;
		for (int i=1; i<=N; i++) {
			if (distance[i]==K) {
				System.out.println(i);
				flag = true;
			}
		}
		if (!flag) {
			System.out.println(-1);
		}
		
	}

}
