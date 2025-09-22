package baekjoon;
import java.io.*;
import java.util.*;


public class Main_0919 {
	static class Node{
		int num;
		Node left, right;
		Node(int num){
			this.num = num;
		}
		Node(int num, Node left, Node right){
			this.num=num;
			this.left=left;
			this.right=right;
		}
		void insert(int n) {
			if (n<this.num) {
				if (this.left==null) this.left=new Node(n);
				else this.left.insert(n);
			}else {
				if (this.right==null)this.right=new Node(n);
				else this.right.insert(n);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node root = new Node(Integer.parseInt(br.readLine()));
		
		while (true) {
			String next = br.readLine();
			if (next==null) break;
			root.insert(Integer.parseInt(next));
			
		}
		postOrder(root);
	}
	static void postOrder(Node n) {
		if (n == null) return;
		postOrder(n.left);
		postOrder(n.right);
		System.out.println(n.num);
	}

}
