// Before ES5

var sayNode = function () {
  console.log("Node");
};
var es = "ES";
var oldObject = {
  sayJS: function () {
    console.log("JS");
  },
  sayNode: sayNode,
};
oldObject[es + 6] = "Fantastic";
oldObject.sayNode();
oldObject.sayJS();
console.log(oldObject.ES6);

// ES5
const newObject = {
  sayJS() {
    console.log("JS");
  },
  sayNode,
  [es + 6]: "Fantastic",
};

newObject.sayNode();
newObject.sayJS();
console.log(newObject.ES6);

/**
 * 더는 클론(:)과 function()을 붙이지 않아도 되며
 * 속성명 변수명이 동일(sayNode)한 경우 한 번만 써도 호출된다.
 */
