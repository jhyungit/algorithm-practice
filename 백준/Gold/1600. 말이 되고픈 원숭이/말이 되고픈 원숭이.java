import java.io.*;
import java.util.*;


public class Main {
	static int[] horseR = {-2,-2,-1,-1,1,1,2,2};
	static int[] horseC = {1,-1,2,-2,2,-2,1,-1};
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][][] visited;
	static Deque<int[]> q = new ArrayDeque<>();
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		
		map = new int[height][width];
		for(int r = 0; r < height; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < width; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[k+1][height][width];
		
		bfs(k, height, width);	
	}
	
	static void bfs(int k, int height, int width) {
		
		
		// 시작 좌표나 최종 목적지가 1이면 불가능
		if(map[0][0] == 1 || map[height-1][width-1] == 1) {
			System.out.println(-1);
			return;
		}
		
		
		q.offer(new int[] {0,0,0,0}); // r, c, used, dist
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int used = cur[2];
			int dist = cur[3];
			
			// 목적지에 도착했다
			if(r == height -1 && c == width-1) {
				System.out.println(dist);
				return;
			}
			
			// 일반 이동
			for(int i = 0; i <4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				
				if(nr<0||nr>=height||nc<0||nc>=width) continue;
				if(map[nr][nc] == 1) continue;
				if(visited[used][nr][nc]) continue;
				
				visited[used][nr][nc] = true;
				q.add(new int[] {nr,nc,used,dist+1});
			}
			
			// 말 이동
			if(used < k) {
				for(int i = 0; i <8; i++) {
					int nr = r+horseR[i];
					int nc = c+horseC[i];
					
					if(nr<0||nr>=height||nc<0||nc>=width) continue;
					if(map[nr][nc] == 1) continue;
					if(visited[used+1][nr][nc]) continue;
					
					visited[used+1][nr][nc] = true;
					q.add(new int[] {nr,nc,used+1,dist+1});
				}
			}
		}
		System.out.println(-1);
	}

}