import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] pos;
	static double[][] adjMatrix;
	static double[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine().trim());
		
		pos = new int[n][2];
		adjMatrix = new double[n][n];
		dp = new double[n][1<<n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			pos[i][0] = x;
			pos[i][1] = y;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i == j) {
					adjMatrix[i][j] = 0;
					continue;
				}
				double dist = Math.sqrt(Math.pow(pos[i][0] - pos[j][0], 2) + Math.pow(pos[i][1] - pos[j][1], 2));
				adjMatrix[i][j] = dist;
			}
		}
		
		for (int i = 0; i < n; i++) {
		    Arrays.fill(dp[i], -1);
		}
		
		double ans = makeDP(0, 1); // (시작, 마스크)
		
		System.out.println(ans);
	}
	
	static double makeDP(int cur, int mask) {
		//  mask == 1111.. 모두 1이면 (모두 방문)
		if (mask == (1<<n) - 1) {
			if(adjMatrix[cur][0] == 0) return Integer.MAX_VALUE; // 갈 수 없는 경우
			return adjMatrix[cur][0];
		}
		
	    if (dp[cur][mask] != -1) return dp[cur][mask];  // 메모이제이션 체크(계산돼 있으면 바로 사용)
	    
	    double result = Integer.MAX_VALUE;
		
		for(int i = 0; i < n; i++) { // i는 다음 지점
			if((mask & (1<<i)) != 0) continue; // 이미 방문함
			if(adjMatrix[cur][i] == 0) continue;
			
			//미방문 도시 이동(재귀함수)
			double next = makeDP(i , mask | (1<<i));
			
			if(next != Integer.MAX_VALUE) {
				result = Math.min(result, adjMatrix[cur][i] + next); //최솟값 갱신
			}
		}
		dp[cur][mask] = result;
		return result;
	}
}
