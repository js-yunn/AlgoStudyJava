package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1106 {

    static class Jewelry implements Comparable<Jewelry> {
        int w; // 보석 무게 (Weight)
        int v; // 보석 가격 (Value)

        public Jewelry(int w, int v) {
            this.w = w;
            this.v = v;
        }

        // 무게 오름차순, 보석 무게 같으면 가격 내림차순으로 정렬
        @Override
        public int compareTo(Jewelry o) {
            if (this.w == o.w) return o.v - this.v;
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        long answer = 0;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 아직 처리 안 된 보석
        PriorityQueue<Jewelry> jewelries = new PriorityQueue<>(); 
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jewelries.add(new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 가방 최대 무게 입력
        Integer[] bags = new Integer[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            bags[i] = Integer.parseInt(st.nextToken());
        }

        // 가방 무게 기준 오름차순 정렬
        Arrays.sort(bags);
        // 가방에 담을 수 있는 보석의 value 기준 내림차순 정렬시키는 pq
        PriorityQueue<Jewelry> pq = new PriorityQueue<>((o1, o2) -> o2.v - o1.v); 

        for (int bagCapacity : bags) {
            // 가방에 담을 수 있는 보석이면 pq에 추가
            while (!jewelries.isEmpty() && bagCapacity >= jewelries.peek().w) {
                pq.add(jewelries.poll());
            }
            // pq에서 가장 비싼 보석을 꺼내 총합에 더함
            if (!pq.isEmpty()) {
                answer += pq.poll().v;
            }
        }
        
        System.out.print(answer);
    }
}

/*
배낭 문제, 가방에는 최대 하나의 보석만 넣을 수 있음. 보석 최대 가격 구하기
전: 보석 무게 순으로 내림차순 정렬, 무게 같으면 보석 가치 순으로 내림차순 정렬
k개 가방에 n개의 보석을 담음
담은 보석은 visit로 하고 무게가 가방 용량 초과하면 continue로 했는데 n*k라서 시간초과남

후: 우선순위 큐 이용
현재 가방에 담을 수 있는 보석을 후보로 큐에 넣고, 가장 무거운 걸 마지막에 더해줘서 시간초과 해결함
1. 가방을 무게 기준 오름차순 정렬해서 작은 가방부터 처리
2. 작은 가방부터 순서대로 그 가방에 넣을 수 있는 모든 보석을 pq에 담고, pq에는 value가 높은 순서로 정렬시킴
3. pq에서 가장 비싼 보석을 poll해서 총 가격에 더함
*/