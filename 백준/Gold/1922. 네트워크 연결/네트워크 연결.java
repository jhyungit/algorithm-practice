import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] parent;
	static List<int[]> edges;
	static int ans;
	
	static int findParent(int x) {
		if(parent[x] == x) return x;
		return parent[x] = findParent(parent[x]);
	}
	
	static void unionParent(int a, int b) {
		int x = findParent(a);
		int y = findParent(b);
		
		if(x<y) parent[y] = x;
		else parent[x] = y;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N 컴퓨터 수, M 연결할 수 있는 선의 수
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		
		// 부모 초기화
		parent = new int[N+1];
		for(int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		
		StringTokenizer st;
		edges = new ArrayList<>(); // 간선 가중치 정보 a,b, dist
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			edges.add(new int[] {a,b,dist});
		}

		edges.sort((a,b) -> Integer.compare(a[2], b[2])); // 거리 기준 오름차순 정렬
		
		for(int[] info : edges) {
			int a = info[0];
			int b = info[1];
			int dist = info[2];

			if(findParent(a) != findParent(b)) { // 경로 압축
				unionParent(a,b);
			}else { // cycle 발생
				continue;
			}
			ans += dist;
		}
		
		System.out.println(ans);
		
	}

}
