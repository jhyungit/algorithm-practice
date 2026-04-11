import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		System.out.println(bfs(0,0));
	}	
	
	static int bfs(int sr, int sc) {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {sr, sc, 1});
		visited[sr][sc] = true;
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0];
			int c = cur[1];
			int moves = cur[2];
			
			if(r == n-1 && c == m-1) return moves;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				if(map[nr][nc] == 0) continue;
				if(visited[nr][nc]) continue;
				
				dq.offer(new int[] {nr,nc,moves+1});
				visited[nr][nc] = true;
			}
		}
		return -1;
	}
}
