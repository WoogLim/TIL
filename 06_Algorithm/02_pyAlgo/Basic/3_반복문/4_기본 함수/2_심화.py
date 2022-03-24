print("# 구문 내부에 여러 줄 문자열을 사용했을 때 문제점")

# 변수 선언
number = 10

# if 조건문으로 홀/짝 구분
# 출력문도 들여쓰기가 된다. 그렇다고 한줄로 쓰기엔 가독성이 떨어진다.
if number % 2 == 0:
    print("""\
        입력한 문자열은 {}입니다.
        {}는(은) 짝수입니다.""".format(number,number))

else:
    print("""\
        입력한 문자열은 {}입니다.
        {}는(은) 홀수입니다.""".format(number,number))
    
print("\n# 괄호로 문자열 연결하기")

# 변수 선언 ! 튜플이 아님. 튜플을 문자열마다 쉼표로 구분짓는다.
test = (
    "이건 튜플이 아닙니다."
    "그냥 문자열 연결입니다."
)

# 출력
print("test:", test)
print("type(test):", type(test))

print("\n# 여러 줄 문자열과 if 구문을 조합할 때 문제해결(1)")

# 변수 선언
number = 10

# if 조건문으로 홀/짝 구분
if number % 2 == 0:
    print((
        "입력한 문자열은 {}입니다.\n"
        "{}는(은) 짝수입니다."
    ).format(number, number))
else:
    print((
        "입력한 문자열은 {}입니다.\n"
        "{}는(은) 홀수입니다."
    ).format(number, number))
    
print("\n# 리스트의 요소를 문자열로 연결")

print("::".join(["1", "2", "3", "4", "5"]))

print("\n#여러 줄 문자열과 if 구문을 조합할 때 문제해결(2)")

# 변수 선언
number = 10

# if 조건문으로 홀/짝 구분
if number % 2 == 0:
    print("\n".join([
        "입력한 문자열은 {}입니다.",
        "{}는(은) 짝수입니다."
    ]).format(number, number))
else:
    print("\n".join([
        "입력한 문자열은 {}입니다.",
        "{}는(은) 홀수입니다."
    ]).format(number, number))
    
print("\n# 이터레이터")

# 반복문은 다음과 같다. for 반복자 in 반복할 수 있는 것 > 여기에서 "반복할 수 있는 것"을 이터러블이라고 한다.
# 이터러블 중 next() 함수를 적용해 하나씩 꺼내는 요소를 이터레이터라고 한다.

print("\n# reversed()함수와 이터레이터")

# 변수 선언
numbers = [1, 2, 3, 4, 5, 6]
r_num = reversed(numbers)

# reversed_numbers를 출력
print("reversed_numbers :", r_num)
print(next(r_num))
print(next(r_num))
print(next(r_num))
print(next(r_num))
print(next(r_num))
print(next(r_num))
print(next(r_num))
