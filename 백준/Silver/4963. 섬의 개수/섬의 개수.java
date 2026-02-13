import java.io.*;
import java.util.*;

public class Main{
	static int w, h;
	static int[] dx = {0,0,1,-1,   1, 1, -1,-1};
	static int[] dy = {1,-1,0,0,   1, -1, 1,-1};
	static int[][] map;
	static Deque<int[]> dq = new ArrayDeque<>(); 
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if(w==0 && h == 0) break;
			map = new int[h][w];
			
			for (int r = 0; r < h; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < w; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			int ans = cntIsland();
			System.out.println(ans);
		}
	}
	
	static int cntIsland() {
		int cnt= 0;
		for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				if(map[r][c]==0)continue;
				
				dq.addLast(new int[] {r,c});
				map[r][c]=0;
				
				while(!dq.isEmpty()) {
					int[] q = dq.pollFirst();
					int x = q[0], y = q[1];
					
					for(int i=0;i<8;i++) {
						int nx = x+dx[i], ny = y+dy[i];
						if(nx<0 || nx>=h || ny<0 || ny>=w) continue;
						if(map[nx][ny] == 0) continue;
						dq.addLast(new int[] {nx,ny});
						map[nx][ny]=0;
					}
				}
				cnt++;
			}
		}
		return cnt;
	}

}