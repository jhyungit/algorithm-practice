import heapq
from collections import defaultdict

n,m = map(int,input().split())

graph = defaultdict(list)
degree = [0] * (n+1)

for _ in range(m):
    a,b = map(int,input().split())
    graph[a].append(b)
    degree[b] += 1

heap = []
for i in range(1,n+1):
    if degree[i] == 0:
        heapq.heappush(heap,i)

ans = []    
while heap:
    num = heapq.heappop(heap)
    ans.append(str(num))
    for i in graph[num]:
        degree[i] -= 1
        if degree[i] == 0:
            heapq.heappush(heap,i)


print(" ".join(ans))