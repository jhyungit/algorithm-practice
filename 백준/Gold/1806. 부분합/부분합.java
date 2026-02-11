
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int s =0 ,e=0;
		int ans = 0;
		int sum = arr[0];
		
		while(s<=e) {
			if(sum >= target) { // 합이 목표값 이상이면
				if(ans == 0) { ans = e-s+1;}
				else {ans = Math.min(ans, e-s+1);}
				sum -= arr[s++]; // 시작 좌표 ++
			}else { // 미만이면
				if(e==n-1) break;
				sum += arr[++e]; // 끝 좌표
			}
		}
		System.out.println(ans);
	}

}
