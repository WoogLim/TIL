# 문자열을 숫자로 변환
import string


print("1. 문자열을 숫자로 변환")
string_a = input("입력 A > ")
int_a = int(string_a)

string_b = input("입력 B > ")
int_b = int(string_b)

print("문자열 자료", string_a + string_b)
print("숫자 자료", int_a + int_b)

# float(), str() 도 존재함. int > float, int | float > str, str > int | float X
