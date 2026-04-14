import heapq

INF = float("inf")

# 출발지 -> 산봉우리 경로 중
# 경로 내 최대 간선 값의 최소 구하기  
def dijkstra(n, gates, summits, graph):
    heap = []
    # dist 초기화
    dist = [INF] * (n+1)
    
    for gate in gates:
        heapq.heappush(heap, (0,gate))
        dist[gate] = 0 # 출발지는 0
        
    gates_set = set(gates)
    summits_set = set(summits)
        
    while heap:
        intensity, cur = heapq.heappop(heap)
        
        # 이미 저장된 intensity가 더 작으면
        if intensity > dist[cur]:
            continue
            
        if cur in summits_set:
            continue
            
        for v,w in graph[cur]:
            if v in gates_set:
                continue
            
            new_intensity = max(w, intensity)
            
            if new_intensity < dist[v]:
                dist[v] = new_intensity
                heapq.heappush(heap, (dist[v], v))
    
    return dist
        
# 지점, 등산로 정보, 출입구, 산봉우리
def solution(n, paths, gates, summits):
    graph = [[] for _ in range(n+1)]
    
    for u,v,w in paths:
        graph[u].append((v,w))
        graph[v].append((u,w))
    
    dist = dijkstra(n, gates, summits, graph)
    
    summits.sort()
    answer = [0, INF]
    
    for summit in summits:
        if dist[summit] < answer[1]:
            answer = [summit, dist[summit]]
    return answer