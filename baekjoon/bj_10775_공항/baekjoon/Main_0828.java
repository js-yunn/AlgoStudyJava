package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0828 {
	static int G, P;
	static int[] arr;
	
	static int find(int x) {
		if (x==arr[x]) return x;
		return arr[x]=find(arr[x]); 
	}
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x<y) {
			arr[y]=x;
		}else arr[x]=y;
	}

	public static void main(String[] args) throws Exception {
		/*
		 * 공항은 1~G 번호
		 * 비행기는 P개 옴, i번째 비행기를 1번부터 Gi번까지 도킹 가능
		 * 도킹할 수 없으면 공항은 즉시 폐쇄, break
		 * 
		 * G, P 입력받아서 저장
		 * Find(g) 값이 0이라면, 도킹이 불가능하니까 바로 break
		 * find(g) 값이 0이 아니라면 도킹 가능하므로 
		 * answer ++
		 * union(find(g), find(g)-1)
		 * 
		 */
		int answer = 0 ;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		arr = new int[G+1];
		for (int i=0; i<G+1; i++) {
			arr[i]=i; // 초기화 
		}
		for (int i=0; i<P; i++) {
			int g = Integer.parseInt(br.readLine());
			int gate = find(g);
			if (gate == 0) break;
			answer ++;
			union(gate, gate-1);
		}
		System.out.println(answer);
		
		br.close();
	}

}
