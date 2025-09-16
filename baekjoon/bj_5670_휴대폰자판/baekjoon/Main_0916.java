package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0916 {
	// 트라이 노드 클래스
	static class Node{
		HashMap<Character, Node> child = new HashMap<>();
		boolean isCount;
	}
	// 트라이 구조 클래스
	static class Trie{
		Node root;
		public Trie() {
			this.root=new Node();
		}
		public void insert(String word) {
			Node currentNode = this.root;
			for(int i=0; i<word.length(); i++) {
				char ch = word.charAt(i);
				// 현재 노드의 자식 중 해당 문자가 없으면 새로 생성
				currentNode = currentNode.child.computeIfAbsent(ch, c->new Node());
			}
			currentNode.isCount = true; // 단어의 끝임을 표시
		}
		// 단어를 입력하기 위해 몇 번의 키를 눌러야 하는지 계산
		public double getCnt(String word) {
			double cnt = 1.0d; // 첫 글자 1로 시작
			Node tmpNode = this.root;
			tmpNode = tmpNode.child.get(word.charAt(0));
			
			for (int i=1; i<word.length(); i++) {
				// 현재 노드의 자식 수가 2개 이상일 때
				if (tmpNode.child.size()>=2) {
					cnt += 1.0d;
				} // 현재 노드에서 단어가 끝나고 자식 노드가 1개일 때 
				else if (tmpNode.child.size()==1 && tmpNode.isCount) {
					cnt += 1.0d;
				}
				char ch = word.charAt(i);
				tmpNode = tmpNode.child.get(ch);
			}
			return cnt;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line=br.readLine())!=null) {
			if (line.isEmpty()) continue; // 빈 줄 처리
			int N = Integer.parseInt(line);
			String cur = null;
			String[] words = new String[N];
			Trie trie = new Trie();
			for (int i=0; i<N; i++) {
				cur = br.readLine();
				trie.insert(cur);
				words[i]=cur;
			}
			double total = 0.0d;
			for (String e:words) {
				total += trie.getCnt(e); // 각 단어의 키 입력 횟수 합계
			}
			double average = total/N; // 평균 계산
			sb.append(String.format("%.2f", average)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	
}
