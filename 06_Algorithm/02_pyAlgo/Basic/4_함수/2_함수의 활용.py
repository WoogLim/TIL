print("# 재귀함수")

print("1. 재귀 함수를 이용해 팩토리얼 연산")

# 함수 선언
def factorial(n):
    # n이 0이라면 1 리턴
    if n == 0:
        return 1
    # n이 0이 아니라면 n * (n-1)!을 리턴
    else:
        return n * factorial(n - 1)
    
# 함수 호출
print("1!:", factorial(1)) # 1
print("2!:", factorial(2)) # 2 1
print("3!:", factorial(3)) # 3 2 1
print("4!:", factorial(4)) # 4 3 2 1
print("5!:", factorial(5)) # 5 4 3 2 1

print("\n# 재귀함수의 문제")

print("1. 재귀 함수로 피보나치 수열을 구현")

# 변수 선언
counter = 0

# 함수 선언
def fibonacci(n):
    # 어떤 피보나치 수를 구하는지 출력
    print("fibonacci({})를 구합니다".format(n))
    global counter # 함수 외부 변수를 참조. 파이썬은 함수 내부에서 함수 외부의 변수 참조를 하지 못한다.
    counter += 1
    # 피보나치 수를 구한다.
    if n == 1:
        return 1
    if n == 2:
        return 1
    else:
        return fibonacci(n - 1) + fibonacci(n - 2)

# 함수 호출
fibonacci(10)
print("---")
print("fibonacci(10) 계산에 활용된 덧셈 횟수는 {}번".format(counter))

# 트리 안의 각각의 지점을 노드, 노드 중 가장 마지막 단계 노드를 리프라고 했을 때
# 트리 내부 각각 노드 값을 계산하려면 덧셈을 한 번씩 해야한다. 하지만 노드가 한번에 두 개씩 달려있다면
# 현재 재귀 함수에서는 한번 구했던 값이라도 다시 계산한다. 때문에 계산 횟수가 기하급수적으로 늘어난다.

print("\n# 재귀 함수의 올바른 사용. 메모화")

# 메모 변수 생성
dictionary = {
    1: 1,
    2: 1
}

# 함수 선언
def fibonacci(n):
    if n in dictionary:
        # 메모가 되어 있으면 메모된 값을 리턴
        return dictionary[n]
    else:
        # 메모가 되어 있지 않으면 값을 구함
        output = fibonacci(n - 1) + fibonacci(n - 2)
        dictionary[n] = output
        return output
# 함수 호출
print("fibonacci(10):", fibonacci(10))
print("fibonacci(20):", fibonacci(20))
print("fibonacci(30):", fibonacci(30))
print("fibonacci(40):", fibonacci(40))
print("fibonacci(50):", fibonacci(50))

# 딕셔너리를 사용해 한 번 계산한 값을 저장한다. 이를 메모한다고 표현.
# 값이 메모되어있다면 처리를 수행하지 않고 곧바로 메모된 값을 돌려주며 코드 속도를 빠르게한다.
# 메모화 사용시 곧바로 결과를 출력할 정도로 속도가 빨라진다. 즉 재귀함수는 메모화와 같이 사용해야한다.

