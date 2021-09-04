// 09-26
var elem = null;

//elem이 null 또는 undefined이면 undefined를 반환하고, 그렇지 않으면 우항의 프로퍼티 참조를 이어간다.
var value = elem?.value;
console.log(value); // undefiend

var elem = null;

// elem이 Falsy 값이면 elem으로 평가되고, elem이 Truthy 값이면 elem.value로 평가된다./
var value = elem && elem.value;
console.log(value); // null

var str = "";

// 문자열의 길이(length)를 참조한다.
var length = str && str.length;

// 문자열의
