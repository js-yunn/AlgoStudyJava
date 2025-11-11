package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1107 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // pay 값 넣는 우선순위 큐 pq

        int[][] arr = new int[N][2];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]); // d 기준으로 오름차순 정렬
            }
        });

        for(int i = 0; i < N; i++){
            pq.add(arr[i][0]); // 강연의 p 값 큐에 넣기

            if(pq.size() > arr[i][1]){ // 큐 크기가 현재 강연의 d 값보다 크면
                pq.poll(); // 가장 작은 p 값 pop하기
            }
        }

        int result = 0;
        while(!pq.isEmpty()){
            result += pq.poll();
        }
        System.out.println(result);
    }
}

/* 2109. 순회공연
문제: 각 대학에서 d일 안에 강연 해주면 p만큼의 강연료 받음. 최대로 벌 수 있는 돈 구하기
알고리즘: 그리디랑 우선순위 큐
강의 date 기준으로 오름차순 정렬하기
1. 강의의 p 값을 큐에 넣기
2. 큐의 size가 현재 강의의 date보다 크면 날짜가 중복되는거니 가장 낮은 p 값을 큐에서 제거함
*/