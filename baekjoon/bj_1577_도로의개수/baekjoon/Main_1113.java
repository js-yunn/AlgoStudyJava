package baekjoon;
import java.io.*;
import java.util.*;

public class Main_1113 {

	public static void main(String[] args) throws Exception {
		
	}

}

/*
백준 1577-도로의 개수

공사중인 도로 개수 k개
(a,b), (c,d)

처음 생각 - dp로 풀 수 있을 듯
i,j 도착하는 경우의 수 = i-1,j에서 오는 경로+i,j-1에서 오는 경로의 수 합치기 연산하면 됨
근데 공사 중인 도로 처리하는 방법: 더할 때 공사중인지 확인해서 아니면 더하고 공사 중이면 안 더하기

자료형은 long타입, 공사중인 도로 정보는 2차원 배열로 저장하기. 즉 2차원 배열을 2개 선언하기


6 6 
2
0 0 0 1
6 6 5 6
*/