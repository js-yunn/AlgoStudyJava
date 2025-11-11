package baekjoon;
import java.io.*;
import java.util.*;


public class Main_1105_2 {
    static int N;
    //양수는 내림차순 정렬
    static PriorityQueue<Integer> plus = new PriorityQueue<>(Comparator.reverseOrder());
    //음수는 오름차순 정렬
    static PriorityQueue<Integer> minus = new PriorityQueue<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(br.readLine());
            if(num <= 0)
                minus.add(num);
            else
                plus.add(num);
        }
        int answer = 0;
        //음수일 때 최대값 구하기
        while(!minus.isEmpty()){
            int cur = minus.poll();
            if(minus.isEmpty()){	//마지막 하나 남으면 더하기
                answer += cur;
                break;
            }
            //다음 값이 0일 때
            if(minus.peek() == 0)
                minus.poll();
            //(현재 값 x 다음 음수 값) 묶기
            else
                answer += cur * minus.poll();
        }
        //양수일 때 최대값 구하기
        while(!plus.isEmpty()){
            int cur = plus.poll();
            //마지막 하나 남으면 그냥 더하기
            if(plus.isEmpty()){
                answer += cur;
                break;
            }
            //값이 1일 때
            if(cur == 1)
                answer += 1;
            //다음 값도 1이면 둘 다 더하기
            else if(plus.peek() == 1)
                answer += cur + plus.poll();
            //(현재 값 x 다음 양수 값) 묶어서 더하기
            else
                answer += cur * plus.poll();
        }
        System.out.println(answer);
    }
}

/*
1744. 수 묶기
1*1보다 1+1이 더 크니 현재 숫자가 1이랑 다음 숫자도 1인지 검사하기, 나머지는 묶는게 무조건 이득
입력값에 대해 양수끼리 음수끼리 서로 다른 큐에 넣기
양수는 내림차순 정렬(1000->1), 음수는 오름차순 정렬(-1000->0)
음수일 때 최대값: 마지막 하나 남으면 더하고 나머지는 두개씩 곱하기
양수일 때 최대값: 마지막 하나 남으면 더하고, 현재가 1이 다음 값도 1이면 둘 다 더하고, 아니면 두개씩 곱하기
*/