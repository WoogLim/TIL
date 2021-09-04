### null 병합 연산자

---

- ES11(ECMAScript2020)에서 도입된 null 병합연산자 **??** 는 좌항의 피연산자가 **false** 로 평가되는 Falsy 값(**false, undefined, null, 0, -0, NaN, ''**)이라도 null 또는 undefined가 아니면 우항의 프로퍼티 참조를 이어간다.

```js
// 좌항의 피연산자가 null 또는 undefined이면 우항의 피연산자를 반환하고,
// 그렇이 않으면 좌항의 피연산자를 반환한다.

var foo = null ?? "default String";
console.log(foo); // default String
```

- null 병합 연산자 **??** 는 변수에 기본값을 설정할 때 유용하다. null 병합 연산자 이전에 논리합(**||**)연산자를 이용해 **false**로 평가되는 Falsy값(**false, undefined, null, 0, -0, NaN, ''**)이면 우항의 피연산자를 반환한다. 만일 Falsy값이 0 이나 ''도 기본값으로 유효하다면 예기치 않은 동작이 발생할 수 있다.

```js
// Falsy 값인 0이나 ''도 기본값으로서 유효하다면 예기치 않은 동작이 발생할 수 있다.
var foo = "" || "default string";
console.log(foo);
```

- null 병합 연산자 **??** 는 좌항의 피연산자가 false로 평가되는 Falsy(**false, undefined, null, 0, -0, NaN, ''**)이라도 null 또는 undefined가 아니면 좌항의 피연산자를 그대로 변환한다.

```js
// 좌항의 피연산자가 Falsy 값이라도 null 또는 undefined가 아니면 좌항의 피연산자를 반환한다.
var foo = "" ?? "default string";
console.log(foo); // ""
```
