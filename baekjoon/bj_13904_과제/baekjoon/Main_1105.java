import java.io.*;
import java.util.*;

public class Main_1105{
  private static class Prob {
    int deadline; 
    int reward;
    public Prob(int d, int r){
      this.deadline = d;
      this.reward = r;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int N = Integer.parseInt(br.readLine());
    // 데드라인 기준 내림차순 정렬시키기
    PriorityQueue<Prob> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.deadline, o1.deadline));
    for (int i=0; i<N; i++){
      st = new StringTokenizer(br.readLine());
      int deadline = Integer.parseInt(st.nextToken());
      int reward = Integer.parseInt(st.nextToken());
      pq.add(new Prob(deadline, reward));
    }
    // 컵라면 수 기준 내림차순 정렬
    PriorityQueue<Prob> pq2 = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.reward, o1.reward));
    int total = 0;
    for (int day = N; day>0; day--){
      while (!pq.isEmpty() && pq.peek().deadline >= day){
        pq2.add(pq.remove());
      }
      if (pq2.isEmpty()) continue;
      total += pq2.remove().reward; // 컵라면 수 total에 추가
    }
    System.out.println(total);
  }
}
