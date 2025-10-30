package baekjoon;
import java.io.*;
import java.util.*;

class Bus{
	int start;
	int end;
	int cost;
	public Bus(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
}

public class Main_1030 {
	static int n, m;
	static Bus[] e;
	static long[] dist;
	static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		

	}

}

/*
음수 간선이 있으니까 다익스트라가 아니라 벨만 포드 알고리즘

1. 1번 노드에서 출발
2. 최단 거리 테이블 초기화
3. V번 반복
	3-1. 모든 간선 E개를 하나씩 확인
	3-2. 각 간선을 거쳐 다른 노드로 가는 비용을 계산해서 최단 거리 테이블 갱신
음수 간선 순환 발생하는지 체크, 있으면 -1 리턴

*/