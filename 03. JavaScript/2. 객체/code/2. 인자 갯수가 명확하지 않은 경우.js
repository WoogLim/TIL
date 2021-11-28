// arguments 객체는 함수를 정의할 때 매개변수 개수를 확정할 수 없으면 인자 함수를 구현할때 유용하다.
function add(x, y) {
  console.log(arguments);

  return x + y;
}

add(2, 5, 10);
