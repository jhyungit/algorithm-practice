import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static Map<String, List<String>> graph = new HashMap<>();
	static Map<String, Integer> indegree = new HashMap<>();
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String prevItem = st.nextToken();
			String nextItem = st.nextToken();
			
			// graph key 초기화
			graph.putIfAbsent(prevItem, new ArrayList<>());
			graph.putIfAbsent(nextItem, new ArrayList<>());
			
			// indgree 초기화
			indegree.putIfAbsent(prevItem, 0);
			indegree.putIfAbsent(nextItem, 0);
			
			// 간선 추가
			graph.get(prevItem).add(nextItem);
			
			// 진입차수 증가
			indegree.put(nextItem, indegree.get(nextItem)+1);
		}
		
		topologySort();
		
	}
	
	static void topologySort() {
		PriorityQueue<String> pq = new PriorityQueue<>();
		ArrayDeque<String> q = new ArrayDeque<>();
		
		for(String key : indegree.keySet()) {
			if(indegree.get(key) == 0) { // 진입차수 0인 거 pq에 추가
				pq.offer(key);
				q.offer(key);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		
		while(!q.isEmpty()) {
			// 이번 라운드에 구매 가능한 것들 
			while(!pq.isEmpty()) {
				sb.append(pq.poll()).append('\n');
			}
			
			// 다음 라운드 후보 생성
			int size = q.size();
			for(int i = 0; i< size; i++) {
				String cur = q.poll();
				cnt++;
				
				for(String nextItem : graph.get(cur)) {
					indegree.put(nextItem, indegree.get(nextItem)-1);
					
					if(indegree.get(nextItem) == 0 ) {
						pq.offer(nextItem);
						q.offer(nextItem);
					}
				}
			}
		}	

		if(cnt != indegree.size()) {
			System.out.println(-1);
			
		}else {
			System.out.print(sb);
		}
	}
}
