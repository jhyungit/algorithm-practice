import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static boolean[][] area = new boolean[101][101];
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		// 색종이 영역 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for (int r = x; r < x+10; r++) {
				for (int c = y; c < y+10; c++) {
					area[r][c] = true;
				}
			}
		}
		
		int ans = 0;
		for (int x = 0; x < 101; x++) {
			for (int y = 0; y < 101; y++) {
				
				if(area[x][y] == true) { // 색 종이 영역만 보기
					
					for (int k = 0; k < 4; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];
						
						// 범위 벗어나면
						if(nx<0|| nx>100|| ny < 0 || ny>100) {
							ans++;
						}
						// 색종이가 없는 부분이면
						else if(area[nx][ny] == false) {
							ans++;
						}
					}
				} 
					
			}
		}
		System.out.println(ans);
	}
}