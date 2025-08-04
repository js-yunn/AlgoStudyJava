import java.io.*;
import java.util.*;

public class Main_DFS_BFS {
    static boolean[] visited;
    static ArrayList<Integer>[] map; // 간선 저장하는 ArrayList 배열 map
    static int[] result;
    static int cnt = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
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
            map[a].add(b);
            map[b].add(a);

        }
        // 방문 순서 오름차순 정렬
        for (ArrayList<Integer> m : map) {
            Collections.sort(m);
        }
        // DFS 진행
        dfs(V);
        System.out.println();
        // visited 초기화 후 BFS 진행
        Arrays.fill(visited, false);
        bfs(V);
                if (!visited[m]) {
                    q.offer(m);
                    visited[m] = true;
                    System.out.printf("%d ", m);
                }
            }
        }
    }

}
