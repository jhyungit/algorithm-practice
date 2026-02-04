import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Deque<int[]> stack = new ArrayDeque<>(); // 높이, 인덱스
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= n; i++) {
			int h = Integer.parseInt(st.nextToken());
			
			while(!stack.isEmpty() && stack.peek()[0] < h) {
				stack.pop();
			}
			
			if(stack.isEmpty()) sb.append(0);
			else sb.append(stack.peek()[1]);
			
			if(i < n) sb.append(" ");
			
			stack.push(new int[] {h, i});
		}
		
		System.out.println(sb.toString());
	}

}