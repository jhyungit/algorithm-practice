import java.util.*;
import java.io.*;

public class Main {
	static int N, ans=Integer.MAX_VALUE;
	static int[] arr, sour, bitter;
	static List<Integer> list = new ArrayList<>();

	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		sour = new int[N];
		bitter = new int[N];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			sour[i-1] = Integer.parseInt(st.nextToken());
			bitter[i-1] = Integer.parseInt(st.nextToken());
			arr[i-1] = i;
		}
//		nCr 구하기(r: 1~n까지)
		for (int r = 1; r <= N; r++) {
			getCombination(0, 0, r); // 탐색 시작 인덱스, 현재까지 선택한 개수, 몇 개짜리 조합을 만들거냐
		}
		System.out.println(ans);
	}

	static void getCombination(int start, int depth, int r) {
		
		if(depth == r) {
			int mulSour = 1;
			int sumBitter = 0;
			for(Integer l : list) {
				mulSour *= sour[l-1];
				sumBitter += bitter[l-1];
			}
			ans = Math.min(ans, Math.abs(mulSour-sumBitter));
			
			return;
		}
		
		for(int i = start; i < N; i++) {
			list.add(arr[i]);
			getCombination(i+1, depth+1, r);
			list.remove(list.size()-1);
		}
	}
}
