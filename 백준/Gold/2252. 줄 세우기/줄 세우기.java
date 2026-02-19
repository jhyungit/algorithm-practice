import java.io.*;
import java.util.*;

public class Main {
	static int[] indegree;
	static List<Integer>[] adj;
	static int N, M;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		indegree = new int[N+1]; // 진입차수 0으로 초기화
		adj = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int tall = Integer.parseInt(st.nextToken());
			int small = Integer.parseInt(st.nextToken());
			
			adj[tall].add(small);
			indegree[small]++;
		}

		
		Deque<Integer> dq = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0){
				dq.offer(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		while (!dq.isEmpty()) {
			int cur = dq.poll();
			sb.append(cur).append(' ');
			
			for(int next: adj[cur]) {
				indegree[next]--;
				if (indegree[next] == 0){
					dq.offer(next);
				}
			}

		}
		
		System.out.println(sb);
	}
}
