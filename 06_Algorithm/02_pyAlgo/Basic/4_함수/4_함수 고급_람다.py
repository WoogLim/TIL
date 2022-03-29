# 요즘 프로그래밍 언어는 함수라는 '기능'을 매개변수로 전달하는 코드를 사용한다.

print("# 람다의 특징")
print("\n1. 함수의 매개 변수로 함수 전달하기")

# 매개변수로 받은 함수를 10번 호출하는 함수
def call_10_times(func):
    for i in range(10):
        func()
        
# 간단히 출력하는 함수
def print_hello():
    print("안녕하세요")
    
# 조합하기
call_10_times(print_hello) # 매개변수로 함수를 전달.

print("\n2. filter() 함수와 map() 함수")
