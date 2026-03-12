import java.io.*;
import java.util.*;

public class Main {
	static int n, limit, road; // 지역 개수, 수색 범위, 길의 개수
	static int[] dist;
	static int[] items; // 지역별 아이템 수
	static List<int[]>[] graph;
	
	static int ans = Integer.MIN_VALUE;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());
		road = Integer.parseInt(st.nextToken());
		
		// 초기화
		items = new int[n+1];
		graph = new ArrayList[n+1];
		
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 지역별 아이템 수 입렵
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		// 간선 정보 입력
		for(int i = 0; i < road; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			
			graph[start].add(new int[] {end,length});
			graph[end].add(new int[] {start,length});
		}

		for(int i = 1; i <= n; i++) { //1~n까지 모든 경로 최단거리 탐색
			dist = new int[n+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dijkstra(i);
			
			int sum = 0;
			for(int j = 1; j <= n; j++) {
				if(dist[j] <= limit) {
					sum += items[j];
				}
			}
			
			ans = Math.max(ans, sum);
		}
		
		System.out.println(ans);
	}
	
	static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
		
		dist[start] = 0;
		pq.offer(new int[] {start,0}); // 시작좌표, 거리
		
		while(!pq.isEmpty()) {
			int[] cur= pq.poll();
			int now = cur[0];
			int cost = cur[1];
			
			if(dist[now] < cost) continue;
			
			for(int[] next : graph[now]) {
				int nextNode = next[0];
				int nextCost = next[1];
				
				if(nextCost + cost < dist[nextNode]) {
					dist[nextNode] = nextCost + cost;
					pq.offer(new int[] {nextNode,  dist[nextNode]});
				}
			}
		}
	}
	
}