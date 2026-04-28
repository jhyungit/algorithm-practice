import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<int[]> graph;
    static List<int[]> mstEdges = new ArrayList<>();
    static List<int[]> mbstEdges = new ArrayList<>();
    static int[] parent;
    static Set<Integer> mstSet = new HashSet<>();
    static int maxW = -1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        parent = new int[N+1];
        for(int i = 1; i <= N; i++) parent[i] = i;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.add(new int[] {u, v, w, i+1});
        }
        graph.sort((a,b) -> Integer.compare(a[2], b[2]));

        // MST 구하기
        for(int[] e : graph) {
            if(findParent(e[0]) != findParent(e[1])) {
                unionParent(e[0], e[1]);
                mstEdges.add(e);
                maxW = Math.max(maxW, e[2]);
            }
        }
        for(int[] edge : mstEdges) mstSet.add(edge[3]);

        // parent 리셋
        for(int i = 1; i <= N; i++) parent[i] = i;

        // 1단계: non-MST 간선으로 최대한 채우기
        for(int[] e : graph) {
            if(e[2] > maxW) break;
            if(mstSet.contains(e[3])) continue;
            if(findParent(e[0]) != findParent(e[1])) {
                unionParent(e[0], e[1]);
                mbstEdges.add(e);
            }
        }

        // 2단계: MST 간선으로 보충 (가중치 높은 것부터)
        List<int[]> mstSorted = new ArrayList<>(mstEdges);
        mstSorted.sort((a,b) -> Integer.compare(b[2], a[2])); // 내림차순
        for(int[] e : mstSorted) {
            if(findParent(e[0]) != findParent(e[1])) {
                unionParent(e[0], e[1]);
                mbstEdges.add(e);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("NO\n"); // MST는 항상 MBST
        if(check()) {
            sb.append("NO\n");
        } else {
            sb.append("YES\n");
            for(int[] edge : mbstEdges) sb.append(edge[3]).append("\n");
        }
        System.out.print(sb);
    }

    static int findParent(int x) {
        if(parent[x] != x) parent[x] = findParent(parent[x]);
        return parent[x];
    }

    static void unionParent(int a, int b) {
        int x = findParent(a);
        int y = findParent(b);
        if(x < y) parent[y] = x;
        else parent[x] = y;
    }

    static boolean check() {
        long mstSum = 0;
        for(int[] e : mstEdges) mstSum += e[2];
        long mbstSum = 0;
        for(int[] e : mbstEdges) mbstSum += e[2];
        return mstSum == mbstSum;
    }
}