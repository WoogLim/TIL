# 문자열의 format() 함수
# 중괄호{} 를 포함한 문자열 뒤에 마침표(.)를 찍고 format() 함수를 사용하며 중괄호 개수와 format 함수내 매개변수 개수가 동일해야 함.
print("1. 문자열의 format() 함수")
# {} 기호를 format 함수 내 매개변수로 대체
string_ex = "{}".format(10)

print(string_ex)
print(type(string_ex))

# format() 함수의 다양한 형태
print("2. format() 함수의 다양한 형태")
# format() 함수로 숫자를 문자열로 변환하기
format_a = "{}만 원".format(5000)
format_b = "{} {} {}".format(3000, 4000, 5000)
format_c = "{} {} {}".format(1, "문자열", True)

print(format_a)
print(format_b)
print(format_c)

# {} 기호 개수가 format() 함수 매개변수보다 많으면 IndexError 예외 발생

# format() 함수의 다양한 기능
print("2. format() 함수의 다양한 기능")
print("2-1. 정수를 특정 칸에 출력")

# 정수
output_a = "{:d}".format(52)

# 특정 칸에 출력하기
output_b = "{:5d}".format(52)   # 5칸
output_c = "{:10d}".format(52)  # 10칸

# 빈칸을 0으로 채우기
output_d = "{:05d}".format(52)  # 양수
output_e = "{:05d}".format(-52) # 음수

print("# 기본")
print(output_a)
print("# 특정 칸에 출력하기")
print(output_b)
print(output_c)
print("# 빈칸을 0으로 채우기")
print(output_d)
print(output_e)

# 기호 붙여 출력하기
print("2-2. 기호 붙여 출력하기")

output_f = "{:+d}".format(52)   # 양수
output_g = "{:+d}".format(-52)  # 음수
output_h = "{: d}".format(52)   # 양수 : 기호 부분 공백
output_i = "{: d}".format(-52)  # 음수 : 기호 부분 공백

print("# 기호와 함께 출력하기")
print(output_f)
print(output_g)
print(output_h)
print(output_i)

# 조합해 보기
output_j = "{:+5d}".format(52)      # 기호를 뒤로 밀기: 양수
output_k = "{:+5d}".format(-52)     # 기호를 뒤로 미기: 음수
output_l = "{:=+5d}".format(52)     # 기호를 앞으로 밀기 : 양수
output_m = "{:+05d}".format(-52)    # 기호를 앞으로 밀기: 음수
output_n = "{:+05d}".format(52)     # 0으로 채우기: 양수
output_o = "{:+05d}".format(-52)    # 0으로 채우기: 음수

print("# 조합하기")
print(output_j)
print(output_k)
print(output_l)
print(output_m)
print(output_n)
print(output_o)

# float 자료형의 경우

print("# float 자료형 기본")

output_p = "{:f}".format(52.273)
output_q = "{:15f}".format(52.273)
output_r = "{:+15f}".format(52.273)
output_s = "{:+015f}".format(52.273)

print(output_p)
print(output_q)
print(output_r)
print(output_s)

# 소수점 아래 자리수 지정

print("# 소수점 아래 자리수 지정")

output_t = "{:15.3f}".format(52.273)
output_u = "{:15.2f}".format(52.273)
output_v = "{:15.1f}".format(52.273)

print(output_t)
print(output_u)
print(output_v)

# 의미 없는 소수점 제거 0과 0.0 같은 의미없는 0을 제거한 후 출력 하고싶은 경우
print("# 의미 없는 소수점 제거하기")
output_w = 52.0
output_x = "{:g}".format(output_w)

print(output_w)
print(output_x)