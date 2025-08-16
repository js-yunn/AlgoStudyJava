package baekjoon;
import java.io.*;
import java.util.*;

public class Main_2477_참외밭_0816 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int melonNum = Integer.parseInt(st.nextToken());
		int[] dir = new int[6];
		int[] len = new int[6];
		int maxX = 0; int maxY = 0;
		int indexX = 0; int indexY = 0;
		
		for (int i=0; i<6; i++) {
			st = new StringTokenizer(br.readLine());
			dir[i] = Integer.parseInt(st.nextToken());
			len[i] = Integer.parseInt(st.nextToken());
			// 가로 변 찾기
			if (dir[i]==1 || dir[i]==2) {
				if (maxX<len[i]) {
					maxX = len[i];
					indexX = i;
				}
			} else { // 세로 변 찾기
				if (maxY<len[i]) {
					maxY = len[i];
					indexY = i;
				}
			}
			
		}
		// 큰 사각형 넓이 totalArea
		int totalArea = maxX*maxY;

		// 작은 사각형 넓이 smallArea
		int smallX = Math.abs(len[(indexY+5)%6]-len[(indexY+1)%6]);
		int smallY = Math.abs(len[(indexX+5)%6]-len[(indexX+1)%6]);
		int smallArea = smallX*smallY;
		
		int answer = (totalArea-smallArea)*melonNum;
		System.out.println(answer);
		
		br.close();
	}
}
