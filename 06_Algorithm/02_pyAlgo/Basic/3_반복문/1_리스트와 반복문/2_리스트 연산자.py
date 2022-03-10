# 1. 리스트 연산자 : 연결(+), 반복(*), len()

# 리스트를 선언합니다.
print("1. 리스트 연산자 : 연결(+), 반복(*), len()")
list_a = [1, 2, 3]
list_b = [4, 5, 6]

# 출력합니다.
print("# 리스트")
print("list_a =", list_a)
print("list_B =", list_b)
print()

# 기본 연산자
print("# 리스트 기본 연산자")
print("list_a + list_b", list_a + list_b)
print("list_a * 3", list_a * 3)
print()

# 함수
print("# 길이 구하기")
print("len(list_a) =", len(list_a))

# 2. 리스트에 요소 추가하기 : append, insert
print("\n2. 리스트에 요소 추가하기 : append, insert")

# 리스트를 선언
list_a = [1, 2, 3]

# 리스트 뒤에 요소 추가 append
print("# append로 리스트 뒤에 요소 추가하기")
list_a.append(4)
list_a.append(5)
print(list_a)
print()

# 리스트 중간에 요소 추가
print("# insert로 리스트 중간에 요소 추가하기")
list_a.insert(0, 10) # 0번째 인덱스에 10 요소 추가
print(list_a)

# 한번의 여러 오소 추가
print("# 한번의 여러 오소 추가")
list_a = [1, 2, 3]
list_a.extend([4, 5, 6])
print(list_a)
print()

# 3. 리스트 연결 연산자와 요소 추가의 차이
print("\n3. 리스트 연결 연산자와 요소 추가의 차이")
print("# 리스트 연결 연산자와 요소 추가의 차이")
list_a = [1, 2, 3]
list_b = [4, 5, 6]

list_a + list_b # 비파괴적 처리. 원본값은 보존된다.
print(list_a) 
print(list_b) 
list_a.extend(list_b) # 파괴적 처리 원본 변경
print(list_a)

# 원본에 영향을 주는 insert, append, extend 함수를 파괴적 처리이다.

# 4. 리스트에 요소 제거하기
print("\n4. 리스트에 요소 제거하기")

# 인덱스로 제거하기: del, pop
print("# 인덱스로 제거하기: del, pop")

# del 리스트명[인덱스]
# 리스트명.pop(인덱스)

# 리스트 연결 연산자와 요소 추가의 차이

list_a = [0, 1, 2, 3, 4, 5]
print("# 리스트의 요소 하나 제거하기")

# 제거 방법[1] - del
del list_a[1]
print("del list_a[1]:", list_a)

# del 범위 지정
del list_a[:2]
print("del list_a[:2]:", list_a)

# 제거 방법[2] - pop()
list_a.pop(2)
print("pop(2):", list_a)

# 값으로 제거하기 : remove
print("# 값으로 제거하기 remove")
list_c = [1, 2, 1, 2]
list_c.remove(2)
print("list_c.remove(2):", list_c)
# [1, 1, 2] 앞쪽의 2만 제거된다. 모두 제거하기위에 while 사용 필요

# 모두 제거하기 : clear
print("# 모두 제거하기 : clear")
list_d = [0, 1, 2, 3, 4, 5]
list_d.clear()
print("list_d.clear():", list_d)

# 리스트 내부에 존재하는지 확인 : in, not in
list_a = [212, 32, 421, 12]
print("\n# 리스트 내부에 존재하는지 확인 : in, not in")
print("list_a:", list_a)
print("212 in list_a: ", 212 in list_a)
print("212 not in list_a: ", 212 not in list_a)