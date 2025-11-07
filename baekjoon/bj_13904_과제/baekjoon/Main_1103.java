package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1103 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<int[]> tasks = new ArrayList<>();
		int maxday = 0; // 가장 늦은 마감일 
		// 마감일, 점수 입력받기
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			tasks.add(new int[] {d, w});
			if (d>maxday) maxday = d;
		}
		
		tasks.sort((a, b) -> Integer.compare(b[0], a[0]));
		
		// 우선순위 큐
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		int total = 0;
		int taskIdx = 0;
		
		for (int d = maxday; d>=1; d--) {
			// 마감일이 d보다 크거나 같은 모든 과제를 pq 큐에 추가
			while(taskIdx<N && tasks.get(taskIdx)[0]>=d) {
				pq.offer(tasks.get(taskIdx)[1]);
				taskIdx++;
			}
			// 현재 날짜 d에 수행할 가장 점수 높은 과제 선택, total에 더하기
			if (!pq.isEmpty()) {
				total += pq.poll();
			}
		}
		System.out.println(total);
	}
}