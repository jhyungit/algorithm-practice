import java.io.*;
import java.util.*;

public class Main {
	static int cnt, N;
	static int[][] area, safe;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine().trim());
		area = new int[N][N];
		safe = new int[N][N];
		
		for(int i=0; i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Set<Integer> candidate = new HashSet<>();
		for(int rain = 0; rain<=100;rain++) {
			cnt = 0;
			candidate.add(changeArea(rain));
		}
		System.out.println(Collections.max(candidate));
	}
	
	static int changeArea(int rain) {
		for(int i=0; i<N;i++) {
			for (int j = 0; j < N; j++) {
				if(area[i][j] > rain) { // 안 잠기면 true로 바꿈
					safe[i][j] = 1;
				}
			}
		}
		
		for(int i=0; i<N;i++) {
			for (int j = 0; j < N; j++) {
				if(safe[i][j] == 1) { // 안잠긴 곳이면 탐색
					safe[i][j] = 0;
					cnt++;
					searchSafeArea(i, j);
				}
			}
		}
		return cnt;
	}
	
	static void searchSafeArea(int x, int y) {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.addLast(new int[]{x,y});
		safe[x][y] = 0;
		
		while(!dq.isEmpty()) {
			int[] q = dq.pollFirst();
			
			for (int i = 0; i < 4; i++) {
				int nx = q[0]+dx[i];
				int ny = q[1]+dy[i];
				
				// 범위 밖이거나, 방문했거나 물에 잠긴 곳일 때.
				if(nx<0 || nx>=N || ny<0 || ny>=N || safe[nx][ny]==0) continue;
				
				safe[nx][ny] = 0;
				dq.addLast(new int[] {nx,ny});	
			}
		}
	}
}