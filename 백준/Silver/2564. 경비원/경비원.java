import java.io.*;
import java.util.*;

public class Main {
	static int row, col;
	static int[] road;
	static int[] location;
	static int xLoc;
	
	// 위치 방향, 경계부터 거리

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		col = Integer.parseInt(st.nextToken()); // 가로 길이
		row = Integer.parseInt(st.nextToken()); // 세로 길이
		
		road = new int[2*row+2*col+1];
		
		st = new StringTokenizer(br.readLine());
		int storeNum = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i<=storeNum; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			if(d == 1) {//북
				road[dist] = i+1;
			}else if(d == 2) {//남
				road[2*col+row-dist] = i+1;
			}else if(d == 3) {//서
				road[2*(col+row)-dist] = i+1;
			}else {//동
				road[col+dist] = i+1;
			}
		}

		location = new int[storeNum];
		// x 인덱스와, 나머지 인덱스 저장
		for(int i =0; i< road.length; i++) {
			if(road[i]==storeNum+1) {
				xLoc = i; 
				continue;
			}
			if(road[i]!=0 && road[i] != storeNum+1) {
				location[road[i]-1] = i;
			}
		}
		int ans = 0;
		for(int i = 0; i<location.length; i++) {
			int a = Math.abs(xLoc-location[i]);
			int b = 2*(row+col) -a;
			ans += Math.min(a, b);
		}
		System.out.println(ans);
		
	}
	
}