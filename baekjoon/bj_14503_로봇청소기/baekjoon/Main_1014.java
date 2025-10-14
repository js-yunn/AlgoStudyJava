package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1014 {
	static int[] dr = {-1, 0, 1, 0}; // 북 동 남 서
	static int[] dc = {0, 1, 0, -1};
	static int N, M;
	static int[][] map;
	static int count;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		simulation(r, c, d);
		System.out.println(count);

	}
	static void simulation(int r, int c, int d) {
		while (true) {
			// 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸 청소
			if (map[r][c]==0) {
				map[r][c]=2; count++;
			}
			
			// 2&3. 현재 칸 주변 4칸 중 청소되지 않은 빈 칸이 있는지 판별
			boolean isThereZero = false;
			for (int i=0; i<4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if (map[nr][nc]==0) {
					isThereZero = true;
					break;
				}
			}
			// 2. 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
			if (!isThereZero) {
				// 후진 가능한지 판별
				int backd = (d+2)%4;
				int br = r+dr[backd];
				int bc = c+dc[backd];
				if (map[br][bc]!=1) {
					r=br; c=bc; // 2-1. 한 칸 후진 후 1번으로 돌아가기
				} else {
					break; // 2-2. 종료 조건
				}
			}
			
			// 3. 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
			else {
				// 반시계 방향으로 회전 및 전진 시도
				for (int i=0; i<4; i++) {
					d = (d-1+4)%4;
					int nr = r+dr[d];
					int nc = c+dc[d];
					if (map[nr][nc]==0) {
						r = nr; c=nc;
						break;
					}
				}
			}
			
		}
		
	}

}
