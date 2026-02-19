import java.io.*;
import java.util.*;

public class Main {
	static int R, C, N;
	static int count = 0;
	static int maxSafe = 0;
	static int[] dr = {0,0,1,-1}, dc = {1,-1,0,0};
	static int[] numbers = new int[3];
	static int[][] map;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		N = (R * C)-1;
		
		map = new int [R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		//nC3 구하기 
		combination(0, 0);
		System.out.println(maxSafe);
	}

	static void combination(int start, int depth) {

		if(depth == 3) {
			int[][] temp = new int[R][C];
			for(int i = 0; i < R; i++) temp[i] = map[i].clone(); // 깊은 복사
			
			int wall = 0;
			for(int n : numbers) {
				int row = n / C;
				int col = n % C;
				
				// 0이 아니면 break
				if(temp[row][col] != 0)  return;
				
				// 벽 세우기
				temp[row][col] = 1;
				wall++;
			}
			
			// 벽 3개를 세웠다면 체크
			maxSafe = Math.max(maxSafe, check(temp));
			
			count++;
			return;
		}
		
		for(int i = start; i < R*C; i++) {
			numbers[depth] = i;
			combination(i+1, depth+1);
		}
		
	}
	
	static int check(int[][] temp) {
		Deque<int[]> dq = new ArrayDeque<>();
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (temp[r][c]==2) { // 가스 좌표 저장 
					dq.offer(new int[] {r,c});
				}
			}
		}
		
		while(!dq.isEmpty()) {
			int[] q = dq.poll();
			
			int r = q[0];
			int c = q[1];
			
			for(int k = 0; k<4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				if(temp[nr][nc] != 0) continue;
				temp[nr][nc] = 2; // 확산
				dq.offer(new int[] {nr,nc});
			}
		}
		
		int ans = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (temp[r][c]==0) ans++;
			}
		}
		return ans;
	}
}
