package baekjoon;
import java.io.*;
import java.util.*;

class Lecture implements Comparable<Lecture> {
    int start;
    int end;

    public Lecture(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public int compareTo(Lecture other) {
        if (this.start == other.start) {
            return this.end - other.end; // 시작 시간이 같으면 종료 시간 오름차순
        }
        return this.start - other.start; // 시작 시간 오름차순
    }
}

public class Main_1031 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Lecture[] lectures = new Lecture[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            lectures[i] = new Lecture(S, T);
        }
        // 시작 시간 기준으로 정렬
        Arrays.sort(lectures); 
        
        PriorityQueue<Integer> roomEnds = new PriorityQueue<>();
        roomEnds.add(lectures[0].end); // 첫 강의 종료시간 큐에 넣기
        
        for (int i = 1; i < N; i++) {
            int currentStart = lectures[i].start;
            int earliestEnd = roomEnds.peek(); // 가장 빨리 끝나는 강의실의 종료 시간

            if (currentStart >= earliestEnd) {
                roomEnds.poll();
                roomEnds.add(lectures[i].end);
            } 
            else {
                roomEnds.add(lectures[i].end);
            }
        }
        System.out.println(roomEnds.size());
    }
}