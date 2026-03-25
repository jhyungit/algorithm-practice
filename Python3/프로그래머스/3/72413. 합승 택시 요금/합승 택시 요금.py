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

        for v, weight in graph[u]:
            if dist[v] < length + weight:
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
    
    # 합승하지 않는 최단 거리
    dist = dijkstra(s, n) # s에서 모든 좌표 최단거리 경로
    answer.add(dist[a]+dist[b]) # 후보 추가
    
    # s -> 경유(k) -> a,b 최단 거리
    for k in range(1, n+1):
        tot = 0
        if k == s:
            continue
        
        # s -> k까지 최단 거리 
        dist = dijkstra(s, n)
        tot += dist[k]
        
        # k -> a 최단거리
        dist = dijkstra(k, n)
        tot += dist[a]
        # k -> b 최단거리
        tot += dist[b]
        
        answer.add(tot)
    
    return min(answer)