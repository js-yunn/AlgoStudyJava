package baekjoon;
import java.io.*;
import java.util.*;

public class Main_bj_11279_최대힙 {
	static int N;
	static PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int val = Integer.parseInt(st.nextToken());
			if (val == 0) {
				if (heap.size()== 0) System.out.println("0");
				else {
					int max = heap.poll();
					System.out.println(max);
				}
			}else {
				heap.offer(val);
			}	
		}
	}
}
