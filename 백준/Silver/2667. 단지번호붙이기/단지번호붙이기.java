import java.io.*;
import java.util.*;

public class Main {
	static int N, groupCnt = 0;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int[][] map;
	static Deque<int[]> dq = new ArrayDeque<>(); 
	static List<Integer> orderByCnt = new ArrayList<>();
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			String input = br.readLine();
			for (int c = 0; c < input.length(); c++) {
				map[r][c] = input.charAt(c) - '0';
			}
		}
		cntHouseGroup();
		
		System.out.println(groupCnt);
		orderByCnt.sort((a,b) -> a-b);
		for(int num : orderByCnt) {
			System.out.println(num);
		}
		
	}
	
	static void cntHouseGroup() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c]==0)continue;
				int cnt = 1;
				
				dq.addLast(new int[] {r,c});
				map[r][c]=0;
				
				while(!dq.isEmpty()) {
					int[] q = dq.pollFirst();
					int x = q[0], y = q[1];
					
					for(int i=0;i<4;i++) {
						int nx = x+dx[i], ny = y+dy[i];
						if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
						if(map[nx][ny] == 0) continue;
						dq.addLast(new int[] {nx,ny});
						map[nx][ny]=0;
						cnt++;
					}
				}
				groupCnt++;
				orderByCnt.add(cnt);
			}
		}
		return;
	}

}