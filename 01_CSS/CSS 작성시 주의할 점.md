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
<br>

# 숫자가 아닌 시각을 믿자
- 우리의 눈은 물체가 한가운데 가로로 위치한 경우 기하학적으로 가운데보다 약간 위에 있을 때 부정확하게 보일 때가 있다.
- 아래 그림을 보면 동그라미 쪽이 작아 보이지만 왼쪽 사각형과 경계선은 같다. 때문에 글꼴 디자인에서는 O와 같이 둥근 문자는 네모진 문자보다 약간 크게 디자인한다.

![image](https://user-images.githubusercontent.com/51357635/127744686-420237c8-7cfc-448e-a196-406161d4eab5.png)

- 만일 같은 양의 패딩을 상자의 각 측면에 지정할 때 다음 아래 그림과 같이 균일하게 보이지 않을 수 있다.
- 활자 형태가 상하보다 좌우로 이어지기 때문에 우리의 눈이여분의 공간을 여백의 패딩으로 받아들이기 때문이다.
- 그러므로 두번째 그림처럼 위 아래 패딩을 적게 두어야 균일하게 받아들일 수 있다.

![image](https://user-images.githubusercontent.com/51357635/127744826-45b9e1b9-92d4-4739-81de-fdf37660d236.png)

---
<br>

# 반응형 웹
- 반응형 웹은 다양한 해상도에 대응하기 위해 많은 미디어 쿼리를 사용한다.
- 미래의 수정 사항은 어떤 해상도에 적용할 것인지, 또 잠재적으로는 어떤 수정이 있을지 파악한 후 반응형 작업을 해야한다.
- 아래 불필요한 미디어 쿼리 방법을 살펴보고 작업시 참고하도록 하자.

```TEXT
[1] 고정된 폭(px)보다 백분율(%)을 사용한다. 이것이 어려운 경우 Viewport단위(vm, wh, wmin, wmax)를 사용해
Viewport를 가로 또는 세로로 분할하여 보이도록 한다.

[2] 큰 해상도에서 고정된 폭을 지정할 때 width가 아닌, max-width를 사용 하여 작은 해상도에서도 미디어 쿼리 없이 적용되도록 한다.

[3] <img>, <object>, <video>, <iframe>과 같은 대체 요소를 위해 max-width를 100%로 설정하는 것을 잊지 말자.

[4] 배경 이미지가 전체 공간을 커버해야 할 경우 background-size: cover 를 사용하면 내용의 크기에 상관없이 유지할 수 있다. 그러나 모바일 해상도 기준으로 설계 시 bandwidth의 한계가 있으므로 큰 크기의 이미지는 현명하지 않다.

[5] 이미지(또는 다른 요소) 가로와 세로 그리드가 있을 때 세로 번호가 뷰포트의 너비로 생각하면 된다.
Flexbox, inline-block과 같은 줄 인라인 요소가 이에 도움이 될 수 있다.

[6] 다단의 텍스트 사용시 column-count 대신 column-width를 지정해 작은 해상도에서는 한 단만 나타나게 한다.
```

---
<br>

# 단축 속성을 현명하게 사용하자
- 다음 키워드는 같은 키워드가 아니다.
```CSS
background: dodgerblue;
```
```CSS
background-color: dodgerblue;
```
- background는 단축 속성으로, 배경이 dodgerblue인 반면
- background-color는 일반 속성으로 background-image와 같이 그라디언트, 고양이 그림 등 어떤것으로도 사용할 수 있다. 
- 이러한 일반 속성은 단축 속성과 조합해 사용할 경우 매우 유용하다.
```CSS
background: url(tr.png) no-repeat top right / 2em 2em,
            url(br.png) no-repeat bottom right / 2em 2em,
            url(bl.png) no-repeat bottom left / 2em 2em;
```
- 위 키워드에서는 bacgkround-size, background-repeat 값이 3번 반복된다.
- 아래와 같이 일반속성으로 반복되는 값을 축약할 수 있다.
```CSS
background: url(tr.png) top right,
            url(br.png) bottom right,
            url(bl.png) bottom left;
background-size: 2em 2em;
background-repeat: no-repeat;
```
---
<br>

# 전처리기는 필수인가?
- 최근 LESS(lesscss.org), Sass(sass-lang.com), Stylus(learnboost.github.io/stylus)와 같은 전처리는 변수, 믹스인(mixin)함수, 중첩, 색상 변환 등 CSS 작업에 편의를 제공한다.
- 적절히 사용한다면 CSS 자체만 사용하는 것보다 대규모 프로젝트에서 코드를 좀 더 유연하게 관리할 수 있다. 허나 기존의 전신인 CSS보다 한계에 부딪히기도 할 것이다.
- 위와 같은 전처리는 아래와 같은 문제점이 있다.
```TEXT
[1] CSS의 파일 크기와 복잡성에서 길을 잃을 수 있다. 축약되고 짧은 코드가 거대한 CSS로 컴파일된다.

[2] 디버깅이 더 힘들어진다. IDE에서 CSS는 더는 처음 작성한 CSS가 아니게 되며 이러한 문제를 줄이고자 나온 소스맵(SourceMaps)에서는 생성된 CSS코드와 본래의 CSS코드를 매칭시켜 알게 해줌으로 디버깅을 지원한다.

[3] 전처리로 개발 프로세스에 지연시간이 추가된다.

[4] 모든 것을 추상화해 유지보수하기 힘들 수 있다. 협업자가 이러한 전처리 언어를 알지 못하면 협업에 제한이 생기고 사용법을 가르치는 시간도 필요할 수 있다.

[5] 작은 것까지 모두 추상화 한다면 빈틈이 생긴다. 자체 버그가 있을 수도 있으며 원하는 결과가 나오지 않을 수 있다.

[6] 무엇보다 CSS는 스크립트를 통해서도 변경될 수 있다는 점을 간과해서는 안 된다.
```
- 전처리기의 전신이 CSS라는 점을 간과하지말자.
- 현재 CSS는 계속 진화하고 있다.
```TEXT
[1] CSS Custom Properties for Cascading Variables(w3.org/TR/css-variables-1)안에 변수와 비슷한 사용자 정의 키워드로 여러 전처리에서 지원하는 기능들을 대신할 수 있다.

[2] CSS Value & Units Level 3에서 등장하는 calc()는 계산을 수행하는 강력한 기능이며 현재도 잘 지원되고 있다.

[3] CSS Color Level 4(dev.w3.org/csswg/css-color)의 color()는 지원될 색상 변환에 관한 기능을 볼 수 있다.
```
- 물론 전처리에서 유지보수가 중요할 경우 전처리기를 사용해도 좋다. 선택할 것.
- 이 책에서는 CSS, SCSS를 사용할 것이다.