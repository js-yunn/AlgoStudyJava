package a0823;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution_0826 sol = new Solution_0826();

        // 첫 번째 입출력 예시
        int n1 = 6;
        int[][] roads1 = {{1, 2, 3, 1}, {1, 5, 4, 2}, {1, 3, 4, 3}, {1, 6, 7, 4}, {2, 4, 1, 2}, {2, 5, 5, 4}, {3, 6, 2, 1}, {4, 5, 4, 1}, {5, 6, 5, 3}};
        int[] record1 = {1, -4, 2};
        int[][] result1 = sol.solution(n1, roads1, record1);
        System.out.println("결과 1: " + Arrays.deepToString(result1)); // [[3, 5], [4, 5]] 출력 예상

        // 두 번째 입출력 예시
        int n2 = 5;
        int[][] roads2 = {{1, 2, 3, 2}, {1, 5, 2, 1}, {2, 5, 4, 3}, {2, 3, 4, 1}, {3, 5, 7, 2}, {3, 4, 5, 3}};
        int[] record2 = {2, -3, 2, -1};
        int[][] result2 = sol.solution(n2, roads2, record2);
        System.out.println("결과 2: " + Arrays.deepToString(result2)); // [[1, 3], [1, 4]] 출력 예상
    }
}