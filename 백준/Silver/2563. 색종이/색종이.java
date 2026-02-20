
import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static boolean[][] area = new boolean[101][101];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for (int r = x; r < x+10; r++) {
				for (int c = y; c < y+10; c++) {
					area[r][c] = true;
				}
			}
		}
		
		int ans = 0;
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if(area[i][j] == true) ans++;
			}
		}

		System.out.println(ans);
	}

}
