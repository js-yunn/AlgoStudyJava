package baekjoon;
import java.io.*;
import java.util.*;

public class Main_단축키지정 {
	static Set<Character> wordShortCut = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			
			// 1. 배열에 대해 첫번째 규칙 수행 (단어 첫 글자 비교)
			if (tryRule1(input)) {
				continue;
			}
			if (tryRule2(input)) {
				continue;
			}
			System.out.println(input);
		}
	}
	private static boolean tryRule1(String input) {
		String[] words = input.split(" ");
		for (int i=0; i<words.length; i++) {
			String word = words[i];
			if (word.isEmpty()) continue;
			char firstChar = word.charAt(0);
			char upperChar = Character.toUpperCase(firstChar);
			
			if (!wordShortCut.contains(upperChar)) {
				wordShortCut.add(upperChar);
				
				String newWord = "["+firstChar+"]"+word.substring(1);
				StringBuilder sb = new StringBuilder();
				for (int j=0; j<words.length; j++) {
					if (j==i) {
						sb.append(newWord);
					}else {
						sb.append(words[j]);
					}
					if (j<words.length-1) {
						sb.append(" ");
					}
				}
				System.out.println(sb.toString());
				return true;
				
			}
		}
		return false;
	}
	private static boolean tryRule2(String input) {
		for (int i=0; i<input.length(); i++) {
			char currentChar = input.charAt(i);
			if (!Character.isAlphabetic(currentChar)) continue;
			
			char upperChar = Character.toUpperCase(currentChar);
			if (!wordShortCut.contains(upperChar)) {
				wordShortCut.add(upperChar);
				String output = input.substring(0, i)+
						"["+currentChar+"]"+
						input.substring(i+1);
				System.out.println(output);
				return true;
			}
		}
		return false;
	}

}
/*
 * 
set 자료구조 사용하기

문자열 입력받기
문자열을 공백 단위로 split하기, 분리해서 words 배열에 저장하기
1. 배열에 대해 첫번째 규칙 수행 (단어 첫 글자 비교)
2. optionLine 순회하면서 모든 알파벳 비교

*/