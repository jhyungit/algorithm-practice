import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge{
		int u,v,w;
		
		Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	
	static int T, N, M, W;
	static int[] dist;
	static final int INF = 1_000_000_000;
	static List<Edge> edges = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// N개의 지점, M개 도로(무방향), W개 웜홀(시간이 뒤로)
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			// 도로 정보
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				edges.add(new Edge(u,v,w));
				edges.add(new Edge(v,u,w));
			}
			
			// 웜홀 정보
			for(int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				edges.add(new Edge(u,v,-w));
			}
			
			boolean ans = bellmanFord();
			sb.append(ans == true ? "YES" : "NO").append("\n");
			edges.clear();
		}
		System.out.println(sb);
	}
	
	static boolean bellmanFord() {
		dist = new int[N+1];
		Arrays.fill(dist, 0);
				
		// N-1번 완화
		for(int i = 1; i <= N; i++) {
			for(Edge edge : edges) {
				int u = edge.u;
				int v = edge.v;
				int w = edge.w;
				
				if(dist[u] + w < dist[v]) {
					dist[v] = dist[u] + w;
					
					// N-1번 완화 후에도 갱신되면!
					if(i == N) return true;
				}
			}
		}
		return false;
	}
	
}
