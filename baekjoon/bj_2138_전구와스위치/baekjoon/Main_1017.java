package baekjoon;
import java.io.*;
import java.util.*;

/*
<시나리오 & 그리디>
1. 시나리오 
1) 0번 스위치를 누르지 않고 시작(count=0)
	arr[i-1]!=result[i-1]
		i번 스위치를 무조건 눌러야함.
		count++
	arr[i-1]==result[i-1] 
		스위치 안 눌러도 됨.

2) 0번 스위치를 누르고 시작(count=1)

3) 최종 리턴된 count 값 두개를 비교하며 결과 출력

-------------------------------------
2. 그리디
arr[i-1]!=result[i-1]이면 arr[i]을 무조건 바꿔야 한다.
바꿔나가다가 i가 N-1이 되면 마지막 값을 result와 비교해서 다르다면 불가능하므로 -1리턴,
가능하면 count 리턴
*/ 

public class Main_1017 {
	static int N;
	static int count;
	
	static int greedy(int[] arr, int[] res, int cnt) {
		for (int i=1; i<N; i++) {
			if (arr[i-1]!=res[i-1]) {
				if (i==N-1) {
					cnt++;
					arr[i-1]= (arr[i-1]+1)%2;
					arr[i]= (arr[i]+1)%2;
				} else {
					cnt++;
					arr[i-1]= (arr[i-1]+1)%2;
					arr[i]= (arr[i]+1)%2;
					arr[i+1]= (arr[i+1]+1)%2;
				}
			}
		}
		
		if (arr[N-1]==res[N-1]) {
			return cnt;
		}else {
			return -1;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		String output = br.readLine();
		int[] arr = new int[N];
		int[] result = new int[N];
		for (int i=0; i<N; i++) {
			arr[i]= Integer.parseInt(input.substring(i, i+1));
			result[i]= Integer.parseInt(output.substring(i, i+1));
		}
		
		// 1) 0번 스위치 누르지 않고 시작
		int[] copy1 = Arrays.copyOf(arr, N);
		int count1 = greedy(copy1, result, 0);
		
		
		// 2) 0번 스위치 누르고 시작
		int[] copy2 = Arrays.copyOf(arr, N);
		copy2[0]= (copy2[0]+1)%2;
		copy2[1]= (copy2[1]+1)%2;
		int count2 = greedy(copy2, result, 1);
		
		
		// 3) 결과값 비교, 정답 출력
		if (count1!=-1 && count2!=-1) {
			System.out.println(Math.min(count1, count2));
		} else if (count1!=-1) {
			System.out.println(count1);
		} else if (count2!=-1) {
			System.out.println(count2);
		} else {
			System.out.println(-1);
		}
	}
}