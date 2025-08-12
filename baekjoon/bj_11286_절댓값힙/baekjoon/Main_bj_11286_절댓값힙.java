package baekjoon;
import java.io.*;
import java.util.*;


public class Main_bj_11286_절댓값힙 {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) { // 순서 비교
				if (Math.abs(o1)>Math.abs(o2)) { // o1의 절댓값이 더 큰 경우
					return 1; // o1이 뒤에 오도록
				}else if (Math.abs(o1)<Math.abs(o2)) { // o1의 절댓값이 더 작은 경우
					return -1; // o1이 앞에 오도록
				}else { // 절댓값 같은 경우 
					if (o1>o2) return 1; //o1이 더 크면 뒤로
					else if (o1<o2) return -1; // o1이 더 작으면 앞으로
					else return 0;
				}
			}
		});
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int val = Integer.parseInt(st.nextToken());
			if (val == 0) {
				if (heap.size()==0) System.out.println("0");
				else System.out.println(heap.poll());
			}else {
				heap.offer(val);
			}
		}
	}
}
