# 리스트가 인덱스를 기반으로 값을 저장하는 것 이라면. List []
# 딕셔너리는 키를 기반으로 값을 저장하는 방식이다. Map {}

# 1. 딕셔너리 선언하기

# 딕셔너리 요소에 접근하기
print("\n # 딕셔너리 요소에 접근하기")
name = "name"
dictionary = {
    name: "7D 건조 망고",
    "type": "당절임",
    "ingredient": ["망고", "설탕", "메타중아황상나트륨", "치자황색소"],
    "origin" : "필리핀"
}

# 출력
print("name:", dictionary["name"])
print("type:", dictionary["type"])
print("ingredient:", dictionary["ingredient"])
print("ingredient:", dictionary["ingredient"][1])
print("origin:", dictionary["origin"])
print()

# 값 변경
dictionary["name"] = "8D 건조 망고"
print("name:", dictionary["name"])

# 딕셔너리에 값 추가/제거
dictionary["price"] = 5000
del dictionary["ingredient"]
print(dictionary)

## Key Error 예외. 리스트에 길이를 넘는 인덱스 접근시 IndexError 에러 발생.
## 딕셔너리도 존재하지 않는 키에 접근시 Key Error가 발생한다.

# 딕셔너리 내부에 키가 존재하는지 확인하고 접근하기
print("\n2. 딕셔너리 내부에 키가 존재하는지 확인하고 접근하기")
dictionary_b = {
    "name": "7D 건조 망고",
    "type": "당절임",
    "ingredient": ["망고", "설탕", "메타중아황상나트륨", "치자황색소"],
    "origin" : "필리핀"
}

# 사용자로부터 입력 받는다.
key = input("> 접근하고자 하는 키: ")

# 출력
if key in dictionary:
    print(dictionary[key])
else:
    print("dictionary[{}] 존재하지 않는 키에 접근하고 있습니다.".format(key))
    
# 존재하지 않는 키에 접근한다.
value = dictionary.get("존재하지 않는 키") # get 함수로 존재하지 않는 키값을 가져오면 KeyError대신 None이 반환된다.
print("값:", value)

if value == None:
    print("존재하지 않는 키에 접근했습니다.")
    
# for 반복문: 딕셔너리와 함께 사용
# for 키 변수 in 딕셔너리:

# 1. for 반복문과 딕셔너리
print("\n# 1. for 반복문과 딕셔너리")

# 딕셔너리 선언
dictionary_c = {
    "name": "7D 건조 망고",
    "type": "당절임",
    "ingredient": ["망고", "설탕", "메타중아황상나트륨", "치자황색소"],
    "origin" : "필리핀"
}

# for 반복문 사용
for key in dictionary_c:
    print(key, ":", dictionary_c[key])