import enum


print("+ 리스트에 적용할 수 있는 기본 함수: min(), max(), sum()")
print("+ 리스트 뒤집기: reversed()")
print("+ 현재 인덱스가 몇 번째인지 확인하기: enumerate() ")
print("+ 딕셔너리로 쉽게 반복문 작성하기: items() ")
print("+ 리스트 안에 for문 사용하기: 리스트 내포")

print("\n+ 리스트에 적용할 수 있는 기본 함수: min(), max(), sum()")
# min : 리스트 내부에서 최솟값 검색
# max : 리스트 내부에서 최댓값 검색
# sum : 리스트 내부에서 값을 모두 더하기

number = [103, 52, 273, 32, 77]
print("number: {}".format(number))
print("min(): {}".format(min(number)))
print("max(): {}".format(max(number)))
print("sum(): {}".format(sum(number)))

print("\n+ 리스트 뒤집기: reversed()")

# 리스트 선언 후 뒤집기
list_a = [1, 2, 3, 4, 5]
list_reversed = reversed(list_a)

# 출력
print("# reversed() 함수")
print("reversed([1, 2, 3, 4, 5]):", list_reversed)
print("list(reversed([1, 2, 3, 4, 5])):", list(list_reversed))
print()

# 반복문 적용
print("# reversed() 함수와 반복문")
print("for i in reversed([1, 2, 3, 4, 5]):")
for i in reversed(list_a):
    print("-", i)
    
# 제너레이터
print("\n! 제너레이터")
temp = reversed([1, 2, 3, 4, 5, 6])

# 두 번째 반복문은 출력되지 않는다. reversed 함수의 결과가 제너레이터이기 때문이다.
# reversed함수와 반복문을 조합할 때 함수의 결과를 여러 번 활용하지 않고 for 구문 내부에 reversed() 함수를 곧바로 넣어 사용한다.
for i in temp:
    print("첫 번째 반복문: {}".format(i))
for i in temp:
    print("두 번째 반복문: {}".format(i))

print("\n! 확장 슬라이싱")

# 리스트를 뒤집는 추가 방법으로 확장 슬라이싱 ::-1이 있다.

print(list_a)
print(list_a[::-1])
print("안녕하세요"[::-1])

print("\n+ 현재 인덱스가 몇 번째인지 확인하기: enumerate() ")

# 리스트 가정
example_list = ["요소A", "요소B", "요소C"]

# 단순 출력
print("# 단순 출력")
print(example_list)
print()

# enumerate() 함수 적용해 출력
print("# enumerate() 함수 적용 출력")
print(enumerate(example_list))
print()

# list() 함수로 강제 변환해 출력
print("# list() 함수로 강제 변환 출력")
print(list(enumerate(example_list)))
print()

# for 반복문과 enumerate() 함수 조합해 사용
print("# 반복문과 조합하기")
for i, value in enumerate(example_list):
    print("{}번째 요소는 {}입니다.".format(i, value))
    
print("\n+ 딕셔너리로 쉽게 반복문 작성하기: items() ")

# 변수 선언
example_dictionary = {
    "키A" : "값A",
    "키B" : "값B",
    "키C" : "값C"
}

# 딕셔너리의 items() 함수 결과 출력하기
print("# 딕셔너리의 items() 함수")
print("items():", example_dictionary.items())
print()

# for 반복문과 items() 함수 조합해서 사용하기
print("# 딕셔너리의 items() 함수와 반복문 조합하기")

for key, element in example_dictionary.items():
    print("dictionary[{}] = {}".format(key, element))
    
