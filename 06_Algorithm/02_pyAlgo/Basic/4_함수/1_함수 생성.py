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