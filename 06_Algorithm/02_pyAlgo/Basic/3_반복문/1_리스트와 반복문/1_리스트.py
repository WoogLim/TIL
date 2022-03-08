# 요소와 인덱스
print("# 요소와 인덱스")
list_a = [273, 32, 103, "문자열", True, False]
print(list_a[0])
print(list_a[1])
print(list_a[2])
print(list_a[-1])
print(list_a[-2])
print(list_a[1:3]) # 32 103
print(list_a[3][0]) # 요소가 문자열인 경우 이중으로 값을 가져올 수 있다.

# 리스트안에 리스트
print("# 리스트안에 리스트")
list_a = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
print(list_a[1])
print(list_a[1][1])