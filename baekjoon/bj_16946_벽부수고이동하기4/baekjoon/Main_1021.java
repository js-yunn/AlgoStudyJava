package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1021 {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N, M;
	static int[][] data;
	
	// bfs 함수로 서로 연결된 빈 칸 찾고 그룹 크기 계산해서 리턴함
	private static int bfs(int[][] group_data, int startR, int startC, int id) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {startR, startC});
		group_data[startR][startC]= id;
		int cnt = 1;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int r = now[0]; 
			int c = now[1];
			for (int i=0; i<4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if (nr>=0 && nr<N && nc>=0 && nc<M &&
						data[nr][nc]==0 && group_data[nr][nc]==0) {
					group_data[nr][nc]=id;
					q.offer(new int[] {nr, nc});
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		data = new int [N][M];
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				data[i][j]= input.charAt(j)-'0';
			}
		}
		
		// 1. 벽 부수기 전: 빈칸끼리 먼저 id_counts에 그룹화하기
		int[][] group_arr = new int[N][M];
		Map<Integer, Integer> id_counts = new HashMap<>();
		int next_group = 1;
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				// 빈 칸이면서 아직 그룹화되지 않은 경우
				if (data[r][c]==0 && group_arr[r][c]==0) {
					int size = bfs(group_arr, r, c, next_group);
					id_counts.put(next_group, size); // id_counts 해시맵에 추가
					next_group++; // 그룹 id 값 ++
				}
			}
		}
		
		// 2. 벽 처리 단계
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				// 현재 칸이 벽일 때만 처리해야함
				if (data[r][c]==1) {
					int reachable = 0;
					Set<Integer> nearby_ids = new HashSet<>();
					// 상하좌우 탐색
					for (int i=0; i<4 ; i++) {
						int nr = r+dr[i];
						int nc = c+dc[i];
						if (nr>=0 && nr<N && nc>=0 && nc<M) {
							int id = group_arr[nr][nc];
							if (id!=0) {
								nearby_ids.add(id); // set에 id 추가
							}
						}
					}
					// set에 모인 그룹들의 크기 합산
					for (int i : nearby_ids) {
						reachable += id_counts.get(i);
					}
					reachable++; // 벽 자신도 포함시킴
					data[r][c]=reachable%10; // 10으로 나눈 나머지 저장
				}
			}
		}
		
		// 3. 결과 출력
		StringBuilder sb = new StringBuilder();
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				sb.append(data[r][c]);
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
		
	}
}
