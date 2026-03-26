import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] hp, weight; // 내구도, 무게
	static int ans = 0;
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine().trim());
		
		hp = new int[N];
		weight = new int[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			hp[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0);
		
		System.out.println(ans);
	}

	// 손에 든 계란 idx
	static void dfs(int idx) {
		if(idx == N) {
			int broken = 0;
			for(int i = 0; i < N; i++) {
				if(hp[i] <= 0) broken++;
			}
			ans = Math.max(ans, broken);
			return;
		}
		
		// 현재 계란이 이미 깨져있다면 무시
		if(hp[idx] <= 0) {
			dfs(idx + 1);
			return;
		}
		
		boolean hit = false;
		
		for(int i = 0; i < N; i++) {
			if(i == idx) continue; // 자기 자신 일 때
			if(hp[i] <= 0) continue; // 깨져있을 때
			
			hit = true;
			
			hp[idx] -= weight[i];
			hp[i] -= weight[idx];
			
			dfs(idx + 1);
			
			hp[idx] += weight[i];
			hp[i] += weight[idx];
		}
		
		// 칠 계란이 없으면 다음으로
		if(!hit) dfs(idx + 1);
	}
}
