package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0908 {
	static class TrieNode {
		Map<Character, TrieNode> childNode = new HashMap<>();
		boolean isEndOfWord; // 단어의 끝 표시
	}

	static class Trie {
		TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		public boolean insert(String word) {
			TrieNode current = root;
			for (int i = 0; i < word.length(); i++) {
				char ch = word.charAt(i);
				TrieNode node = current.childNode.get(ch);
				
				if (node == null) {
					node = new TrieNode();
					current.childNode.put(ch, node);
				}
				current = node;

				// 더 짧은 단어가 존재하는지 확인
				if (current.isEndOfWord) {
					return false;
				}
			}
			
			// 현재 노드에 자식 노드가 있는지 확인
			if (!current.childNode.isEmpty()) {
				return false;
			}
			
			current.isEndOfWord = true;
			return true;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			Trie trie = new Trie();
			boolean isConsistent = true;
			
			List<String> phoneNumbers = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				phoneNumbers.add(br.readLine());
			}
			Collections.sort(phoneNumbers);
			
			for (String phoneNumber : phoneNumbers) {
				if (!trie.insert(phoneNumber)) {
					isConsistent = false;
					break;
				}
			}

			bw.write(isConsistent ? "YES\n" : "NO\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
