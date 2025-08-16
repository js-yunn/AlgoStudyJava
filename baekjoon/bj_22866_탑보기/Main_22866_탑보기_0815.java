import java.io.*;
import java.util.*;

public class Main_22866_탑보기_0815 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] height = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			height[i]=Integer.parseInt(st.nextToken());
		}
		int[] seeCnt = new int[N+1]; // 볼 수 있는 건물 개수
		int[] nearest = new int[N+1]; // 가장 가까운 건물
		Arrays.fill(nearest, Integer.MAX_VALUE);
		
		Stack<Integer> stack = new Stack<>();
		// L->R
		for (int i=1; i<=N; i++) {
			// height[i] 보다 낮다면 pop
			while(!stack.isEmpty() && height[stack.peek()]<=height[i]) {
				stack.pop();
			}
			seeCnt[i] += stack.size(); // 보이는 건물 개수 저장
			if (!stack.isEmpty()) {
				nearest[i]=stack.peek();
			}
			stack.push(i); // 현재 건물이 stack에서 가장 낮은 건물이므로 push
		}
		stack.clear(); // L->R 완료 후 초기화
		// R->L
		for (int i=N; i>=1; i--) {
			while(!stack.isEmpty() && height[stack.peek()]<=height[i]) {
				stack.pop();
			}
			seeCnt[i]+= stack.size();
			if (!stack.isEmpty()) {
				// 가장 가까운 건물 확인하고 현재보다 멀다면 nearest[i] 갱신
				if (Math.abs(nearest[i]-i)>Math.abs(stack.peek()-i)) {
					nearest[i]=stack.peek();
				}
			}
			stack.push(i);
		}
		for (int i=1; i<=N; i++) {
			if (seeCnt[i]==0) System.out.println(0);
			else System.out.println(seeCnt[i]+" "+nearest[i]);
		}
	}

}
