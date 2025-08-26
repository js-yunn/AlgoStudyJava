package baekjoon;
import java.io.*;
import java.util.*;


public class Main_0826 {
    static int n; // 전체 직원 수
    static List<List<Integer>> subordinates; // 직속 부하 리스트
    static int[] inTime; // DFS 순회 시 직원의 입장 시간
    static int[] outTime; // DFS 순회 시 직원의 퇴장 시간
    static int timer; // DFS 순회 시간을 기록하는 타이머

    static long[] tree; // 칭찬 수치를 저장할 세그먼트 트리
    static long[] lazy; // 레이지 프로퍼게이션을 위한 지연 업데이트 배열

    // DFS를 통해 트리를 1차원 배열처럼 만들기
    // 각 직원의 서브트리가 배열의 특정 범위에 매핑됨
    static void dfs(int currentEmployee) {
        // 현재 직원의 서브트리가 시작하는 시간(배열의 시작 인덱스) 기록
        inTime[currentEmployee] = ++timer;
        
        // 현재 직원의 모든 직속 부하들을 순회
        for (int subordinate : subordinates.get(currentEmployee)) {
            dfs(subordinate); // 재귀 호출로 더 깊이 탐색
        }
        
        // 현재 직원의 서브트리가 끝나는 시간(배열의 끝 인덱스) 기록
        outTime[currentEmployee] = timer;
    }

    // 레이지 프로퍼게이션: 부모 노드의 지연 업데이트 값을 자식에게 전파
    static void pushDown(int node, int start, int end) {
        if (lazy[node] != 0) {
            // 현재 노드에 지연된 칭찬 값 적용
            tree[node] += lazy[node];
            
            // 현재 노드가 리프 노드가 아니면 (즉, 자식이 있으면)
            if (start != end) {
                // 자식 노드들에게 지연 값 전파
                // node * 2는 왼쪽 자식, node * 2 + 1은 오른쪽 자식
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            
            // 현재 노드의 지연 값은 전파했으니 초기화
            lazy[node] = 0;
        }
    }

    // [l, r] 범위에 칭찬 값(val)을 더하는 함수 (범위 업데이트)
    static void updateRange(int node, int start, int end, int l, int r, long val) {
        // 먼저 현재 노드의 지연 값을 처리
        pushDown(node, start, end);
        
        // 업데이트할 범위와 현재 노드의 범위가 겹치지 않는 경우
        if (start > end || start > r || end < l) {
            return;
        }

        // 현재 노드의 범위가 업데이트할 범위 [l, r] 안에 완전히 포함되는 경우
        if (l <= start && end <= r) {
            // 이 노드와 그 아래 자식들은 나중에 한꺼번에 업데이트하기 위해
            // 지연 배열(lazy)에만 값을 더해놓고 끝냄
            lazy[node] += val;
            pushDown(node, start, end); // 그리고 바로 현재 노드에 적용
            return;
        }

        // 범위가 부분적으로 겹치는 경우, 자식 노드로 쪼개서 재귀 호출
        int mid = (start + end) / 2;
        updateRange(node * 2, start, mid, l, r, val);
        updateRange(node * 2 + 1, mid + 1, end, l, r, val);
    }
    
    // 특정 인덱스(idx)에 해당하는 최종 칭찬 수치 조회
    static long query(int node, int start, int end, int idx) {
        // 먼저 현재 노드의 지연 값을 처리
        pushDown(node, start, end);
        
        // 리프 노드에 도달했으면, 그 값을 반환
        if (start == end) {
            return tree[node];
        }
        
        // 찾으려는 인덱스가 왼쪽 자식 범위에 있으면
        int mid = (start + end) / 2;
        if (idx <= mid) {
            return query(node * 2, start, mid, idx);
        } else { // 오른쪽 자식 범위에 있으면
            return query(node * 2 + 1, mid + 1, end, idx);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 직원 수
        int m = Integer.parseInt(st.nextToken()); // 칭찬 횟수

        // 부하 리스트 초기화
        subordinates = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            subordinates.add(new ArrayList<>());
        }
        
        // DFS 시간 배열 초기화
        inTime = new int[n + 1];
        outTime = new int[n + 1];

        // 상사-부하 관계를 입력받아 부하 리스트 구성
        st = new StringTokenizer(br.readLine());
        st.nextToken(); // 1번 직원은 상사가 -1 이므로 그냥 건너뛰기
        for (int i = 2; i <= n; i++) {
            int superior = Integer.parseInt(st.nextToken());
            subordinates.get(superior).add(i);
        }

        // DFS를 시작하여 트리를 선형화 (1번 직원이 사장이므로 1부터 시작)
        timer = 0;
        dfs(1);

        // 세그먼트 트리와 레이지 배열 초기화
        tree = new long[4 * (n + 1)];
        lazy = new long[4 * (n + 1)];

        // m번의 칭찬 정보를 처리
        for (int k = 0; k < m; k++) {
            st = new StringTokenizer(br.readLine());
            int employeeId = Integer.parseInt(st.nextToken());
            int praiseValue = Integer.parseInt(st.nextToken());
            
            // 칭찬을 받은 직원의 서브트리 전체에 칭찬 값 더하기
            updateRange(1, 1, n, inTime[employeeId], outTime[employeeId], praiseValue);
        }

        // 각 직원의 최종 칭찬 수치를 조회하여 출력
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

