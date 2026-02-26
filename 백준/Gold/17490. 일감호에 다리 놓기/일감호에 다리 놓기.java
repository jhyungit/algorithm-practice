import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
    static long K;
	static int[] needs;
	static int[] parent;
	static boolean[] outOfService; // 공사중인 정보
	
	static int findParent(int[] parent, int x) {
		if(parent[x] != x) {
			parent[x] = findParent(parent, parent[x]);
		}
		
		return parent[x];
	}
	
	static void unionParent(int v1, int v2) {
		int a = findParent(parent, v1);
		int b = findParent(parent, v2);
		
		// 작은 쪽으로 맞춰줌 
		if(a<b) parent[b] = a;
		else parent[a] = b;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 강의동의 수 N(정점), 공사구간의 수 M, 건덕이가 가진 돌의 수 K
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());
		
		needs = new int[N+1]; // 1~N번 강의실까지 필요한 돌 개수 저장
		parent = new int[N+1]; // 연결 정보 저장
		
		// 강의동 - 와우도 필요한 돌의 개수 S1~Sn
		st = new StringTokenizer(br.readLine());
		for(int room = 1 ; room <= N; room++) {
			needs[room] = Integer.parseInt(st.nextToken());
			parent[room] = room; // 자기 자신 초기화
		}
		
		outOfService = new boolean[N+1];
		// M개의 줄에 i,j:   i -(공사중)- j
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            // 공사 중인 곳 표시
            if ((x == 1 && y == N) || (x == N && y == 1)) outOfService[N] = true;
            else outOfService[Math.min(x, y)] = true; // i-(i+1)에서 i에 해당
		}
		
		for(int i = 1; i <= N; i++) {
			int v1 = i;
			int v2 = (i == N) ? 1:i+1; // i == N일 때, v2는 1
			
			// 공사 중이 아니면
			if(!outOfService[v1]) {
				unionParent(v1,v2);
			}
		}
		
		int[] minCost = new int[N+1];
		Arrays.fill(minCost, Integer.MAX_VALUE);
		
		// 각 정점의 루트 찾으면서 최소값 갱신
		for(int i = 1; i <= N; i++) {
			int root = findParent(parent, i);
			minCost[root] = Math.min(minCost[root], needs[i]);
		}
        
        // 루트 압축
        for(int i = 1; i <= N; i++){
            findParent(parent,i);
        }
		
        long total = 0;
        int components = 0;

        for(int i = 1; i <= N; i++) {
            if(i == parent[i]) {
                total += minCost[i];
                components++;
            }
        }

        // 하나로 연결돼 있으면 가능
        if(components == 1) {
            System.out.println("YES");
            return;
        }

        System.out.println(total <= K ? "YES" : "NO");
	}

}
