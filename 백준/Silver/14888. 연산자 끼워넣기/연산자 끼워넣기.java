import java.io.*;
import java.util.*;

public class Main {
	static int M;
	static int[] num;
	static List<Character> list= new ArrayList<>();
	static char[] selected;
	static boolean[] visited;
	static char[] calc = {'+','-','x','/'};
	
	static int minAns = 1_000_000_000;
	static int maxAns = -1_000_000_000;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim()); // 숫자 개수
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		num = new int[n];
		for(int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken()); // 숫자 배열
		}
		
		M = n - 1; // 연산자 개수(mPm 구해야함)
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			int cnt = Integer.parseInt(st.nextToken());
			for(int j = 0; j < cnt; j++) {
				list.add(calc[i]);	// 연산자 리스트
			}
		}
		
		visited = new boolean[M];
		selected = new char[M];
		
		perm(0); // 시작
		System.out.println(maxAns);
		System.out.println(minAns);
		
	}
	
	static void perm(int depth) {
		if(depth == M) {
			int temp = num[0];
			for(int i = 1; i < num.length; i++) {
				if(selected[i-1] == '+') {
					temp += num[i];
				}else if(selected[i-1] == '-'){
					temp -= num[i];
				}else if(selected[i-1] == 'x'){
					temp *= num[i];
				}else { // 나누기
					temp /= num[i];
				}
			}
			minAns = Math.min(minAns, temp);
			maxAns = Math.max(maxAns, temp);
			return;
		}
		
		for(int i = 0; i < M; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			selected[depth] = list.get(i);
			perm(depth+1);
			
			visited[i] = false;
		}
	}
}