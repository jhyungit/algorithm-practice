// MST문제 최단 경로로 가로등 불키기(크루스칼 이용)
// 간많프 간적쿠
import java.io.*;
import java.util.*;

public class Main {
	static int M,N; //집의 수, 길의 수
	static int total, mstCost;
	static int[] parent;
	
	static List<int[]> graph; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0) break;
			
			graph = new ArrayList<>();
			parent = new int[M];
			total = 0;
			mstCost = 0;
			
			for(int i = 0; i < M; i++) {
				parent[i] = i; // 자기자신으로 초기화
			}
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				graph.add(new int[] {a,b,cost});
				total += cost;
			}
			
			graph.sort((a,b) -> a[2] - b[2]);
			
			for(int[] g : graph) {
				int x = g[0];
				int y = g[1];
				int dist = g[2];
				
				int parentX = findParent(x, parent);
				int parentY = findParent(y, parent);
				
				if(parentX == parentY) {
					continue;
				}else {
					unionParent(parentX,parentY);
					mstCost += dist;
				}
			}
			sb.append(total - mstCost).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static int findParent(int x, int[] parent) {
		if(x == parent[x]) return parent[x];
		
		return parent[x] = findParent(parent[x], parent);
	}
	
	static void unionParent(int x, int y) {
		int a = findParent(x, parent);
		int b = findParent(y, parent);
		
		if(a<b) parent[b] = a;
		else parent[a] = b;
	}
    
}
