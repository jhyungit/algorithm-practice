import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int goal = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		
		// 시작점 
		int x = N/2;
		int y = N/2;
		
		// 상 우 하 좌
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		
		int num = 1;
		map[x][y] = num;
		
		int goalX,goalY;
		if(goal == 1) {
			goalX = x+1;
			goalY = y+1;
		}else {
			goalX = 0;
			goalY = 0;
		}
		
		
		int step = 1;
		int dir = 0;
		
		while(num < N*N) {
			for(int i=0; i<2; i++) {
				if(num == N*N) break;
				// 한 방향으로 step만큼 이
				for(int j=0; j<step;j++) {
					if(num == N*N) break;
					x += dx[dir];
					y += dy[dir];
					num++;
					map[x][y]=num;
					if(num==goal) {
						goalX = x+1;
						goalY = y+1;
					}
				}
				dir = (dir+1)%4;
			}
			step++;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				sb.append(map[i][j]);
				if(j < N-1) sb.append(' ');
			}	
			sb.append('\n');
		}
		sb.append(goalX+" "+goalY);
		System.out.print(sb.toString());
		
	}

}
