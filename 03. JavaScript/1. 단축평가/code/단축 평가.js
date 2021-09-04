// 논리 연산자를 사용한 단축 평가
// 논리곱은 좌항에서 우항으로 진행
// "cat" && "dog" => "dog"
// cat : true dog : ture 이므로 dog 출력
// "cat" || "dog" => "cat"
// cat : true dog : true 이므로 하나만 true여도 되므로 cat 출력
// 논리 연산의 결과를 결정하는 피연산자를 타입 변환하지 않고 그대로 출력. 이를 단축 평가라고 한다.

// true || anything => true
// false || anything => anything
// true && anything => anything
// false || anything => false

// 단축 평가를 이용한 if문의 대체

// var done = true;
// var message = "";

// 주어진 조건이 true일 때
// if (done) message = "완료";

// if문은 단축 평가로 대체 가능하다.
// done이 false라면 message에 미완료를 할당
// message = done && "완료";
// console.log(message);

// var done = false;
// var message = "";

// // 주어진 조건이 false 일 때
// if (!done) message = "미완료";

// // if문은 단축 평가로 대체 가능하다.
// // done false라면 message에 '미완료;를 할당
// message = done || "미완료";
// console.log(message); // 미완료
