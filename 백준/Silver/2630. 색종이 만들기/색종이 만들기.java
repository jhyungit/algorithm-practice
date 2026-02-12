import java.util.Scanner;

public class Main{
	
	static int[][] spaces;
	static int white, green; // 하얀색, 초록색 공간의 수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		spaces = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				spaces[i][j] = sc.nextInt();
			}
		}
		cut(0,0,n);
		System.out.println(white);
		System.out.println(green);
	}
	
	static void cut(int r, int c, int size) { // r,c: 탐색 영역의 좌상단 좌표, size: 영역 한 변의 크기
		//주어진 영역의 색상 카운트
		int sum = 0;
		
		for (int i = r; i < r+size; i++) {
			for (int j = c; j < c+size; j++) {
				sum += spaces[i][j];
			}
		}
		
		if(sum == size*size) { // 모두 초록색
			++green;
		}else if(sum==0) { //모두 하얀색
			++white;
		}else { // 색상이 섞여있다
			int half = size/2;
			cut(r,c,half); // 좌상영역
			cut(r,c+half,half); // 우상영역
			cut(r+half, c, half); // 좌하영역
			cut(r+half, c+half, half); //우하영역
		}
		
	}
}
