package baekjoon;
import java.io.*;
import java.util.*;
/*
 * 1038 감소하는 수
 * 0부터 9까지 한 자리 감소하는 수를 큐에 넣고, 큐에서 숫자를 하나씩 꺼냄.
 * 다음 자릿수가 현재 자릿수보다 작은 새로운 감소하는 수를 만들어 다시 큐에 추가함.
 * N번째 감소하는 수 찾으면 출력하고 종료.
*/
public class Main_0913 { 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
		if (N<=10) {
			System.out.println(N); 
			return;
		}
		Queue<Long> q = new LinkedList<>();
		for (int i=1; i<=9; i++) {
			q.add((long) i);
		}
		int cnt=9;
		while(!q.isEmpty()) {
			long now = q.poll();
			
			long lastNum = now%10; // 마지막 자릿수
			for (int i=0; i<lastNum; i++) {
				long nextNum = now*10+i;
				cnt++;
				if (cnt == N) {
					System.out.println(nextNum);
					return;
				}
				q.add(nextNum);
			}
		}
		System.out.println(-1);	
	}
}
