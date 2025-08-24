package baekjoon;
import java.io.*;
import java.util.StringTokenizer;

public class Main_0821 {
	
	public static int N;
	public static int M;
	public static int K;
	public static int min = Integer.MAX_VALUE;
	public static int[] seq;
	public static int[][] arr;
	public static int[][] turnArr;
	public static boolean[] flag;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//값을 입력할 배열
		arr = new int[N][M];
		//배열을 돌릴 범위
		turnArr = new int[K][3];
		//돌릴 범위 k개
		flag = new boolean[K];
		//돌리는 순서를 저장할 배열
		seq = new int[K];
		
		//배열값 입력
		for(int r = 0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c<M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		//돌릴 범위 입력
		for(int i = 0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			turnArr[i][0] = Integer.parseInt(st.nextToken())-1;
			turnArr[i][1] = Integer.parseInt(st.nextToken())-1;
			turnArr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0);
		
		bw.write(String.valueOf(min));
		br.close();
		bw.close();
	}
	
	public static void dfs(int depth) {
		//k번 돌렸을 떄 최소합인 행을 구함
		if(depth == K) {
			turn();
			return;
		}
		//k번 돌리는 순열 생성
		for(int i = 0; i<K; i++) {
			if(flag[i])
				continue;
			flag[i] = true;
			//돌릴 범위 순서를 저장
			seq[depth] = i;
			dfs(depth+1);
			flag[i]=false;
		}
	}
	
	//정방향 돌리기(시계방향)
	public static void turn() {
		int[][] temp = new int[N][M];
		//원본 배열은 바꾸지 않으며, temp 배열을 생성
		for(int r = 0; r<N; r++) {
			for(int c = 0; c<M; c++) {
				temp[r][c] = arr[r][c];
			}
		}
		
		//순서대로 k번 돌리기
		for(int k : seq) {
			
			//범위 입력
			int row = turnArr[k][0];
			int col = turnArr[k][1];
			int s = turnArr[k][2];
			
			//테두리부터 안쪽으로 들어옴
			for(int i = s; i>0; i--) {
				int r = row-i;
				int c = col+i;
				//우
				int tempRT = temp[r][col+i];
				while(c != col-i) {
					temp[r][c] = temp[r][c-1];
					c--;
				}
				//상
				while(r != row+i) {
					temp[r][c] = temp[r+1][c];
					r++;
				}
				//좌
				while(c != col+i) {
					temp[r][c] = temp[r][c+1];
					c++;
				}
				//하
				while(r != row-i) {
					temp[r][c] = temp[r-1][c];
					r--;
				}
				temp[row-i+1][col+i] = tempRT;
			}
			
		}
		//돌리기가 끝나고 행별 합을 계산 후 min값과 비교
		for(int r = 0; r<N; r++) {
			int sum = 0;
			for(int c = 0; c<M; c++)
				sum += temp[r][c];
			min = Math.min(min, sum);
		}
	}

}