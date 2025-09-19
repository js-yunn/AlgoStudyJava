package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0918 {
	static List<Integer>[] tree;
	static int delete; // 삭제한 노드
	static int answer=0; // 리프 노드 개수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N+1; i++) {
			tree[i]= new ArrayList<>();
		}
		int root = -1;
		for (int i=0; i<N; i++) {
			int p = Integer.parseInt(st.nextToken());
			if (p==-1) {
				root = i;
			}else {
				tree[p].add(i); // 부모 노드에 자식 노드 연결시키기
			}
		}
		delete = Integer.parseInt(br.readLine());
		dfs(root);
		System.out.println(answer);
	}
	
	static void dfs(int v) {
		if (v==delete) { // 삭제한 노드라면 바로 리턴
			return;
		}
		if (tree[v].isEmpty()) { // 리프 노드라면 answer++ 후 리턴
			answer++;
			return;
		}
		boolean hasChild = false; // 자식 노드 확인 플래그
		for (int child:tree[v]) { 
			if (child != delete) { // 자식 노드가 있고 delete가 아니면
				dfs(child); // 재귀 호출
				hasChild = true; // 자식 노드 있음
			}
		}
		if (!hasChild) {
			answer++;
		}
	}
}
