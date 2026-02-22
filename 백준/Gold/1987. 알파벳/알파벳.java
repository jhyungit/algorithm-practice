import java.io.*;
import java.util.*;


public class Main {
	static int R, C;
	static char[][] map;
	static boolean[] used = new boolean[26]; // 알파벳 개수만큼 초기화
	static int max = 0;
	
	static int[] dr = {0,0,-1,1};
	static int[] dc = {-1,1,0,0};
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int r = 0; r < R; r++) {
			String line = br.readLine();
			for(int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
			}	
		}
		
		used[map[0][0] - 'A'] = true;
		dfs(0, 0, 1); // 시작 좌표
		
		System.out.println(max);
		
		
	}
	
	static void dfs(int r, int c, int cnt) {
		max = Math.max(max, cnt);
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nr >= R || nc<0 || nc >= C) continue;
			
			int alphaIdx = map[nr][nc] - 'A';
			
			if(!used[alphaIdx]) {
				used[alphaIdx] = true;
				dfs(nr, nc, cnt+1);
				used[alphaIdx] = false; // 원상복구 
			}
		}
	}
}
