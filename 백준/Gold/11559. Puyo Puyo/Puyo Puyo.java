import java.io.*;
import java.util.*;


public class Main{
	static final int R = 12, C = 6;
	static char[][] board = new char[R][C];
	static boolean[][] visited = new boolean[R][C];
	static boolean[][] pop = new boolean[R][C];
	
	static int[] dr = {0,0,-1,1};
	static int[] dc = {-1,1,0,0};

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb;
		
		for(int r = 0; r < 12; r++) {
			sb = new StringBuffer(br.readLine());
			 for(int c = 0; c < 6; c++) {
				 board[r][c] = sb.charAt(c);
			 }
		}
		
		int ans = 0;
		
		while(true) {
			// 초기화 
			for(int i = 0; i < R; i++) {
				Arrays.fill(visited[i], false);
				Arrays.fill(pop[i], false);
			}
			
			boolean isPop = false;
			
			// 탐색
			for(int r = 0; r<R; r++) {
				for(int c = 0; c<C; c++) {
					if(board[r][c] == '.' || visited[r][c]) continue;
					
					List<int[]> list = bfs(r,c);
					
					// 4개가 붙어 있으면 
					if(list.size() >= 4) {
						isPop = true;
						for(int[] p : list) {
							pop[p[0]][p[1]] = true;
						}
					}
				}	
			}
			
			//터질 게 없을 떄 
			if(!isPop) break;
			
			// 터뜨리기
			for(int r = 0; r < R; r++) {
				for(int c = 0 ; c < C; c++) {
					if(pop[r][c]) board[r][c] = '.';
				}
			}
			
			// 중력 적용
			gravity();
			
			ans++;
		}
		
		System.out.println(ans);
	}
	
	static List<int[]> bfs(int sr,int sc){
		
		List<int[]> list = new ArrayList<>();
		Queue<int[]> q = new ArrayDeque<>();
		
		visited[sr][sc] = true;
		q.offer(new int[] {sr,sc});
		list.add(new int[] {sr,sc});
		
		char color = board[sr][sc];
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int d = 0; d <4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				if(visited[nr][nc]) continue;
				if(board[nr][nc] != color) continue;
				
				visited[nr][nc] = true;
				q.offer(new int[] {nr,nc});
				list.add(new int[] {nr,nc});
			}
		}
		
		return list;
	}
	
	static void gravity() {
		
		for(int c = 0; c < C; c++) {
			int write = R-1;
			
			for(int r = R -1; r>=0; r--) {
				if(board[r][c] != '.') {
					char temp = board[r][c];
					board[r][c] = '.';
					board[write][c] = temp;
					write--;
				}
			}
		}
	}
}
