package baekjoon;
import java.io.*;
import java.util.*;

public class Main_bj_10868_최솟값 {
	static int N;
	static long[] arr;
	static long[] tree;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new long[N+1];
		for (int i=1; i<=N; i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		int height = (int)Math.ceil(Math.log(N)/Math.log(2))+1; // 트리 높이 (logN 올림 +1) 계산
		int nodeCnt = (int)Math.pow(2, height); // 트리 전체 노드 수(2^height) 계산
		tree = new long[nodeCnt];
		
		init(1, N, 1);
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			sb.append(find(1, N, left, right, 1)).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	// 자식들 최솟값을 노드에 입력하는 init 메서드
	public static long init(int start, int end, int node) {
		if (start == end) { // 트리의 가장 밑부분
			return tree[node]=arr[start]; // 입력 받은 배열 입력
		}
		int mid = (start+end)/2;
		// 가장 밑부분이 아니라면 좌우 자식 노드 중 최소값 리텅
		return tree[node]=Math.min(init(start,mid,node*2), init(mid+1, end, node*2+1));
	}
	// 탐색하려는 범위 중 최솟값 찾는 find 메서드
	public static long find(int start, int end, int left, int right, int node) {
		if (right<start || end<left) { // 범위 벗어남
			return 1000000001;
		}
		if (left<=start && end<=right) { // 범위 내로 들어오면
			return tree[node]; // 해당 노드값 리턴
		}
		// 일부가 범위 안에 들어오면 최소값 리턴
		int mid = (start+end)/2; 
		return Math.min(find(start, mid, left, right, node*2), 
				find(mid+1, end, left, right, node*2+1));
	}
}
