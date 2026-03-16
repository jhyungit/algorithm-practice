import java.util.*;
import java.io.*;


public class Main{
	static int S, N;
	
	static boolean[] selected;
	static int[] arr;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		//------여기에 솔루션 코드를 작성하세요.------------
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 마력석(N개), 마력수치(S)
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		selected = new boolean[N];
		
		//부분집합 구하기
		subset(0); //depth
		
		// S가 0이면 모두 선택하지 않았을 때, 처음 0에 무조건 걸리므로 ans - 1해줌
		System.out.println(S == 0 ? ans-1:ans);
	}
	
	static void subset(int depth) {
		if(depth == N) {
			int sum = 0;
			for(int i = 0; i < N; i++) {
				if(selected[i]) sum += arr[i];
			}
			if(sum == S) ans++; // 마력석들 중 1개 이상 합이 = S
			return;
		}
		
		
		//선택한 경우
		selected[depth] = true;
		subset(depth+1);
		
		//선택하지 않은 경우
		selected[depth] = false;
		subset(depth+1);
		
	}
}