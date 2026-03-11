import java.io.*;
import java.util.*;

public class Main {
	static int V, E;
	static List<int[]>[] graph;
	static final int INF = 1_000_000_000;
		
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[V+1];	
		for(int i = 0; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			//양방향 a<->b, 거리 c
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new int[] {b,c});
			graph[b].add(new int[] {a,c});
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		int a = dijkstra(1, v1);
		int b = dijkstra(v1, v2);
		int c = dijkstra(v2, V);
		
		int d = dijkstra(1, v2);
		int e = dijkstra(v2, v1);
		int f = dijkstra(v1, V);
		
		//다익스트라
		long candi1 = (long) a + b + c;
		long candi2 = (long) d + e + f;

		if(a == INF || b == INF || c == INF) candi1 = Long.MAX_VALUE;
		if(d == INF || e == INF || f == INF) candi2 = Long.MAX_VALUE;
		
		long ans = Math.min(candi1, candi2);

		System.out.println(ans == Long.MAX_VALUE ? -1 : ans);
	}
	
	static int dijkstra(int start, int end) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
		int[] dist = new int[V+1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		pq.offer(new int[] {start,0});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int cost = cur[1];
			
			if(dist[node] < cost) continue;
			
			for(int[] next : graph[node]) {
				int nextNode = next[0];
				int nextCost = next[1];
				
				if(cost+nextCost < dist[nextNode]) {
					dist[nextNode] = cost+nextCost;
					pq.offer(new int[] {nextNode, dist[nextNode]});
				}
			}
		}
		
		return dist[end];
	}

}
