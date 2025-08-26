package a0823;
import java.io.*;
import java.util.*;

class Solution_0826 {
    static final long INF = Long.MAX_VALUE;
    static Map<Integer, List<int[]>> graph = new HashMap<>(); // 그래프 인접리스트
    private static long[][] dist; // 교차로 간 최단 거리 저장
    private static int N;
    
    // 다익스트라 알고리즘 총 N번 실행
    private void dijkstra(int start) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        Arrays.fill(dist[start], INF);
        dist[start][start] = 0;
        pq.add(new long[]{0, start});

        while (!pq.isEmpty()) {
            long[] current = pq.poll();
            long d = current[0];
            int u = (int) current[1];

            if (d > dist[start][u]) {
                continue;
            }

            for (int[] edge : graph.getOrDefault(u, new ArrayList<>())) {
                int v = edge[0];
                int weight = edge[1];
                if (dist[start][u] + weight < dist[start][v]) {
                    dist[start][v] = dist[start][u] + weight;
                    pq.add(new long[]{dist[start][v], v});
                }
            }
        }
    }

    // DFS 재귀로 경로 탐색
    private boolean findPath(int current, int end, int[] record, int step) {
        if (step == record.length) {
            return true;
        }

        int currentRecord = record[step];
        int color = Math.abs(currentRecord); // 유도색 절대값 color에 저장
        boolean isPositive = currentRecord > 0; // 양수 음수 저장

        for (int[] edge : graph.getOrDefault(current, new ArrayList<>())) {
            int next = edge[0];
            int roadColor = edge[2];
            int roadLength = edge[1];

            if (roadColor == color) {
                // dist[현재][다음]+dist[다음][목적지]==dist[현재][목적지]이면 최단 경로에 포함됨
                boolean onShortestPath = (dist[current][next] + dist[next][end] == dist[current][end]);

                if ((isPositive && onShortestPath) || (!isPositive && !onShortestPath)) {
                    if (findPath(next, end, record, step + 1)) { // 다음 단계 재귀 호출
                        return true; // 유효한 경로 찾았으므로 true 반환
                    }
                }
            }
        }
        return false;
    }

    public int[][] solution(int n, int[][] roads, int[] record) {
    	this.N = n;
        this.dist = new long[N + 1][N + 1];
        
        // 그래프와 dist 초기화
        graph.clear(); // 기존 static graph를 초기화
        
        // 그래프 생성
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int length = road[2];
            int color = road[3];
            // 그래프에서 u에 대한 인접 리스트가 없으면 v로 가는 간선을 새로 만듦
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, length, color});
            // 양방향이므로 v에도 추가
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, length, color});
        }
        
        // 모든 교차로에 대해 다익스트라 실행, 모든 교차로에 대한 최단 거리 dist 배열에 저장
        for (int i = 1; i <= N; i++) {
            dijkstra(i);
        }

        List<int[]> result = new ArrayList<>();
        // 모든 출발지-목적지 쌍 탐색
        for (int start = 1; start <= N; start++) {
            for (int end = 1; end <= N; end++) {
                if (findPath(start, end, record, 0)) {
                    result.add(new int[]{start, end});
                }
            }
        }
        
        // 결과 정렬
        result.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]); // 출발 교차로 기준 오름차순
            }
            return Integer.compare(a[1], b[1]); // 목적지 교차로 기준 오름차순
        });
        
        return result.toArray(new int[0][]);
    }
}