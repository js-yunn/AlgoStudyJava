package baekjoon;
import java.io.*;
import java.util.*;

/*
 * baekjoon_1786 찾기 문제
 * 문제 풀이: KMP 알고리즘 사용해서 문자열 찾는 코드
 * 먼저 패턴 문자열을 분석해서 접두사 접미사 테이블 table을 만들고, table을 이용해서 불일치가 발생했을 때 
 * 얼마나 점프를 해야할지 table[idx-1]로 정해서 점프하여 완전 탐색보다 더 빠른 탐색 진행함
 */

public class Main_0911 {
	static int[] table; 
	static String text, pattern;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		text = br.readLine();
		pattern = br.readLine();
		
		int len = pattern.length();
		table = new int[len];
		setTable();
		System.out.println(kmp(len));
		System.out.println(sb);
		br.close();
	}
	public static void setTable() {
		int i=0;
		for (int j=1; j<pattern.length(); j++) {
			while(i>0 && pattern.charAt(j)!=pattern.charAt(i))
				i=table[i-1];
			if (pattern.charAt(j)==pattern.charAt(i))
				table[j]=++i;
		}
	}
	public static int kmp(int len) {
		int count = 0;
		int j = 0;
		for (int i=0; i<text.length(); i++) {
			while(j>0 && text.charAt(i)!=pattern.charAt(j))
				j = table[j-1];
			if (text.charAt(i)== pattern.charAt(j)) {
				if (j == pattern.length()-1) {
					sb.append(i+2-len).append(" ");
					count++;
					j = table[j];
					
				}else j++;
			}
		}
		return count;
	}
}
