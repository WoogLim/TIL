// ... 으로 이루어진 연산자

// 1. Spread 연산자 [배열]
const numbers = [1, 2, 3];
const newNumbers = [...numbers, 4];

console.log(newNumbers); // > (4) [1, 2, 3, 4]

// 1. Spread 연산자 [객체]
const person = {
    name: 'Max'
};

const newPerson = {
    ...person,
    age:28
}

console.log(newPerson); // > {name: 'Max', age: 28}

// 2. Rest 연산자

// 매개변수를 배열로 변환 후 이용
const filter = (...args) => {
    // 리턴 여부는 선택사항
    // el 이 number타입이고 1인지
    return args.filter(el => el === 1);
}

console.log(filter(1, 2, 3));