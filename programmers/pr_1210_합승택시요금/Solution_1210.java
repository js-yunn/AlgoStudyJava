import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
    int e;
    int cost;
    public Edge(int e, int cost){
        this.e = e;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Edge o){
        return this.cost-o.cost;
    }
}

class Solution {
    ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    static int MAX = Integer.MAX_VALUE;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        for (int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }
        for (int[] fare: fares){
            graph.get(fare[0]).add(new Edge(fare[1], fare[2]));
            graph.get(fare[1]).add(new Edge(fare[0], fare[2]));
            // 양방향 간선이라 둘 다 추가
        }
        
        int[] together = new int[n+1];
        int[] onlyA = new int[n+1];
        int[] onlyB = new int[n+1];
        
        Arrays.fill(together, MAX);
        Arrays.fill(onlyA, MAX);
        Arrays.fill(onlyB, MAX);
        together = dijkstra(s, together);
        onlyA = dijkstra(a, onlyA);
        onlyB = dijkstra(b, onlyB);
        
        int answer = MAX;
        for (int i=1; i<=n; i++){
            answer = Math.min(answer, together[i]+onlyA[i]+onlyB[i]);
        }
        return answer;   
    }
    
    public int[] dijkstra(int start, int[] costs){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        costs[start]=0;
        
        while(!pq.isEmpty()){
            Edge now = pq.poll();
            // now.cost가 최단 거리 배열 costs의 값보다 크면 continue
            if (now.cost > costs[now.e]) continue;
            // 아니면 costs 배열 갱신하고 pq에 next add하기
            for (Edge next: graph.get(now.e)){
                int cost = next.cost + costs[now.e]; // 다음 노드에 대한 최종 비용 계산
                if (costs[next.e]>cost){
                    costs[next.e]=cost;
                    pq.add(next);
                }
            }
        }
        return costs;
    }
}

/*
together -> S부터 모든 정점까지 최소 비용을 저장함
onlyA -> A부터 모든 정점까지 최소 비용을 저장함
onlyB -> B부터 모든 정점까지 최소 비용을 저장함
풀이 - 합승하는 곳을 i라고 한다면 together[i]+onlyA[i]+onlyB[i]의 값이 최소가 되면 문제를 풀 수 있음.
그러려면 다익스트라 세 번 돌려야 함.
다익스트라 세 번 돌려서 답 구할 수 있음.
*/
