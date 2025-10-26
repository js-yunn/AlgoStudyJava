package baekjoon;
import java.util.*;
import java.io.*;

// Comparable 구현으로 간선 비용(cost)을 기준으로 정렬
class Edge implements Comparable<Edge> {
    int start;
	int end;  
	int cost;  // 비용

	Edge(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}

    // 최소 비용 간선부터 선택하기 위해 cost를 기준으로 오름차순 정렬
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

public class Main_1024 {
	static int n, m; 
    static List<Edge> arr = new ArrayList<>();
    static int[] parent; // 유니온 파인드를 위한 부모 배열
    static int totalMinCost = 0; // 최종적으로 구하려는 최소 비용의 합

	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 1. 간선 정보 입력 받기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
            
			arr.add(new Edge(start, end, cost));
		}

        // 2. 각 집(노드)의 부모를 자기 자신으로 설정
        parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

        // 3. 간선들 비용 기준으로 오름차순 정렬
        Collections.sort(arr);

        // 4. MST 만들면서 가장 비용이 큰 간선을 기록
        int maxCostOfMST = 0; // MST를 구성하는 간선 중 가장 비용이 큰 간선
        
        // 정렬된 간선들을 순회하며 MST를 구성합니다.
        for (int i = 0; i < arr.size(); i++) {
			Edge edge = arr.get(i);
            
            // 두 노드의 부모가 다를 경우
			if (find(edge.start) != find(edge.end)) {
                // 두 노드를 하나의 집합으로 합치고 totalMinCost에 간선 비용 더함
				union(edge.start, edge.end); 
				totalMinCost += edge.cost;
                
                // MST에 포함되는 간선들 중 가장 비용이 큰 간선을 지금 추가된 간선으로 업데이트시킴
                maxCostOfMST = edge.cost; 
			}
		}
        
        // 5. 전체 비용에서 가장 비용이 큰 간선 하나 제거하면 최소비용
        System.out.println(totalMinCost - maxCostOfMST);
    }

    private static void union(int x, int y) {
        int rootX = find(x);
		int rootY = find(y);

        // 부모가 다를 경우에만 합치기
		if (rootX != rootY) {
			parent[rootY] = rootX;
		}
    }
    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }
}

