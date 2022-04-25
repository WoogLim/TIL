class Human {
    gender = 'male'; // ES7 생성자는 생략되지만 수행됨

    printGender = () => {
        console.log(this.gender);
    }
}

class Person extends Human {// 부모 부터 생성한 후 메모리에 적재한다.
    // 부모도 자동으로 적재해주는듯?
    name = 'Max'
    gender = 'female';

    printMyName = () => {
        console.log(this.name);
    }
}

const person = new Person();
person.printMyName();
person.printGender();