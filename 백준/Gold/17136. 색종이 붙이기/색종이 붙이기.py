import sys
input = sys.stdin.readline

board = []
for _ in range(10):
    board.append(list(map(int, input().split())))

paper = [0, 5, 5, 5, 5, 5] # 남은 색종이 개수
answer = float('inf')

def find_first_one():
    for r in range(10):
        for c in range(10):
            if board[r][c] == 1:
                return (r,c)
    return None

def can_attach(r, c, size):
    if r + size > 10 or c + size > 10: # 범위 벗어나면
        return False
    
    for i in range(r, r + size):
        for j in range(c, c + size):
            if board[i][j] != 1:
                return False

    return True

def attach(r, c, size, num):
    for i in range(r, r + size):
        for j in range(c, c + size):
            board[i][j] = num

def dfs(used): # used는 색종이 개수
    global answer

    # 가지치기
    if used >= answer:
        return

    pos = find_first_one() # 왼쪽 위부터 1 찾기
    if pos is None:
        answer = min(answer, used)
        return

    r, c = pos

    for size in range(5, 0, -1):
        if paper[size] > 0 and can_attach(r, c, size): #  종이가 남아 있고 and 붙일 수 있으면
            paper[size] -= 1
            attach(r, c, size, 0) # 붙이기, 0으로 바꿈

            dfs(used + 1)

            attach(r, c, size, 1) # 원상 복구, 1로 바꿈
            paper[size] += 1
    
dfs(0)
print(-1 if answer == float('inf') else answer)