import java.io.*;
import java.util.*;

public class Main {
	static int N, sc, sr;
	static int size = 2; // 아기 상어 크기
	static int time = 0; // 누적 시간
	static int eat = 0; // 먹은 개수 
	
	static int[] dr = {-1,0,0,1}; // 상, 좌, 우, 하
	static int[] dc = {0,-1,1,0};
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
	
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
				if (map[i][j]==9) { // 상어 위치 
					sr = i; sc = j;
					map[i][j] = 0; // 상어 자리 빈칸 처리
				}
			}	
		}
		
		while(true) {
			int[] target = bfs();
			if(target == null) break;
			
			int tr = target[0], tc = target[1], dist = target[2];
			
			time += dist;
			sr = tr; sc = tc;
			map[sr][sc] = 0;
			
			eat++;
			if(eat==size) {
				size++;
				eat = 0;
			}
		}
		System.out.println(time);
	}
	
	static int[] bfs() {
		boolean[][] visited = new boolean[N][N];
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {sr,sc,0});
		visited[sr][sc] = true;
		
		int minDist = Integer.MAX_VALUE;//최단 거리
		int minR = -1, minC = -1;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0], c = cur[1], d = cur[2];
			
			if(d > minDist) break; // 최단거리 보다 커지면 나감
			
			for (int i = 0; i < 4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				int nd = d+1;
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue; // 범위 체크 
				if (visited[nr][nc]) continue;
				if (map[nr][nc] > size) continue; // 나보다 큰 물고기 일 때
				
				visited[nr][nc] = true;
				q.add(new int[] {nr,nc,nd});
				
				// 먹을 수 있는 물고기
	            if (map[nr][nc] > 0 && map[nr][nc] < size) {
	                if (nd < minDist) {
	                    minDist = nd;
	                    minR = nr;
	                    minC = nc;
	                } else if (nd == minDist) {
	                    if (nr < minR || (nr == minR && nc < minC)) {
	                        minR = nr;
	                        minC = nc;
	                    }
	                }
	            }
	        }
	    }

	    if (minDist == Integer.MAX_VALUE) return null;
	    return new int[]{minR, minC, minDist};
	}
}
