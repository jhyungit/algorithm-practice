import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R,C;
	static int ans;
	static int[][] map;
	static int[] dr =  {-1,0,1}; // 우상, 우, 우하
	static int[] dc =  {1,1,1};
	
	static boolean dfs(int r, int c) { // 시작 좌표 r,c
		if(c == C-1)return true;
		
		for(int i=0;i<3;i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			
			if(nr<0|| nr>=R || nc>=C || map[nr][nc]==1) continue;
			map[nr][nc] = 1;
            if(dfs(nr,nc)) return true;
			}
		return false;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for(int i=0; i<R; i++) {
			String ss = br.readLine();
			char[] sc = ss.toCharArray(); // Char -> Array로
			for(int j=0; j<C; j++) {
				if(sc[j] == 'x') map[i][j] = 1;
			}
		}
		
		for(int r=0; r<R;r++) {
			if(dfs(r,0)) ans++;
		}
		System.out.println(ans);
	}
}
