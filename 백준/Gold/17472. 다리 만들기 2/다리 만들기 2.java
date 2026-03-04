import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static int[][] map;
	
	static int[][] d = {{0,1},{0,-1},{1,0},{-1,0}};
	static Deque<int[]> dq = new ArrayDeque<>();
	static int[] temp;
	
	// dist를 기준으로 정렬
	static PriorityQueue<int[]> pq = new PriorityQueue<>(
			(a,b) -> a[2] - b[2] // 3번째 값 기준 정렬
		);
	
	
	// 섬 그룸핑 함수
	static void islandGrouping(int r, int c, int num){
		map[r][c] = num;
		dq.offer(new int[] {r,c});
		
		
		while(!dq.isEmpty()) {
			temp = dq.poll();
		
			for(int k = 0; k < 4; k++) {
				int nr = temp[0] + d[k][0];
				int nc = temp[1] + d[k][1];
				
				if(nr<0 || nr >= R || nc < 0 || nc >= C) continue;
				if(map[nr][nc] == 1) {
					dq.offer(new int[] {nr,nc});
					map[nr][nc] = num;
				}
			}
		}
	}
	
	// 가로로 건설 가능한 다리
	static void HorizentalBuildBridge(int r, int c) {
		int startIsland = map[r][c-1]; // 시작 섬 번호
		int length = 1; // 다리 길이 초기화
		
		while(true) {
			c += 1; // 오른쪽으로 한칸씩 이동
			length++;
			
			if(c >= C) return; //오른쪽에 섬이 없음
			if(length == 2 && map[r][c] != 0) return; // 다리 길이 2에 섬이 있음: 건설 불가
			
			if(map[r][c] != 0) {
				pq.add(new int[] {startIsland, map[r][c], length-1});
				break; // 건설 가능
			}
			
		}
		
	}
	
	// 세로로 건설 가능한 다리
	
	
	static void verticalBuildBridge(int r, int c) {
		int startIsland = map[r-1][c]; // 시작 섬 번호
		int length = 1; // 다리 길이 초기화
		
		while(true) {
			r += 1; // 아래로 한칸씩 이동
			length++;
			
			if(r >= R) return; //오른쪽에 섬이 없음
			if(length == 2 && map[r][c] != 0) return; // 다리 길이 2에 섬이 있음: 건설 불가
			
			if(map[r][c] != 0) {
				pq.add(new int[] {startIsland, map[r][c], length-1});
				break; // 건설 가능
			}
			
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬끼리 묶기
		int iNum = 2;
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == 1) {
					islandGrouping(r, c, iNum++);
				}
			}
		}	
		
		// 가로 다리 구하기(start, end, dist)
		// 가능한 범위 r(0~R), c(1~C)
		for(int r = 0; r < R; r++) {
			for(int c = 1; c < C; c++) {
				// 해당 좌표가 바다이고 왼쪽이 바로 섬이면 가능
				if(map[r][c] == 0 && map[r][c-1] != 0) {
					HorizentalBuildBridge(r,c); // 시작, 끝, 길이
				}
			}
		}
		
		
		// 세로 다리 놓기(start, end, dist)
		// 가능한 범위 r(1~R), c(0~C)
		for(int r = 1; r < R; r++) {
			for(int c = 0; c < C; c++) {
				// 해당 좌표가 바다이고 위가 섬이면 가능
				if(map[r][c] == 0 && map[r-1][c] != 0) {
					verticalBuildBridge(r,c); // 시작, 끝, 길이
				}
			}
		}
				
		// 크루스칼		
		parent = new int[iNum];
		 // 자기자신 초기화
		for(int i = 2; i < iNum; i++) {
			parent[i] = i;
		}
		
		
		int ans = 0;
		int picked = 0;
		
		while(!pq.isEmpty()) {
			int[] p = pq.poll();
			int start = p[0];
			int end = p[1];
			int dist = p[2];
			
			if(findParent(start) != findParent(end)) {
				unionParent(start,end);
				ans += dist;
				picked++;
			}
		}
		
		int K = iNum -2; // 섬의 개수
		if(picked != K-1) { //다리 개수 == 섬 개수 - 1 (간선의 수는 노드수-1)
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
	}
	
	static int[] parent;
	
	
	static void unionParent(int a, int b) {
		int x = findParent(a);
		int y = findParent(b);
		
		if(x<y) parent[y] = x;
		else parent[x] = y;
	}
	
	static int findParent(int x) {
		if(x != parent[x]) parent[x] = findParent(parent[x]); // 경로 압축
		return parent[x];
	}
}