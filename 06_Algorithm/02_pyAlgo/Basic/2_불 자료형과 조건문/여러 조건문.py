# else 조건문
number = int("15")

if number % 2 == 0:
    print("짝수입니다.")
else :
    print("홀수입니다.")
    
# elif 구문
import datetime

now = datetime.datetime.now()
month = now.month

if 3 <= month <= 5:
    print("현재는 봄입니다.")
elif 6 <= month <= 8:
    print("현재는 여름입니다.")
elif 9 <= month <= 11:
    print("현재는 가을입니다.")
else:
    print("현재는 겨울입니다.")
    
# Flase로 변환되는 값
print("# if 조건문에 0 넣기")
if 0:
    print("0은 True로 변환됩니다.")
else:
    print("0은 False로 변환됩니다.")
print()

print("# if 조건문에 빈 문자열 넣기")
if "":
    print("빈 문자열은 True로 변환됩니다.")
else:
    print("빈 문자열은 False로 변환됩니다.")
    
# pass 키워드 미구현 들여쓰기 부분에 넣어 나중에 구현할 것임을 명시
# 예시 1 나중에 구현하려고 비워 둔 구문
number = "3"
number = int(number)

# 조건문 사용
if number > 0:
    # 양수일 때 : 아직 미구현 상태
    0
else:
    # 음수일 때 : 아직 미구현 상태
    0

print("# pass 키워드")
number = "2"
number = int(number)

if number > 0:
    # 양수일 때 : 아직 미구현 상태입니다.
    # pass
    # raise : 아직 구현하지 않는 부분이라는 의미의 에러를 보내 콘솔창에 표시
    raise NotImplementedError 
else:
    # 음수일 때 : 아직 미구현 상태입니다.
    # pass
    raise NotImplementedError

