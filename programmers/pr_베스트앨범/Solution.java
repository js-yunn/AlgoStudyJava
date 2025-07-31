import java.util.*;

public class Solution {
    public static int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> result = new ArrayList<>(); // ���� �迭
        HashMap<String, Integer> total = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            if (!total.containsKey(genres[i])) {// �ش� �帣�� genre �ؽøʿ� �������� �ʴٸ�
                HashMap<Integer, Integer> m = new HashMap<>();
                m.put(i, plays[i]);
                music.put(genres[i], m);
                total.put(genres[i], plays[i]);

            } else { // �ش� �帣�� �̹� �ؽøʿ� �����Ѵٸ�
                music.get(genres[i]).put(i, plays[i]);// ���� ������ȣ �� ���Ƚ�� ����
                total.put(genres[i], total.get(genres[i]) + plays[i]); // �帣�� �� �÷��� Ƚ�� ����

            }
        } // ������� �ߴµ� �� �ڴ� �����ؼ� Ǯ�����ϴ�

        // �� ��� Ƚ�� �ؽø� total�� �����ϱ� ���� List�� ��ȯ
        List<String> genreSort = new ArrayList<>(total.keySet());
        genreSort.sort((o1, o2) -> total.get(o2).compareTo(total.get(o1)));
        for (String key : genreSort) {
            HashMap<Integer, Integer> map = music.get(key);
            List<Integer> genre_key = new ArrayList<>(map.keySet());

            Collections.sort(genre_key, (o1, o2) -> map.get(o2) - (map.get(o1)));
            result.add(genre_key.get(0));
            if (genre_key.size() > 1) {
                result.add(genre_key.get(1));
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        String[] genres = { "classic", "pop", "classic", "classic", "pop" };
        int[] plays = { 500, 600, 150, 800, 2500 };

        System.out.println(Arrays.toString(solution(genres, plays))); // [4, 1, 3, 0]
    }

}
