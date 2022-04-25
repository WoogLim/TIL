// utility.js
const person = {
    name: 'Max',
}

// default 인 경우 import시 person 객체를 기본적으로 찾아옴
export default person

export const clean = () => {}

export const baseData = 10;

// import 시
// import person from './person.js'
// import pre from './person.js'

// default 로 export하지 않은채 import 한 경우

// import 시 상수로 정의된 객체는 중괄호에 명시해주어야한다.
// import {baseData} from ''./utility.js'
// import {clean as cle} from './utility.js'
// import * as bundled from './utility.js'