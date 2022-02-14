/** 타이머 기능을 제공하는 함수인 setTimeout, setInterval, setImmediate는 노드에서 window 대신
 *  global 객체 안에 들어있다.
 * 
 * setTimeout(콜백 함수, 밀리초): 주어진 밀리초(1000분의 1초) 이후 콜백 함수를 실행
 * setInterval(콜백 함수, 밀리초): 주어진 밀리초마다 콜백 함수를 반복 실행
 * setImmediate(콜백 함수): 콜백 함수를 즉시 실행한다.
 * 
 * 이 타이머 함수들은 모두 아이디를 반환한다.
 * 
 * clearTimeout(아이디): setTimeout을 취소합니다.
 * clearInterval(아이디): setInterval을 취소합니다.
 * clearImmediate(아이디): setImmediate를 취소합니다.
 */

const timeout = setTimeout(() => {
    console.log('1.5초 후 실행');
},1500);

const interval = setInterval(() => {
    console.log('1초 마다 실행');
},1000);

const timeout2 = setTimeout(() => {
    console.log('실행되지 않습니다.');
},3000);

setTimeout(() => {
    clearTimeout(timeout2);
    clearInterval(interval);   
},2500);

const immediate = setImmediate(() => {
    console.log('즉시 실행');
});

const immediate2 = setImmediate(() => {
    console.log('실행되지 않습니다.');
})

clearImmediate(immediate2);

// setTimeout 사용은 권장하지 않는다. setImmediate를 우선 네트워크 인터페이스 사용용도로 먼저 접속하기 위해 사용하는 경우가 있지만
// 반드시 먼저 실행되리란 보장이 없다.