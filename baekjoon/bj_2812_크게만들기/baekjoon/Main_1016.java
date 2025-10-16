package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1016 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int deleteCnt = K;
		String numbers = br.readLine();
		Deque<Character> stack = new ArrayDeque<>();
		
		for (int i=0; i<N; i++) {
			char now = numbers.charAt(i);
			// 스택 맨 위 숫자가 now 보다 작으면 제거하고 K--
			while (deleteCnt>0 && !stack.isEmpty() && stack.peekLast()<now) {
				stack.removeLast();
				deleteCnt--;
			}
			
			// 아니면 스택에 추가
			stack.addLast(now);
		}
		
		StringBuilder result = new StringBuilder();
		for (int i=0; i<N-K; i++) {
			result.append(stack.removeFirst());
		}
		System.out.println(result.toString());
	}
}

/*
numbers 문자열 입력받기
스택 하나 만들기
앞에부터 하나씩 넣음
이제 넣으려는 숫자랑 스택에 이미 있는 숫자랑 비교해서
1. 스택에 있는게 더 작으면 스택 pop하고 숫자 push, k--
2. 넣으려는 숫자가 더 작으면 스택에 push

k==0이면 계속 스택에 순서대로 쌓아서 스택 출력하기
*/