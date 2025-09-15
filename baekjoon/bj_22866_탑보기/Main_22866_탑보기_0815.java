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
		int[] seeCnt = new int[N+1]; // �� �� �ִ� �ǹ� ����
		int[] nearest = new int[N+1]; // ���� ����� �ǹ�
		Arrays.fill(nearest, Integer.MAX_VALUE);
		
		Stack<Integer> stack = new Stack<>();
		// L->R
		for (int i=1; i<=N; i++) {
			// height[i] ���� ���ٸ� pop
			while(!stack.isEmpty() && height[stack.peek()]<=height[i]) {
				stack.pop();
			}
			seeCnt[i] += stack.size(); // ���̴� �ǹ� ���� ����
			if (!stack.isEmpty()) {
				nearest[i]=stack.peek();
			}
			stack.push(i); // ���� �ǹ��� stack���� ���� ���� �ǹ��̹Ƿ� push
		}
		stack.clear(); // L->R �Ϸ� �� �ʱ�ȭ
		// R->L
		for (int i=N; i>=1; i--) {
			while(!stack.isEmpty() && height[stack.peek()]<=height[i]) {
				stack.pop();
			}
			seeCnt[i]+= stack.size();
			if (!stack.isEmpty()) {
				// ���� ����� �ǹ� Ȯ���ϰ� ���纸�� �ִٸ� nearest[i] ����
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
