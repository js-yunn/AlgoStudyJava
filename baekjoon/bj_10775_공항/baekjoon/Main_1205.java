package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1205 {
    static int N, M;
    static int[] stringTunes; // 각 줄의 튜닝 음 (0~11)
    static Set<Integer> targetNotes = new HashSet<>(); // 목표 음 집합
    static List<Integer>[] possibleFrets; // 각 줄마다 프렛 가능한 후보들
    static int[] selectedFrets; // 각 줄에서 프렛을 임시 저장하는 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 음 -> 숫자로 변환 (A=0, A#=1 ...) 해서 noteToNum에 저장
        Map<String, Integer> noteToNum = new HashMap<>();
        String[] notes = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
        for (int i = 0; i < 12; i++) noteToNum.put(notes[i], i);

        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        stringTunes = new int[N];
        selectedFrets = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            stringTunes[i] = noteToNum.get(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            targetNotes.add(noteToNum.get(st.nextToken())); // 목표 음 저장
        }

        // 각 줄마다 가능한 프렛 미리 구하기(0~18)
        possibleFrets = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            possibleFrets[i] = new ArrayList<>();
            for (int fret = 0; fret <= 18; fret++) {
                int sound = (stringTunes[i] + fret) % 12;
                // 이 프렛 소리가 목표 음 중 하나라면 후보인 possibleFrets에 추가
                if (targetNotes.contains(sound)) {
                    possibleFrets[i].add(fret);
                }
            }
        }
        // 난이도 0~18까지 탐색하기
        if (solve(0, 0, 0)) { // 1. 난이도 0 - 0번 프렛만 사용함
            System.out.println(0);
            return;
        }
        // diff: 난이도 (손가락 범위 크기 + 1)
        for (int diff = 1; diff <= 18; diff++) { // 2. 난이도 1~18 
            // 1번 프렛부터 시작해서 창문을 옆으로 밈
            for (int start = 1; start + diff - 1 <= 18; start++) {
                int end = start + diff - 1;
                
                if (solve(0, start, end)) {
                    System.out.println(diff);
                    return; // 성공하면 바로 종료해서 최소값만 구함
                }
            }
        }
        System.out.println(-1);
    }

    static boolean solve(int lineIdx, int min, int max) { // lineIdx: 현재 몇 번째 줄, min, max: 가능한 프렛 범위
        if (lineIdx == N) { // 모두 프렛을 고르면 playedNotes에 넣고 targetNotes랑 비교하기
            Set<Integer> playedNotes = new HashSet<>();
            for (int i = 0; i < N; i++) {
                int fret = selectedFrets[i];
                int sound = (stringTunes[i] + fret) % 12;
                playedNotes.add(sound);
            }
            return playedNotes.containsAll(targetNotes);
        }

        // 현재 줄(lineIdx)에서 고를 수 있는 후보 확인
        for (int fret : possibleFrets[lineIdx]) {
            if (fret == 0 || (fret >= min && fret <= max)) { // 0번이거나 정한 범위 내에 있어야 선택 가능함
                
                selectedFrets[lineIdx] = fret; // 현재 줄에 fret 입력
                
                if (solve(lineIdx + 1, min, max)) { // 다음줄로 재귀로 넘어감, 끝까지 성공해서 돌아오면 true 리턴하기
                    return true;
                }
                // 조건문 거짓이면 다음 후보 꺼냄 (백트래킹)
            }
        }
        return false; // 모든 후보가 다 실패하면 false 리턴하기
    }
}
