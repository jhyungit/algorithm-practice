# 완전탐색

def rotate(key, M):
    temp  = [[0] * M for _ in range(M)]
    
    for r in range(M):
        for c in range(M):
            if key[r][c] == 1:
                temp[c][M-1-r] = 1
    return temp

def possible(key, lock):
    M = len(key)
    N = len(lock)
    for dr in range(-(M-1), N):
        for dc in range(-(M-1),N):
            board = [row[:] for row in lock]
            success = True
            for i in range(M):
                for j in range(M):
                    if key[i][j] == 1:
                        r, c = i + dr, j +dc
                        if r < 0 or r >= N or c < 0 or c >= N:
                            continue
                        if board[r][c] == 1:
                            success = False
                            break
                        board[r][c] = 1
                if not success:
                    break
            if success and all(board[r][c] == 1 for r in range(N) for c in range(N)):
                return True
    return False

def solution(key, lock):
    
    rot_key = key
    # 오른쪽 90도 회전 4번 다 확인
    for _ in range(4):
        # 첫 회전
        rot_key = rotate(rot_key, len(key))
        if possible(rot_key, lock):
            return True
    return False