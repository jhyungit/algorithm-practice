import java.util.*;
import java.io.*;

public class Main{
	static int N, M, X;
	
	static int[] dist;
	static final int INF = 1_000_000_000;
	static int[] goAndCome;
	
	static List<int[]>[] graph;
	
	public static void main(String[] args) throws Exception {
		//------여기에 솔루션 코드를 작성하세요.------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// n명(1~n도시), 도로  M개, 모임장소 x번
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// 시작 -> 끝, 소요시간  !일방통행 도로!
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[u].add(new int[] {v,w});
		}
		
		// 왕복시간 배열
		goAndCome = new int[N+1];
		
		// 요원 중 X왕복 가장 오래 걸리는 요원의 소요시간 구하기(다익스트라)
		
		// X -> 모든 장소 최소시간
		dijkstra(X);
		
		for(int i = 1; i <= N; i++) {
			goAndCome[i] += dist[i];
		}
		
		// 모든 장소 -> X 최소시간 (d일방향이므로 반대로 하면 됨.)
		for(int i = 1; i <= N; i++) {
			if(i == X) continue;
			dijkstra(i);
			
			goAndCome[i] += dist[X];
		}
		
		int ans = Integer.MIN_VALUE;
		for(int i = 1; i <= N; i++) {
			ans = Math.max(ans, goAndCome[i]);
		}
		System.out.println(ans);
		
	}
	
	static void dijkstra(int start) {
		// 거리 초기화
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
		pq.offer(new int[] {start, 0}); // 시작, 
		// 시작 좌표 0으로 초기화
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			int node = cur[0];
			int cost = cur[1];
			
			if(dist[node] < cost) continue;
			
			for(int[] next: graph[node]) {
				int nextNode = next[0];
				int weight = next[1];
				
				if(cost+weight < dist[nextNode]) {
					dist[nextNode] = cost+weight;
					pq.offer(new int[] {nextNode, dist[nextNode]});
				}
			}
		}
	}
}