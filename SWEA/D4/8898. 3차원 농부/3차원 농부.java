import java.util.*;
import java.io.*;

public class Solution {
	static int cowNum, horseNum, cowX, horseX;
	static int minDist, cnt;
	
	static List<Integer> cowZ;
	static List<Integer> horseZ;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			cnt = 0;
			cowZ = new ArrayList<>();
			horseZ = new ArrayList<>();
			
			
			st = new StringTokenizer(br.readLine());
			cowNum = Integer.parseInt(st.nextToken());
			horseNum = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			cowX = Integer.parseInt(st.nextToken());
			horseX = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < cowNum; c++) {
				int z = Integer.parseInt(st.nextToken());
				cowZ.add(z);
			}
			
			st = new StringTokenizer(br.readLine());
			for(int h = 0; h < horseNum; h++) {
				int z = Integer.parseInt(st.nextToken());
				horseZ.add(z);
			}
			
			cowZ.sort((a,b) -> a-b);
			horseZ.sort((a,b) -> a-b);
			
			twoPointer();
			sb.append("#").append(tc).append(" ")
			.append(minDist).append(" ")
			.append(cnt).append('\n');
		}
		System.out.println(sb);
	}
	
	static void twoPointer() {
		int c = 0;
		int h = 0;
		minDist = Integer.MAX_VALUE;
		cnt = 0;
		
		while(c < cowNum && h < horseNum) {
			int z1 = cowZ.get(c);
			int z2 = horseZ.get(h);

			if (z1 > z2) {
				h++;
			}else if(z1 < z2) {
				c++;
			}else { // 아무거나 올려도 상관 없음. 최소거리가 0이 나왔기 때문.
				c++;
			}
			
			int dist = Math.abs(cowX - horseX) + Math.abs(z1 - z2);
			
			if(dist < minDist) {
				cnt = 1;
				minDist = dist;
			}else if(minDist == dist) {
				cnt++;
			}
		}
	}

}