import java.io.*;
import java.util.*;

public class Main {
	static int V, E; // 우주신 개수, 이미 연결된 통로 개수
	static int[] selected = new int[2];
	static List<Integer> list;
	
	static List<int[]> godPos = new ArrayList<>();
	
	static int[] parent;
	
	static List<double[]> graph =  new ArrayList<>();
	static double ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		
		for(int i = 1; i <= V; i++) {
			list.add(i);
		}
		
		godPos.add(null); // 0 번째는 없음
		for(int god = 1; god <= V; god++) { // 우주신
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			godPos.add(new int[] {x,y});
		}
		
		combi(0, 0);
		
		parent = new int[V+1];
		for(int i = 1; i <= V; i++) {
			parent[i] = i; // 부모 초기화 
		}
		
		for(int i = 0; i < E; i++) { // 우주신
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(findParent(x) != findParent(y)) unionParent(x, y);
		}
		
		graph.sort((a,b) -> Double.compare(a[2], b[2]));
		
		for(int i = 0; i < graph.size(); i++) {
			double[] node = graph.get(i);
			int s = (int) node[0];
			int e = (int) node[1];
			double dist = node[2];
			
			if(findParent(s) != findParent(e)) {
				unionParent(s, e);
				ans += dist;
			}
			
		}
		
		System.out.printf("%.2f", ans);
	}
	
	static void combi(int start, int depth) {
		if(depth == 2) {
			int from = selected[0];
			int fromX = godPos.get(from)[0];
			int fromY = godPos.get(from)[1];
			
			int to = selected[1];
			int toX = godPos.get(to)[0];
			int toY = godPos.get(to)[1];
			
			long x = Math.abs(fromX - toX);
			long y = Math.abs(fromY - toY);
			
			double distance = Math.sqrt( (x*x) + (y*y) );
						
			graph.add(new double[] {from, to, distance});
			return;
		}
		
		for(int i = start; i < V; i++) {
			selected[depth] = list.get(i);
			combi(i+1, depth+1);
		}
	}
	
	static int findParent(int x) {
		if(x == parent[x]) return x;
		return parent[x] = findParent(parent[x]);
	}
	
	static void unionParent(int a, int b) {
		int pa = findParent(a);
		int pb = findParent(b);
		
		if(pa < pb) parent[pb] = pa;
		else parent[pa] = pb;
	}

}
