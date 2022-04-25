class Human {
    constructor(){
        this.gender = 'male';
    }

    printGender(){
        console.log(this.gender);
    }
}

class Person extends Human {
    constructor(){
        super() // 부모 부터 생성한 후 메모리에 적재한다.
        this.name = 'Max'
        this.gender = 'female';
    }

    printMyName() {
        console.log(this.name);
    }
}

const person = new Person();
person.printMyName();
person.printGender();