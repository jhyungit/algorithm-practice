import sys
import heapq

input = sys.stdin.readline

n, m = map(int, input().split())
dist = [float('inf')] * (n+1)
graph = [[] for _ in range(n+1)]

def dijkstra(start):
    heap = []
    dist[start] = 0 # 시작 정점 초기화
    heapq.heappush(heap, (0, start))
    
    while heap:
        food, cur = heapq.heappop(heap)
        
        if dist[cur] < food:
            continue
    
        for weight, next in graph[cur]:
            if dist[cur] + weight < dist[next]:
                dist[next] = dist[cur] + weight
                heapq.heappush(heap, (dist[next], next))

def solution():
    for _ in range(m):
        u, v, w = map(int, input().split())
        graph[u].append((w,v))
        graph[v].append((w,u))

    dijkstra(1)

    print(dist[n])

solution()