package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1127 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		if (a+b-1>N) { System.out.println("-1"); return; }
		
		
		
	}

}

/*
백준- 24337. 가희와 탑

생각 : 그리디로 풀 수 있음.
일단 -1인 경우는 문제 조건이 안 맞아야 함.
1. N이 a+b-1보다 작으면 절대 해가 존재할 수 없음. 이런 경우 -1 리턴

2. 1번 조건 통과한다면
2-1. 왼쪽부터 1, 2, ... , a-1 까지 커지기, T, 오른쪽부터 1, 2, ... , b-1까지 커지기
가장 높은 건물을 T라고 두면 a=1이면 맨 앞에 T를 두고 1, 2, ... , b-1, ... , 1로 숫자 채워넣기

자료구조 : ArrayList add하면 될 듯 

시간 복잡도는 O(N)

*/