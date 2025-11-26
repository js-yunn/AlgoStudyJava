package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1125 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] trees = new int[N];
		int max = 0;
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			trees[i]= Integer.parseInt(st.nextToken());
			if (trees[i]>max) {
				max = trees[i];
			}
		}
		// 이분 탐색으로 높이 조절하기
		int min = 0;
		while (min<=max) {
			int mid = (max+min)/2;
			long sum = 0;
			
			for (int t: trees){
				if (t > mid) {
					sum+= t-mid;
				}
			}
			
			if (sum>=M) { // 나무를 너무 많이 잘랐으니까 min값을 높여서 나무 덜 자르기
				min = mid+1;
			}
			else { 
				max = mid-1;
			}
		}
		
		System.out.println(max);	
	}
}
