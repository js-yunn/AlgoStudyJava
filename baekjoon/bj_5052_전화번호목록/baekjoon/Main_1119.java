package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1119 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i=0; i<T; i++) {
			int N = Integer.parseInt(br.readLine());
			String[] phones = new String[N];
			for (int j=0; j<N; j++) {
				phones[j]=br.readLine();
			}
			if (yesorno(N,phones)) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
		br.close();
	}

	private static boolean yesorno(int n, String[] phones) {
		// 정렬시키고 하나씩 문자 비교
		Arrays.sort(phones);
		for (int i=0 ; i<n-1; i++) {
			String first = phones[i];
			String second = phones[i+1];
			if (second.startsWith(first)) { // second의 접두어가 first인지 확인
				return false; // 발견하면 바로 false 리턴
			}
		}
		return true; // 모든 쌍 검사했는데 접두사 관계 없으면 true 리턴	
	}
}

/*
백준 5052 전화번호 목록

트라이로 저번에 트라이 공부하면서 풀었어서 이번에는 다른 방법으로 생각해봄...
처음 생각: 비슷한것끼리 묶거나 정렬시키고 앞뒤로 접두사 비교하면 될 듯

구현 방법: 숫자 문자열을 오름차순으로 정렬하면 자동으로 정렬됨
정렬된 문자열에서 i번, i+1번을 비교할 예정
i번째 문자열의 길이만큼 i+1번째 문자열과 비교 -> 비교했을 때 i번째 문자열의 모든 문자와 동일하다면 컨티뉴 시키기
다르다면 No 리턴, 반복문 탈출

찾아보니 String에서 startsWith를 쓰면 훨씬 빠르게 풀 수 있음
*/