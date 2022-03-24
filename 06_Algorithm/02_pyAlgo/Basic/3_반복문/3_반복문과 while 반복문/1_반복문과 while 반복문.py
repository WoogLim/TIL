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

# 리스트 선언
array = [273, 32, 103, 57, 52]
value = 57
# 리스트에 반복문 적용
for element in array:
    print(element);

# 몇 번째 인덱스인지
for i in range(len(array)):
    print("{}번째 반복: {}".format(i, array[i]))
    
# 반대로 반복
for i in range(4, 0 - 1, -1):
    print("현재 반복 변수: {}".format(i))
    
for i in reversed(range(5)):
    print("현재 반복 변수: {}".format(i))

print("\n2. while 반복문")
print("\n2-1. 상태를 기반으로 반복하기(해당하는 값 모두 제거하기)")

# 변수를 선언
list_test = [1, 2, 1, 2]
value = 2

# list_test 내부에 value가 있다면 반복
# list_test 를 순회하면서 해당 값이 있는 경우 삭제한다.
print("list : {}".format(list_test), "value : {}".format(value))

while value in list_test:
    list_test.remove(value)
    
# 출력합니다.
print(list_test)

print("\n2-2. 시간을 기반으로 반복(5초 동안 반복)")
# 시간을 기반으로 반복하는 경우 유닉스 타임에대해 알아야 한다. 유닉스 타임이란 세계 표준시로, 1970년 1월 1일 0시 0분 0초를 기준으로
# 몇 초가 지났는지를 정수로 나타낸 것이다. 파이썬에서는 유닉스 타임을 구할 때 다음과 같은 코드를 사용한다.

# 먼저 시간 관련 기능을 가져온다.
import time

# 변수를 선언헌다.
number = 0

# 5초 동안 반복 - 시간을 기반으로 조건을 거는 경우 while반복문을 활용한다. 주로 통신시 사용한다.
target_tick = time.time() + 5
while time.time() < target_tick:
    number += 1
    
# 출력합니다.
print("5초 동안 {}번 반복했습니다.".format(number))

print("\n2-3. break/continue 키워드")

# 1. break
# 변수 선언
i = 0

# 무한 반복
while True:
    # 몇 번째 반복인지 출력
    print("{}번째 반복문입니다.".format(i))
    i = i + 1
    # 반복을 종료합니다.
    input_text = input("> 종료하시겠습니까?(Y/N): ")
    if input_text in ["Y", "y"]:
        print("반복을 종료합니다.")
        break
    
# 2. continue
# 변수 선언
numbers = [5, 15, 6, 20, 7, 25]

# 반복을 돌린다.
for number in numbers:
    # number가 10보다 작으면 다음 반복으로 넘어감
    # continue를 사용하므로 해당 if문에서만 반복됨
    if number < 10:
        continue
    # 출력한다.
    print(number)