package a0808;
import java.io.*;
import java.util.*;

public class Solution_1206_1일차View {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/d3_1206_view_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		for (int t=1; t<=10; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 건물 개수
			int[] building = new int[N]; // 건물 높이
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				building[j]=Integer.parseInt(st.nextToken());
			}
			int viewSum=0;
			for (int i=2; i<N-2; i++) {
				int forward = Math.max(building[i-2], building[i-1]); // 전방 2칸 중 높은 건물 높이
				int backward = Math.max(building[i+1], building[i+2]); // 후방 2칸 중 높은 건물 높이
				int maxHeight = Math.max(forward, backward); // 전방, 후방 중 가장 높은 건물 높이 maxHeight
				if (building[i]>maxHeight) viewSum+=(building[i]-maxHeight);
				
			}
			System.out.println("#"+t+" "+viewSum);
		}
	}
}
