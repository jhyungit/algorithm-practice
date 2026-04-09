import sys
input = sys.stdin.readline

n = int(input())
cost = [list(map(int, input().split())) for _ in range(n)]
status = input().strip()
p = int(input())

INF = float('inf')
dp = [INF] * (1 << n)

# 초기 상태: 현재 켜져있는 발전소들 mask만들기
init = 0
for i in range(n):
    if status[i] == 'Y':
        init |= (1 << i)

dp[init] = 0

for mask in range(1<<n):
    if dp[mask] == INF:
        continue
    for i in range(n):
        if not (mask & (1 << i)): # i가 꺼져있으면 스킵
            continue
        for j in range(n):
            if mask & (1 << j): # j가 이미 켜져있으면 스킵
                continue
            new_mask = mask | (1 << j)
            dp[new_mask] = min(dp[new_mask], dp[mask] + cost[i][j])

# P개 이상 켜진 mask 중 최솟값
ans = INF
for mask in range(1<<n):
    if bin(mask).count('1') >= p:
        ans = min(ans, dp[mask])

print(ans if ans != INF else -1)