import heapq

INF = float("inf")

def dijkstra(start, n):
    heap = []
    dist = [INF] * (n + 1)

    # 출발 좌표, 거리
    heapq.heappush(heap, (0, start))
    dist[start] = 0
    
    while heap:
        length, u = heapq.heappop(heap)

        if dist[u] < length:
            continue

        for v, weight in graph[u]:
            if dist[v] <= length + weight:
                continue

            dist[v] = length + weight
            heapq.heappush(heap, (dist[v], v))
    return dist
    
def solution(n, s, a, b, fares):
    answer = set()
    global graph
    
    graph = [[] for _ in range(n+1)]
    
    for u, v, w in fares:
        graph[u].append((v,w))
        graph[v].append((u,w))
    
    # s -> k까지 최단 거리 
    distS = dijkstra(s, n)

    # 합승하지 않는 최단 거리
    answer.add(distS[a] + distS[b])
    
    # s -> 경유(k) -> a,b 최단 거리
    for k in range(1, n+1):
        if k == s:
            continue

        # k부터 모든 곳의 최단거리
        distK = dijkstra(k, n)
        tot = distS[k] + distK[a] + distK[b]

        answer.add(tot)
    
    return min(answer)