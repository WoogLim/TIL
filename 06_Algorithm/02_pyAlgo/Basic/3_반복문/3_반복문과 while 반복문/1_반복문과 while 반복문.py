# 1. for문의 범위 지정 : range 자료형

# 1-1. 범위 range
# range(4) 0 ~ 3
# range(4, 8) 4 ~ 7
# range(4, 9, 2) 4 6 8
print("1-1. 범위 range()")

print(list(range(10)))
print(list(range(5, 10)))
print(list(range(0, 10, 2)))
print(list(range(0, 10 + 1)))
print(list(range(0, 10 // 2)))

print("\n1-2. for 반복문과 범위")

for i in range(5):
    print(str(i) + "= 반복 변수")
print()

for i in range(5, 10):
    print(str(i) + "= 반복 변수")
print()

for i in range(0, 10, 3):
    print(str(i) + "= 반복 변수")
print()

print("\n1-3. 리스트와 범위 조합하기")