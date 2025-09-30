package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0927 {
	private static long[] minArr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		calculateMin();
		while (tc-- >0) {
			int count = Integer.parseInt(br.readLine());
			long smallNum = minArr[count];
			String bigNum = getMax(count);
			sb.append(smallNum).append(" ").append(bigNum).append('\n');
		}
		System.out.println(sb);
	}
	
	private static void calculateMin() {
		minArr = new long[101];
		Arrays.fill(minArr, Long.MAX_VALUE);
		String[] minDigs = {"1", "7", "4", "2", "0", "8"}; // 2~7개 성냥으로 만드는 최소 자릿수
		// 기본값 (2개~8개 성냥) 초기화
		minArr[2]=1;
		minArr[3]=7;
		minArr[4]=4;
		minArr[5]=2;
		minArr[6]=6;
		minArr[7]=8;
		minArr[8]=10;
		
		for (int i=9; i<=100; i++) {
			for (int j=2; j<=7; j++) { // 마지막 자릿수에 2~7개 성냥 사용
				if (i-j>0) {
					String lastDig = minDigs[j-2];
					String prevMin = String.valueOf(minArr[i-j]);
					String newNumStr = prevMin+lastDig; // 새로운 숫자 생성 (문자열 연결)
					minArr[i]=Math.min(minArr[i], Long.parseLong(newNumStr)); // 최소값 갱신
				}
			}
		}
	}
	
	private static String getMax(int count) {
		StringBuilder result = new StringBuilder();
		if (count%2!=0) { // 홀수 개라면
			count -= 3; // 3개를 사용해 맨 앞에 '7' 배치
			result.append(7);
		}
		int ones = count/2; // 남은 짝수 개수는 2개씩 '1'로 채워 자릿수 최대화
		for (int i=0; i<ones; i++) {
			result.append(1);
		}
		return result.toString();
	}
}
