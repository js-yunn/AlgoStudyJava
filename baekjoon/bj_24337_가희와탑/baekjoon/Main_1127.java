package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1127 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		// 1. 아예 불가능한 경우 확인
		if (a+b-1>N) { System.out.println("-1"); return; }
		// 2. 오름차순, peak, 내림차순으로 채워넣기
		ArrayList<Integer> list = new ArrayList<>();
		for (int i=1; i<a; i++) {
			list.add(i);
		}
		list.add(Math.max(a, b));
		for (int i=b-1; i>=1; i--) {
			list.add(i);
		}
		// 3. N-(a+b-1) 남은 개수만큼 1로 채우기
		int remains = N-(a+b-1);
		// a==1인 경우를 예외로 처리하기 (그리디) -> 맨앞에 무조건 가장 높은 건물 놓기
		if (a==1) {
			sb.append(list.get(0)).append(" "); // a==1이어서 가장 높은 건물이 0번 인덱스에 있음
			for (int i=0; i<remains; i++) {
				sb.append(1).append(" ");
			}
			for (int i=1; i<list.size(); i++) {
				sb.append(list.get(i)).append(" ");
			}
		}
		// a>1인 경우
		else {
			for (int i=0; i<remains; i++) {
				sb.append(1).append(" "); // 맨 앞에 1을 배치하는게 사전 순 가장 앞임
			}
			for (int l : list) {
				sb.append(l).append(" ");
			}
		}
		System.out.println(sb.toString());
		
		
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
