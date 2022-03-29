# 파이썬은 함수를 조금 더 편하게 사용할 수 있도록 튜플과 람다를 지원한다.

# 튜플 : 함수와 함께 많이 사용되는 리스트와 비슷한 자료형. 리스트와 다른 점은 한번 결정된 요소는 바꿀 수 없다는 것
# 람다 : 매개변수로 함수를 전달하기 위해 함수 구문을 작성하는 것이 번거롭고, 코드 공간 낭비라는 생각이 들 때 함수를 간단하고 쉽게 선언

print("# 튜플")

# (데이터, 데이터, 데이터, ...)
# 리스트는 [] 튜플은 ()

# 요소를 하나만 가지는 튜플의 경우 (데이터, ) 로 명시해야한다.

print("\n1. 튜플의 특징")
tuple_test = (10, 20, 30)

print(tuple_test[0])
print(tuple_test[1])
print(tuple_test[2])
# tuple_test[2] = 40  -  한번 설정된 튜플은 바꿀 수 없다. 에러 발생

print("\n2. 리스트와 튜플의 특이한 사용")

# 리스트와 튜플의 특이한 사용
[a, b] = [10, 20]
(c, d) = (10, 20)

# 출력
print("a:", a)
print("b:", b)
print("c:", c)
print("d:", d)

print("\n3. 괄호가 없는 튜플")

# 괄호가 없는 튜플
tuple_test = 10, 20, 30, 40
print("# 괄호가 없는 튜플의 값과 자료형 출력")
print("tuple_test:", tuple_test)
print("type(tuple_test):", type(tuple_test))
print()

# 괄호가 없는 튜플 활용
a, b, c = 10, 20, 30
print("# 괄호가 없는 튜플을 활용한 할당")
print("a:", a)
print("b:", b)
print("c:", c)

print("\n4. 변수의 값을 교환하는 튜플")
a, b = 10, 20

print("# 교환 전 값")
print("a:", a)
print("b:", b)
print()

# 값을 교환
a, b = b, a

print("# 교환 후 값")
print("a:", a)
print("b:", b)
print()

print("# 튜플과 함수")

# 튜플은 함수의 리턴으로 많이 사용한다. 여러 개의 값을 리턴하고 할당할 수 있기 때문이다.
print("\n1. 여러 개의 값 리턴하기")

# 함수 선언
def test():
    return (10, 20)

# 여러 개의 값을 리턴
a, b = test()

# 출력합니다.
print("a:", a)
print("b:", b)

# enumertae() 함수와 items() 함수를 사용하면 인덱스와 값을 동시에 받을 수 있었는데 이 함수들이 튜플을 리턴하기 때문이다.
# 몫과 나머지를 구하는 divmod() 함수도 튜플을 리턴한다. 몫과 나머지를 동시에 받을 수 있다.