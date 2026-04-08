import sys
input = sys.stdin.readline

def solution():
    dp = [[0] * (k+1) for _ in range(n + 1)]

    # max(바로 위, 현재 아이템 가치)
    for item in range(1, n + 1): # 선택 가능한 아이템 범위
        weight, value = products[item]
        for limit in range(k + 1):
            # 아이템 무게 <= 제한 무게이면
            if weight <= limit:
                dp[item][limit] = max(dp[item-1][limit], dp[item-1][limit - weight] + value)
            else:
                dp[item][limit] = dp[item-1][limit]
    
    print(dp[n][k])

# 물건 N개, 무게 W, 가치 V
# 제한 무게 K
n, k = map(int, input().split())
products = [None]
for _ in range(n):
    w, v= map(int, input().split())
    products.append((w,v))
solution()