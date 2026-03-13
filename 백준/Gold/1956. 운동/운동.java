import java.io.*;
import java.util.*;

public class Main {
	
	static int V,E;
	static final int INF = 1_000_000_000;
	
	static int[][] graph;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		
		graph = new int[V+1][V+1];
		
		for(int i = 1; i <= V; i++) {
			Arrays.fill(graph[i], INF);
		}
		
		// 방향이 있는 그래프
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a][b] = c;
		}

		// 플로이드 워셜 수행.
		floydwarshell();
		
		int ans = Integer.MAX_VALUE;
		for(int i = 1; i < V; i++) {
			for(int j = i+1; j<=V; j++) {
				if(graph[i][j] == INF || graph[j][i] == INF) continue;
				//왕복 가능
				ans = Math.min(graph[i][j]+graph[j][i], ans);
			}
		}
		
		System.out.println(ans != Integer.MAX_VALUE ? ans : -1);
	}
	
	static void floydwarshell() {
		for(int k = 1; k <= V; k++) {
			for(int i = 1; i <= V; i++) {
				for(int j = 1; j <= V; j++) {
					if(graph[i][k] == INF || graph[k][j] == INF) continue;
					
					graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
				}
			}
		}
	}
}