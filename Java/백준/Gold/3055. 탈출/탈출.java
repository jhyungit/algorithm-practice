import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static int sr,sc;
	static int[] dr= {0,0,-1,1};
	static int[] dc= {-1,1,0,0};
	static boolean[][] visited;
	static char[][] map;
	static Deque<int[]> waterPos;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		waterPos = new ArrayDeque<>(); 
		
		for (int r = 0; r < R; r++) {
			String s = br.readLine();
			for(int c = 0; c < s.length(); c++) {
				map[r][c] = s.charAt(c);
				if(map[r][c] == 'S') {
					// 시작 좌표
					sr = r;
					sc = c;
				}
				if(map[r][c] == '*') {
					waterPos.offer(new int[] {r,c});
				}
			}
		}
		
		
		
		int ans = bfs();
		if(ans ==-1) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(ans);
		}
	}
	
	static int bfs() {
		Deque<int[]> que = new ArrayDeque<>();
		que.offer(new int[] {sr,sc,0}); // 시작좌표와 cnt
		visited[sr][sc] = true;
		map[sr][sc] = '.';
		
		while(!que.isEmpty()) {
			
			//물 확장
			int wSize = waterPos.size();
			for(int i =0;i<wSize; i++) {
				int[] w = waterPos.poll();
				int wr = w[0], wc = w[1];
				
				for(int k=0; k < 4; k++) {
					int nwr = wr + dr[k];
					int nwc = wc + dc[k];
					
					if( nwr < 0 || nwr >= R || nwc < 0 || nwc >= C) continue;
					if(map[nwr][nwc] == 'X' || map[nwr][nwc] == 'D' || map[nwr][nwc] == '*') continue;
					map[nwr][nwc] = '*';
					waterPos.offer(new int[] {nwr,nwc});
				}	
			}
			
			// 고슴도치 이동
			int qSize = que.size();
			for(int i =0;i<qSize; i++) {
				int[] q = que.poll();
				int r = q[0];
				int c = q[1];
				int cnt = q[2];
				
				for(int k=0; k < 4; k++) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
					if(visited[nr][nc]) continue;
					if(map[nr][nc] == 'X' || map[nr][nc] == '*') continue;
					
					// 목적지라면
					if(map[nr][nc]=='D') {
						return cnt+1;
					}
					visited[nr][nc] = true;
					que.offer(new int[] {nr,nc, cnt+1});
				}	
			}
		}
		
		return -1;
	}

}
