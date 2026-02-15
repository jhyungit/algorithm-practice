import java.io.*;
import java.util.*;

public class Main{
	static int N, ans = Integer.MAX_VALUE;
	static int[] Populations, arr;
	static boolean[][] isConnect;
	static Set<Integer> A;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());


		// 구역의 개수 입력
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		// 1~N까지의 숫자 배열
		for(int i = 0; i<N; i++) {
			arr[i] = i+1;
		}

		
		// 각 구역의 인구수
		Populations = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			Populations[i] = Integer.parseInt(st.nextToken());
		}
		
		isConnect = new boolean[N+1][N+1];
		
		// 연결된 도시 입력
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < num; j++) {
				int city = Integer.parseInt(st.nextToken());
				// 양방향 처리
				isConnect[i+1][city] = true; 
				isConnect[city][i+1] = true; 
			}
		}
		
		for(int r = 1; r <N; r++) {
			A = new HashSet<>();
			combination(0, 0 , r); // start, depth, r
		}
		
		System.out.println(ans==Integer.MAX_VALUE?-1:ans);

		
	}
	
	static void combination(int start, int depth, int r) {
		if(depth == r) {
			Set<Integer> B = new HashSet<>();
			for(int i = 1; i<=N; i++) if(!A.contains(i)) B.add(i); // A와 B선거구 구함.
			boolean a = isConnected(A);
			boolean b = isConnected(B);
			
			// 둘 다 연결됐을 떄
			if(a&&b) {
				int diff = Math.abs(popSum(A)- popSum(B));
				ans = Math.min(diff, ans);
			}
			return;
		}
		
		for(int i = start; i < N; i++) {
			A.add(arr[i]);
			combination(i+1, depth+1,r);
			A.remove(arr[i]); //원상복구
		}
	}
	
	static boolean isConnected(Set<Integer> region) {
		if(region.isEmpty()) return false;
		
		Deque<Integer> que = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		
		int start = 0;
		
		for(int city : region) {
			start = city;
			break;
		}
		
		
		que.offerLast(start);
		visited[start] = true;
		int cnt = 1; // 방문한 지역 수
		
		while(!que.isEmpty()) {
			int cur = que.pollFirst();
			
			for(int next = 1; next <= N; next++) {
				if(!isConnect[cur][next]) continue; // 연결 안 됨
				if(!region.contains(next)) continue; // 같은 선거구 아님
				if(visited[next]) continue;
				
				visited[next] = true;
				cnt++;
				que.addLast(next);
			}
		}
		
		return cnt == region.size();
		
	}
	
	static int popSum(Set<Integer> region) {
		int sum = 0;
		for(int n : region) {
			sum += Populations[n];
		}
		return sum;
	}

}
