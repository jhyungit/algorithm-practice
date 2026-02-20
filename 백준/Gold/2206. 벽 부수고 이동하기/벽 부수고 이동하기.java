import java.io.*;
import java.util.*;

public class Main {
	static int N,M,k;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int[][] map;
	static boolean[][][] visited;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][M][2];
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}	
		}

		Deque<int[]> que = new ArrayDeque<>();
		
		// x, y, cnt, k
		que.offer(new int[] {0, 0, 1, 1}); // 시작 좌표 추가
		visited[0][0][1] = true;
		
		int ans = -1;
		
		while(!que.isEmpty()) {
			int[] q = que.poll();
			int x = q[0];
			int y = q[1];
			int cnt = q[2];
			int k = q[3];
			
			if(x == N-1 && y == M-1) {
				ans = cnt;
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				
				//다음 칸이 벽인 경우
				if(map[nx][ny] == 1) {
					if(k==0) continue; // 스킬 모두 소모함
					if(visited[nx][ny][0]) continue;
					visited[nx][ny][0] = true;
					que.offer(new int[] {nx,ny,cnt+1,0});
					
				}
				// 빈 칸인 경우
				else {
					if(visited[nx][ny][k]) continue;
					visited[nx][ny][k] = true;
					que.offer(new int[] {nx,ny,cnt+1,k});
				}
			}
		}
		
		System.out.println(ans);
		
	}
}
