import sys
input = sys.stdin.readline

def find_parent(parent,x):
    while parent[x] != x:
        parent[x] = parent[parent[x]]
        x = parent[x]
    return x

def union_parent(a,b,parent):
    a = find_parent(parent,a)
    b = find_parent(parent,b)
    
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

v,e = map(int,input().split())
graph = []
parent = [i for i in range(v+1)]

for _ in range(e):
    graph.append(list(map(int,input().split())))

graph.sort(key=lambda x:x[2])

answer = 0
cnt = 0
for start,end,dist in graph:
    if find_parent(parent,start) != find_parent(parent,end):
        union_parent(start,end,parent)
        answer += dist
        cnt += 1
        if cnt == v-1:
            break

print(answer)