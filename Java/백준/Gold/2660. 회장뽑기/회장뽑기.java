import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] dist;
	static final int INF = 1_000_000_000;
	static int[] maxOther;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dist = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				dist[i][j] = (i==j) ? 0 : INF;
			}	
		}
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			if(u == -1 && v == -1) break;
			dist[u][v] = 1;
			dist[v][u] = 1;
		}
		
		floydWarshall();
		
		int m = Arrays.stream(maxOther,1,N+1).min().getAsInt();
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			if(maxOther[i] == m) {
				cnt++;
				sb.append(i).append(" ");
			}
		}
		
		System.out.println(m+" "+cnt);
		System.out.println(sb);
	}
	
	static void floydWarshall(){
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(i==j) continue;
					
					if(dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}	
			}	
		}
		
		findMin();
	}
	
	static void findMin() {
		maxOther = new int[N+1];
		Arrays.fill(maxOther, -1);
		for(int i = 1; i <= N; i++) {
			maxOther[i] = Arrays.stream(dist[i],1,N+1).max().getAsInt();
		}
	}
}
