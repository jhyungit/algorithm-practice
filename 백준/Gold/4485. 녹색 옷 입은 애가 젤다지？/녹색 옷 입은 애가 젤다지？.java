import java.io.*;
import java.util.*;


public class Main {
	static int N;
	static int[][] map, dist;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {-1,1,0,0};
	static final int INF = Integer.MAX_VALUE;

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = 1;
		
		while (true) {
			N = Integer.parseInt(br.readLine());
			if(N==0) break;
			
			map = new int[N][N];
			dist = new int[N][N];
			
			for(int i = 0; i < N; i++){
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++){
					map[i][j] = Integer.parseInt(st.nextToken());;
					dist[i][j] = INF;
				}
			}
			
			dijkstra();
			
			sb.append("Problem ").append(tc++).append(": ").append(dist[N-1][N-1]).append("\n");
		}
		System.out.println(sb);
		
	}
	
	static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[2]));
		
		dist[0][0] = map[0][0];
		pq.offer(new int[] {0,0,dist[0][0]}); // r, c, cost
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			int r = cur[0];
			int c = cur[1];
			int cost = cur[2];
			
			if(cost != dist[r][c]) continue; // 이미 더 작은 비용이 있으면
			if(r == N-1 && c == N-1) return;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				int nCost = cost + map[nr][nc];
				if(nCost < dist[nr][nc]) {
					dist[nr][nc] = nCost;
					pq.offer(new int[] {nr,nc,nCost});
				}
			}
		}

	}
}