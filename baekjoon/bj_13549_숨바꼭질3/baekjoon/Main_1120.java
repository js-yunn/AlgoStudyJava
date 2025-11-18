package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1120 {
	static final int MAX = 100001;
	static int[] dist = new int[MAX];

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new InputStreamReader(System.in));
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		if (N>=K) {
			System.out.println(N-K);
			return;
		}
		int result = bfs(N,K);
		System.out.println(result);
	}
	
	public static int bfs(int start, int end) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		Deque<Integer> dq = new LinkedList<>();
		
		dist[start]=0;
		dq.addFirst(start);
		
		while(!dq.isEmpty()) {
			int now = dq.pollFirst();
			if (now == end) { // 현재 위치가 목표 위치이면 dist[now] 리턴
				return dist[now];
			}
			
			// 2. 현재 위치에서 세가지 이동 방법 모두 탐색하기
			// 2-1. 순간 이동
			int jump = now*2;
			if (jump<MAX && dist[now]<dist[jump]) {
				dist[jump]=dist[now];
				dq.addFirst(jump);
			}
			
			// 2-2. 양쪽 이동
			int[] leftright = {now-1, now+1};
			for (int move:leftright) {
				if (move>=0 && move<MAX && dist[now]+1<dist[move]) {
					dist[move]=dist[now]+1;
					dq.addLast(move);
				}
			}
		}
		return -1;
	}
}
/*
처음 생각: 그리디로 K-N>N인 동안 N 2배씩 순간이동 후 K-2N, K-2(N-1), K-2(N+1) 비교해서 했는데 최적해 아님

다시 생각: dist 배열에 최소 시간 기록, bfs 돌리기
bfs 내부에서는 deque 양방향 큐 써서 순간이동은 앞에 1초 이동은 뒤로 넣기
1. N>=K이면 순간 이동 안 하니까 무조건 N-K 출력하면 됨
2. N<K이면 순간 이동 포함해서 bfs로 완전 탐색해야 함

bfs 탐색
덱이 빌 때까지 탐색
	1. 목표 지점이면 현재 위치 인덱스 dist 값 반환하기
	2. 현재 위치에서 세가지 이동 방법 모두 탐색하기
		2-1. 순간이동
		MAX 범위 내에 있고, 순간이동 했을 때가 dist에 저장된 값보다 작은 경우에 업데이트하고 덱 앞에 addfisrt
		2-2. 양옆 이동, 가중치 1
		MAX 범위 내에 있고, 이동하는 경우의 시간이 dist 저장된 값보다 작은 경우에 업데이트하고 덱 뒤에 addlast
	
*/