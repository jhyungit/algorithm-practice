import java.io.*;
import java.util.*;

public class Main {
	
	static class CCTV{
		int type, r, c;
		
		CCTV(int type, int r, int c){
			this.type = type;
			this.r = r;
			this.c = c;
		}
	}
	
	static List<CCTV> cctvs = new ArrayList<>();
	static List<int[]> temp = new ArrayList<>();
	static List<int[]> history= new ArrayList<>();
	
	static int ans = 64;
	static int R, C;
	static int[][] map;
	
	static int[][][] cctv = {
			{}, // 0
			{
				{0},{1},{2},{3}, // 1
			},
			{
				{0,2},{1,3}, // 2
			},
			{
				{0,1},{1,2},{2,3},{3,0} // 3
			},
			{
				{0,2,3},{0,1,3},{1,2,3},{0,1,2}, // 4
			},
			{
				{0,1,2,3}, // 5
			}
	};
	
	//상 우 하 좌 
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};

	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c] >= 1 && map[r][c] <= 5) {
					cctvs.add(new CCTV(map[r][c],r,c));
				}
			}	
		}
		
		dfs(0);
		System.out.println(ans);
	}
	
	static void dfs(int depth) { // depth
		
		if(depth == cctvs.size()) {
			ans = Math.min(ans, countBlindArea());
			return;
		}
		
		CCTV cam = cctvs.get(depth);
		int type = cam.type;
		
		for(int[] direction : cctv[type]) {
			int rbCnt = history.size();
			
			// 감시되는 곳 체크
			for(int d : direction) watch(cam.r, cam.c, d);

			dfs(depth+1);
			
			rollback(rbCnt);
		}
		
		
	}
	
	static int countBlindArea() {
		int cnt = 0;
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == 0) {
					cnt++;
				}
			}	
		}
		return cnt;
	}
	
	static void watch(int r, int c, int d) {
		int nr = r + dr[d];
		int nc = c + dc[d];
		while(nr >= 0 && nr < R && nc >= 0 && nc < C ) {
			if(map[nr][nc] == 6) break;
			if(map[nr][nc] == 0) {
				map[nr][nc] = -1;
				history.add(new int[]{nr,nc});
			}
			nr += dr[d];
			nc += dc[d];
		}
	}
	
	static void rollback(int cnt) {
		for(int i = history.size()-1; i >= cnt; i--) {
			int[] p = history.get(i);
			map[p[0]][p[1]] = 0;
			history.remove(i);
		}
	}

}