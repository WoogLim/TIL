print("\n".join(["함수의 기본","def 함수 이름():","    문장"]))

print("\n# 기본적인 함수")

def print_3_times():
    print("안녕하세요")
    print("안녕하세요")
    print("안녕하세요")
    
print_3_times()

print("\n# 매개변수의 기본")

# 매개변수를 하나라도 빼먹는다면 missing required positional argument 에러 발생
def print_n_times(value, n):
    for i in range(n):
        print(value)
        
print_n_times("안녕하세요", 2)

print("\n# 가변 매개변수")
print("\n".join(["def 함수 이름(매개변수, 매개변수, ... , *가변 매개변수):","    문장"]))

def print_n_times(n, *values):
    # n번 반복
    for i in range(n):
        #values는 리스트처럼 활용
        for value in values:
            print(value)
        #줄바꿈
        print()
        
# 함수 호출 파이썬에서는 가변 매개변수 뒤에 일반 매개변수를 두지 않는다.
# 매개변수 n을 앞으로 옮기고 가변매개변수를 뒤에 추가한다. 이 가변 매개변수는 리스트처럼 이용하면 된다.
print_n_times(2, "안녕하세요", "파이썬 프로그래밍")

print("\n# 기본 매개변수")
# 가변 매개변수 뒤에는 일반 매개변수가 올 수 없지만 기본 매개변수(매개변수=값)은 올 수 있다.
# 매개변수를 입력하지 않았을 경우 매개변수에 들어가는 값이 기본값이다.
def print_n_times(value, n=2):
    # n번 반복
    for i in range(n):
        print(value)
        
# 함수 호출
print_n_times("안녕하세요")

print("\n# 키워드 매개변수")
# 가변, 기본 매개변수를 같이 사용하는 경우

print("1. 기본 매개변수가 가변 매개변수보다 앞에 올 때")
print("기본 매개변수는 가변 매개변수 앞에 써도 의미가 없다.")
# def print_n_times(n=2, *values):
#     for i in range(n):
#         # values는 리스트처럼 활용
#         for value in values:
#             print(value)
#         print()
        
# print_n_times("안녕하세요", "즐거운", "파이썬 프로그래밍")
# n자리에 안녕하세요가. 가변 매개변수로 즐거운 파이썬 프로그래밍.
# range 매겨변수로 숫자만 들어갈 수 있으므로 에러가 발생한다.

print("2. 가변 매개변수가 기본 매개변수보다 앞에 올 때")

def print_n_times(*values, n=2):
    # n번 반복
    for i in range(n):
        # values는 리스트처럼 활용
        for value in values:
            print(value)
        # 단순한 줄바꿈
        print()

# 함수를 호출합니다. 보다 시피 가변 매개변수와 기본 매개변수는 같이 쓸 수 없다.
# 같이 사용할 방법은 키워드 매개변수를 이용해야한다.
print_n_times("안녕하세요", "즐거운", "파이썬 프로그래밍", 3)

print("#. 키워드 매개변수 예제")
# 다시 print()함수의 기본 형태를 보자.
# print(value, ..., sep=' ', end ='\n', file=sys.stdout, flush=False)
# value를 여러 개 입력할 수 있으므로 가변 매개변수를 앞에 두고, 뒤에 기본 매개변수들이 들어가 있는 형태이다.
# 매개변수 이름을 직접적으로 지정해서 가변, 기본 매개변수를 같이 사용할 수 있다.

# while 반복문 이용
# while True:
    # "."을 출력합니다.
    # 기본적으로 end가 "\n"이라 줄바꿈이 일어나는데,
    # 빈 문자열 ""로 바꿔서 줄바꿈이 일어나지 않게 한다.
    # print(".", end="")

# ["안녕하세요.", "즐거운", "파이썬 프로그래밍"]을 세 번 출력하려면 다음과 같이 매개변수 이름을 직접적으로 지정해 값을 입력해야한다.
def print_n_times(*values, n=2):
    # n 번 반복
    for i in range(n):
        #values는 리스트처럼 활용
        for value in values:
            print(value)
        print()
        
# 함수 호출. 다음과 같이 기본 매개변수에 키워드 매개변수를 지정해서 입력한다.
print_n_times("안녕하세요", "즐거운", "파이썬 프로그래밍", n=3)

print("# 기본 매개변수 중에서 필요한 값만 입력하기")

print("\n1. 여러 함수 호출 형태")

def test(a, b=10, c=100):
    print(a + b + c)
    
# 1) 기본 형태
test(10, 20, 30)
# 2) 키워드 매개변수로 모든 매개변수를 지정한 형태
test(a=10, b=100, c=200)
# 3) 키워드 매개변수로 모든 매개변수를 마구잡이로 지정한 형태
test(c=10, a=100, b=200)
# 4) 키워드 매개변수르 일부 매개변수만 지정한 형태
test(10, c=200)

# 해설. test()함수
# 첫 번째 매개변수 a는 일반 매개변수로 해당 위치에 반드시 입력해야한다. (1번)
# 단 일반 매개변수이지만 키워드 매개변수 처럼 이용할 수 있다. (2번 3번)
# 3번 처럼 키워드를 지정하는 경우 마구잡이로 지정해도 된다.
# 4번 처럼 키워드로 이미 함수에서 지정된 경우 생략이 가능하다.

print("# 리턴을 사용하는 기본적인 함수의 활용")

print("\n1. 범위 내부에 정수를 모두 더하는 함수")
# 함수 선언
def sum_all(start, end):
    # 변수 선언
    output = 0
    # 반복문을 돌려 숫자 더하기
    for i in range(start, end + 1):
        output += i
    # 리턴
    return output

# 함수 호출
print("0 to 100:", sum_all(0, 100))
print("0 to 1000:", sum_all(0, 1000))
print("50 to 100:", sum_all(50, 100))
print("500 to 1000:", sum_all(500, 1000))
