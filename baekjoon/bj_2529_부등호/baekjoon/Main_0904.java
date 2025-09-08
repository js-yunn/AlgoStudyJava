package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0904 {
	static int K;
	static char arr[];
	static boolean v[] = new boolean[10];
	static ArrayList<String> answer = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 부등호 배열 입력받기
		arr = new char[K];
		for (int i=0; i<K; i++) {
			arr[i]=st.nextToken().charAt(0);
		}
		
		// 숫자 10개에 대해 모두 dfs 진행, v배열 업데이트
		for (int i=0; i<10; i++) {
			v[i]= true;
			dfs(i, 0, i+"");
			v[i]=false;
		}
		
		// 출력
		System.out.println(answer.get(answer.size()-1));
		System.out.println(answer.get(0));
	}
	
	static void dfs(int now, int cnt, String num) {
		if (cnt==K) {
			answer.add(num);
			return;
		}
		for (int next=0; next<10; next++) {
			if (!v[next]) {
				if ((arr[cnt]=='<' && now<next) || (arr[cnt]=='>' && now>next)) {
					v[next]=true;
					dfs(next, cnt+1, num+next);
					v[next]=false;
				}
			}
		}
	}
}