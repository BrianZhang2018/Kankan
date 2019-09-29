# https://github.com/BrianZhang2018/Pawns-Gaming-Algorithm
# 这答案缺东西, failed on rally healthy OA.

def solution(B):
    # write your code in Python 3.6
    pos_x = 0
    pos_y = 0
    # find "O" character from B
    for i in range(len(B)):
        for j in range(len(B[i])):
            if B[i][j] == "O":
                pos_x = i
                pos_y = j

    # if O is located in first line, return 0
    if pos_x-1 < 0:
        return 0

    # if O is located in end line
    elif pos_x + 1 > len(B):
        # if O is located in up-left, return 1
        if B[pos_x-1][pos_y-1] == "X":
            return 1
        # if O is located in up-right, return 1
        elif B[pos_x-1][pos_y+1] == "X":
            return 1
        # if O is located in up-left and up-right, return 2
        elif B[pos_x-1][pos_y-1]=="X" and B[pos_x-1][pos_y+1] == "X":
            return 2
    # if X is located in down status, return 0
    elif B[pos_x+1][pos_y-1] == "X" or B[pos_x+1][pos_y+1] == "X":
        return 0
    else:
        return 0

B = ['X....', '.X...', '..O..', '...X.', '.....'] 
print(solution(B))
print(111)