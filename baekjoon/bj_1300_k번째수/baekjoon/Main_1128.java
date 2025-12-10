package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1128 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        // 이분 탐색, low=1, high=K로 시작
        long low = 1;
        long high = K;
        long answer = 0;
        while (low <= high) {
            long mid = (low + high) / 2;
            long count = 0;

            // 각 행별로 mid보다 작거나 같은 수 min(N, mid/i)를 더함
            for (int i = 1; i <= N; i++) {
                count += Math.min(N, mid / i);
            }
            
            // count가 K보다 크거나 같으면 더 작아지도록 high 값 조정
            if (count >= K) {
                answer = mid;
                high = mid - 1;
            } 
            // count가 K보다 작으면 low 값 조정해서 더 커지도록
            else {
                low = mid + 1;
            }
        }
        
        System.out.println(answer);
        
    }
}

/*
백준 1300- K번째 수
배열 다 정렬시키면 N*N이라 시간 초과 발생함
예 ) N=3
1, 2, 3
2, 4, 6
3, 6, 9
이 2차원 배열을 1열로 나열하고 정렬함
1, 2, 2, 3, 3, 4, 6, 6, 9
여기서 b[k]=b[7]=6
다 만들어서 정렬시키면 무조건 시간 초과니까 이분 탐색으로 mid값에 따라 count 개수 구하고,
k랑 비교해서 mid값 조절해가면서 찾기

근데 여기서 count를 어떻게 구하지?
count += min(N, mid/i)
mid/i하면 mid 이하 배수의 개수 알 수 있음
*/
