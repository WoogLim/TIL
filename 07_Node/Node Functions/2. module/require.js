console.log('require가 가장 위에 오지 않아도 됩니다.');

module.exports = '저를 찾아보세요.';

require('./var');

console.log('require.cache 입니다.');
console.log(require.cache);
console.log('require.main 입니다.');
console.log(require.main === module); // 현재 파일이 첫 모듈인지 확인 true
console.log(require.main.filename);

// require가 반드시 파일 최상단에 위치할 필요는 없다. module.exports도 최하단에 위치할 필요가 없다.
// require.cache 객체에 require.js나 var.js 같은 파일 이름이 속성명으로 존재, 각 파일의 모듈 객체가 들어 있다. 한 번 require한 파일을
// require.cache 객체에 저장되므로 다음 번 require할 때 새로 불러오지 않고 require.cache에 있는 것이 재사용된다.
// require.main 은 노드 실행 시 첫 모듈을 가리킨다.