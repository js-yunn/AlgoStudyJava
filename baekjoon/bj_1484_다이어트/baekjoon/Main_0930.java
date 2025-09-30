package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0930 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new InputStreamReader(System.in));
		int G = sc.nextInt(); 
		int now=2;
		int remember=1;
		boolean flag = false;
		while (true) {
			long diff = now*now-remember*remember;
			if (now == remember) break;
			if (diff == G) {
				System.out.println(now);
				now++;
				flag = true;
			}
			else if (now*now - remember*remember >G) {
				remember++;
			}
			else if (now*now - remember*remember < G) {
				now++;
			}
		}
		if (!flag) System.out.println(-1);
	}

}
