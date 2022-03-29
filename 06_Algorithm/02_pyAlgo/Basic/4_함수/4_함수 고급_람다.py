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

print("\n2. map() 함수와 filter() 함수")

# 함수를 매개변수로 전달하는 대표적인 파이썬의 표준 함수로 map() 함수와 filter()함수가 있다.

# map() 함수는 리스트의 요소를 함수에 넣고 리턴된 값으로 새로운 리스트를 구성해 주는 함수
# map(함수, 리스트)

# filter() 함수는 리스트의 요소를 함수에 넣고 리턴된 값이 True인 것으로, 새로운 리스트를 구성해주는 함수이다"
# filter(함수, 리스트)

# 함수를 선언
def power(item):
    return item * item
def under_3(item):
    return item < 3

# 변수를 선언
list_input_a = [1, 2, 3, 4, 5]

# map() 함수 사용
output_a = map(power, list_input_a)
print("# map() 함수의 실행결과")
print("map(power, list_input_a):", output_a)
print("map(power, list_input_a):", type(output_a))
print("map(power, list_input_a):", list(output_a))
print()

# filter() 함수 사용
output_b = filter(under_3, list_input_a)
print("# filter() 함수의 실행결과")
print("filter(under_3, list_input_a):", output_b)
print("filter(under_3, list_input_a):", type(output_b))
print("filter(under_3, list_input_a):", list(output_b)) # 리스트로 강제 변환

# map()의 경우 map class, filter()의 경우 filter class 인데. 이 object를 제너레이터라고 부른다.

print("\n3. 람다의 개념")

# lambda 매개변수: 리턴값

power = lambda x: x * x
under_3 = lambda x: x < 3

# 변수를 선언
list_input_a = [1, 2, 3, 4, 5]

# map() 함수 사용
output_a = map(power, list_input_a)
print("# map() 함수의 실행결과")
print("map(power, list_input_a):", output_a)
print("map(power, list_input_a):", type(output_a))
print("map(power, list_input_a):", list(output_a))
print()

# filter() 함수 사용
output_b = filter(under_3, list_input_a)
print("# filter() 함수의 실행결과")
print("filter(under_3, list_input_a):", output_b)
print("filter(under_3, list_input_a):", type(output_b))
print("filter(under_3, list_input_a):", list(output_b)) # 리스트로 강제 변환

print("\n 4. 인라인 람다")

# 변수 선언
list_input_a = [1, 2, 3, 4, 5]
output_a = map(lambda x: x * x, list_input_a)
print("# map() 함수의 실행결과")
print("map(power, list_input_a):", output_a)
print("map(power, list_input_a):", type(output_a))
print("map(power, list_input_a):", list(output_a))
print()

# filter() 함수 사용
output_b = filter(lambda x: x < 3, list_input_a)
print("# filter() 함수의 실행결과")
print("filter(under_3, list_input_a):", output_b)
print("filter(under_3, list_input_a):", type(output_b))
print("filter(under_3, list_input_a):", list(output_b)) # 리스트로 강제 변환
