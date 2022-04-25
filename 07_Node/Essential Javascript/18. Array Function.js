const numbers = [1, 2, 3];

const doubleNumArray = numbers.map((num) => {
    // number 배열의 인덱스를 하나씩 선회하고 배열로 저장한다.
    return num * 2;
});

console.log(numbers);
console.log(doubleNumArray);