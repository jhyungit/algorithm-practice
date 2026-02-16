import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine().trim());

        int[] dir = new int[6];
        int[] len = new int[6];

        int maxW = 0, maxH = 0;
        int idxW = -1, idxH = -1;

        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dir[i] = Integer.parseInt(st.nextToken());
            len[i] = Integer.parseInt(st.nextToken());

            // 동(1), 서(2)
            if (dir[i] == 1 || dir[i] == 2) {
                if (len[i] > maxW) { maxW = len[i]; idxW = i; }
            } else { // 남(3), 북(4)
                if (len[i] > maxH) { maxH = len[i]; idxH = i; }
            }
        }

        // 원형 인덱스 처리
        int leftH = len[(idxH + 5) % 6];
        int rightH = len[(idxH + 1) % 6];
        int smallW = Math.abs(leftH - rightH);

        int leftW = len[(idxW + 5) % 6];
        int rightW = len[(idxW + 1) % 6];
        int smallH = Math.abs(leftW - rightW);

        int area = maxW * maxH - smallW * smallH;
        System.out.println(area * K);
    }
}
