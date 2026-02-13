import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N,M,x1,y1,x2,y2, ans;
	static int[][] arr, prefix;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j]= Integer.parseInt(st.nextToken());
			}		}
		prefix = new int[N+1][N+1];
		makePrefix();
		
		for (int i = 0; i < M; i++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			calc();
			System.out.println(ans);
		}
		
	}
	
	static void makePrefix() {
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				prefix[i][j] = prefix[i-1][j]+prefix[i][j-1]- prefix[i-1][j-1]+arr[i-1][j-1];
			}
		}
	}
	
	static void calc() {
		ans = prefix[x2][y2] - prefix[x2][y1-1]-prefix[x1-1][y2]+prefix[x1-1][y1-1];
	}

}
