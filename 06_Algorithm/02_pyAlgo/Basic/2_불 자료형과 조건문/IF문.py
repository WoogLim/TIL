# 조건문 return 값은 Boolean
print("# 조건문")
print("가방" == "가방")
print("가방" != "하마")
print("가방" <  "하마")
# 사전순으로 판별
print("가방" >  "하마")

# 범위 조건문
print("# 범위 조건문 n < 30 < n")
x = 25
print(10 < x < 30)
print(40 < x < 60)

# Boolean의 연산
# not or and
print("# Boolean의 연산")
print("not True : ", not True)
print("not False : ", not False)

# 조건문의 기본 사용 IF
print("# IF문")

# 입력 받기
# number = input("정수 입력> ")
# number = int(number)
number = 5

# 양수 조건
if number > 0:
    print("양수입니다.")
    
# 음수 조건
if number < 0:
    print("음수입니다.")
    
# 0 조건
if number == 0:
    print("0입니다.")
    
# 날짜/시간 라이브러리 활용하기

print("# 날짜/시간을 한 줄로 출력하기")
import datetime

# 현재 날짜/시간 가져오기
now = datetime.datetime.now()

# 출력 다른 언어로는 월이 0부터 시작이지만 파이썬은 1부터 시작
print("{}년 {}월 {}일 {}시 {}분 {}초".format(
    now.year,
    now.month,
    now.day,
    now.hour,
    now.minute,
    now.second
))

# 오전 오후를 구분하는 프로그램
print("# 오전, 오후를 구분하는 프로그램")

if now.hour < 12:
    print("현재 시간은 {}시로 오전입니다.".format(now.hour))
    
if now.hour >= 12:
    print("현재 시간은 {}시로 오후입니다.".format(now.hour))
    
# 계절 구분
print("# 계절 구분")

if 3 <= now.month <= 5:
    print("이번 달은 {}월로 봄입니다!".format(now.month))

if 6 <= now.month <= 9:
    print("이번 달은 {}월로 여름입니다!".format(now.month))

if 10 <= now.month <= 11:
    print("이번 달은 {}월로 가을입니다!".format(now.month))

if now.month == 12 or now.month <= 2:
    print("이번 달은 {}월로 겨울입니다1".format(now.month))
    
# 끝자리로 짝수와 홀수 구분
print("# 끝자리로 짝수와 홀수 구분")

# number = input("정수 입력 > ")
number = "115"

# 마지막 자리 숫자 추출
last_character = number[-1]

# 숫자로 변환
last_number = int(last_character)

# 짝수 확인. 줄이 너무 긴 경우 이스케이프 \ 기호를 입력하고 줄바꿈해 코드 입력할 것
if last_number == 0 \
    or last_number == 2 \
    or last_number == 4 \
    or last_number == 6 \
    or last_number == 8:
    print("짝수입니다.")
    
if last_number == 1 \
    or last_number == 3 \
    or last_number == 5 \
    or last_number == 7 \
    or last_number == 9:
    print("홀수입니다.")
    
# in 문자열 연산자 이용해 구분
print("# in 연산자 이용")

# 짝수 조건
if last_character in "02468":
    print("짝수입니다.")

if last_character in "13579":
    print("홀수입니다.")
    
# 이외에 나머지 연산으로도 구할 수 있다.