import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static int x, y;
	static boolean[][][] visited;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
	    visited = new boolean[R][C][1<<6]; // 64개 만듬.
		for (int r = 0; r < R; r++) {
			String s = br.readLine();
			for(int i = 0; i < s.length(); i++) {
				map[r][i] = s.charAt(i);
				if(map[r][i] == '0') {
					x = r;
					y = i;
					map[r][i] = '.';
				}
			}
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Deque<int[]> que = new ArrayDeque<>();
		que.offer(new int[] {x,y,0,0}); // x,y, mask, dist
		visited[x][y][0] = true;
		
		while(!que.isEmpty()) {
			int[] q = que.poll();
			int x = q[0], y = q[1], mask = q[2], dist = q[3];
			
			for(int i = 0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx<0 || nx >= R || ny<0 || ny >= C) continue; // 범위 벗어나면
				char c = map[nx][ny];
				if(c == '#') continue; //벽이면
				
				int keyStatus = mask;
				
				// 목적지 
				if(c == '1') return dist+1;
				
				if(c >= 'a' && c <= 'f') keyStatus = mask | (1 << (c - 'a'));

				if(c >= 'A' && c <= 'F') {
					if( (keyStatus & (1<< (c - 'A')) ) == 0) continue; // 열쇠가 없으면 못 지나감
				}
				
				if(visited[nx][ny][keyStatus]) continue;
				visited[nx][ny][keyStatus] = true;
				que.offer(new int[] {nx,ny,keyStatus, dist+1});
				
			}
			
		}
		return -1;
	}

}
