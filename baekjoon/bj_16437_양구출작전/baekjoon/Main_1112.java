package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1112 {
	static long[][] animalCnt;
	// animalCount[i][0]: i번 섬의 양의 수
    // animalCount[i][1]: i번 섬의 늑대의 수
	static List<Integer>[] tree;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		animalCnt = new long[N+1][2];
		tree = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			tree[i]= new ArrayList<>();
		}
		
		// 2번 섬부터 정보 입력받아서 트리 만들고 동물 수도 저장하기
		for (int i=2; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String type = st.nextToken();
			int animalNum = Integer.parseInt(st.nextToken());
			int parent = Integer.parseInt(st.nextToken());
			tree[parent].add(i); // 부모 노드랑 연결시키기
			if (type.equals("W")) {
				animalCnt[i][1]=animalNum; // 늑대면 [i][1]
			}else {
				animalCnt[i][0]=animalNum; // 양이면 [i][0]
			}
		}
		// 1번 섬에서 dfs 시작하기, 최종적으로 1번 섬에 도착하는 양의 수 계산
		long totalSheep = dfs(1);
		// 결과 출력
		System.out.println(totalSheep);
	}
	
	private static long dfs(int nowSum) {
		// 재귀 호출 부분
		long totalSheep = 0;
		for (int child:tree[nowSum]) {
			totalSheep += dfs(child); // 자식 섬에서 오는 양을 모두 가져와서 total에 더하기
		}
		
		// 양이랑 늑대 처리 부분
		totalSheep += animalCnt[nowSum][0];
		long wolf = animalCnt[nowSum][1];
		if (totalSheep>wolf) {
			return totalSheep-wolf;
		}else {
			return 0;
		}
	}

}
/*
백준 16437 - 양 구출 작전
1~n 섬
늑대는 안 움직임. 섬에 들어온 양을 잡아먹음
2~N번 섬까지 정보를 나타내는 t, a, p 있음
1번 섬에 최대한 많은 양이 도착해야 함.

생각- 1이 루트 노드, 각 노드를 부모(p) 랑 연결시키기, 노드에는 동물 정보(s/w)랑 동물 개체 수(a)가 담겨있음
각 리프 노드에서 루트 노드까지 dfs 깊이 탐색하면서 w만나면 s-w하고 0이면 break 해서 바로 가지치기
리프 노드가 w이면 하나 올라가서 s 만나는 순간부터 탐색 시작하기
class node에 p, t, a 정보 저장하기

생각 수정- 굳이 노드로 하는 것보다 트리 리스트 N크기, 동물 리스트 N크기 하면 더 효율적일 듯 
dfs에서 양 수+늑대 수 더했을 때 0보다 작아지면 return 0하기
양수이면 return total_sheep하기

*/