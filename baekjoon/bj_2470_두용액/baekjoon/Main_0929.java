package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0929 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i=0; i<N; i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);		
		int id1=0; int id2=N-1;
		int min = Integer.MAX_VALUE;
		int answer1=0, answer2=0;
		while(id1<id2) {
			int tmp = arr[id1]+arr[id2];
			if (Math.abs(tmp)<min) {
				min = Math.abs(tmp);
				answer1=arr[id1]; answer2 = arr[id2];
			}
			if (tmp == 0) {
				break;
			}else if (tmp<0) {
				id1++;
			}else {
				id2--;
			}
		}
		System.out.println(answer1+" "+answer2);
	}
}
