import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static Point[] points;
	
	static class Point{
		long x, y;
		
		Point(long x, long y){
			this.x = x;
			this.y = y;
		}
	}
	
	static Point base; // 기준점
	
	static int[][] loc;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		points = new Point[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long x = Integer.parseInt(st.nextToken());
			long y = Integer.parseInt(st.nextToken());
			
			points[i] = new Point(x,y);
		}

		// 맨 아래 기준점 찾기
		base = points[0];
		for(int i = 1; i < N; i++) {
			// y가 가장 작고, 같다면 x가 더 작은 거 비교
			if(points[i].y < base.y || (points[i].y == base.y && points[i].x < base.x)) {
				base = points[i];
			}
		}
		
		// 기준점  기준 반시계 방향 정렬
		Arrays.sort(points, (p1,p2) ->{
			if(p1 == base) return -1; // (base, ?)이면 -1
			if(p2 == base) return 1; // (?, base)이면 1
			
			long ccwValue = ccw(base, p1, p2); // 세 점 방향 판단
			
            // base -> p1 -> p2 가 반시계면 p1이 앞
            if (ccwValue > 0) return -1;
            if (ccwValue < 0) return 1;

            // 일직선이면 base와 더 가까운 점이 앞
            long d1 = dist(base, p1);
            long d2 = dist(base, p2);
            return Long.compare(d1, d2);
		});

        // 3. Graham Scan
        Stack<Point> stack = new Stack<>();
        stack.push(points[0]);
        stack.push(points[1]);

        for (int i = 2; i < N; i++) {
            Point next = points[i];

            while (stack.size() >= 2) {
                Point second = stack.pop();
                Point first = stack.peek();

                // first -> second -> next 가 반시계면 유지
                if (ccw(first, second, next) > 0) {
                    stack.push(second);
                    break;
                }
                // 시계 방향이거나 일직선이면 second 제거
            }

            stack.push(next);
        }

        System.out.println(stack.size());
	}
	
	// 반시계: 양수, 시계: 음수, 일직선: 0
	static long ccw(Point a, Point b, Point c) {
		return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);		
	}
	
	// 거리 제곱
	 static long dist(Point a, Point b) {
		 long dx = a.x - b.x;
		 long dy = a.y - b.y;
		 return dx * dx + dy * dy;
	 }

}
