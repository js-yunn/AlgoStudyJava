package baekjoon;
import java.io.*;

public class Main_0912 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		String answer = br.readLine();
		int[] table = makeTable(answer); // 접두사-접미사 테이블 생성
		String result="NO";
		if (KMP(A, answer, table) && KMP(B, answer, table)) { 
			result = "YES";
		}
		System.out.println(result);
	}
	// 가장 긴 접두사-접미사인 부분 문자열 길이를 저장하는 table 배열을 리턴하는 makeTable
	static int[] makeTable(String str) {
		int[] table = new int[str.length()];
		int idx = 0;
		for (int i=1; i<table.length; i++) {
			while(idx>0 && str.charAt(i)!=str.charAt(idx)) {
				idx = table[idx-1];
			}
			if (str.charAt(idx)== str.charAt(i)) {
				table[i]= ++idx;
			}
		}		
		return table;
	}
	// parent 문자열에서 target 문자열을 찾는 함수 KMP()
	static boolean KMP(String parent, String target, int[] table) {
		int idx = 0;
		for (int i=0; i<parent.length(); i++) {
			while(idx>0 && parent.charAt(i)!=target.charAt(idx)) {
				idx=table[idx-1]; // 불필요한 부분 건뛰
			}
			if (parent.charAt(i)==target.charAt(idx)) { // 두 문자가 일치하면
				if (idx == target.length() - 1) { // 패턴을 모두 찾았으니 리턴
					return true;
				}else {
					idx++; // idx 증가시켜 계속 비교해나감
				}
			}
		}
		return false;
	}
}
