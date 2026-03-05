import java.io.*;
import java.util.*;

public class Main {
	static int cheeseCnt;
	static int beforeCnt ;
    static int R, C;
    static int[][] map;
    static boolean[][] line;
    
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};
    
    static Deque<int[]> dq = new ArrayDeque<>();
    
    
    static void erase() {
    	int eraseCnt = 0;
    	for(int r = 0; r < R; r++) {
    		for(int c = 0; c < C; c++) {
    			if(line[r][c]== true) {
    				eraseCnt++;
    				map[r][c] = 0;
    			}
    		}
    	}
    	cheeseCnt -= eraseCnt;
    	if(cheeseCnt == 0) beforeCnt = eraseCnt; 
    }
    
    static void bfs(int r, int c) {
    	boolean[][] visited = new boolean[R][C];
    	line = new boolean[R][C];
        
        dq.offer(new int[] {r,c});
        visited[r][c] = true;

        // 경계 치즈 erase check
        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            int rr = cur[0];
            int cc = cur[1];
            
            for(int k = 0; k < 4; k++) {
                int nr = rr + dr[k];
                int nc = cc + dc[k];
                
                if(nr<0 || nr >= R || nc < 0 || nc>=C) continue;
                if(visited[nr][nc]) continue;
                
                if(map[nr][nc] == 0) {
                	visited[nr][nc] = true;
                	dq.offer(new int[] {nr,nc});
                }else { // 경계 치즈 발견
                	line[nr][nc] = true;
                	visited[nr][nc] = true;
                }
            }
        }
        
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new int[R][C];
        cheeseCnt = 0;
        for(int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 1) cheeseCnt++;
            }
        }
        
        int time = 0;
        while(cheeseCnt != 0) {
        	bfs(0,0); // 경계 치즈 탐색
        	
        	erase();
        	time++;
        }
        
        System.out.println(time);
        System.out.println(beforeCnt);
    }
}