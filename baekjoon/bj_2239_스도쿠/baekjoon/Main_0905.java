package baekjoon;
import java.io.*;
import java.util.*;

public class Main_0905 {
	static int[][] map=new int[9][9];
	static boolean end;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i=0; i<9; i++) {
			String arr = br.readLine();
			for (int j=0; j<9; j++) {
				map[i][j]=arr.charAt(j)-'0'; // 숫자로 변환하여 입력
			}
		}
		dfs(0);
		bw.close();
	}
	static void dfs(int depth) throws IOException {
		if (end) return;
		if (depth == 81) {
			end = true;
			for (int i=0; i<9; i++) {
				for (int j=0; j<9; j++) {
					bw.write(map[i][j]+"");
				}
				bw.write("\n");
			}
			bw.flush();
			return;
		}
		int y = depth/9; // 좌표값 계산
		int x = depth%9;
		if (map[y][x] != 0) { // 해당 좌표에 값이 있다면 
			dfs(depth+1);
		}else {
			for (int i=1; i<=9; i++) {
				if (!check(y, x, i)) continue;
				map[y][x]=i;
				dfs(depth+1);
				if (end) return;
				map[y][x]=0;
			}
		}
	}
	static boolean check(int y, int x, int num) {
		// 가로 세로 체크
		for (int i=0; i<9; i++) {
			if (map[y][i] == num || map[i][x]==num) return false;
		}
		// 3*3 박스 내부 체크
		int sy = y/3*3;
		int sx = x/3*3;
		for (int i=sy; i<sy+3; ++i) {
			for (int j =sx; j<sx+3; ++j) {
				if (map[i][j]==num) return false;
			}
		}
		return true;
	}

}
