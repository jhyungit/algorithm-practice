import java.io.*;
import java.util.*;

public class Main {
    static int R, C, T;
    static List<Integer> airCondiPos = new ArrayList<>();
    static int[][] room;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        room = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) airCondiPos.add(i);
            }
        }

        for (int sec = 0; sec < T; sec++) {
            int[][] next = new int[R][C];          // 매 초 초기화
            spread(room, next);                    // room -> next
            onAirCondition(next);                  // next 정화
            room = next;                           // 상태 갱신
        }

        int dust = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (room[r][c] > 0) dust += room[r][c]; // 먼지 더하기
            }
        }
        System.out.println(dust);
    }

    static void spread(int[][] src, int[][] dest) {
        int topR = airCondiPos.get(0);
        int bottomR = airCondiPos.get(1);

        // 공기청정기 위치 고정
        dest[topR][0] = -1;
        dest[bottomR][0] = -1;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (src[r][c] <= 0) continue; // 0 또는 -1이면 continue 

                int amount = src[r][c] / 5;
                int cnt = 0;

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if (src[nr][nc] == -1) continue;

                    dest[nr][nc] += amount;
                    cnt++;
                }

                dest[r][c] += src[r][c] - amount * cnt; // 남은 양
            }
        }
    }

    static void onAirCondition(int[][] roomInfo) {
        int topR = airCondiPos.get(0);
        int bottomR = airCondiPos.get(1);

        // 위쪽(반시계)
        Deque<Integer> counterClockwise = new ArrayDeque<>();

        for (int c = 1; c < C; c++) counterClockwise.offerLast(roomInfo[topR][c]);      // 우 
        for (int r = topR - 1; r >= 0; r--) counterClockwise.offerLast(roomInfo[r][C-1]); // 상
        for (int c = C - 2; c >= 0; c--) counterClockwise.offerLast(roomInfo[0][c]);    // 좌
        for (int r = 1; r < topR; r++) counterClockwise.offerLast(roomInfo[r][0]);      // 하

        counterClockwise.pollLast();        // 청정기로 들어가는 먼지 제거
        counterClockwise.offerFirst(0);     // 청정기에서 나온 깨끗한 바람

        for (int c = 1; c < C; c++) roomInfo[topR][c] = counterClockwise.pollFirst();
        for (int r = topR - 1; r >= 0; r--) roomInfo[r][C-1] = counterClockwise.pollFirst();
        for (int c = C - 2; c >= 0; c--) roomInfo[0][c] = counterClockwise.pollFirst();
        for (int r = 1; r < topR; r++) roomInfo[r][0] = counterClockwise.pollFirst();

        // 아래쪽(시계)
        Deque<Integer> clockwise = new ArrayDeque<>();

        for (int c = 1; c < C; c++) clockwise.offerLast(roomInfo[bottomR][c]);        // 우 
        for (int r = bottomR + 1; r < R; r++) clockwise.offerLast(roomInfo[r][C-1]);  // 하 
        for (int c = C - 2; c >= 0; c--) clockwise.offerLast(roomInfo[R-1][c]);       // 좌 
        for (int r = R - 2; r > bottomR; r--) clockwise.offerLast(roomInfo[r][0]);    // 상

        clockwise.pollLast();        // 청정기로 들어가는 먼지 제거
        clockwise.offerFirst(0);     // 청정기에서 나온 깨끗한 바람

        for (int c = 1; c < C; c++) roomInfo[bottomR][c] = clockwise.pollFirst();
        for (int r = bottomR + 1; r < R; r++) roomInfo[r][C-1] = clockwise.pollFirst();
        for (int c = C - 2; c >= 0; c--) roomInfo[R-1][c] = clockwise.pollFirst();
        for (int r = R - 2; r > bottomR; r--) roomInfo[r][0] = clockwise.pollFirst();

        // 공기청정기 위치 복구
        roomInfo[topR][0] = -1;
        roomInfo[bottomR][0] = -1;
    }
}
