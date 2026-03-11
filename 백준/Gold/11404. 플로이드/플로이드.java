import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] graph;
	static final int MAX = 1_000_000_000;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 도시 수, 버스 개수 
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		
		graph = new int[N+1][N+1];
		
		for(int i = 0; i <= N; i++) {
			for(int j = 0; j <= N; j++) {
				graph[i][j] = MAX;
			}
			graph[i][i] = 0;
		}
		
		StringTokenizer st;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[s][e] = Math.min(cost, graph[s][e]);
		}
		
		floydWarshall();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(graph[i][j] == MAX) {
					graph[i][j] = 0;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(graph[i][j]).append(" ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static void floydWarshall() {
		for (int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					int cost = graph[i][k] + graph[k][j];
					
					if(cost < graph[i][j]) {
						graph[i][j] = cost;
					}
				}
			}
		}
	}

}
