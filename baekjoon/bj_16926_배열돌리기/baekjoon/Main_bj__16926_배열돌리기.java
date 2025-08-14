package baekjoon;
import java.io.*;
import java.util.*;

public class Main_bj__16926_배열돌리기 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st=new StringTokenizer(br.readLine(), " ");
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int R=Integer.parseInt(st.nextToken());
		int[][] arr=new int[N][M];
		// 배열 입력받기
		for (int i=0; i<N;i++) {
			st=new StringTokenizer(br.readLine()," ");
			for (int j=0; j<M; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		// R만큼 회전시키기
		for (int r=0; r<R; r++) {
			for (int i=0; i<Math.min(M, N)/2; i++) {
				int start = arr[i][i];
				//왼쪽으로
				for (int k=i; k<M-i-1; k++) {
					arr[i][k]=arr[i][k+1];
				}
				//아래에서 위로
				for (int k=i; k<N-i-1; k++) {
					arr[k][M-i-1]=arr[k+1][M-i-1];
				}
				//오른쪽으로
				for (int k=M-i-1; k>i; k--) {
					arr[N-i-1][k]=arr[N-i-1][k-1];
				}
				//위에서 아래로
				for (int k=N-i-1; k>i; k--) {
					arr[k][i]=arr[k-1][i];
				}
				
				arr[i+1][i]=start;
			}
		}
		// 출력하기
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}

}
