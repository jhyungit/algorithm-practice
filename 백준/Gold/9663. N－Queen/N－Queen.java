import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean[] slash, bSlash, col;
	static int n, total;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());

		
		int i = 1;
		total = 0;
		
		col = new boolean[n+1];
		slash = new boolean[2*n+1];
		bSlash = new boolean[2*n];
		
		nQueens(1);
		
		System.out.println(total);
	}
	
	static void nQueens(int row) {
		if(row>n) {
			total++;
			return;
		}
		
		for(int c = 1; c<=n; c++) {
			if(col[c] || slash[row+c] || bSlash[row-c+n]) {
				continue;
			}
			col[c] = slash[row+c] = bSlash[row-c+n] = true;
			nQueens(row+1);
			col[c] = slash[row+c] = bSlash[row-c+n] = false;
			
		}
		
	}

}
