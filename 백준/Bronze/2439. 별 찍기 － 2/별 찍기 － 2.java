
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= n; i++) { //5번 반
			StringBuilder ans = new StringBuilder();
			
			for(int j=0; j<n-i; j++) ans.append(" ");
			for(int j=0; j<i; j++) ans.append("*");
			System.out.println(ans.toString());
		}
		
	}

}