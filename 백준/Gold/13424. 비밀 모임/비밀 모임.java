import java.io.*;
import java.util.*;

public class Main {
	static int T, N, M, K; // 테스트케이스, 방 개수, 비밀통로 수, 친구 수
	static int[] dist;
	static int[] sumDist;
	static List<int[]>[] adjList;
	static final int INF = 1_000_000_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		
		StringBuilder sb = new StringBuilder();
		for(int tc = 0; tc < T; tc++) {	
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			sumDist = new int[N+1]; // 최단 거리 합산
			adjList = new ArrayList[N+1];
			for(int i = 0; i < N + 1; i++) {
				adjList[i] = new ArrayList<>();
			}
						
			// 간선 정보 입력
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				adjList[u].add(new int[] {v, w});
				adjList[v].add(new int[] {u, w});
			}
			
			// 친구 수 입력
			K = Integer.parseInt(br.readLine().trim());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < K; i++) {
				int student = Integer.parseInt(st.nextToken());
				dijsktra(student);
			}
			
			sb.append(findMinDist(sumDist));
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void dijsktra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
		
		dist = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			dist[i] = INF;
		}
		
		pq.offer(new int[] {0, start});
		dist[start] = 0;
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int length = cur[0]; int u = cur[1];
			
			if(dist[u] < length) continue;
			
			for(int[] next: adjList[u]) {
				int v = next[0];
				int w = next[1];
				
				if(dist[v] > w + length) {
					dist[v] = w + length;
					pq.offer(new int[] {dist[v], v});
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			sumDist[i] += dist[i];
		}
		return;
	}
	
	static int findMinDist(int[] sumDist) {
		int[] min = {-1, INF};
		
		for(int i = 1; i <= N; i++) {
			if(sumDist[i] < min[1]) {
				min[0] = i;
				min[1] = sumDist[i];
				
			}
		}
		
		return min[0];
	}
}
