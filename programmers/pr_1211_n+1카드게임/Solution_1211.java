import java.io.*;
import java.util.*;

class Solution {
    public boolean pairExists(Set<Integer> deck1, Set<Integer> deck2, int target){
        for (int c: deck1){
            int pair = target-c;
            
            if (deck2.contains(pair)){ // deck2에 짝 존재하는지 확인
                deck1.remove(c);
                deck2.remove(pair);
                return true;
            }
        }
        return false; // 짝 존재 안하면 false
    }
    
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int target = n+1;
        
        Set<Integer> hand = new HashSet<>();
        Set<Integer> wait = new HashSet<>(); // 뽑았지만 아직 안 가져온 대기 카드들
        int idx = 0;
        for (;idx<n/3; idx++){
            hand.add(cards[idx]);
        }
        int round = 1;
        
        while (idx<n){
            // 이번 턴에 카드 2장 wait set에 추가
            wait.add(cards[idx++]);
            wait.add(cards[idx++]);
            
            boolean isPair = false; // 짝이 있는지 없는지 검사
            // 1. 동전 사용 0개
            if (pairExists(hand, hand, target)){
                isPair = true;
            }
            // 2. 동전 사용 1개
            else if (coin>=1 && pairExists(hand, wait, target)){
                coin-=1;
                isPair = true;
            }
            // 3. 동전 사용 2개
            else if (coin>=2 && pairExists(wait, wait, target)){
                coin-=2;
                isPair = true;
            }
            // 짝 없으면 게임 끝내기
            if (!isPair) {
                break;
            }
            round++;
            
        }
        return round;
        
    }
}

/*
구현 문제
버릴 카드 쌍 선택하는게 어려울 듯
일단 코인이 남아있으면 카드 2개 뽑아서 wait set에 넣어둠.

그리디 구현
1. 동전 사용 0개: 손에 있는 카드들 hand set 중 합이 n+1가 되는 카드 쌍 찾아짐. 카드들 remove하기
2. 동전 사용 1개: hand set과 wait set 중 합이 n+1이 되는 카드 쌍 찾아짐. 그 카드는 remove하기
3. 동전 사용 2개: wait set에서만으로 합이 n+1이 되는 카드 쌍 찾아짐. 카드들 remove하기

3가지 중 하나에서 true가 되면 round++하고 continue. 모두 false로 떨어지면 break하기
 */
