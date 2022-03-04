# 대소문자 바꾸기: upper()와 lower()
print("# 대문자로 바꾸기")
a = "Hello Python"
print(a)
print(a.upper())

print("# 소문자로 바꾸기")
b = "Hello Python"
print(b)
print(b.lower())

# 문자열 공백 제거하기: strip()
# strip() : 문자열 양옆 공백 제거
# lstrip() : 문자열 왼쪽 공백 제거
# rstrip() : 문자열 오른쪽 공백 제거
# trim() : 모든 공백 제거

print("# 문자열 공백 제거")
input_a = """
    안녕하세요
문자열의 함수 알아보기
"""
print(input_a)
print(input_a.strip())

# 문자열 구성 파악하기
print("# 문자열의 구성 파악하기 : isOO()")
# alnum : 알파벳, 숫자
# alpha : 알파벳
# identifier : 식별자로 사용할 수 있는지
# decimal : 정수
# digit : 문자열이 숫자로 인식될 수 있는 것인지
# space : 공백으로만 구성되어 있는지
# lower : 소문자로만 구성되어 있는지
# upper : 대문자로만 구성되어 있는지

print("TrainA10".isalnum())
print("10".isdigit())

# 문자열 찾기: find(), rfind()
# find() : 왼쪽부터 찾아서 처음 등장하는 위치
# rfind() : 오른쪽부터 찾아서 처음 등장하는 위치

print("# 문자열 인덱스 찾기")
output_a = "안녕안녕하세요"
print(output_a)
print("find() : ", output_a.find("안녕"))
print("rfind() : ", output_a.rfind("안녕"))

# 문자열과 in 연산자. 문자열 내부에 어떤 문자열이 있는지 확인하려면 in 연산자 사용
print("# 문자열 in : '안녕' in '안녕하세요'")
print("안녕" in "안녕하세요")
print("# 문자열 in : '잘자' in '안녕하세요'")
print("잘자" in "안녕하세요")

# 문자열 자르기 split()
print('# 문자열 자르기 split() a.split(" ")')
a = "10 20 30 40 50"
print(a)
print(a.split(" "))
