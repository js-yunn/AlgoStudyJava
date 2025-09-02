package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0902 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i=0; i<N; i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}
		
		// 투 포인터
		int left=0, right=0, sum=0;
		int answer = Integer.MAX_VALUE;
		// 완전 탐색, 배열 한 번 순회
		while (true) {
			if (sum>=S) {
				// sum>=S 이면 left만 움직임
				answer = Math.min(right-left, answer);
				sum -= arr[left++];
			}else {
				// sum<S 이면 right만 움직임
				if (right == N) break; // right가 끝에 도달하면 break
				sum += arr[right++];
			}
		}
		if (answer==Integer.MAX_VALUE) answer=0;
		System.out.println(answer);
	}
}
