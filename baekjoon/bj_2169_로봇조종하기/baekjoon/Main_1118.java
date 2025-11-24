import java.io.*;
import java.util.*;

public class Main_1118 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String str = br.readLine();
            if (str.equals(".")) break;

            sb.append(solve(str)).append("\n");
        }
        System.out.print(sb);
    }

    private static int solve(String str) {
        int len = str.length();
        int[] pi = getPi(str); // 실패 함수 pi 배열 생성
        // 가장 긴 접두사=접미사 길이
        int maxMatch = pi[len - 1]; 
        // 반복되는 최소 단위 길이
        int minlen = len - maxMatch;

        // 전체가 단위 길이로 딱 나눠떨어지면 그게 반복 횟수
        if (len % minlen == 0) {
            return len / minlen;
        } else {
            return 1;
        }
    }

    // 실패 함수
    private static int[] getPi(String str) {
        int len = str.length();
        int[] pi = new int[len];
        int j = 0;

        for (int i = 1; i < len; i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = pi[j - 1]; // 문자열 점프
              // j: 현재까지 맞춰온 길이 ex: 5
              // j-1: 마지막으로 일치했던 문자의 인덱스 ex: 4
              // pi[j-1]: "방금 맞춘 문자열 중에서, 앞뒤가 똑같은 길이" ex: 3
            }
            if (str.charAt(i) == str.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
}
