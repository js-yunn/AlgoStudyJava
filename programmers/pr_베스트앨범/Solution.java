
import java.util.*;

public class Solution {
	public static int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> result = new ArrayList<>(); // 정답 배열
        HashMap<String, Integer> total = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>();
        
        for (int i=0; i<genres.length; i++){
        	if (!total.containsKey(genres[i])) {// 해당 장르가 genre 해시맵에 존재하지 않다면
        		HashMap<Integer, Integer> m = new HashMap<>();
        		m.put(i,  plays[i]);
        		music.put(genres[i], m);
        		total.put(genres[i], plays[i]);
        		
        	}else { // 해당 장르가 이미 해시맵에 존재한다면
        		music.get(genres[i]).put(i, plays[i]);//음악 고유번호 별 재생횟수 저장
        		total.put(genres[i], total.get(genres[i])+plays[i]); // 장르별 총 플레이 횟수 저장
        		
        	}
        }//여기까진 했는데 그 뒤는 참고해서 풀었습니다
        
        //총 재생 횟수 해시맵 total을 정렬하기 위해 List로 변환
        List<String> genreSort = new ArrayList<>(total.keySet());
        genreSort.sort((o1, o2) -> total.get(o2).compareTo(total.get(o1)));
        for (String key: genreSort) {
        	HashMap<Integer, Integer> map = music.get(key);
        	List<Integer> genre_key = new ArrayList<>(map.keySet());
        	
        	Collections.sort(genre_key, (o1, o2)-> map.get(o2)-(map.get(o1)));
        	result.add(genre_key.get(0));
        	if (genre_key.size()>1) {
        		result.add(genre_key.get(1));
        	}
        }
        return result.stream().mapToInt(i->i).toArray();
    }
	public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        System.out.println(Arrays.toString(solution(genres, plays)));   // [4, 1, 3, 0]
    }
	

}

