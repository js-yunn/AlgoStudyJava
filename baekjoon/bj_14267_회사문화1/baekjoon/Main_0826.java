package baekjoon;
import java.io.*;
import java.util.*;


public class Main_0826 {
    static int n;
    static List<List<Integer>> subordinates; // 직속 부하 리스트
    static int[] inTime; // 입장 시간
    static int[] outTime; // 퇴장 시간
    static int timer; // DFS 순회 시간 기록

    static long[] tree; // 칭찬 수치를 저장할 세그먼트 트리
    static long[] lazy; // 레이지 프로퍼게이션을 위한 지연 업데이트 배열

    // DFS로 트리를 순회하며 각 노드(직원)의 서브트리 시작/끝 시간을 기록
    static void dfs(int currentEmployee) {
        inTime[currentEmployee] = ++timer;
        for (int subordinate : subordinates.get(currentEmployee)) {
            dfs(subordinate); 
        }
        outTime[currentEmployee] = timer;
    }

    // 지연 업데이트 값을 자식 노드로 전파
    static void pushDown(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree[node] += lazy[node];
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

 // 범위 [l, r]에 값(val)을 더하는 함수 (레이지 프로퍼게이션 적용)
    static void updateRange(int node, int start, int end, int l, int r, long val) {
        pushDown(node, start, end);
        if (start > end || start > r || end < l) {
            return;
        }

        if (l <= start && end <= r) {
            lazy[node] += val;
            pushDown(node, start, end);
            return;
        }

        // 범위가 부분적으로 겹치면 자식 노드로 쪼개서 재귀 호출
        int mid = (start + end) / 2;
        updateRange(node * 2, start, mid, l, r, val);
        updateRange(node * 2 + 1, mid + 1, end, l, r, val);
    }
    
    // 특정 인덱스(idx)에 해당하는 최종 칭찬 수치 조회
    static long query(int node, int start, int end, int idx) {
        pushDown(node, start, end);
        
        // 리프 노드에 도달
        if (start == end) {
            return tree[node];
        }
        
        int mid = (start + end) / 2;
        if (idx <= mid) {
            return query(node * 2, start, mid, idx);
        } else { 
            return query(node * 2 + 1, mid + 1, end, idx);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        subordinates = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            subordinates.add(new ArrayList<>());
        }
        
        inTime = new int[n + 1];
        outTime = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        st.nextToken(); 
        for (int i = 2; i <= n; i++) {
            int superior = Integer.parseInt(st.nextToken());
            subordinates.get(superior).add(i);
        }

        timer = 0;
        dfs(1);
        
        tree = new long[4 * (n + 1)];
        lazy = new long[4 * (n + 1)];

        // m번의 칭찬 정보를 처리
        for (int k = 0; k < m; k++) {
            st = new StringTokenizer(br.readLine());
            int employeeId = Integer.parseInt(st.nextToken());
            int praiseValue = Integer.parseInt(st.nextToken());
            updateRange(1, 1, n, inTime[employeeId], outTime[employeeId], praiseValue);
        }

        // 최종 칭찬 수치를 조회하여 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(query(1, 1, n, inTime[i])).append(" ");
        }

        bw.write(sb.toString().trim());
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}

