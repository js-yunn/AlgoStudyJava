package baekjoon;
import java.io.*;
import java.util.*;
 
public class Main_0920 {
    static int[] depth;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
    static int answer = 0;
    static int makeDepth(int node){ // 리프 노드까지 최대 깊이 계산
        if(graph.get(node).size()==0){
            return depth[node]=1;
        }
        visited[node]=true;
 
        int maxDepth = 0;
        for(int child : graph.get(node)){ // 모든 자식 노드만큼 반복
            if(!visited[child]) {
                visited[child]=true;
                int depth = makeDepth(child) + 1;
                maxDepth = Math.max(maxDepth, depth);
            }
        }
 
        return depth[node]=maxDepth;
    }
 
    static int dfs(int node,int D){
        visited[node]=true; // 방문처리
        if(depth[node]<=D){ // 현재 노드 깊이가 D 이하면 이동할 필요 없음
            return 0;
        }
        int ret = 0;
        int cCount = 0;
        for(int child : graph.get(node)){
            if(visited[child]) continue;
            if(depth[child]>=D){
                cCount++; // 자식 노드 거리
                ret += dfs(child,D); // 총 이동 거리 누적
            }
        }
        return 2*cCount+ret; // 자식 노드 왕복 거리 + 자식 노드로부터 총 이동거리
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
 
        depth = new int[N+1];
        visited = new boolean[N+1];
        Arrays.fill(depth,0);
 
        for(int i=0;i<=N;i++) graph.add(new ArrayList<>());
 
        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
 
            graph.get(X).add(Y);
            graph.get(Y).add(X);
        }
 
        makeDepth(S); // 노드 s를 루트로 하는 트리 전체 깊이 계산
        Arrays.fill(visited,false);
        System.out.println(dfs(S,D));
    }
}