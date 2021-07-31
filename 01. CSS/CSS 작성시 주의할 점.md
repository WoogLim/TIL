# 코드 중복의 최소화

```CSS
padding: 6px 16px;
border: 1px solid #446d88;
background #58a linear-gradient(#77a0bb, #58a);
border-radius: 4px;
box-shadow: 0 1px 5px gray;
color: white;
text-shadow: 0 -1px 1px #335166;
font-size: 20px;
line-height: 30px;
```

![image](https://user-images.githubusercontent.com/51357635/127741582-914d17da-9771-414b-826b-4be7c6896cb0.png)

---
- 만일 위에서 폰트 크기를 바꿀 경우 줄 간격 또한 같이 수정되어야 한다.
적합한 글꼴 크기를 알아내기 위해 서로 의존 하는 두 값을 여러번 변형해야 할 수 있다.

```css
font-size: 20px;
line-height: 1.5;
```
- 폰트 크기를 절대 값으로 쓰면 작업은 쉽지만 매번 변경해야 할 문제는 계속 다가온다.
- 따라서 이때는 %나 em을 사용하는 것이 좋다.
```css
font-size: 125%; /* 부모 컴포넌트 폰트 크기를 16px로 가정 */
line-height: 1.5;
```
![image](https://user-images.githubusercontent.com/51357635/127741775-5fee33e0-8116-4c2d-805d-abdfcde87a4d.png)
