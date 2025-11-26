package baekjoon;
import java.io.*;
import java.util.*;

class Node{
	int to;
	int weight;
	public Node(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}		
}

public class Main_1126 {
	static int N, M;
	static ArrayList<ArrayList<Node>> island;
	static int start, end;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); 
        M = Integer.parseInt(st.nextToken()); 
		
        island = new ArrayList<>();
        for (int i=0; i<=N; i++) {
        	island.add(new ArrayList<>());
        }
        
        int high = 0;
        
        for (int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	int C = Integer.parseInt(st.nextToken());
        	
        	// 양방향이니까 양쪽에 추가하기
        	island.get(A).add(new Node(B, C));
        	island.get(B).add(new Node(A, C));
        	
        	if (C>=high) { // 최대값 갱신
        		high = C;
        	}
        }
        
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        
        // 이분 탐색
        int low = 0;
        int answer = 0;
        while(low<=high) {
        	int mid = (high+low)/2;
        	
        	if (bfs(mid)) {
        		answer = mid;
        		low = mid+1;
        	} else {
        		high = mid-1;
        	}
        }
        System.out.println(answer);
        
	}
	
	public static boolean bfs(int w) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(start);
		visited[start]= true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if (now == end) { // 도착지까지 도달하면 true 리턴
				return true;
			}
			
			// bfs 진행 - 현재 섬에서 연결된 모든 다리 visited 확인, 다리의 weight와 w 비교하여 진행
			for (Node n : island.get(now)) {
				if (!visited[n.to] && n.weight >= w) {
					visited[n.to] = true;
					q.offer(n.to);
				}
			}
		}
		// 다 돌았는데 못 도착하면 false 리턴
		return false;
	}
}
/*
백준 1939번 중량제한

로직: 일단 무게로 이분탐색해야 함.
그리고 현재 무게 w 일 때, 이 무게로 시작 섬으로부터 도착 섬으로 갈 수 있는지 다리 weight를 검사하면서 건넘. -> bfs 사용하기, 큐 사용!
도착 섬에 도달한다면 현재 w는 가능한 중량임
혹시 더 무거워도 가능한지 알기 위해 w를 높임. (low = mid+1)
도착 섬에 못 도달하면 너무 무거운거니까 w를 낮춰서 다시함. (high = mid-1)

자료구조: 섬 정보 저장하는 arraylist, 한 섬에서 연결되는 모든 도로를 저장해야 하니까 2중 arraylist로 선언해야함.


*/