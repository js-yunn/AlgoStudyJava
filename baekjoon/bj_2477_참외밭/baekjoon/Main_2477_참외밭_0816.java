package baekjoon;
import java.io.*;
import java.util.*;

public class Main_2477_���ܹ�_0816 {

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
			// ���� �� ã��
			if (dir[i]==1 || dir[i]==2) {
				if (maxX<len[i]) {
					maxX = len[i];
					indexX = i;
				}
			} else { // ���� �� ã��
				if (maxY<len[i]) {
					maxY = len[i];
					indexY = i;
				}
			}
			
		}
		// ū �簢�� ���� totalArea
		int totalArea = maxX*maxY;

		// ���� �簢�� ���� smallArea
		int smallX = Math.abs(len[(indexY+5)%6]-len[(indexY+1)%6]);
		int smallY = Math.abs(len[(indexX+5)%6]-len[(indexX+1)%6]);
		int smallArea = smallX*smallY;
		
		int answer = (totalArea-smallArea)*melonNum;
		System.out.println(answer);
		
		br.close();
	}
}
