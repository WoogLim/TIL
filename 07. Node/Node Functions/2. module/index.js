const {odd, even} = require('./var');
const checkNumber = require('./func'); // checkOddOrEven 함수를 저장
// 값을 불러올때 변수명을 다르게 지정할 수 있다.

function checkStringOddOrEven(str) {
    if(str.length % 2){ // 문자열 길이가 홀수인 경우
        return odd;
    }
    return even;
}

console.log(checkNumber(10));
console.log(checkStringOddOrEven('hello'));
