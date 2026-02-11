from itertools import combinations

L, C = map(int, input().split())
alpha = list(input().split())
alpha.sort()
answer = []

for c in combinations(alpha, L):
    n, m = 0, 0 # 모음, 자음 개수
    
    for a in c:
        if a in "aeiou":
            n += 1
        else:
            m += 1
    if n >= 1 and m >= 2:
        print("".join(c))