package baekjoon.bj_상어초등학교;

import java.util.*;
import java.io.*;

public class Main_0803 {
    static int N;
    static final int[] dx = { 1, -1, 0, 0 };
    static final int[] dy = { 0, 0, 1, -1 };
    static int[][] classroom; // 교실 좌석
    static Map<Integer, List<Integer>> studentLikes; // 각 학생이 좋아하는 학생 리스트 map에 저장

    /*
     * static class Student{
     * int id;
     * List<Integer> likes;
     * public Student(int id, List<Integer> likes) {
     * this.id = id;
     * this.likes = likes;
     * }
     * }
     */

    static class Seat implements Comparable<Seat> {
        int row, col;
        int likeCnt; // 주변에 좋아하는 학생 수
        int emptyCnt; // 주변 빈칸 수

        public Seat(int row, int col, int likeCnt, int emptyCnt) {
            this.row = row;
            this.col = col;
            this.likeCnt = likeCnt;
            this.emptyCnt = emptyCnt;
        }

        @Override
        public int compareTo(Seat other) {
            // 1. 주변에 좋아하는 학생 수가 많은 자리 (내림차순)
            if (this.likeCnt != other.likeCnt) {
                return other.likeCnt - this.likeCnt;
            }
            // 2. 주변 빈칸 많은 자리 (내림차순)
            if (this.emptyCnt != other.emptyCnt) {
                return other.emptyCnt - this.emptyCnt;
            }
            // 3. 행 번호 작은 자리 (오름차순)
            if (this.row != other.row) {
                return this.row - other.row;
            }
            // 4. 열 번호 작은 자리 (오름차순)
            return this.col - other.col;
        }

    }

    public static void main(String[] args) throws IOException {
        /*
         * N 크기 입력받음.
         * N의 제곱 크기만큼 학생 번호와 그 학생이 좋아하는 학생 4명의 번호가 한 줄마다 입력됨. 입력된 학생 순서대로 자리가 정해짐.
         * 일단 첫번째 학생은 항상 가운데에 배정되므로 (n+1)/2+1의 위치에 배정됨.
         * 다음 학생부터, 다음의 알고리즘에 따라 자리가 선정됨.
         * 가장 먼저, 현재 자리 배치된 학생 중 본인이 좋아하는 학생 번호가 있는지 검사함.
         * 만약 좋아하는 학생이 있다면, 그 학생 근처로 4방 탐색을 진행함.
         * 4방 탐색을 하면서 근처에 또 다른 좋아하는 학생이 있다면 거리가 1인 자리에 앉고, 다른 좋아하는 학생이 없다면 4방 탐색을 하면서 가장
         * 근처에 빈칸이 많은 곳에 앉음.
         * 만약 근처에 빈칸이 같은 수만큼 비어있는 자리가 여러개 존재한다면, 그 중 행이 가장 작은 위치에 앉음.
         * 또 행이 같은 자리가 여러 곳이라면 그 중 열이 가장 작은 자리에 앉음.
         * 
         * 다음으로, 현재 자리 배치된 학생 중 본인이 좋아하는 학생 번호가 없다면, 위의 알고리즘과 유사하게 남은 자리들 중 가장 근처에 빈칸이
         * 많은 자리에 앉음.
         * 다음으로, 근처 빈칸의 수가 같은 자리가 여러개 존재한다면 그 중 가장 행이 작은 자리에 앉음.
         * 마지막으로, 같은 행인 자리가 여럿이라면 그 중 열이 가장 작은 자리에 앉음.
         * 
         */

        // 입력받고 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int totalStudents = N * N;
        classroom = new int[N + 1][N + 1];
        studentLikes = new HashMap<>();
        List<Integer> studentOrder = new ArrayList<>();

        for (int i = 0; i < totalStudents; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int studentId = Integer.parseInt(st.nextToken());
            studentOrder.add(studentId);
            List<Integer> likes = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                likes.add(Integer.parseInt(st.nextToken()));
            }
            studentLikes.put(studentId, likes);
        }

        // 첫 번째 학생은 항상 중앙에 배치 (N=3일 때 (2,2)에 해당)
        int firstStudent = studentOrder.get(0);
        int mid = (N + 1) / 2;
        classroom[mid][mid] = firstStudent;

        // 나머지 학생 배치
        for (int i = 1; i < totalStudents; i++) {
            int currentStudent = studentOrder.get(i);
            placeStudent(currentStudent);
        }

        // 만족도 계산
        long totalScore = calculateSatisfaction();
        System.out.println(totalScore);

    }

    // 자리 배치 메소드 구현
    private static void placeStudent(int id) {
        ArrayList<Seat> candidates = new ArrayList<>();

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (classroom[row][col] == 0) { // 빈자리 확인
                    int likeCnt = 0;
                    int emptyCnt = 0;

                    for (int i = 0; i < 4; i++) {
                        int nr = row + dx[i];
                        int nc = col + dy[i];
                        if (nr > 0 && nr <= N && nc > 0 && nc <= N) {
                            if (classroom[nr][nc] == 0) {
                                emptyCnt++;
                            } else if (studentLikes.get(id).contains(classroom[nr][nc])) {
                                likeCnt++;
                            }
                        }
                    }
                    candidates.add(new Seat(row, col, likeCnt, emptyCnt));
                }
            }
        }
        Collections.sort(candidates);
        Seat best = candidates.get(0);
        classroom[best.row][best.col] = id;
    }

    private static long calculateSatisfaction() {
        long total = 0;
        int[] scores = { 0, 1, 10, 100, 1000 };
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                int current = classroom[r][c];
                int likeCnt = 0;
                for (int i = 0; i < 4; i++) {
                    int nr = r + dx[i];
                    int nc = c + dy[i];
                    if (nr > 0 && nr <= N && nc > 0 && nc <= N) {
                        if (studentLikes.get(current).contains(classroom[nr][nc])) {
                            likeCnt++;
                        }
                    }
                }
                total += scores[likeCnt];
            }
        }
        return total;
    }

}
