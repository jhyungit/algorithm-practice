import java.io.*;
import java.util.*;


public class Main {
	static int[][] map = new int[9][9];
	static List<int[]> blanks = new ArrayList<>();
	
	// 상 하 좌 우 
	static boolean[][] colUsed = new boolean[9][10];
	static boolean[][] rowUsed = new boolean[9][10];
	static boolean[][] boxUsed = new boolean[9][10];
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 9; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < 9; j++) {
				int n = line.charAt(j) - '0';
				map[i][j] = n;
				
				if(n == 0) {
					blanks.add(new int[] {i,j}); // 0인 좌표들 모아두기
				} else {
					// 행과열에 사용했는지 체크
					rowUsed[i][n] = true;
					colUsed[j][n] = true;
					boxUsed[boxIndex(i,j)][n] = true;	
				}
			}
		}
		
		dfs(0);
		
	}
	
	static int boxIndex(int r, int c) {
		return (r/3) * 3 + (c/3);
	}
	
	// 처음 성공을 바로 return
	static boolean dfs(int depth) { 
		if(depth == blanks.size()) { // depth가 0의 개수와 같아지면	
			StringBuilder sb = new StringBuilder();
			
			for(int r = 0; r < 9; r++) {
				for(int c = 0; c < 9; c++) {
					sb.append(map[r][c]);
				}
				sb.append('\n');
			}
			
			System.out.println(sb);
			return true;
		}
		
		// 현재 빈칸 r,c에 대해 1~9 넣어보기
		int r =blanks.get(depth)[0];
		int c =blanks.get(depth)[1];
		int b = boxIndex(r,c);
		
		for(int num = 1; num <= 9; num++) {
			if(rowUsed[r][num] || colUsed[c][num] || boxUsed[b][num]) continue;
			
			map[r][c] = num;
			rowUsed[r][num] = colUsed[c][num] = boxUsed[b][num] = true;
			
			if(dfs(depth+1)) return true;
			
			// 백트래킹
			map[r][c] = 0;
			rowUsed[r][num] = colUsed[c][num] = boxUsed[b][num] = false;
		}
		return false;
	}

}
