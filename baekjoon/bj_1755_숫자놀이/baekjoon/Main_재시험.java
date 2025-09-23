package baekjoon;
import java.io.*;
import java.util.*;

public class Main_재시험 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new InputStreamReader(System.in));
		int N = sc.nextInt();
		int M = sc.nextInt();
		String[] arr = {"zero", "one","two","three","four","five","six","seven","eight","nine"};
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i=N; i<=M; i++) {
			if (i<10) map.put(arr[i], i);
			else {
				map.put(arr[i/10]+" "+arr[i%10], i);
			}
		}
		List<String> keyList = new ArrayList<>(map.keySet());
		Collections.sort(keyList);
		int cnt = 1;
		for (String key: keyList) {
			System.out.print(map.get(key)+" ");
			if (cnt++%10 == 0) System.out.println();
		}
		
		
	}

}
