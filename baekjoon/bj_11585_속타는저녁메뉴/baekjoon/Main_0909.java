package baekjoon;
import java.util.*;

public class Main_0909 {

    // pi 테이블 만드는 함수
    private static int[] getPartialMatch(String N) {
        int M = N.length();
        int[] pi = new int[M];
        int begin = 1;
        int matched = 0;

        // 비교할 문자가 N의 끝에 도달할 때까지 탐색함
        while (begin + matched < M) {
            if (N.charAt(begin + matched) == N.charAt(matched)) { // 두 수가 일치한다면
                matched++; // matched를 증가시킴
                pi[begin + matched - 1] = matched; // 가장 긴 접두사/접미사 일치 길이 matched
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
        return pi;
    }

    // KMP 알고리즘을 이용해 문자열 H에서 패턴 N을 찾아봄
    private static List<Integer> kmpSearch(String H, String N) {
        int n = H.length();
        int m = N.length();
        List<Integer> result = new ArrayList<>();
        int[] pi = getPartialMatch(N);

        int matched = 0; // 현재까지 일치한 글자 수

        for (int i = 0; i < n; i++) {
            // 불일치 시, pi 테이블을 이용해 matched 값을 줄여 탐색 위치를 효율적으로 이동
            while (matched > 0 && H.charAt(i) != N.charAt(matched)) {
                matched = pi[matched - 1]; // matched 값 재조정
            }
            if (H.charAt(i) == N.charAt(matched)) { // 글자가 일치하면 matched 수를 증가
                matched++;
                
                if (matched == m) { // 패턴을 찾았다면
                    result.add(i - m + 1); // result에 기작 인덱스 추가함
                    matched = pi[matched - 1]; // 다음 탐색을 위해 matched 값 재설정
                }
            }
        }
        return result;
    }

    // 원래 문자열을 두 번 이어 붙인 후 KMP 탐색을 수행함. 마지막 문자는 중복이므로 제외함
    private static int shifts(String original, String target) {
        String doubleOriginal = original + original.substring(0, original.length() - 1);
        return kmpSearch(doubleOriginal, target).size();
    }

    // 유클리드 호제법으로 최대공약수(GCD)를 구함
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        StringBuilder targetBuilder = new StringBuilder();
        StringBuilder originalBuilder = new StringBuilder();

        String[] targetParts = scanner.nextLine().split(" ");
        String[] originalParts = scanner.nextLine().split(" ");

        for (String part : targetParts) {
            targetBuilder.append(part);
        }
        for (String part : originalParts) {
            originalBuilder.append(part);
        }

        String target = targetBuilder.toString();
        String original = originalBuilder.toString();

        int result = shifts(target, original);
        int divider = gcd(N, result);

        System.out.println((result / divider) + "/" + (N / divider));
    }
}
