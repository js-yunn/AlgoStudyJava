
import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited; // 방문 여부 저장
    static ArrayList<Integer>[] map; // ArrayList로 선언
    static int[] result; // 방문 순서 저장
    static int cnt = 1; // 방문 순서 카운트 변수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점
        int M = Integer.parseInt(st.nextToken()); // 간선
        int R = Integer.parseInt(st.nextToken()); // 시작점

        visited = new boolean[N + 1];
        result = new int[N + 1];
        map = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        // 간선 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 무방향 그래프라 양쪽에 모두 추가
            map[a].add(b);
            map[b].add(a);
        }

        // 방문 순서 오름차순 정렬
        for (ArrayList<Integer> m : map) {
            Collections.sort(m);
        }

        dfs(R);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < result.length - 1; i++) {
            sb.append(result[i]).append("\n");
        }
        sb.append(result[result.length - 1]);
        System.out.println(sb);
    }

    public static void dfs(int x) {
        visited[x] = true; // 현재 정점 방문
        result[x] = cnt++; // 방문 순서 기록

        for (int i : map[x]) { // 현재 x점에서 연결된 모든 정점 검사
            if (!visited[i]) {
                dfs(i);
            }
        }
    }
}
