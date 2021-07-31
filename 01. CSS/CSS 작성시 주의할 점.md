# 코드 중복의 최소화

### 1. 크기가 변경되는 경우 비례하게 나타내는 법

```CSS
padding: 6px 16px;
border: 1px solid #446d88;
background: #58a linear-gradient(#77a0bb, #58a);
border-radius: 4px;
box-shadow: 0 1px 5px gray;
color: white;
text-shadow: 0 -1px 1px #335166;
font-size: 20px;
line-height: 30px;
```

![image](https://user-images.githubusercontent.com/51357635/127741582-914d17da-9771-414b-826b-4be7c6896cb0.png)

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

- 이때 폰트크기를 %로 지정하는 경우 모든 사이즈를 고려하지 않기 때문에 예상하는 바와 같은 버튼이 아닐 수 있다.
- 때문에 모든 효과의 사이즈를 고려해 em를 사용해야 한다.
```CSS
padding: .3em .8em;
border: 1px solid #446d88;
background: #58a linear-gradient(#77a0bb, #58a);
border-radius: .2em;
box-shadow: 0 0.05em .25em gray;
color: white;
text-shadow: 0 0.05em 0.05em #335166;
font-size: 125%
line-height: 30px;
```
![image](https://user-images.githubusercontent.com/51357635/127743192-4cb8a7ec-e577-4d69-a74e-920508def9e5.png)
  - px 단위의 값을 부모 컴포넌트의 px을 기준으로 나누어 em으로 수정한다면 크기가 변경되더라도 간격이 비례하는 것을 볼 수 있다.

---

### 2. 색상이 변경되는 경우
- 4개의 색상 코드
```CSS
border-color, background, box-shadow, text-shadow
```
- 색상의 경우 밝기와 어둡기를 다시 계산해 색상을 얼마나 밝게 또는 어둡게 해야 할지 계산하고 각각의 색상을 얼마나 밝게, 어둡게 해야 한다는 것은 매우 번거로운 일이다.
- 이는 밝기/어둡기 변수에 반투명 흰색과 검은색을 사용해 각 주 색상에 덮어씌우면 번거로움을 줄일 수 있다.
```CSS
color:white;
text-shadow: 0 -.05em .05em rgba(0, 0, 0, 5);
font-size:125%;
line-height: 1.5;

.cancel{
    background-color:#c00;
}

.ok{
    background-color:#6b0;
}
```
![image](https://user-images.githubusercontent.com/51357635/127743536-198993c4-1df7-4423-b3a6-47141c171ee9.png)

---

### 3. 유지보수성 vs 간결성
- 때론 유지보수와 간결성이 교섭되지 않을 수 있다. 예로, 10px 두깨의 경계선을 왼쪽 한 변을 제외한 경우를 보자.
```CSS
border-width: 10px 10px 10px 0;
```
- 선언문은 하나인데 경계선 두 개를 변경할 경우 3개 모두 수정해야 한다. 이때 선언문 2개로 만드는 것이 수정하기에 적합할 것이다.

```CSS
border-width: 10px;
border-left-width: 0;
```

---
### 4. 현재 색상

- SVG에서 차용한 currentColor와 같이 새로운 색상 키워드가 등장하였는데, 이 키워드는 고정된 색상 값이 아닌, CSS 최초의 변수이다.
- 다음과 같은 경우 이 키워드를 사용할 수 있다. 모든 수평 구분자가 자동으로 텍스트와 같은 색상이 되도록 한다고 가정하자면 이럴 때 currentColor를 사용하기 좋다.
- currentColor는 부모 컨테이너의 값을 기준으로 한다.
```CSS
hr {
    height: .5em;
    background: currentColor;
}
```
![image](https://user-images.githubusercontent.com/51357635/127743908-73ac8ef7-d0be-4b05-95a9-4b647623c383.png)

- currentColor는 아래와 같은 색상 키워드의 기본 값이기도 하다.
```CSS
border-color, text-shadow, box-shadow, outline-color
```

---
### 5. 상속
- inherit 키워드는 잘 알고 있지만, 평소 CSS를 작성하다 보면 사용할 일이 없을 것이다.
- inherit 키워드는 어떤 CSS 속성에도 사용할 수 있으며 계산된 부모 컴포넌트의 요소 값을 따른다.
- 예로 페이지의 다른 부분과 같은 글꼴을 사용하고 싶지 않은 경우 inherit을 사용하면 된다.
```CSS
input, select, button {
    font: inherit; 
}
```
- 다른 텍스트와 같은 색상의 하이퍼링크를 만들 때로 inherit을 사용한다.

```CSS
a {
    color: inherit; 
}
```
- inherit 키워드는 배경을 지정할 때도 자주 사용된다. 
- 예로 포인터가 자동으로 배경과 경계선을 상속하는 말풍선을 만들 수 있다.
```CSS
.callout {
    position: relative;
}

.callout::before {
    content: "";
    position: absolute;
    top: -4.em;
    left: 1em;
    padding: .35em;
    background: inherit;
    border: inherit;
    border-right: 0;
    border-bottom: 0;
    transform: rotate(45deg);
}
```
![image](https://user-images.githubusercontent.com/51357635/127744359-19cb987b-b542-4d60-9a44-3f9a4638981e.png)

---
<br><br>

# 숫자가 아닌 자신의 눈을 믿어라!
- 우리의 눈은 물체가 한가운데 가로로 위치한 경우 기하학적으로 가운데보다 약간 위에 있을 때 부정확하게 보일 때가 있다.
- 아래 그림을 보면 동그라미 쪽이 작아 보이지만 왼쪽 사각형과 경계선은 같다. 때문에 글꼴 디자인에서는 O와 같이 둥근 문자는 네모진 문자보다 약간 크게 디자인한다.

![image](https://user-images.githubusercontent.com/51357635/127744686-420237c8-7cfc-448e-a196-406161d4eab5.png)

- 만일 같은 양의 패딩을 상자의 각 측면에 지정할 때 다음 아래 그림과 같이 균일하게 보이지 않을 수 있다.
- 활자 형태가 상하보다 좌우로 이어지기 때문에 우리의 눈이여분의 공간을 여백의 패딩으로 받아들이기 때문이다.
- 그러므로 두번째 그림처럼 위 아래 패딩을 적게 두어야 균일하게 받아들일 수 있다.

![image](https://user-images.githubusercontent.com/51357635/127744826-45b9e1b9-92d4-4739-81de-fdf37660d236.png)

---
<br><br>

# 반응형 디자인
