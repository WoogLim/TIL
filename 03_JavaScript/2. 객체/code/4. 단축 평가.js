function add(a, b, c) {
  a = a || 0;
  b = b || 0;
  c = c || 0;
  return a + b + c;
}

console.log(add(1, 2, 3));
console.log(add(1, 2));
console.log(add(1));
console.log();

// ES6 매개변수 기본값
function add(a = 0, b = 0, c = 0) {
  return a + b + c;
}

console.log(add(1, 2, 3));
console.log(add(1, 2));
console.log(add(1));
